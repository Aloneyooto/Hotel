package com.alone.hotel.exceptions;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-29 14:54
 * @Description:
 */
public class OrderException extends RuntimeException {

    private static final long serialVersionUID = 7161996832834185073L;

    public OrderException(String message) {
        super(message);
    }
}
