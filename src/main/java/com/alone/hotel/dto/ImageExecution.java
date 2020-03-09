package com.alone.hotel.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.dto
 * @Author: Alone
 * @CreateTime: 2020-03-09 20:14
 * @Description: 图片(不一定有用)
 */
@Data
public class ImageExecution {
    private MultipartFile file;
    //图片原文件名
    private String fileName;

    public ImageExecution(MultipartFile file, String fileName) {
        this.file = file;
        this.fileName = fileName;
    }
}
