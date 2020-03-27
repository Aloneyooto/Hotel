package com.alone.hotel.exceptions;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-24 08:55
 * @Description:
 */
public class CustomerRelationException extends RuntimeException {

    private static final long serialVersionUID = -4313213705626955242L;

    public CustomerRelationException(String message) {
        super(message);
    }
}
