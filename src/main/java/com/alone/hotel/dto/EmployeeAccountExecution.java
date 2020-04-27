package com.alone.hotel.dto;

import com.alone.hotel.entity.EmployeeAccount;
import com.alone.hotel.enums.ResultEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-18 15:08
 * @Description:
 */
@Data
public class EmployeeAccountExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //操作的账号
    private EmployeeAccount employeeAccount;
    //操作的账号列表
    private List<EmployeeAccount> employeeAccountList;

    public EmployeeAccountExecution() {
    }

    public EmployeeAccountExecution(ResultEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public EmployeeAccountExecution(ResultEnum stateEnum, EmployeeAccount employeeAccount){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.employeeAccount = employeeAccount;
    }

    public EmployeeAccountExecution(ResultEnum stateEnum, List<EmployeeAccount> employeeAccountList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.employeeAccountList = employeeAccountList;
    }
}
