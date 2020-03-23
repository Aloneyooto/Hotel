package com.alone.hotel.service.impl;

import com.alone.hotel.dao.WorkDao;
import com.alone.hotel.dto.WorkExecution;
import com.alone.hotel.entity.Work;
import com.alone.hotel.enums.WorkStateEnum;
import com.alone.hotel.exceptions.WorkException;
import com.alone.hotel.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.service.impl
 * @Author: Alone
 * @CreateTime: 2020-03-22 21:25
 * @Description:
 */
@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    private WorkDao workDao;

    @Override
    @Transactional
    public WorkExecution addWorkMessage(Work work) {
        if(work != null){
            //设置未打卡
            work.setStatus(0);
            try {
                int effectedNum = workDao.addWorkMessage(work);
                if(effectedNum <= 0){
                    throw new WorkException(WorkStateEnum.INNER_ERROR.getStateInfo());
                }
                return new WorkExecution(WorkStateEnum.SUCCESS);
            } catch (Exception e){
                throw new WorkException(WorkStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new WorkExecution(WorkStateEnum.EMPTY);
        }
    }

    @Override
    public WorkExecution queryWorkTimeById(String employeeId) {
        if(employeeId != null){
            try {
                List<Work> workList = workDao.queryWorkTimeById(employeeId);
                return new WorkExecution(WorkStateEnum.SUCCESS, workList);
            } catch (Exception e){
                throw new WorkException(WorkStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new WorkExecution(WorkStateEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public WorkExecution updateWorkTime(Work work) {
        if(work != null){
            try {
                int effectedNum = workDao.updateWorkTime(work);
                if(effectedNum <= 0){
                    throw new WorkException(WorkStateEnum.INNER_ERROR.getStateInfo());
                }
                return new WorkExecution(WorkStateEnum.SUCCESS, work);
            } catch (Exception e){
                throw new WorkException(WorkStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new WorkExecution(WorkStateEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public WorkExecution deleteWorkTime(String employeeId, Date workTime) {
        if(employeeId != null && workTime != null){
            try {
                int effectedNum = workDao.deleteWorkTime(employeeId, workTime);
                if(effectedNum <= 0){
                    throw new WorkException(WorkStateEnum.INNER_ERROR.getStateInfo());
                }
                return new WorkExecution(WorkStateEnum.SUCCESS);
            } catch (Exception e){
                throw new WorkException(WorkStateEnum.INNER_ERROR.getStateInfo());
            }
        } else {
            return new WorkExecution(WorkStateEnum.EMPTY);
        }
    }
}
