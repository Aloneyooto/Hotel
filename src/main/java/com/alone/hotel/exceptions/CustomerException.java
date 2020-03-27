package com.alone.hotel.exceptions;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-23 20:28
 * @Description:
 */
public class CustomerException extends RuntimeException {

    private static final long serialVersionUID = -3448928103260426400L;

    public CustomerException(String message) {
        super(message);
    }
}
