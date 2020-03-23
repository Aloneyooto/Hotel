package com.alone.hotel.exceptions;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-22 21:32
 * @Description:
 */
public class WorkException extends RuntimeException {

    private static final long serialVersionUID = -4090466154964934367L;

    public WorkException(String message) {
        super(message);
    }
}
