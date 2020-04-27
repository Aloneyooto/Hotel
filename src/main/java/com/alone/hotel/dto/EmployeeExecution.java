package com.alone.hotel.dto;

import com.alone.hotel.entity.Employee;
import com.alone.hotel.enums.ResultEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-14 22:16
 * @Description:
 */
@Data
public class EmployeeExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的房间
    private Employee employee;
    //操作的房间列表
    private List<Employee> employeeList;

    public EmployeeExecution() {
    }

    public EmployeeExecution(ResultEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public EmployeeExecution(ResultEnum stateEnum, Employee Employee){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.employee = Employee;
    }

    public EmployeeExecution(ResultEnum stateEnum, List<Employee> EmployeeList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.employeeList = EmployeeList;
    }
}
