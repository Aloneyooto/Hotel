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
    private static final String cardUrl = "/card/";
    private static final String faceUrl = "/face/";

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
            basePath = "/root/hotel/";
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

    /**
     * 获取头像图片上传路径
     * @param accountName
     * @return
     */
    public static String getHeadImagePath(String accountName){
        String imagePath = "/upload/images/item/head/" + accountName + "/";
        try {
            imagePath = URLDecoder.decode(imagePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return imagePath;
    }

    /**
     * 获取用户图片上传路径
     * @param id ID
     * @param imageType 图片类型
     * @param personType 用户类型
     * @return
     */
    public static String getPersonImagePath(String id, int imageType, int personType){
        String imagePath = "/upload/images/item/";
        if(personType == 1){
            //员工
            imagePath += "employee";
        } else if(personType == 0){
            //顾客
            imagePath += "customer";
        }
        if(imageType == 1){
            //证件图片
            imagePath += cardUrl;
        } else if (imageType == 0){
            //面部图片
            imagePath += faceUrl;
        }
        imagePath += id + "/";
        try {
            imagePath = URLDecoder.decode(imagePath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return imagePath;
    }
}
