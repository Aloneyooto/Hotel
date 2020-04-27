package com.alone.hotel.dto;

import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.enums.ResultEnum;
import lombok.Data;

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
    //操作账户列表
    private List<CustomerAccount> customerAccountList;

    public CustomerAccountExecution() {
    }

    public CustomerAccountExecution(ResultEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public CustomerAccountExecution(ResultEnum stateEnum, String token){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.token = token;
    }

    public CustomerAccountExecution(ResultEnum stateEnum, CustomerAccount customerAccount){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.customerAccount = customerAccount;
    }

    public CustomerAccountExecution(ResultEnum stateEnum, List<CustomerAccount> customerAccountList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.customerAccountList = customerAccountList;
    }
}
