package com.alone.hotel.exceptions;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.exceptions
 * @Author: Alone
 * @CreateTime: 2020-03-16 21:41
 * @Description:
 */
public class CleanerException extends RuntimeException {

    private static final long serialVersionUID = 1304589715735226728L;

    public CleanerException(String message) {
        super(message);
    }
}
