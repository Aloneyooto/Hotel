package com.alone.hotel.enums;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-23 20:17
 * @Description:
 */
public enum CustomerStateEnum {
    SUCCESS(1, "操作成功"),
    INNER_ERROR(-8001, "内部错误"),
    CARD_IMAGE_EMPTY(-8002, "证件图片为空"),
    FACE_IMAGE_EMPTY(-8003, "面部图片为空"),
    BASIC_MESSAGE_ERROR(-8004, "基本信息不合法"),
    FILE_INSERT_ERROR(-8005, "文件插入出错"),
    ID_CARD_EMPTY(-8006, "身份证号为空"),
    CERTIFICATION_ERROR(-8007, "实名认证失败"),
    RELATION_INSERT_ERROR(-8008, "关联信息添加失败"),
    RELATION_DELETE_ERROR(-8009, "关联信息删除失败"),
    ;

    private int state;
    private String stateInfo;

    private CustomerStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static CustomerStateEnum stateOf(int state){
        for (CustomerStateEnum stateEnum : values()){
            if(stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
