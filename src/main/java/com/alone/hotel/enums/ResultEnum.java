package com.alone.hotel.enums;

import lombok.Getter;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-09 19:39
 * @Description: 返回给前端的消息
 */
@Getter
public enum ResultEnum {
    ROOM_INSERT_ERROR(-10, "房间信息插入错误"),
    ROOM_IMAGE_ERROR(-11, "房间图片插入失败"),
    ROOM_UPDATE_ERROR(-12, "房间信息更新失败"),
    ROOM_IMG_DELETE_ERROR(-13, "房间图片删除失败"),
    ROOM_DELETE_ERROR(-14, "房间信息删除失败"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
