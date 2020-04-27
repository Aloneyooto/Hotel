package com.alone.hotel.utils;

import com.alone.hotel.entity.CustomerAccount;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.utils
 * @Author: Alone
 * @CreateTime: 2020-04-12 11:39
 * @Description:
 */
public class TokenUtil {

    //redis过期时间为1天
    public static final long TOKEN_EXPRIE_TIME = 24 * 60L;
    //redis重置时间(单位:毫秒)
    //过23小时59分重置一次
    public static final long TOKEN_RESET_TIME = 1000 * 60 * (60 * 23 + 59L);

    /**
     * 生成token
     * @param customerAccount
     * @return
     */
    public static String getToken(CustomerAccount customerAccount){
        String token = "";
        //把用户名存到token里,使用密码作为密钥
        token = JWT.create().withAudience(customerAccount.getAccountName())
                .sign(Algorithm.HMAC256(customerAccount.getAccountPassword()));
        return token;
    }
}
