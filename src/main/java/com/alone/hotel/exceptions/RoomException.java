package com.alone.hotel.exceptions;

import com.alone.hotel.enums.ResultEnum;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-09 19:41
 * @Description:
 */
public class RoomException extends RuntimeException{
    private Integer code;

    public RoomException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public RoomException(Integer code, String message){
        super(message);
        this.code = code;
    }
}
