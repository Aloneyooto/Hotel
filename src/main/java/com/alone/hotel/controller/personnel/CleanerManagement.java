package com.alone.hotel.controller.personnel;

import com.alone.hotel.dto.CleanerExecution;
import com.alone.hotel.entity.Cleaner;
import com.alone.hotel.enums.CleanerStateEnum;
import com.alone.hotel.service.CleanerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.controller.personnel
 * @Author: Alone
 * @CreateTime: 2020-03-16 22:52
 * @Description:
 */
@RestController
@RequestMapping("/personnel")
public class CleanerManagement {
    @Autowired
    private CleanerService cleanerService;

    @PostMapping("/addcleaner")
    private CleanerExecution addCleaner(@RequestParam("cleaner") Cleaner cleaner){
        if(cleaner != null && cleaner.getEmployee().getEmployeeId() != null){
            try{
                CleanerExecution pe = cleanerService.addCleaner(cleaner);
                if(pe.getState() == CleanerStateEnum.SUCCESS.getState()){
                    return new CleanerExecution(CleanerStateEnum.SUCCESS);
                } else {
                    return new CleanerExecution(CleanerStateEnum.INSERT_INNER_ERROR);
                }
            } catch (Exception e){
                return new CleanerExecution(CleanerStateEnum.INSERT_INNER_ERROR);
            }
        } else {
            return new CleanerExecution(CleanerStateEnum.EMPTY);
        }
    }

    @GetMapping("/querycleanerbyid")
    private CleanerExecution queryCleanerById(@RequestParam("cleanerId")String cleanerId){
        CleanerExecution cleanerExecution = null;
        if(cleanerId != null){
            Cleaner cleaner = cleanerService.queryCleanerById(cleanerId);
            cleanerExecution = new CleanerExecution(CleanerStateEnum.SUCCESS, cleaner);
        } else {
            cleanerExecution = new CleanerExecution(CleanerStateEnum.EMPTY);
        }
        return cleanerExecution;
    }

    @GetMapping("/querycleanerlist")
    private CleanerExecution queryCleanerList(@RequestParam("roomFloor")String roomFloor,
                                                @RequestParam("pageIndex")int pageIndex,
                                              @RequestParam("pageSize")int pageSize){
        if(pageIndex > -1 && pageSize > 0){
            try{
                CleanerExecution pe = cleanerService.queryCleanerList(roomFloor, pageIndex, pageSize);
                return pe;
            } catch (Exception e){
                return new CleanerExecution(CleanerStateEnum.INNER_ERROR);
            }
        } else {
            return new CleanerExecution(CleanerStateEnum.PAGE_ERROR);
        }
    }

    @PostMapping("/modifycleaner")
    private CleanerExecution modifyCleaner(@RequestParam("cleaner")Cleaner cleaner){
        if(cleaner != null && cleaner.getEmployee().getEmployeeId() != null){
            try{
                CleanerExecution cleanerExecution = cleanerService.updateCleaner(cleaner);
                return cleanerExecution;
            } catch (Exception e){
                return new CleanerExecution(CleanerStateEnum.INNER_ERROR);
            }
        } else {
            return new CleanerExecution(CleanerStateEnum.INNER_ERROR);
        }
    }

    @PostMapping("/deletecleaner")
    private CleanerExecution deleteCleaner(@RequestParam("cleanerId")String cleanerId){
        if(cleanerId != null){
            try{
                CleanerExecution cleanerExecution = cleanerService.deleteCleaner(cleanerId);
                return cleanerExecution;
            } catch (Exception e){
                return new CleanerExecution(CleanerStateEnum.INNER_ERROR);
            }
        } else {
            return new CleanerExecution(CleanerStateEnum.EMPTY);
        }
    }
}
