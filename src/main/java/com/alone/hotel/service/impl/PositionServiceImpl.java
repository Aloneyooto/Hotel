package com.alone.hotel.service.impl;

import com.alone.hotel.dao.PositionDao;
import com.alone.hotel.dto.PositionExecution;
import com.alone.hotel.entity.Position;
import com.alone.hotel.enums.PositionStateEnum;
import com.alone.hotel.exceptions.PositionException;
import com.alone.hotel.service.PositionService;
import com.alone.hotel.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-13 14:34
 * @Description: 职位
 */
@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionDao positionDao;

    @Override
    @Transactional
    public PositionExecution insertPosition(Position position) {
        if(position != null){
            int effectNum = positionDao.insertPosition(position);
            if(effectNum <= 0){
                throw new PositionException("插入失败");
            } else {
                return new PositionExecution(PositionStateEnum.SUCCESS);
            }
        } else {
            return new PositionExecution(PositionStateEnum.POSITION_EMPTY);
        }
    }

    @Override
    public PositionExecution queryPositionById(int positionId) {
        if(positionId < 0){
            return new PositionExecution(PositionStateEnum.POSITION_ID_ERROR);
        } else {
            try{
                Position position = positionDao.queryPositionById(positionId);
                return new PositionExecution(PositionStateEnum.SUCCESS, position);
            } catch (Exception e){
                throw new PositionException("查询职位信息出错");
            }
        }
    }

    @Override
    public PositionExecution queryPositionList(Position positionCondition, int pageIndex, int pageSize) {
        if(pageIndex > 0 && pageSize > 0){
            //页数转换成数据库的行数
            int rowIndex = PageUtil.calculateRowIndex(pageIndex, pageSize);
            List<Position> positionList = positionDao.queryPositionList(positionCondition, rowIndex, pageSize);
            int count = positionDao.queryPositionCount(positionCondition);
            PositionExecution pe = new PositionExecution();
            pe.setPositionList(positionList);
            pe.setCount(count);
            return pe;
        } else {
            return new PositionExecution(PositionStateEnum.POSITION_PAGE_ERROR);
        }
    }

    @Override
    @Transactional
    public PositionExecution modifyPosition(Position position) {
        if(position != null){
            try {
                int effectedNum = positionDao.updatePosition(position);
                if(effectedNum <= 0){
                    throw new PositionException("修改职位信息失败");
                }
                return new PositionExecution(PositionStateEnum.SUCCESS, position);
            } catch (Exception e){
                throw new PositionException("修改职位信息失败");
            }
        } else {
            return new PositionExecution(PositionStateEnum.POSITION_EMPTY);
        }
    }


    @Override
    @Transactional
    public PositionExecution deletePosition(int positionId) {
        try{
            int effectedNum = positionDao.deletePosition(positionId);
            if(effectedNum <= 0){
                throw new PositionException("职位信息删除失败");
            }
            return new PositionExecution(PositionStateEnum.SUCCESS);
        } catch (Exception e){
            throw new PositionException("职位信息删除失败" + e.getMessage());
        }
    }


}
