package com.alone.hotel.controller.personnel;

import com.alone.hotel.dto.CleanerExecution;
import com.alone.hotel.dto.EmployeeAccountExecution;
import com.alone.hotel.dto.EmployeeExecution;
import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.EmployeeAccount;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.service.CleanerService;
import com.alone.hotel.service.EmployeeAccountService;
import com.alone.hotel.service.EmployeeService;
import com.alone.hotel.utils.FaceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.personnel
 * @Author: Alone
 * @CreateTime: 2020-03-16 10:07
 * @Description:
 */

@RestController
@RequestMapping("/personnel")
public class EmployeeManagement {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CleanerService cleanerService;
    @Autowired
    private EmployeeAccountService employeeAccountService;

    /**
     * 添加员工
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/addemployee")
    private EmployeeExecution addEmployee(HttpServletRequest request, @RequestBody Employee employee){
        //@RequestParam("employeeStr")String employeeStr,@RequestParam("cardImg")MultipartFile cardImg,@RequestParam("faceImg")MultipartFile faceImg
        MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
        MultipartFile cardImg = params.getFile("cardImg");
        MultipartFile faceImg = params.getFile("faceImg");
        //空值判断
        if(employee != null && cardImg != null && faceImg != null){
            try{
                EmployeeExecution employeeExecution = employeeService.addEmployee(employee, cardImg, faceImg);
                if(employeeExecution.getState() == ResultEnum.SUCCESS.getState()){
                    try{
                        //自动生成账号
                        EmployeeAccount employeeAccount = new EmployeeAccount();
                        //用户名为工号
                        employeeAccount.setAccountName(employeeExecution.getEmployee().getEmployeeId());
                        //初始密码为123456
                        employeeAccount.setAccountPassword("123456");
                        EmployeeAccountExecution employeeAccountExecution = employeeAccountService.addEmployeeAccount(employeeAccount);
                        if(employeeAccountExecution.getState() != ResultEnum.SUCCESS.getState()){
                            return new EmployeeExecution(ResultEnum.CREATE_ACCOUNT_ERROR);
                        }
                    } catch (Exception e){
                        return new EmployeeExecution(ResultEnum.CREATE_ACCOUNT_ERROR);
                    }
                    return new EmployeeExecution(ResultEnum.SUCCESS, employee);
                } else {
                    return new EmployeeExecution(ResultEnum.INNER_ERROR);
                }
            } catch (IOException e) {
                return new EmployeeExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new EmployeeExecution(ResultEnum.EMPTY);
        }
    }

    /**
     * 根据员工ID查询员工信息
     * @param employeeId
     * @return
     */
    @GetMapping("/getemployeebyid")
    private EmployeeExecution getEmployeeById(@RequestParam String employeeId){
        EmployeeExecution employeeExecution = null;
        if(employeeId != null){
            Employee employee = employeeService.queryEmployeeById(employeeId);
            employeeExecution = new EmployeeExecution(ResultEnum.SUCCESS, employee);
        } else {
            employeeExecution = new EmployeeExecution(ResultEnum.EMPTY);
        }
        return employeeExecution;
    }

    /**
     * 根据检索条件查询员工列表
     * 姓名,性别,职位
     * @param employeeCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/getemployeelist")
    private EmployeeExecution getEmployeeList(@RequestParam(value = "employeeCondition") Employee employeeCondition, @RequestParam(value = "pageIndex") int pageIndex, @RequestParam(value = "pageSize") int pageSize){
        //保证页码合法
        if(pageIndex > 0 && pageSize > 0){
            EmployeeExecution employeeExecution = employeeService.queryEmployeeList(employeeCondition, pageIndex, pageSize);
            return employeeExecution;
        } else {
            return new EmployeeExecution(ResultEnum.PAGE_ERROR);
        }
    }

    /**
     * 修改员工信息
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/updateemployee")
    private EmployeeExecution updateEmployee(HttpServletRequest request, @RequestBody Employee employee){
//        @RequestParam("employeeStr")String employeeStr,
//        @RequestParam("cardImg")MultipartFile cardImg,
//        @RequestParam("faceImg")MultipartFile faceImg
        MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
        MultipartFile cardImg = params.getFile("cardImg");
        MultipartFile faceImg = params.getFile("faceImg");
        //空值判断
        if(employee != null){
            try{
                EmployeeExecution employeeExecution = employeeService.updateEmployee(employee, cardImg, faceImg);
                if(employeeExecution.getState() == ResultEnum.SUCCESS.getState()){
                    return new EmployeeExecution(ResultEnum.SUCCESS);
                } else {
                    return new EmployeeExecution(ResultEnum.INNER_ERROR);
                }
            } catch (IOException e) {
                return new EmployeeExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new EmployeeExecution(ResultEnum.EMPTY);
        }
    }

    /**
     * 删除员工信息
     * @param employeeId
     * @return
     */
    @PostMapping("/deleteEmployee")
    private EmployeeExecution deleteEmployee(@RequestBody String employeeId){
        EmployeeExecution employeeExecution = null;
        if(employeeId != null){
            try{
                Employee employee = employeeService.queryEmployeeById(employeeId);
                if(employee.getPosition().getPositionId() == 3){
                    try {
                        CleanerExecution cleanerExecution = cleanerService.deleteCleaner(employeeId);
                        if(cleanerExecution.getState() != ResultEnum.SUCCESS.getState()){
                            return new EmployeeExecution(ResultEnum.CLEANER_DELETE_ERROR);
                        }
                        employeeExecution = employeeService.deleteEmployee(employeeId);
                    } catch (Exception e){
                        employeeExecution = new EmployeeExecution(ResultEnum.INNER_ERROR);
                    }
                }
            } catch (Exception e){
                employeeExecution = new EmployeeExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            employeeExecution = new EmployeeExecution(ResultEnum.EMPTY);
        }
        return employeeExecution;
    }

    /**
     * 人脸识别
     * @param faceFile
     * @return
     */
    @GetMapping("/compareFaces")
    private EmployeeExecution compareFaces(@RequestParam MultipartFile faceFile){
        //初始化引擎
        FaceUtil.initEngine();
        //查询数据库内已有的人脸
        List<Employee> employeeList = employeeService.queryEmployeeFaceImg();
        //生成人脸特征信息
        FaceUtil.getEmployeeFeature(employeeList);
        try {
            File newFile = FaceUtil.multipartFileToFile(faceFile);
            String employeeId = FaceUtil.compareFaces(newFile);
            Employee employee = employeeService.queryEmployeeById(employeeId);
            return new EmployeeExecution(ResultEnum.SUCCESS, employee);
        } catch (IOException e) {
            return new EmployeeExecution(ResultEnum.INNER_ERROR);
        }
    }
}
