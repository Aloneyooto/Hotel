package com.alone.hotel.dao;

import com.alone.hotel.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dao
 * @Author: Alone
 * @CreateTime: 2020-03-13 20:20
 * @Description:
 */
public interface EmployeeDao {
    /**
     * 插入员工
     * @param employee
     * @return
     */
    int addEmployee(Employee employee);

    /**
     * 查询对应工号的员工
     * @param employeeId
     * @return
     */
    Employee queryEmployeeById(String employeeId);

    /**
     * 分页查询员工
     * @param employeeCondition
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Employee> queryEmployeeList(@Param("employeeCondition")Employee employeeCondition, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);

    /**
     * 按条件查询员工记录总数
     * @param employeeCondition
     * @return
     */
    int queryEmployeeCount(@Param("employeeCondition")Employee employeeCondition);

    /**
     * 查询所有员工的脸部图片
     * @return
     */
    List<Employee> queryEmployeeFaceImg();

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    int updateEmployee(Employee employee);

    /**
     * 删除员工信息
     * @param employeeId
     * @return
     */
    int deleteEmployee(String employeeId);
}
