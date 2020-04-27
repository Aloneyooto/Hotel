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
    SUCCESS(1, "操作成功"),
    EMPTY(-1001, "信息为空"),
    INNER_ERROR(-1002, "内部错误"),
    CACHE_WRITE_ERROR(-1003, "缓存写入错误"),
    TOKEN_EMPTY(-1004, "token为空"),
    FILE_INSERT_ERROR(-1005, "文件插入出错"),
    PAGE_ERROR(-1006, "页码错误"),

    OLD_PASSWORD_ERROR(-1011, "旧密码错误"),
    NEW_PASSWORD_ERROR(-1012, "两次输入的密码不一致"),
    ACCOUNT_EMPTY(-1013, "账号信息为空"),
    ADD_HEADIMG_ERROR(-1014, "添加头像错误"),
    CERTIFICATION_ERROR(-1015, "实名认证失败"),
    CARD_IMAGE_EMPTY(-1016, "证件图片为空"),
    FACE_IMAGE_EMPTY(-1017, "面部图片为空"),
    RELATION_INSERT_ERROR(-1018, "关联信息添加失败"),
    RELATION_DELETE_ERROR(-1019, "关联信息删除失败"),
    CLEANER_DELETE_ERROR(-1020, "清洁员信息删除错误"),
    CREATE_ACCOUNT_ERROR(-1021, "创建账号失败"),
    ROOM_LACKING(-1022, "房间数量不足"),
    CHECK_IN_INSERT_ERROR(-1023, "房间关联信息插入失败"),
    CUSTOMER_EMPTY(-1024, "入住人信息为空"),
    ROOM_INSERT_ERROR(-1025, "房间信息插入错误"),
    ROOM_IMAGE_ERROR(-1026, "房间图片插入失败"),
    ROOM_UPDATE_ERROR(-1027, "房间信息更新失败"),
    ROOM_IMG_DELETE_ERROR(-1028, "房间图片删除失败"),
    ROOM_DELETE_ERROR(-1029, "房间信息删除失败"),
    ROOM_ID_ERROR(-1030, "房间号错误"),
    POSITION_TYPE_ERROR(-1031, "员工类别不合法"),
    ID_ERROR(-1032, "ID错误"),
    INVENTORY_UPDATE_ERROR(-1033, "库存更新失败"),
    ;
    private Integer state;
    private String stateInfo;

    private ResultEnum(Integer state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }
}
