package com.alone.hotel.controller.personnel;

import com.alone.hotel.dto.CustomerAccountExecution;
import com.alone.hotel.entity.Customer;
import com.alone.hotel.entity.CustomerAccount;
import com.alone.hotel.enums.CustomerAccountStateEnum;
import com.alone.hotel.service.CustomerAccountService;
import com.alone.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.personnel
 * @Author: Alone
 * @CreateTime: 2020-04-16 20:25
 * @Description:
 */
@RestController
@RequestMapping("/personnel")
public class ClientManagement {
    @Autowired
    private CustomerAccountService customerAccountService;
    @Autowired
    private CustomerService customerService;

    /**
     * 查询顾客账号列表
     * @return
     */
    @GetMapping("/querycustomeraccountlist")
    private CustomerAccountExecution queryCustomerAccountList(){
        try{
            List<CustomerAccount> customerAccountList = customerAccountService.queryCustomerAccountList();
            if(customerAccountList != null){
                for (int i = 0; i < customerAccountList.size(); i++) {
                    String customerCardNumber = customerAccountList.get(i).getCustomer().getCustomerCardNumber();
                    if(customerCardNumber != null){
                        try {
                            Customer customer = customerService.queryCustomerById(customerCardNumber);
                            customerAccountList.get(i).setCustomer(customer);
                        } catch (Exception e){
                            return new CustomerAccountExecution(CustomerAccountStateEnum.INNER_ERROR);
                        }
                    }
                }
            }
            return new CustomerAccountExecution(CustomerAccountStateEnum.SUCCESS, customerAccountList);
        } catch (Exception e){
            return new CustomerAccountExecution(CustomerAccountStateEnum.INNER_ERROR);
        }
    }
}
