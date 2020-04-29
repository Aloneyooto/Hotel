package com.alone.hotel.service.impl;

import com.alone.hotel.dao.CheckInDao;
import com.alone.hotel.entity.CheckIn;
import com.alone.hotel.entity.Room;
import com.alone.hotel.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-28 14:59
 * @Description:
 */
@Service
public class CheckInServiceImpl implements CheckInService {
    @Autowired
    private CheckInDao checkInDao;

    @Override
    @Transactional
    public boolean addCheckInMessage(CheckIn checkIn) {
        if(checkIn != null){
            try {
                int effectedNum = checkInDao.addCheckInMessage(checkIn);
                if(effectedNum <= 0){
                    return false;
                }
                return true;
            } catch (Exception e){
                return false;
            }
        }
        return false;
    }

    @Override
    public Room queryCheckInByRoom(Integer roomId) {
        return checkInDao.queryCheckInByRoom(roomId);
    }

    @Override
    public CheckIn queryCheckInByCustomer(String customerCardNumber) {
        return checkInDao.queryCheckInByCustomer(customerCardNumber);
    }


    @Override
    @Transactional
    public boolean deleteCheckInMessage(CheckIn checkIn) {
        if (checkIn != null) {
            try {
                int effectedNum = checkInDao.deleteCheckInMessage(checkIn);
                if (effectedNum <= 0) {
                    return false;
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteCheckInMessageByRoom(Integer roomId) {
        if(roomId > 0){
            try {
                int effectedNum = checkInDao.deleteCheckInMessageByRoom(roomId);
                if(effectedNum <= 0){
                    return false;
                }
                return true;
            } catch (Exception e){
                return false;
            }
        } else {
            return false;
        }
    }

}
