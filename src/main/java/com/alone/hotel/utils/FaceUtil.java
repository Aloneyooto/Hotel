package com.alone.hotel.utils;

import com.alone.hotel.entity.Customer;
import com.arcsoft.face.*;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.Executors;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

/**
 * @BelongsProject: hotel
 * @BelongsPackage: com.alone.hotel.utils
 * @Author: Alone
 * @CreateTime: 2020-04-03 00:21
 * @Description:
 */
public class FaceUtil {
    // TODO 提交到github上的时候记得换掉数据库账号和密码

    private static final String appId = "C7gdeP7N47L9f7njWAYsi3JjPG1eGr43aDqRkY8qe1bc";

    private static String sdkKey;

    private static FaceEngine faceEngine;

    private static int errorCode;

    private static Map<String, FaceFeature> faceStoreMap;

    private static FunctionConfiguration configuration;

    /**
     * 获取引擎文件路径
     * @return
     */
    public static String getEnginePath(){
        //获取操作系统的名称
        String os = System.getProperty("os.name");
        String enginePath = "";
        if(os.toLowerCase().startsWith("win")){
            sdkKey = "9mbcQt4jDW4HihSQcN4fBx1gRzycGkt3Zh7VaCg83ejM";
            enginePath = "E:\\Alone\\ArcSoft_ArcFace_Java_Windows_x64_V3.0\\libs\\WIN64";
        } else {
            sdkKey = "9mbcQt4jDW4HihSQcN4fBx1gHtQUR3TWtGtLi2W8wvAj";
            //根据linux上路径的改变
            enginePath = "/root/hotel/ArcSoft_ArcFace_Java_Linux_x64_V3.0/libs/LINUX64";
        }
        return enginePath;
    }

    public static void initEngine(){
        faceEngine = new FaceEngine(getEnginePath());
        //激活引擎
        errorCode = faceEngine.activeOnline(appId, sdkKey);

        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }


        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("获取激活文件信息失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        engineConfiguration.setDetectFaceMaxNum(10);
        engineConfiguration.setDetectFaceScaleVal(16);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);

        //设置活体测试
        errorCode = faceEngine.setLivenessParam(0.5f, 0.7f);
        //人脸属性检测
        configuration = new FunctionConfiguration();
        configuration.setSupportAge(true);
        configuration.setSupportFace3dAngle(true);
        configuration.setSupportGender(true);
        configuration.setSupportLiveness(true);

