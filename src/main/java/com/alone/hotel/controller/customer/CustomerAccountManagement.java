package com.alone.hotel.controller.customer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alone.hotel.annotation.PassToken;
import com.alone.hotel.annotation.UserLoginToken;
import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.enums.CustomerAccountStateEnum;
import com.alone.hotel.service.CustomerAccountService;
import com.alone.hotel.service.impl.RedisService;
import com.alone.hotel.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
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
                //TODO
                return new CustomerAccountExecution(CustomerAccountStateEnum.INNER_ERROR);
            }
            return new CustomerAccountExecution(CustomerAccountStateEnum.SUCCESS, token);
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

    @PostMapping(value = "/register")
    @PassToken
    public CustomerAccountExecution register(@RequestBody CustomerAccount customerAccount){
        if(customerAccount.getAccountName() != null && customerAccount.getAccountPassword() != null){
            CustomerAccountExecution customerAccountExecution = customerAccountService.addCustomerAccount(customerAccount);
            return customerAccountExecution;
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

    @PostMapping(value = "/changepwd")
    @PassToken
    public CustomerAccountExecution changePwd(@RequestBody JSONObject jsonObject){
        //String accountName, String oldPsw, String newPsw, String newPsw2
        String accountName = jsonObject.get("accountName").toString();
        String oldPsw = jsonObject.get("oldPsw").toString();
        String newPsw = jsonObject.get("newPsw").toString();
        String newPsw2 = jsonObject.get("newPsw2").toString();
        if(accountName != null && oldPsw != null && newPsw != null && newPsw2 != null){
            if(newPsw.equals(newPsw2)){
                //查找账号的原来信息
                CustomerAccount customerAccount = customerAccountService.queryCustomerAccountByNameAndPsw(accountName, oldPsw);
                if(customerAccount != null){
                    //将密码更换成新的
                    customerAccount.setAccountPassword(newPsw);
                    CustomerAccountExecution customerAccountExecution = customerAccountService.updateCustomerAccount(customerAccount, null);
                    return customerAccountExecution;
                } else {
                    //旧密码错误
                    return new CustomerAccountExecution(CustomerAccountStateEnum.NAME_OR_PASSWORD_ERROR);
                }
            } else {
                //两次输入的密码不一致
                return new CustomerAccountExecution(CustomerAccountStateEnum.NEW_PASSWORD_ERROR);
            }
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

    /**
     * 获取账号信息
     * @param request
     * @return
     */
    @UserLoginToken
    @GetMapping("/queryaccountmessage")
    private CustomerAccountExecution queryAccountMessage(HttpServletRequest request){
        try{
            String token = request.getHeader("token");
            if(token != null){
                //获取customerAccount对象
                String jsonStr = (String)redisTemplate.opsForValue().get(token);
                CustomerAccount customerAccount = JSONArray.parseObject(jsonStr, CustomerAccount.class);
                return new CustomerAccountExecution(CustomerAccountStateEnum.SUCCESS, customerAccount);
            } else {
                return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
            }
        } catch (Exception e){
            return new CustomerAccountExecution(CustomerAccountStateEnum.INNER_ERROR);
        }
    }


}
