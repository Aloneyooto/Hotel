package com.alone.hotel.controller.superadmin;

import com.alone.hotel.dto.RecreationExecution;
import com.alone.hotel.entity.Recreation;
import com.alone.hotel.enums.RecreationStateEnum;
import com.alone.hotel.service.RecreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.superadmin
 * @Author: Alone
 * @CreateTime: 2020-03-17 21:05
 * @Description:
 */
@RestController
@RequestMapping("/superadmin")
public class RecreationManagement {
    @Autowired
    private RecreationService recreationService;

    @PostMapping("/addrecreation")
    private RecreationExecution addRecreation(@RequestParam("recreation") Recreation recreation){
        if(recreation != null && recreation.getRecreationId() != null){
            try{
                RecreationExecution pe = recreationService.insertRecreation(recreation);
                if(pe.getState() == RecreationStateEnum.SUCCESS.getState()){
                    return new RecreationExecution(RecreationStateEnum.SUCCESS);
                } else {
                    return new RecreationExecution(RecreationStateEnum.INNER_ERROR);
                }
            } catch (Exception e){
                return new RecreationExecution(RecreationStateEnum.INNER_ERROR);
            }
        } else {
            return new RecreationExecution(RecreationStateEnum.RECREATION_EMPTY);
        }
    }

    @GetMapping("/queryrecreationbyid")
    private RecreationExecution queryRecreationById(@RequestParam("recreationId")int recreationId){
        RecreationExecution recreationExecution = null;
        if(recreationId > 0){
            try {
                Recreation recreation = recreationService.queryRecreationById(recreationId);
                recreationExecution = new RecreationExecution(RecreationStateEnum.SUCCESS, recreation);
            } catch (Exception e){
                recreationExecution = new RecreationExecution(RecreationStateEnum.INNER_ERROR);
            }
        } else {
            recreationExecution = new RecreationExecution(RecreationStateEnum.RECREATION_ID_ERROR);
        }
        return recreationExecution;
    }

    @GetMapping("/queryrecreationlist")
    private RecreationExecution queryRecreationList(@RequestParam("recreationName")String recreationName,
                                                    @RequestParam("pageIndex")int pageIndex,
                                                    @RequestParam("pageSize")int pageSize){
        Recreation recreationCondition = new Recreation();
        recreationCondition.setRecreationName(recreationName);
        if(pageIndex > 0 && pageSize > 0){
            try{
                RecreationExecution pe = recreationService.queryRecreationList(recreationCondition, pageIndex, pageSize);
                return pe;
            } catch (Exception e){
                return new RecreationExecution(RecreationStateEnum.INNER_ERROR);
            }
        } else {
            return new RecreationExecution(RecreationStateEnum.PAGE_ERROR);
        }
    }

    @PostMapping("/modifyrecreation")
    private RecreationExecution modifyRecreation(@RequestParam("recreation")Recreation recreation){
        if(recreation != null && recreation.getRecreationId() != null){
            try{
                RecreationExecution recreationExecution = recreationService.modifyRecreation(recreation);
                return recreationExecution;
            } catch (Exception e){
                return new RecreationExecution(RecreationStateEnum.INNER_ERROR);
            }
        } else {
            return new RecreationExecution(RecreationStateEnum.RECREATION_EMPTY);
        }
    }

    @PostMapping("/deleterecreation")
    private RecreationExecution deleteRecreation(@RequestParam("recreationId")int recreationId){
        if(recreationId > 0){
            try{
                RecreationExecution recreationExecution = recreationService.deleteRecreation(recreationId);
                return recreationExecution;
            } catch (Exception e){
                return new RecreationExecution(RecreationStateEnum.INNER_ERROR);
            }
        } else {
            return new RecreationExecution(RecreationStateEnum.RECREATION_ID_ERROR);
        }
    }
}
