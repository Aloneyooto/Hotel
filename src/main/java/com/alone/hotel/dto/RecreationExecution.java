package com.alone.hotel.dto;

import com.alone.hotel.entity.Recreation;
import com.alone.hotel.enums.RecreationStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-17 20:24
 * @Description:
 */
@Data
public class RecreationExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的职位
    private Recreation recreation;
    //操作的职位列表
    private List<Recreation> recreationList;

    public RecreationExecution() {
    }

    public RecreationExecution(RecreationStateEnum RecreationStateEnum){
        this.state = RecreationStateEnum.getState();
        this.stateInfo = RecreationStateEnum.getStateInfo();
    }

    public RecreationExecution(RecreationStateEnum RecreationStateEnum, Recreation recreation){
        this.state = RecreationStateEnum.getState();
        this.stateInfo = RecreationStateEnum.getStateInfo();
        this.recreation = recreation;
    }

    public RecreationExecution(RecreationStateEnum RecreationStateEnum, List<Recreation> recreationList){
        this.state = RecreationStateEnum.getState();
        this.stateInfo = RecreationStateEnum.getStateInfo();
        this.recreationList = recreationList;
    }
}
