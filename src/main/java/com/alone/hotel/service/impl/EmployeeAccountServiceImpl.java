package com.alone.hotel.service.impl;

import com.alone.hotel.dao.EmployeeAccountDao;
import com.alone.hotel.dto.EmployeeAccountExecution;
import com.alone.hotel.entity.EmployeeAccount;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.exceptions.EmployeeAccountException;
import com.alone.hotel.service.EmployeeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-18 15:15
 * @Description: 员工账号处理
 */
@Service
public class EmployeeAccountServiceImpl implements EmployeeAccountService {
    @Autowired
    private EmployeeAccountDao employeeAccountDao;

    @Override
    @Transactional
    public EmployeeAccountExecution addEmployeeAccount(EmployeeAccount employeeAccount) {
        if(employeeAccount != null){
            try{
                int effectedNum = employeeAccountDao.addEmployeeAccount(employeeAccount);
                if(effectedNum <= 0){
                    throw new EmployeeAccountException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new EmployeeAccountExecution(ResultEnum.SUCCESS);
            } catch (Exception e){
                throw new EmployeeAccountException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new EmployeeAccountExecution(ResultEnum.ACCOUNT_EMPTY);
        }
    }

    @Override
    public EmployeeAccount queryEmployeeAccountByName(String employeeAccountName, String employeeAccountPassword) {
        return employeeAccountDao.queryEmployeeAccountByName(employeeAccountName, employeeAccountPassword);
    }

    @Override
    public List<EmployeeAccount> queryEmployeeAccountList() {
        return employeeAccountDao.queryEmployeeAccountList();
    }


    @Override
    @Transactional
    public EmployeeAccountExecution updateEmployeeAccount(EmployeeAccount employeeAccount) {
        if(employeeAccount != null){
            try{
                int effectNum = employeeAccountDao.updateEmployeeAccount(employeeAccount);
                if(effectNum <= 0){
                    throw new EmployeeAccountException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new EmployeeAccountExecution(ResultEnum.SUCCESS, employeeAccount);
            } catch (Exception e){
                throw new EmployeeAccountException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new EmployeeAccountExecution(ResultEnum.ACCOUNT_EMPTY);
        }
    }

    @Override
    @Transactional
    public EmployeeAccountExecution deleteEmployeeAccount(String employeeAccountName) {
        if(employeeAccountName != null){
            try {
                int effectNum = employeeAccountDao.deleteEmployeeAccount(employeeAccountName);
                if(effectNum <= 0){
                    throw new EmployeeAccountException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new EmployeeAccountExecution(ResultEnum.SUCCESS);
            } catch (Exception e){
                throw new EmployeeAccountException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new EmployeeAccountExecution(ResultEnum.EMPTY);
        }
    }


}
