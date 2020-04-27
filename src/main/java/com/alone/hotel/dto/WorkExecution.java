package com.alone.hotel.dto;

import com.alone.hotel.entity.Work;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.enums.ResultEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-22 21:21
 * @Description:
 */
@Data
public class WorkExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的房间
    private Work work;
    //操作的房间列表
    private List<Work> workList;

    public WorkExecution() {
    }

    public WorkExecution(ResultEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public WorkExecution(ResultEnum stateEnum, Work work){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.work = work;
    }

    public WorkExecution(ResultEnum stateEnum, List<Work> workList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.workList = workList;
    }
}
