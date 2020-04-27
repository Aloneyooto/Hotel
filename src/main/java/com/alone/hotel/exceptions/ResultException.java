package com.alone.hotel.exceptions;

import com.alone.hotel.enums.ResultEnum;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-04-26 16:44
 * @Description:
 */
public class ResultException extends RuntimeException {
    private Integer code;

    private static final long serialVersionUID = 8790222848757265368L;

    public ResultException(String message) {
        super(message);
    }

    public ResultException(ResultEnum resultEnum){
        super(resultEnum.getStateInfo());
        this.code = resultEnum.getState();
    }
}
