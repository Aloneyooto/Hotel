package com.alone.hotel.controller.personnel;

import com.alone.hotel.dto.WorkExecution;
import com.alone.hotel.entity.Work;
import com.alone.hotel.enums.WorkStateEnum;
import com.alone.hotel.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.personnel
 * @Author: Alone
 * @CreateTime: 2020-03-22 21:45
 * @Description:
 */
@RestController
@RequestMapping("/personnel")
public class WorkMessageManagement {
    @Autowired
    private WorkService workService;

    @RequestMapping("/addworkmessage")
    private WorkExecution addWorkMessage(@RequestParam("work") Work work){
        if(work != null && work.getEmployee().getEmployeeId() != null){
            try{
                WorkExecution workExecution = workService.addWorkMessage(work);
                if(workExecution.getState() == WorkStateEnum.SUCCESS.getState()){
                    return new WorkExecution(WorkStateEnum.SUCCESS);
                } else {
                    return new WorkExecution(WorkStateEnum.INNER_ERROR);
                }
            } catch (Exception e){
                return new WorkExecution(WorkStateEnum.INNER_ERROR);
            }
        } else {
            return new WorkExecution(WorkStateEnum.EMPTY);
        }
    }

    @RequestMapping("/queryworktimebyid")
    private WorkExecution queryWorkTimeById(String employeeId){
        if(employeeId != null){
            try {
                WorkExecution workExecution = workService.queryWorkTimeById(employeeId);
                if(workExecution.getState() == WorkStateEnum.SUCCESS.getState()){
                    return workExecution;
                } else {
                    return new WorkExecution(WorkStateEnum.INNER_ERROR);
                }
            } catch (Exception e){
                return new WorkExecution(WorkStateEnum.INNER_ERROR);
            }
        } else {
            return new WorkExecution(WorkStateEnum.EMPTY);
        }
    }

    @RequestMapping("/updateworktime")
    private WorkExecution updateWorkTime(Work work){
        if(work != null && work.getEmployee().getEmployeeId() != null){
            try {
                WorkExecution workExecution = workService.updateWorkTime(work);
                if(workExecution.getState() == WorkStateEnum.SUCCESS.getState()){
                    return workExecution;
                } else {
                    return new WorkExecution(WorkStateEnum.INNER_ERROR);
                }
            } catch (Exception e){
                return new WorkExecution(WorkStateEnum.INNER_ERROR);
            }
        } else {
            return new WorkExecution(WorkStateEnum.EMPTY);
        }
    }

    @RequestMapping("/deleteworktime")
    private WorkExecution deleteWorkTime(String employeeId, Date workTime){
        if(employeeId != null && workTime != null){
            try {
                WorkExecution  workExecution = workService.deleteWorkTime(employeeId, workTime);
                return workExecution;
            } catch (Exception e){
                return new WorkExecution(WorkStateEnum.INNER_ERROR);
            }
        } else {
            return new WorkExecution(WorkStateEnum.EMPTY);
        }
    }
}
