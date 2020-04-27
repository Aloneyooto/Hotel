package com.alone.hotel.controller.customer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alone.hotel.annotation.PassToken;
import com.alone.hotel.annotation.UserLoginToken;
import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.service.CustomerAccountService;
import com.alone.hotel.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.customer
 * @Author: Alone
 * @CreateTime: 2020-03-24 20:26
 * @Description:
 */
@RestController
@RequestMapping("/customer")
public class CustomerAccountManagement {
    @Autowired
    private CustomerAccountService customerAccountService;
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 登录(已验证)
     * @param customerAccount
     * @return
     */
    @PostMapping(value = "/login")
    @PassToken
    public CustomerAccountExecution login(@RequestBody CustomerAccount customerAccount){
        if(customerAccount.getAccountName() != null && customerAccount.getAccountPassword() != null){
            //生成token
            String token = TokenUtil.getToken(customerAccount);
            //缓存token
            //获取当前时间
            Long currentTime = System.currentTimeMillis();
            //存入redis
            try {
                //保存token+accountName-Time
                redisTemplate.opsForValue().set(token + customerAccount.getAccountName(), currentTime.toString(), TokenUtil.TOKEN_EXPRIE_TIME, TimeUnit.MINUTES);
                //保存token-customerAccount
                redisTemplate.opsForValue().set(token, JSON.toJSONString(customerAccount));
            } catch (Exception e){
                return new CustomerAccountExecution(ResultEnum.CACHE_WRITE_ERROR);
            }
            return new CustomerAccountExecution(ResultEnum.SUCCESS, token);
        } else {
            return new CustomerAccountExecution(ResultEnum.ACCOUNT_EMPTY);
        }
    }

    /**
     * 注册
     * @param customerAccount
     * @return
     */
    @PostMapping(value = "/register")
    @PassToken
    public CustomerAccountExecution register(@RequestBody CustomerAccount customerAccount){
        if(customerAccount.getAccountName() != null && customerAccount.getAccountPassword() != null){
            CustomerAccountExecution customerAccountExecution = customerAccountService.addCustomerAccount(customerAccount);
            return customerAccountExecution;
        } else {
            return new CustomerAccountExecution(ResultEnum.ACCOUNT_EMPTY);
        }
    }

    /**
     * 修改密码
     * @param jsonObject
     * @return
     */
    @UserLoginToken
    @PostMapping(value = "/changepwd")
    public CustomerAccountExecution changePwd(HttpServletRequest request, @RequestBody JSONObject jsonObject){
        //String accountName, String oldPsw, String newPsw, String newPsw2
        String oldPsw = jsonObject.get("oldPsw").toString();
        String newPsw = jsonObject.get("newPsw").toString();
        String newPsw2 = jsonObject.get("newPsw2").toString();


        if(oldPsw != null && newPsw != null && newPsw2 != null){
            if(newPsw.equals(newPsw2)){
                //查找账号的原来信息
                //获取token
                String token = request.getHeader("token");
                //获取customerAccount对象
                String jsonStr = (String)redisTemplate.opsForValue().get(token);
                CustomerAccount customerAccount = JSONArray.parseObject(jsonStr, CustomerAccount.class);
                if(customerAccount != null && customerAccount.getAccountName() != null){
                    //判断旧密码是否正确
                    if(oldPsw.equals(customerAccount.getAccountPassword())){
                        //将密码更换成新的
                        customerAccount.setAccountPassword(newPsw);
                        CustomerAccountExecution customerAccountExecution = customerAccountService.updateCustomerAccount(customerAccount, null);
                        return customerAccountExecution;
                    } else {
                        return new CustomerAccountExecution(ResultEnum.OLD_PASSWORD_ERROR);
                    }
                } else {
                    //账户信息为空
                    return new CustomerAccountExecution(ResultEnum.ACCOUNT_EMPTY);
                }
            } else {
                //两次输入的密码不一致
                return new CustomerAccountExecution(ResultEnum.NEW_PASSWORD_ERROR);
            }
        } else {
            return new CustomerAccountExecution(ResultEnum.ACCOUNT_EMPTY);
        }
    }

    /**
     * 获取账号信息(已验证)
     * @param request
     * @return
     */
    @UserLoginToken
    @GetMapping("/queryaccountmessage")
    private CustomerAccountExecution queryAccountMessage(HttpServletRequest request){
        try{
            //获取token
            String token = request.getHeader("token");
            if(token != null){
                //获取customerAccount对象
                String jsonStr = (String)redisTemplate.opsForValue().get(token);
                CustomerAccount customerAccount = JSONArray.parseObject(jsonStr, CustomerAccount.class);
                return new CustomerAccountExecution(ResultEnum.SUCCESS, customerAccount);
            } else {
                return new CustomerAccountExecution(ResultEnum.TOKEN_EMPTY);
            }
        } catch (Exception e){
            return new CustomerAccountExecution(ResultEnum.INNER_ERROR);
        }
    }

    /**
     * 修改头像
     * @return
     */
    @UserLoginToken
    @PostMapping("/updateheadimg")
    private CustomerAccountExecution updateHeadImg(HttpServletRequest request){
        MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
        //获取图片
        MultipartFile headImg = params.getFile("headImg");
        //获取token
        String token = params.getHeader("token");
        if(token != null){
            //获取customerAccount对象
            String jsonStr = (String)redisTemplate.opsForValue().get(token);
            CustomerAccount customerAccount = JSONArray.parseObject(jsonStr, CustomerAccount.class);
            if(customerAccount != null){
                CustomerAccountExecution customerAccountExecution = customerAccountService.updateCustomerAccount(customerAccount, headImg);
                //更新缓存中数据
                redisTemplate.opsForValue().set(token, JSON.toJSONString(customerAccount));
                return customerAccountExecution;
            } else {
                return new CustomerAccountExecution(ResultEnum.ACCOUNT_EMPTY);
            }
        } else {
            return new CustomerAccountExecution(ResultEnum.TOKEN_EMPTY);
        }
    }

    /**
     * 修改押金
     * @param request
     * @return
     */
    @UserLoginToken
    @PostMapping("/updatedeposit")
    private CustomerAccountExecution updateDeposit(HttpServletRequest request){
        //获取token
        String token = request.getHeader("token");
        if(token != null){
            //获取customerAccount对象
            String jsonStr = (String)redisTemplate.opsForValue().get(token);
            CustomerAccount customerAccount = JSONArray.parseObject(jsonStr, CustomerAccount.class);
            if(customerAccount != null){
                CustomerAccountExecution customerAccountExecution = customerAccountService.updateCustomerAccount(customerAccount, null);
                //更新缓存中数据
                redisTemplate.opsForValue().set(token, JSON.toJSONString(customerAccount));
                return customerAccountExecution;
            } else {
                return new CustomerAccountExecution(ResultEnum.ACCOUNT_EMPTY);
            }
        } else {
            return new CustomerAccountExecution(ResultEnum.TOKEN_EMPTY);
        }
    }
}
