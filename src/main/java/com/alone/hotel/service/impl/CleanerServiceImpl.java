package com.alone.hotel.service.impl;

import com.alone.hotel.dao.CleanerDao;
import com.alone.hotel.dao.EmployeeDao;
import com.alone.hotel.dto.CleanerExecution;
import com.alone.hotel.entity.Cleaner;
import com.alone.hotel.entity.Employee;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.exceptions.CleanerException;
import com.alone.hotel.service.CleanerService;
import com.alone.hotel.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-16 21:33
 * @Description:
 */
@Service
public class CleanerServiceImpl implements CleanerService {
    @Autowired
    private CleanerDao cleanerDao;
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    @Transactional
    public CleanerExecution addCleaner(Cleaner cleaner) {
        if(cleaner != null && cleaner.getEmployee() != null && cleaner.getEmployee().getEmployeeId() != null){
            //查询员工信息
            Employee employee = employeeDao.queryEmployeeById(cleaner.getEmployee().getEmployeeId());
            //判断员工类别是否合法,规定3为清洁员
            if(employee.getPosition().getPositionId() == 3){
                //插入
                cleaner.setEmployee(employee);
                try{
                    int effectedNum = cleanerDao.insertCleaner(cleaner);
                    if(effectedNum <= 0){
                        throw new CleanerException(ResultEnum.INNER_ERROR.getStateInfo());
                    }
                    return new CleanerExecution(ResultEnum.SUCCESS, cleaner);
                } catch (Exception e){
                    throw new CleanerException(ResultEnum.INNER_ERROR.getStateInfo());
                }
            } else {
                return new CleanerExecution(ResultEnum.POSITION_TYPE_ERROR);
            }
        } else {
            return new CleanerExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    public Cleaner queryCleanerById(String employeeId) {
        return cleanerDao.queryCleanerById(employeeId);
    }

    @Override
    public CleanerExecution queryCleanerList(String cleanerFloor, int pageIndex, int pageSize) {
        //页数转换成数据库的行数
        int rowIndex = PageUtil.calculateRowIndex(pageIndex, pageSize);
        //查询所需记录
        List<Cleaner> CleanerList = cleanerDao.queryCleanerList(cleanerFloor, rowIndex, pageSize);
        //记录的总行数
        int count = cleanerDao.queryCleanerCount(cleanerFloor);
        CleanerExecution re = null;
        re = new CleanerExecution();
        re.setCleanerList(CleanerList);
        re.setCount(count);
        return re;
    }

    @Override
    @Transactional
    public CleanerExecution updateCleaner(Cleaner cleaner) {
        if(cleaner != null){
            try{
                int effectedNum = cleanerDao.updateCleaner(cleaner);
                if(effectedNum <= 0){
                    throw new CleanerException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new CleanerExecution(ResultEnum.SUCCESS, cleaner);
            } catch (Exception e){
                throw new CleanerException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new CleanerExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public CleanerExecution deleteCleaner(String employeeId) {
        try {
            if(employeeId != null){
                int effectedNum = cleanerDao.deleteCleaner(employeeId);
                if(effectedNum <= 0){
                    throw new CleanerException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new CleanerExecution(ResultEnum.SUCCESS);
            }
            else {
                return new CleanerExecution(ResultEnum.EMPTY);
            }
        } catch (Exception e){
            return new CleanerExecution(ResultEnum.INNER_ERROR);
        }
    }


}
