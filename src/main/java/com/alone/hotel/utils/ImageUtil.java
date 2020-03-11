package com.alone.hotel.utils;

import com.alone.hotel.dto.ImageExecution;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.utils
 * @Author: Alone
 * @CreateTime: 2020-03-09 16:56
 * @Description: 图片上传
 */
public class ImageUtil {
    //设置时间格式
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();

    /**
     * 上传图片
     * @param file
     * @param targetAddr
     * @return
     */
    public static String uploadImage(MultipartFile file, String targetAddr) throws IOException {
        //生成新的随机名
        String realName = getRandomFileName();
        //获取文件扩展名
        String extension = getFileExtension(file.getName());
        //创建目标路径
        makeDirPath(targetAddr);
        //获取文件存储的相对路径
        String relativeAddr = targetAddr + realName + extension;
        //解决乱码问题
        try {
            relativeAddr = URLDecoder.decode(relativeAddr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //文件要保存到的路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        file.transferTo(dest);
        return relativeAddr;
    }

    /**
     * 生成新文件名
     * @return
     */
    public static String getRandomFileName(){
        // 获取随机的五位数
        int rannum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + rannum;
    }

    /**
     * 获取输入文件流的扩展名
     *
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 创建目标路径所涉及到的目录，即/home/work/xiangze/xxx.jpg, 那么 home work xiangze
     * 这三个文件夹都得自动创建
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        try {
            realFileParentPath = URLDecoder.decode(realFileParentPath, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }

    /**
     * storePath是文件的路径还是目录的路径， 如果storePath是文件路径则删除该文件，
     * 如果storePath是目录路径则删除该目录下的所有文件
     *
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }
}
