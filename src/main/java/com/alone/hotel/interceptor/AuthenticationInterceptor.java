package com.alone.hotel.interceptor;

import com.alibaba.fastjson.JSON;
import com.alone.hotel.annotation.PassToken;
import com.alone.hotel.annotation.UserLoginToken;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.service.CustomerAccountService;
import com.alone.hotel.service.impl.RedisService;
import com.alone.hotel.utils.TokenUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.interceptor
 * @Author: Alone
 * @CreateTime: 2020-04-12 11:43
 * @Description: 登录拦截器
 */

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private CustomerAccountService customerAccountService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //获取token
        String token = request.getHeader("token");
        //如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释,有则跳过验证
        if(method.isAnnotationPresent(PassToken.class)){
            return true;
        }
        //检查有没有需要用户权限的注解
        if(method.isAnnotationPresent(UserLoginToken.class)){
            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
            if(userLoginToken.required()){
                //执行认证
                if(token == null){
                    throw new RuntimeException(ResultEnum.TOKEN_EMPTY.getStateInfo());
                }
                //获取token中的用户名
                String accountName;
                try{
                    accountName = JWT.decode(token).getAudience().get(0);
                } catch (JWTDecodeException j){
                    throw new RuntimeException("401");
                }
                //找到用户名对应的账户
                CustomerAccount customerAccount = customerAccountService.queryCustomerAccountByName(accountName);
                if(customerAccount == null){
                    throw new RuntimeException("用户不存在,请重新登录");
                }
                //验证token
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(customerAccount.getAccountPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e){
                    throw new RuntimeException("401");
                }
                //修改token过期时间
                //获取当前系统时间
                Long currentTime = System.currentTimeMillis();
                Long tokenTime = Long.valueOf(redisTemplate.opsForValue().get(token + accountName).toString());
                if(currentTime - tokenTime > TokenUtil.TOKEN_RESET_TIME){
                    Long newTime = System.currentTimeMillis();
                    try {
                        redisTemplate.opsForValue().set(token + accountName, newTime.toString(), TokenUtil.TOKEN_EXPRIE_TIME, TimeUnit.MINUTES);
                        redisTemplate.opsForValue().set(token, JSON.toJSONString(customerAccount));
                    } catch (Exception e){
                        throw new RuntimeException("缓存写入失败");
                    }
                }
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
