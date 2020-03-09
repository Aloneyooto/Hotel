package com.alone.hotel.entity;

import lombok.Data;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.entity
 * @Author: Alone
 * @CreateTime: 2020-03-09 09:06
 * @Description:
 */
@Data
public class EmployeeAccount {
    private String accountName;
    private String accountPassword;
    private String accountPower;
}