        //初始化引擎
        errorCode = faceEngine.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }
    }

    public static void destoryEngine(){
        //引擎卸载
        errorCode = faceEngine.unInit();
    }

    public static void getDataSoureFeature(List<Customer> customerList){
        faceStoreMap = new HashMap<String, FaceFeature>();
        //图片根路径
        String imgBasePath = PathUtil.getImgBasePath();
        //获取库内所有图片的特征
        for (Customer customer : customerList) {
            if(customer != null && customer.getCustomerFaceImg() != null){
                String imgUrl = imgBasePath + customer.getCustomerFaceImg();
                FaceFeature faceFeature = getFaceFeature(imgUrl);
                faceStoreMap.put(customer.getCustomerCardNumber(), faceFeature);
            }
        }
        System.out.println("人脸信息数量:" + faceStoreMap.keySet().size());
    }

    public static FaceFeature getFaceFeature(String imgUrl){
        //人脸检测
        ImageInfo imageInfo = getRGBData(new File(imgUrl));
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
        if(errorCode != 0){
            System.out.println("人脸检测失败");
        }
        //特征提取
        FaceFeature faceFeature = new FaceFeature();
        int errorFlag = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
        if(errorFlag != 0){
            System.out.println("人脸录入失败");
        }
        return faceFeature;
    }

    /**
     * 人脸比较
     * @param file
     * @return
     */
    public static String compareFaces(File file){
        //TODO 可能需要MultipartFile转换为file
        ImageInfo imageInfo = null;
        List<FaceInfo> faceInfoList = null;
        FaceFeature newFaceFeature = null;
        FaceFeature sourceFaceFeature = null;
        FaceSimilar faceSimilar = null;

        if(file != null){
            //人脸检测
            imageInfo = getRGBData(file);
            faceInfoList = new ArrayList<FaceInfo>();
            errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
        }
        if(imageInfo != null && faceInfoList != null){
            //特征提取
            newFaceFeature = new FaceFeature();
            errorCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), newFaceFeature);
        }
        if(newFaceFeature != null){
            sourceFaceFeature = new FaceFeature();
            sourceFaceFeature.setFeatureData(newFaceFeature.getFeatureData());
            faceSimilar = new FaceSimilar();
        }
        Set<String> keySet = faceStoreMap.keySet();
        for (String s : keySet) {
            FaceFeature oldFaceFeature = faceStoreMap.get(s);
            //特征比对
            FaceFeature targetFaceFeature = new FaceFeature();
            targetFaceFeature.setFeatureData(oldFaceFeature.getFeatureData());
            errorCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);
            if(faceSimilar.getScore() >= 0.9){
                return s;
            }
        }
        return null;
    }

    /**
     * 是否是活体
     * @return
     */
    public static Boolean isAlive(ImageInfo imageInfo, List<FaceInfo> faceInfoList){
        errorCode = faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList, configuration);
        //活体检测
        List<LivenessInfo> livenessInfoList = new ArrayList<LivenessInfo>();
        errorCode = faceEngine.getLiveness(livenessInfoList);
        if(livenessInfoList.get(0).getLiveness() == 1){
            return true;
        } else {
            return false;
        }
    }

    /**
     * MultipartFile 转 File
     *
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void example() {

        //从官网获取
        String appId = "C7gdeP7N47L9f7njWAYsi3JjPG1eGr43aDqRkY8qe1bc";
        String sdkKey = "9mbcQt4jDW4HihSQcN4fBx1gRzycGkt3Zh7VaCg83ejM";


        FaceEngine faceEngine = new FaceEngine("E:\\Alone\\ArcSoft_ArcFace_Java_Windows_x64_V3.0\\libs\\WIN64");
        //激活引擎
        int errorCode = faceEngine.activeOnline(appId, sdkKey);

        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("引擎激活失败");
        }


        ActiveFileInfo activeFileInfo=new ActiveFileInfo();
        errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
        if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
            System.out.println("获取激活文件信息失败");
        }

        //引擎配置
        EngineConfiguration engineConfiguration = new EngineConfiguration();
        engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
        engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
        engineConfiguration.setDetectFaceMaxNum(10);
        engineConfiguration.setDetectFaceScaleVal(16);
        //功能配置
        FunctionConfiguration functionConfiguration = new FunctionConfiguration();
        functionConfiguration.setSupportAge(true);
        functionConfiguration.setSupportFace3dAngle(true);
        functionConfiguration.setSupportFaceDetect(true);
        functionConfiguration.setSupportFaceRecognition(true);
        functionConfiguration.setSupportGender(true);
        functionConfiguration.setSupportLiveness(true);
        functionConfiguration.setSupportIRLiveness(true);
        engineConfiguration.setFunctionConfiguration(functionConfiguration);


        //初始化引擎
        errorCode = faceEngine.init(engineConfiguration);

        if (errorCode != ErrorInfo.MOK.getValue()) {
            System.out.println("初始化引擎失败");
        }


        //人脸检测
        ImageInfo imageInfo = getRGBData(new File("E:\\she said\\life\\yjs3.jpg"));
        List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
        errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
        System.out.println(faceInfoList);

        //特征提取
        FaceFeature faceFeature = new FaceFeature();
        errorCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

        //人脸检测2
        ImageInfo imageInfo2 = getRGBData(new File("E:\\she said\\life\\yjs4.jpg"));
        List<FaceInfo> faceInfoList2 = new ArrayList<FaceInfo>();
        errorCode = faceEngine.detectFaces(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(),imageInfo.getImageFormat(), faceInfoList2);
        System.out.println(faceInfoList);

        //特征提取2
        FaceFeature faceFeature2 = new FaceFeature();
        errorCode = faceEngine.extractFaceFeature(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(), imageInfo.getImageFormat(), faceInfoList2.get(0), faceFeature2);
        System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

        //特征比对
        FaceFeature targetFaceFeature = new FaceFeature();
        targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
        FaceFeature sourceFaceFeature = new FaceFeature();
        sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
        FaceSimilar faceSimilar = new FaceSimilar();

        errorCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);

        System.out.println("相似度：" + faceSimilar.getScore());

        //设置活体测试
        errorCode = faceEngine.setLivenessParam(0.5f, 0.7f);
        //人脸属性检测
        FunctionConfiguration configuration = new FunctionConfiguration();
        configuration.setSupportAge(true);
        configuration.setSupportFace3dAngle(true);
        configuration.setSupportGender(true);
        configuration.setSupportLiveness(true);
        errorCode = faceEngine.process(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList, configuration);


        //性别检测
        List<GenderInfo> genderInfoList = new ArrayList<GenderInfo>();
        errorCode = faceEngine.getGender(genderInfoList);
        System.out.println("性别：" + genderInfoList.get(0).getGender());

        //年龄检测
        List<AgeInfo> ageInfoList = new ArrayList<AgeInfo>();
        errorCode = faceEngine.getAge(ageInfoList);
        System.out.println("年龄：" + ageInfoList.get(0).getAge());

        //3D信息检测
        List<Face3DAngle> face3DAngleList = new ArrayList<Face3DAngle>();
        errorCode = faceEngine.getFace3DAngle(face3DAngleList);
        System.out.println("3D角度：" + face3DAngleList.get(0).getPitch() + "," + face3DAngleList.get(0).getRoll() + "," + face3DAngleList.get(0).getYaw());

        //活体检测
        List<LivenessInfo> livenessInfoList = new ArrayList<LivenessInfo>();
        errorCode = faceEngine.getLiveness(livenessInfoList);
        System.out.println("活体：" + livenessInfoList.get(0).getLiveness());


        //引擎卸载
        errorCode = faceEngine.unInit();

    }
}


