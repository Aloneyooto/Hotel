package com.alone.hotel.exceptions;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-16 09:21
 * @Description:
 */
public class EmployeeException extends RuntimeException {

    private static final long serialVersionUID = 840729754594520102L;

    public EmployeeException(String message) {
        super(message);
    }
}
