package com.alone.hotel.dto;

import com.alone.hotel.entity.CustomerRelation;
import com.alone.hotel.enums.CustomerRelationStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-24 08:49
 * @Description:
 */
@Data
public class CustomerRelationExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的
    private CustomerRelation customerRelation;
    //操作的列表
    private List<CustomerRelation> customerRelationList;

    public CustomerRelationExecution() {
    }

    public CustomerRelationExecution(CustomerRelationStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public CustomerRelationExecution(CustomerRelationStateEnum stateEnum, CustomerRelation customerRelation){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.customerRelation = customerRelation;
    }

    public CustomerRelationExecution(CustomerRelationStateEnum stateEnum, List<CustomerRelation> customerRelationList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.customerRelationList = customerRelationList;
    }
}
