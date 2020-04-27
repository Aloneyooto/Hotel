package com.alone.hotel.service.impl;

import com.alone.hotel.dao.RecreationDao;
import com.alone.hotel.dto.RecreationExecution;
import com.alone.hotel.entity.Recreation;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.exceptions.RecreationException;
import com.alone.hotel.service.RecreationService;
import com.alone.hotel.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-17 20:29
 * @Description:
 */
@Service
public class RecreationServiceImpl implements RecreationService {
    @Autowired
    private RecreationDao recreationDao;
    
    @Override
    @Transactional
    public RecreationExecution insertRecreation(Recreation recreation) {
        if(recreation != null){
            int effectNum = recreationDao.addRecreation(recreation);
            if(effectNum <= 0){
                throw new RecreationException("插入失败");
            } else {
                return new RecreationExecution(ResultEnum.SUCCESS);
            }
        } else {
            return new RecreationExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    public Recreation queryRecreationById(int recreationId) {
        return recreationDao.queryRecreationById(recreationId);
    }

    @Override
    public RecreationExecution queryRecreationList(Recreation recreationCondition, int pageIndex, int pageSize) {
        if(pageIndex > 0 && pageSize > 0){
            //页数转换成数据库的行数
            int rowIndex = PageUtil.calculateRowIndex(pageIndex, pageSize);
            List<Recreation> recreationList = recreationDao.queryRecreationList(recreationCondition, rowIndex, pageSize);
            int count = recreationDao.queryRecreationCount(recreationCondition);
            RecreationExecution pe = new RecreationExecution();
            pe.setRecreationList(recreationList);
            pe.setCount(count);
            return pe;
        } else {
            return new RecreationExecution(ResultEnum.PAGE_ERROR);
        }
    }

    @Override
    @Transactional
    public RecreationExecution modifyRecreation(Recreation recreation) {
        if(recreation != null){
            try {
                int effectedNum = recreationDao.updateRecreation(recreation);
                if(effectedNum <= 0){
                    throw new RecreationException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new RecreationExecution(ResultEnum.SUCCESS, recreation);
            } catch (Exception e){
                throw new RecreationException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new RecreationExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public RecreationExecution deleteRecreation(int recreationId) {
        if(recreationId > 0){
            try{
                int effectedNum = recreationDao.deleteRecreation(recreationId);
                if(effectedNum <= 0){
                    throw new RecreationException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new RecreationExecution(ResultEnum.SUCCESS);
            } catch (Exception e){
                throw new RecreationException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new RecreationExecution(ResultEnum.ID_ERROR);
        }
    }
}
