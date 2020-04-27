package com.alone.hotel.service.impl;

import com.alone.hotel.dao.RoomTypeDao;
import com.alone.hotel.dto.RoomTypeExecution;
import com.alone.hotel.entity.RoomType;
import com.alone.hotel.enums.ResultEnum;
import com.alone.hotel.exceptions.RoomException;
import com.alone.hotel.exceptions.RoomTypeException;
import com.alone.hotel.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-30 08:49
 * @Description:
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeDao roomTypeDao;

    @Override
    @Transactional
    public RoomTypeExecution addRoomType(RoomType roomType) {
        if(roomType != null){
            try{
                int effectedNum = roomTypeDao.addRoomType(roomType);
                if(effectedNum <= 0){
                    throw new RoomTypeException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new RoomTypeExecution(ResultEnum.SUCCESS);
            } catch (Exception e){
                throw new RoomTypeException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new RoomTypeExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    public RoomType queryRoomTypeById(Integer typeId) {
        return roomTypeDao.queryRoomTypeById(typeId);
    }

    @Override
    public List<RoomType> queryRoomType() {
        return roomTypeDao.queryRoomType();
    }

    @Override
    @Transactional
    public RoomTypeExecution updateRoomType(RoomType roomType) {
        if(roomType != null){
            try{
                int effectedNum = roomTypeDao.updateRoomType(roomType);
                if(effectedNum <= 0){
                    throw new RoomTypeException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new RoomTypeExecution(ResultEnum.SUCCESS, roomType);
            } catch (Exception e){
                throw new RoomTypeException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new RoomTypeExecution(ResultEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public RoomTypeExecution deleteRoomType(Integer roomTypeId) {
        if(roomTypeId != null){
            try{
                int effectedNum = roomTypeDao.deleteRoomType(roomTypeId);
                if(effectedNum <= 0){
                    throw new RoomTypeException(ResultEnum.INNER_ERROR.getStateInfo());
                }
                return new RoomTypeExecution(ResultEnum.SUCCESS);
            } catch (Exception e){
                throw new RoomTypeException(ResultEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new RoomTypeExecution(ResultEnum.EMPTY);
        }
    }
}
