package com.alone.hotel.dto;

import com.alone.hotel.entity.EmployeeAccount;
import com.alone.hotel.enums.EmployeeAccountStateEnum;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-18 15:08
 * @Description:
 */
public class EmployeeAccountExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的清洁员
    private EmployeeAccount employeeAccount;

    public EmployeeAccountExecution() {
    }

    public EmployeeAccountExecution(EmployeeAccountStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public EmployeeAccountExecution(EmployeeAccountStateEnum stateEnum, EmployeeAccount employeeAccount){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.employeeAccount = employeeAccount;
    }

}
