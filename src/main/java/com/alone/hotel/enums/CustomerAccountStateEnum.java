package com.alone.hotel.enums;

import com.alone.hotel.entity.CustomerAccount;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-24 07:52
 * @Description:
 */
public enum CustomerAccountStateEnum {
    SUCCESS(1, "操作成功"),
    INNER_ERROR(-9001, "内部错误"),
    EMPTY(-9002, "账号信息为空"),
    ACCOUNT_NAME_EMPTY(-9003, "用户名为空"),
    NAME_OR_PASSWORD_ERROR(-9004, "用户名或密码错误"),
    NEW_PASSWORD_ERROR(-9005, "两次输入新密码不一致"),
    HEAD_IMAGE_ERROR(-9006, "头像上传失败"),
    ;

    private int state;
    private String stateInfo;

    private CustomerAccountStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static CustomerAccountStateEnum stateOf(int state){
        for (CustomerAccountStateEnum stateEnum : values()){
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
