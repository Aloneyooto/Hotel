package com.alone.hotel.enums;

import com.alone.hotel.entity.EmployeeAccount;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-18 15:13
 * @Description:
 */
public enum EmployeeAccountStateEnum {
    SUCCESS(1, "操作成功"),
    INNER_ERROR(-5001, "内部错误"),
    EMPLOYEE_ACCOUNT_EMPTY(-5002, "账号信息为空"),
    EMPLOYEE_ACCOUNT_NAME_ERROR(-5003, "用户名错误"),
    EMPLOYEE_ACCOUNT_ERROR(-5004, "账号信息错误"),
    OLD_PASSWORD_ERROR(-5005, "旧密码错误"),
    NEW_PASSWORD_ERROR(-5006, "两次输入密码不一致"),
    ;

    private int state;
    private String stateInfo;

    private EmployeeAccountStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static EmployeeAccountStateEnum stateOf(int state){
        for (EmployeeAccountStateEnum stateEnum : values()){
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
