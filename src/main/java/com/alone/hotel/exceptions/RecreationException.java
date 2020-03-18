package com.alone.hotel.exceptions;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-17 20:32
 * @Description:
 */
public class RecreationException extends RuntimeException {

    private static final long serialVersionUID = 270700061009745928L;

    public RecreationException(String message) {
        super(message);
    }
}
