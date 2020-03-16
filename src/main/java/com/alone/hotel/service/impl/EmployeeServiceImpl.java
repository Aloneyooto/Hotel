package com.alone.hotel.service.impl;

import com.alone.hotel.dao.EmployeeDao;
import com.alone.hotel.dto.EmployeeExecution;
import com.alone.hotel.entity.Employee;
import com.alone.hotel.entity.Position;
import com.alone.hotel.enums.EmployeeStateEnum;
import com.alone.hotel.exceptions.EmployeeException;
import com.alone.hotel.service.EmployeeService;
import com.alone.hotel.utils.ImageUtil;
import com.alone.hotel.utils.PageUtil;
import com.alone.hotel.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-14 22:23
 * @Description:
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    //工号日期格式
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    //部门号格式
    private static final DecimalFormat positionFormat = new DecimalFormat("000");
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    @Transactional
    public EmployeeExecution insertEmployee(Employee employee, MultipartFile cardImg, MultipartFile faceImg) throws IOException {
        //非空判断
        if(employee != null && employee.getEmployeeCardNumber() != null && employee.getEmployeeName() != null && employee.getPosition() != null){
            //设初始值
            employee.setEmployeeCreateTime(new Date());
            //生成工号
            String employeeId = generateEmployeeId(employee.getPosition());
            employee.setEmployeeId(employeeId);
            //文件流
            if(cardImg != null){
                String cardUrl = addImages(employee, 1, cardImg);
                employee.setEmployeeCardImg(cardUrl);
            } else {
                throw new EmployeeException(EmployeeStateEnum.CARD_IMAGE_EMPTY.getStateInfo());
            }
            if(faceImg != null){
                String faceUrl = addImages(employee, 0, faceImg);
                employee.setEmployeeFaceImg(faceUrl);
            } else {
                throw new EmployeeException(EmployeeStateEnum.FACE_IMAGE_EMPTY.getStateInfo());
            }
            //插入信息
            try {
                int effectNum = employeeDao.insertEmployee(employee);
                if(effectNum <= 0){
                    throw new EmployeeException(EmployeeStateEnum.INSERT_ERROR.getStateInfo());
                } else {
                    //TODO 自动生成账号
                    return new EmployeeExecution(EmployeeStateEnum.SUCCESS, employee);
                }
            } catch (Exception e){
                return new EmployeeExecution(EmployeeStateEnum.INSERT_ERROR);
            }
        } else {
            return new EmployeeExecution(EmployeeStateEnum.EMPTY);
        }

    }

    /**
     * 根据工号查询信息
     * @param employeeId
     * @return
     */
    @Override
    public Employee queryEmployeeById(String employeeId) {
        return employeeDao.queryEmployeeById(employeeId);
    }

    /**
     * 模糊查询员工列表
     * @param employeeCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @Override
    public EmployeeExecution queryEmployeeList(Employee employeeCondition, int pageIndex, int pageSize) {
        //页数转换成数据库的行数
        int rowIndex = PageUtil.calculateRowIndex(pageIndex, pageSize);
        //查询所需记录
        List<Employee> employeeList = employeeDao.queryEmployeeList(employeeCondition, rowIndex, pageSize);
        //记录的总行数
        int count = employeeDao.queryEmployeeCount(employeeCondition);
        EmployeeExecution employeeExecution = new EmployeeExecution();
        employeeExecution.setEmployeeList(employeeList);
        employeeExecution.setCount(count);
        return employeeExecution;
    }

    @Override
    @Transactional
    public EmployeeExecution updateEmployee(Employee employee, MultipartFile cardImg, MultipartFile faceImg) throws IOException {
        //空值判断
        if(employee != null && employee.getEmployeeId() != null){
            //身份证图片是否为空
            if(cardImg != null){
                String cardUrl = addImages(employee, 1, cardImg);
                employee.setEmployeeCardImg(cardUrl);
            }
            if(faceImg != null){
                String faceUrl = addImages(employee, 0, faceImg);
                employee.setEmployeeFaceImg(faceUrl);
            }
            try{
                int effectedNum = employeeDao.updateEmployee(employee);
                if(effectedNum <= 0){
                    throw new EmployeeException(EmployeeStateEnum.UPDATE_ERROR.getStateInfo());
                }
                return new EmployeeExecution(EmployeeStateEnum.SUCCESS, employee);
            } catch (Exception e){
                throw new EmployeeException(EmployeeStateEnum.UPDATE_ERROR.getStateInfo());
            }
        } else {
            return new EmployeeExecution(EmployeeStateEnum.PAGE_ERROR);
        }
    }

    @Override
    @Transactional
    public EmployeeExecution deleteEmployee(String employeeId) {
        if(employeeId != null){
            int effectNum = employeeDao.deleteEmployee(employeeId);
            if(effectNum <= 0){
                throw new EmployeeException(EmployeeStateEnum.INNER_ERROR.getStateInfo());
            }
            return new EmployeeExecution(EmployeeStateEnum.SUCCESS);
        } else {
            return new EmployeeExecution(EmployeeStateEnum.EMPTY);
        }
    }


    /**
     * 生成工号 入职日期+部门号(2位)+员工号
     * @param position
     * @return
     */
    private String generateEmployeeId(Position position){
        //日期字符串
        String dateStr = dateFormat.format(new Date());
        //职工号字符串
        String positionStr = positionFormat.format(position.getPositionId());
        //员工号字符串
        Employee employeeCondition = new Employee();
        employeeCondition.setPosition(position);
        int count = employeeDao.queryEmployeeCount(employeeCondition) + 1;
        String employeeStr = positionFormat.format(count);
        return dateStr + positionStr + employeeStr;
    }

    /**
     * 上传图片
     * @param employee
     * @param imageType
     * @param file
     * @return
     */
    private String addImages(Employee employee, int imageType, MultipartFile file) throws IOException {
        //获取存储路径
        String dest = PathUtil.getPersonImagePath(employee.getEmployeeId(), imageType, 1);
        String relativeAddr = ImageUtil.uploadImage(file, dest);
        return relativeAddr;
    }
}
