package com.alone.hotel.dto;

import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.enums.CustomerAccountStateEnum;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-23 22:34
 * @Description:
 */
@Data
public class CustomerAccountExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //生成的token
    private String token;
    //账户信息
    private CustomerAccount customerAccount;

    public CustomerAccountExecution() {
    }

    public CustomerAccountExecution(CustomerAccountStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public CustomerAccountExecution(CustomerAccountStateEnum stateEnum, String token){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.token = token;
    }

    public CustomerAccountExecution(CustomerAccountStateEnum stateEnum, CustomerAccount customerAccount){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.customerAccount = customerAccount;
    }
}
