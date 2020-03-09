package com.alone.hotel.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.utils
 * @Author: Alone
 * @CreateTime: 2020-03-09 17:09
 * @Description:
 */
public class PathUtil {
    /**
     * 获取存放路径的前缀部分
     * @return
     */
    public static String getImgBasePath(){
        //获取操作系统的名称
        String os = System.getProperty("os.name");
        String basePath = "";
        if(os.toLowerCase().startsWith("win")){
            basePath = "E:/proresources/images/hotel";
        } else {
            basePath = "/home/alone/hotel/image/upload";
        }
        try {
            basePath = URLDecoder.decode(basePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return basePath;
    }

    /**
     * 获取房间图片上传路径
     * @param roomId
     * @return
     */
    public static String getRoomImagePath(int roomId){
        String imagePath = "/upload/images/item/room/" + roomId + "/";
        try {
            imagePath = URLDecoder.decode(imagePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return imagePath;
    }
}
