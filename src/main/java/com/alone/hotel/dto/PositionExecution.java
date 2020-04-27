package com.alone.hotel.dto;

import com.alone.hotel.entity.Position;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.enums.ResultEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-11 20:32
 * @Description:
 */
@Data
public class PositionExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的职位
    private Position position;
    //操作的职位列表
    private List<Position> positionList;

    public PositionExecution() {
    }

    public PositionExecution(ResultEnum ResultEnum){
        this.state = ResultEnum.getState();
        this.stateInfo = ResultEnum.getStateInfo();
    }

    public PositionExecution(ResultEnum ResultEnum, Position position){
        this.state = ResultEnum.getState();
        this.stateInfo = ResultEnum.getStateInfo();
        this.position = position;
    }

    public PositionExecution(ResultEnum ResultEnum, List<Position> positionList){
        this.state = ResultEnum.getState();
        this.stateInfo = ResultEnum.getStateInfo();
        this.positionList = positionList;
    }
}
