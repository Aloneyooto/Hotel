package com.alone.hotel.exceptions;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-30 08:52
 * @Description:
 */
public class RoomTypeException extends RuntimeException{

    private static final long serialVersionUID = -6983819191664104899L;

    public RoomTypeException(String message) {
        super(message);
    }
}
