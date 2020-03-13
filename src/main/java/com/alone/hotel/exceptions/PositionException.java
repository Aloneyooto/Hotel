package com.alone.hotel.exceptions;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-13 14:37
 * @Description:
 */
public class PositionException extends RuntimeException {

    private static final long serialVersionUID = -1054774089490837178L;

    public PositionException(String message) {
        super(message);
    }
}
