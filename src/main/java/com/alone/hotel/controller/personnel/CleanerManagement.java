package com.alone.hotel.controller.personnel;

import com.alone.hotel.dto.CleanerExecution;
import com.alone.hotel.entity.Cleaner;
import com.alone.hotel.enums.ResultEnum;
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
    private CleanerExecution addCleaner(@RequestBody Cleaner cleaner){
        if(cleaner != null && cleaner.getEmployee().getEmployeeId() != null){
            try{
                CleanerExecution pe = cleanerService.addCleaner(cleaner);
                if(pe.getState() == ResultEnum.SUCCESS.getState()){
                    return new CleanerExecution(ResultEnum.SUCCESS);
                } else {
                    return new CleanerExecution(ResultEnum.INNER_ERROR);
                }
            } catch (Exception e){
                return new CleanerExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new CleanerExecution(ResultEnum.EMPTY);
        }
    }

    @GetMapping("/querycleanerbyid")
    private CleanerExecution queryCleanerById(@RequestParam("cleanerId")String cleanerId){
        CleanerExecution cleanerExecution = null;
        if(cleanerId != null){
            Cleaner cleaner = cleanerService.queryCleanerById(cleanerId);
            cleanerExecution = new CleanerExecution(ResultEnum.SUCCESS, cleaner);
        } else {
            cleanerExecution = new CleanerExecution(ResultEnum.EMPTY);
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
                return new CleanerExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new CleanerExecution(ResultEnum.PAGE_ERROR);
        }
    }

    @PostMapping("/updatecleaner")
    private CleanerExecution updateCleaner(@RequestBody Cleaner cleaner){
        if(cleaner != null && cleaner.getEmployee().getEmployeeId() != null){
            try{
                CleanerExecution cleanerExecution = cleanerService.updateCleaner(cleaner);
                return cleanerExecution;
            } catch (Exception e){
                return new CleanerExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new CleanerExecution(ResultEnum.INNER_ERROR);
        }
    }

    @PostMapping("/deletecleaner")
    private CleanerExecution deleteCleaner(@RequestBody String cleanerId){
        if(cleanerId != null){
            try{
                CleanerExecution cleanerExecution = cleanerService.deleteCleaner(cleanerId);
                return cleanerExecution;
            } catch (Exception e){
                return new CleanerExecution(ResultEnum.INNER_ERROR);
            }
        } else {
            return new CleanerExecution(ResultEnum.EMPTY);
        }
    }
}
