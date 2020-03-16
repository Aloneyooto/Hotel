package com.alone.hotel.enums;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.enums
 * @Author: Alone
 * @CreateTime: 2020-03-14 22:19
 * @Description:
 */
public enum EmployeeStateEnum {
    SUCCESS(1, "操作成功"),
    INNER_ERROR(-1, "内部错误"),
    EMPTY(-2001, "员工属性为空"),
    EMPLOYEE_ID_ERROR(-2002, "工号错误"),
    PAGE_ERROR(-2003, "页码错误"),
    CARD_IMAGE_EMPTY(-2004, "证件图片为空"),
    FACE_IMAGE_EMPTY(-2005, "脸部图片为空"),
    INSERT_ERROR(-2006, "插入失败"),
    UPDATE_ERROR(-2007, "更新失败"),
    ;

    private int state;
    private String stateInfo;

    private EmployeeStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static EmployeeStateEnum stateOf(int state){
        for (EmployeeStateEnum stateEnum : values()){
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
