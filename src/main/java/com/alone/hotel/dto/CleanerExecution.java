package com.alone.hotel.dto;

import com.alone.hotel.entity.Cleaner;
import com.alone.hotel.enums.CleanerStateEnum;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-16 21:24
 * @Description:
 */
@Data
public class CleanerExecution {
    //结果状态
    private int state;
    //状态标识
    private String stateInfo;
    //商品数量
    private int count;
    //操作的清洁员
    private Cleaner cleaner;
    //操作的清洁员列表
    private List<Cleaner> cleanerList;

    public CleanerExecution() {
    }

    public CleanerExecution(CleanerStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public CleanerExecution(CleanerStateEnum stateEnum, Cleaner cleaner){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.cleaner = cleaner;
    }

    public CleanerExecution(CleanerStateEnum stateEnum, List<Cleaner> cleanerList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.cleanerList = cleanerList;
    }
}
