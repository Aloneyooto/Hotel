package com.alone.hotel.exceptions;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-18 20:24
 * @Description:
 */
public class EmployeeAccountException extends RuntimeException {

    private static final long serialVersionUID = 6039316007633682399L;

    public EmployeeAccountException(String message) {
        super(message);
    }
}
