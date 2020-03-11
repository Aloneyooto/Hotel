package com.alone.hotel.utils;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.utils
 * @Author: Alone
 * @CreateTime: 2020-03-10 15:16
 * @Description:
 */
public class PageUtil {
    /**
     * 页数转换成行数
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static int calculateRowIndex(int pageIndex, int pageSize){
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
