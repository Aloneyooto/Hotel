package com.alone.hotel.dto;

import com.alone.hotel.entity.Recreation;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.enums.ResultEnum;
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

    public RecreationExecution(ResultEnum ResultEnum){
        this.state = ResultEnum.getState();
        this.stateInfo = ResultEnum.getStateInfo();
    }

    public RecreationExecution(ResultEnum ResultEnum, Recreation recreation){
        this.state = ResultEnum.getState();
        this.stateInfo = ResultEnum.getStateInfo();
        this.recreation = recreation;
    }

    public RecreationExecution(ResultEnum ResultEnum, List<Recreation> recreationList){
        this.state = ResultEnum.getState();
        this.stateInfo = ResultEnum.getStateInfo();
        this.recreationList = recreationList;
    }
}
