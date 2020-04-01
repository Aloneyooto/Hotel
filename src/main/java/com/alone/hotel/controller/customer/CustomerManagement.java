package com.alone.hotel.controller.customer;

import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.dto.CustomerExecution;
import com.alone.hotel.dto.CustomerRelationExecution;
import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.entity.CustomerRelation;
import com.alone.hotel.enums.CustomerAccountStateEnum;
import com.alone.hotel.enums.CustomerRelationStateEnum;
import com.alone.hotel.enums.CustomerStateEnum;
import com.alone.hotel.service.CustomerAccountService;
import com.alone.hotel.service.CustomerRelationService;
import com.alone.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.customer
 * @Author: Alone
 * @CreateTime: 2020-03-24 09:08
 * @Description:
 */
@RestController
@RequestMapping("/customer")
public class CustomerManagement {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerAccountService customerAccountService;
    @Autowired
    private CustomerRelationService customerRelationService;

    /**
     * 增加顾客信息
     * @param customer 入住人信息
     * @param cardImg 证件图片
     * @param faceImg 面部图片
     * @param customerAccount 账号信息
     * @param flag 实名认证标记
     * @return
     */
    @PostMapping("/addcustomermessage")
    private CustomerExecution addCustomerMessage(Customer customer, MultipartFile cardImg, MultipartFile faceImg,
                                                 CustomerAccount customerAccount, int flag){
        if(customer != null){
            //添加顾客信息
            try{
                CustomerExecution customerExecution = customerService.addCustomer(customer, cardImg, faceImg);
                if(customerExecution.getState() != CustomerStateEnum.SUCCESS.getState()){
                    return customerExecution;
                }
            } catch (Exception e){
                return new CustomerExecution(CustomerStateEnum.INNER_ERROR);
            }
            //是否实名认证，是的话向customer_account表写入数据
            if(flag == 1){
                //在进行实名认证
                customerAccount.setFlag(1);
                customerAccount.setCustomerCardNumber(customer.getCustomerCardNumber());
                //To check
                CustomerAccountExecution customerAccountExecution = customerAccountService.updateCustomerAccount(customerAccount, null);
                if(customerAccountExecution.getState() != CustomerAccountStateEnum.SUCCESS.getState()){
                    return new CustomerExecution(CustomerStateEnum.CERTIFICATION_ERROR);
                }
            }
            //向customer_relation表添加关系
            CustomerRelation customerRelation = new CustomerRelation();
            customerRelation.setAccount(customerAccount);
            customerRelation.setCustomer(customer);
            CustomerRelationExecution customerRelationExecution = customerRelationService.addCustomerRelation(customerRelation);
            if(customerRelationExecution.getState() == CustomerRelationStateEnum.SUCCESS.getState()){
                return new CustomerExecution(CustomerStateEnum.SUCCESS, customer);
            } else {
                return new CustomerExecution(CustomerStateEnum.RELATION_INSERT_ERROR);
            }
        } else {
            return new CustomerExecution(CustomerStateEnum.BASIC_MESSAGE_EMPTY);
        }
    }

    /**
     * 列出该账号存放的顾客信息
     * @param customerAccount
     * @return
     */
    @GetMapping("/querycustomerbyaccount")
    private CustomerExecution queryCustomerByAccount(CustomerAccount customerAccount){
        if(customerAccount != null){
            CustomerRelation customerRelation = new CustomerRelation();
            customerRelation.setAccount(customerAccount);
            CustomerAccount account = customerRelationService.queryCustomerByAccount(customerRelation);
            if(account != null){
                return new CustomerExecution(CustomerStateEnum.SUCCESS, account.getCustomerList());
            } else {
                return new CustomerExecution(CustomerStateEnum.INNER_ERROR);
            }
        } else {
            return new CustomerExecution(CustomerStateEnum.BASIC_MESSAGE_EMPTY);
        }
    }

    /**
     *
     * @param customer
     * @param cardImg
     * @param faceImg
     * @return
     */
    @PostMapping("/updatecustomermessage")
    private CustomerExecution updateCustomerMessage(Customer customer, MultipartFile cardImg, MultipartFile faceImg){
        //判断顾客信息是否为空
        if(customer != null){
            try{
                CustomerExecution customerExecution = customerService.updateCustomer(customer, cardImg, faceImg);
                if(customerExecution.getState() == CustomerStateEnum.SUCCESS.getState()){
                    return customerExecution;
                } else {
                    return new CustomerExecution(CustomerStateEnum.INNER_ERROR);
                }
            } catch (Exception e){
                  return new CustomerExecution(CustomerStateEnum.INNER_ERROR);
            }
        } else {
            return new CustomerExecution(CustomerStateEnum.BASIC_MESSAGE_EMPTY);
        }
    }

    /**
     * 删除顾客信息
     * @param customer
     * @param customerAccount
     * @return
     */
    @PostMapping("/deletecustomermessage")
    private CustomerExecution deleteCustomerMessage(Customer customer, CustomerAccount customerAccount){
        if(customer != null && customerAccount != null){
            //删除customerrelation里的对应内容
            CustomerRelation customerRelation = new CustomerRelation();
            customerRelation.setAccount(customerAccount);
            customerRelation.setCustomer(customer);
            try {
                CustomerRelationExecution customerRelationExecution = customerRelationService.deleteCustomerRelation(customerRelation);
                if(customerRelationExecution.getState() != CustomerRelationStateEnum.SUCCESS.getState()){
                    return new CustomerExecution(CustomerStateEnum.RELATION_DELETE_ERROR);
                }
            } catch (Exception e){
                return new CustomerExecution(CustomerStateEnum.RELATION_DELETE_ERROR);
            }
            //删除customer的记录
            CustomerExecution customerExecution = customerService.deleteCustomer(customer.getCustomerCardNumber());
            if(customerExecution.getState() == CustomerStateEnum.SUCCESS.getState()){
                return customerExecution;
            } else {
                return new CustomerExecution(CustomerStateEnum.INNER_ERROR);
            }
        } else {
            return new CustomerExecution(CustomerStateEnum.BASIC_MESSAGE_EMPTY);
        }
    }
}
