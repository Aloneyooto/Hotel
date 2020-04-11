package com.alone.hotel.controller.customer;

import com.alibaba.fastjson.JSONObject;
import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.dto.CustomerExecution;
import com.alone.hotel.dto.CustomerRelationExecution;
import com.alone.hotel.dto.RoomExecution;
import com.alone.hotel.entity.CheckIn;
import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.entity.CustomerRelation;
import com.alone.hotel.enums.CustomerAccountStateEnum;
import com.alone.hotel.enums.CustomerRelationStateEnum;
import com.alone.hotel.enums.CustomerStateEnum;
import com.alone.hotel.enums.RoomStateEnum;
import com.alone.hotel.service.CheckInService;
import com.alone.hotel.service.CustomerAccountService;
import com.alone.hotel.service.CustomerRelationService;
import com.alone.hotel.service.CustomerService;
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

    @Autowired
    private CheckInService checkInService;

    /**
     * 增加顾客信息
     * @Param request
     * @return
     */
    @PostMapping("/addcustomermessage")
    private CustomerExecution addCustomerMessage(HttpServletRequest request){
        //Customer customer, MultipartFile cardImg, MultipartFile faceImg, CustomerAccount customerAccount, int flag
        MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
        String customerCardNumber = params.getParameter("customerCardNumber");
        //保证身份证号不为空
        if(customerCardNumber != null && customerCardNumber.length() == 18){
            Customer customer = new Customer();
            CustomerAccount customerAccount = new CustomerAccount();
            int customerAge = 0;
            int customerGender = 0;
            String customerPhone = null;
            MultipartFile cardImg = params.getFile("cardImg");
            MultipartFile faceImg = params.getFile("faceImg");
            int flag = Integer.parseInt(params.getParameter("flag"));
            String customerName = params.getParameter("customerName");
            if(params.getParameter("customerAge") != null){
                customerAge = Integer.parseInt(params.getParameter("customerAge"));
            }
            if(params.getParameter("customerGender") != null){
                customerGender = Integer.parseInt(params.getParameter("customerGender"));
            }
            if(params.getParameter("customerPhone") != null){
                customerPhone = params.getParameter("customerPhone");
            }
            String accountName = params.getParameter("accountName");
            String accountPassword = params.getParameter("accountPassword");


            //添加顾客信息
            try{
                customer.setCustomerCardNumber(customerCardNumber);
                customer.setCustomerName(customerName);
                customer.setCustomerAge(customerAge);
                customer.setCustomerGender(customerGender);
                customer.setCustomerPhone(customerPhone);

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
                customerAccount.setCustomer(customer);

                customerAccount.setAccountName(accountName);
                customerAccount.setAccountPassword(accountPassword);

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
            return new CustomerExecution(CustomerStateEnum.BASIC_MESSAGE_ERROR);
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
            return new CustomerExecution(CustomerStateEnum.BASIC_MESSAGE_ERROR);
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
    private CustomerExecution updateCustomerMessage(@RequestParam("customer") Customer customer, @RequestParam("cardImg") MultipartFile cardImg, @RequestParam("faceImg") MultipartFile faceImg){
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
            return new CustomerExecution(CustomerStateEnum.BASIC_MESSAGE_ERROR);
        }
    }

    /**
     * 删除顾客信息
     * @param jsonObject
     * @return
     */
    @PostMapping("/deletecustomermessage")
    private CustomerExecution deleteCustomerMessage(@RequestBody JSONObject jsonObject){
        //String accountName, String customerCardNumber
        String accountName = jsonObject.getString("accountName");
        String customerCardNumber = jsonObject.getString("customerCardNumber");
        if(accountName != null && customerCardNumber != null){
            //删除customerrelation里的对应内容
            CustomerRelation customerRelation = new CustomerRelation();
            CustomerAccount customerAccount = new CustomerAccount();
            customerAccount.setAccountPassword(accountName);
            Customer customer = new Customer();
            customer.setCustomerCardNumber(customerCardNumber);
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
            return new CustomerExecution(CustomerStateEnum.BASIC_MESSAGE_ERROR);
        }
    }

    /**
     * 人脸识别
     * @param faceFile
     * @param roomId
     */
    @GetMapping("/compareFaces")
    private RoomExecution compareFaces(@RequestParam MultipartFile faceFile, @RequestParam Integer roomId){
        //初始化引擎
        FaceUtil.initEngine();
        //查询数据库内已有的人脸
        List<Customer> customerList = customerService.queryCustomerFaceImages();
        //生成人脸特征信息
        FaceUtil.getDataSoureFeature(customerList);
        try {
            File newFile = FaceUtil.multipartFileToFile(faceFile);
            String customerCardNumber = FaceUtil.compareFaces(newFile);
            CheckIn checkIn = checkInService.queryCheckInByCustomer(customerCardNumber);
            if(roomId == checkIn.getRoom().getRoomId()){
                return new RoomExecution(RoomStateEnum.SUCCESS, checkIn.getRoom());
            } else {
                return new RoomExecution(RoomStateEnum.ROOM_ID_ERROR);
            }
        } catch (IOException e) {
            return new RoomExecution(RoomStateEnum.INNER_ERROR);
        }
    }
}
