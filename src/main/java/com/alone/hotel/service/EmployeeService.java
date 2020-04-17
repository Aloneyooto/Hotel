package com.alone.hotel.service;

import com.alone.hotel.dto.EmployeeExecution;
import com.alone.hotel.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service
 * @Author: Alone
 * @CreateTime: 2020-03-14 22:15
 * @Description:
 */
public interface EmployeeService {
    /**
     * 插入员工
     * @param employee
     * @param cardImg
     * @param faceImg
     * @return
     */
    EmployeeExecution addEmployee(Employee employee, MultipartFile cardImg, MultipartFile faceImg) throws IOException;

    /**
     * 查询对应工号的员工
     * @param employeeId
     * @return
     */
    Employee queryEmployeeById(String employeeId);

    /**
     * 查询员工列表
     * @param employeeCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    EmployeeExecution queryEmployeeList(Employee employeeCondition, int pageIndex, int pageSize);

    /**
     * 查询员工脸部图片
     * @return
     */
    List<Employee> queryEmployeeFaceImg();

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    EmployeeExecution updateEmployee(Employee employee, MultipartFile cardImg, MultipartFile faceImg) throws IOException;

    /**
     * 删除员工信息
     * @param employeeId
     * @return
     */
    EmployeeExecution deleteEmployee(String employeeId);
}
