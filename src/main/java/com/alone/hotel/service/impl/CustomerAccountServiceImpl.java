package com.alone.hotel.service.impl;

import com.alone.hotel.dao.CustomerAccountDao;
import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.enums.CustomerAccountStateEnum;
import com.alone.hotel.enums.CustomerStateEnum;
import com.alone.hotel.exceptions.CustomerAccountException;
import com.alone.hotel.service.CustomerAccountService;
import com.alone.hotel.utils.ImageUtil;
import com.alone.hotel.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-24 08:34
 * @Description:
 */
@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {
    @Autowired
    private CustomerAccountDao customerAccountDao;
    
    @Override
    @Transactional
    public CustomerAccountExecution addCustomerAccount(CustomerAccount customerAccount) {
        if(customerAccount != null && customerAccount.getAccountName() != null && customerAccount.getAccountPassword() != null){
            try{
                //未进行实名验证
                customerAccount.setFlag(0);
                int effectedNum = customerAccountDao.addCustomerAccount(customerAccount);
                if(effectedNum <= 0){
                    throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
                }
                return new CustomerAccountExecution(CustomerAccountStateEnum.SUCCESS);
            } catch (Exception e){
                throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

    @Override
    public CustomerAccount queryCustomerAccountByNameAndPsw(String accountName, String password) {
        return customerAccountDao.queryCustomerAccountByNameAndPsw(accountName, password);
    }

    @Override
    public CustomerAccount queryCustomerAccountByName(String accountName) {
        return customerAccountDao.queryCustomerAccountByName(accountName);
    }

    @Override
    public List<CustomerAccount> queryCustomerAccountList() {
        return customerAccountDao.queryCustomerAccountList();
    }


    @Override
    @Transactional
    public CustomerAccountExecution updateCustomerAccount(CustomerAccount customerAccount, MultipartFile headImg) {
        if(customerAccount != null){
            try{
                if(headImg != null){
                    try {
                        String imgPath = addImage(customerAccount, headImg);
                        customerAccount.setHeadImg(imgPath);
                    } catch (Exception e){
                        throw new CustomerAccountException(CustomerAccountStateEnum.HEAD_IMAGE_ERROR.getStateInfo());
                    }
                }
                int effectNum = customerAccountDao.updateCustomerAccount(customerAccount);
                if(effectNum <= 0){
                    throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
                }
                return new CustomerAccountExecution(CustomerAccountStateEnum.SUCCESS);
            } catch (Exception e){
                throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public CustomerAccountExecution deleteCustomerAccount(String accountName) {
        if(accountName != null){
            try {
                int effectNum = customerAccountDao.deleteCustomerAccount(accountName);
                if(effectNum <= 0){
                    throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
                }
                return new CustomerAccountExecution(CustomerAccountStateEnum.SUCCESS);
            } catch (Exception e){
                throw new CustomerAccountException(CustomerAccountStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new CustomerAccountExecution(CustomerAccountStateEnum.ACCOUNT_NAME_EMPTY);
        }
    }

    /**
     * 上传图片
     * @param customerAccount
     * @param headImg
     * @return
     */
    private String addImage(CustomerAccount customerAccount, MultipartFile headImg) throws IOException {
        //获取存储路径
        String dest = PathUtil.getHeadImagePath(customerAccount.getAccountName());
        String relativeAddr = ImageUtil.uploadImage(headImg, dest);
        return relativeAddr;
    }
}
