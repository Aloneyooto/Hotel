package com.alone.hotel.exceptions;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-24 08:37
 * @Description:
 */
public class CustomerAccountException extends RuntimeException {

    private static final long serialVersionUID = 8658968546381161469L;

    public CustomerAccountException(String message) {
        super(message);
    }
}
