package com.lai.common.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 上传工具类
 * @Author: laixues
 * @Date: 2020/12/11
 */
public class UploadUtils {

    /**
     * 根据源文件和上传参数 处理 文件上传并返回文件信息
     *
     * @param files        文件源
     * @param locationPath 存放绝对路径
     * @param packagesPath 存放目标模块目录路径/相对路径
     * @param filePathKey  指定路径key 如 path
     * @param fileNameKey  指定文件名key 如 name
     * @param fileTypeKey  指定文件类型key 如 type
     * @param fileTimeKey  指定上传时间key 如 time
     * @return
     */
    public static List<Map<String, Object>> getUploadInfoList(
            List<MultipartFile> files,
            String locationPath,
            String packagesPath,
            String fileNameKey,
            String filePathKey,
            String fileTypeKey,
            String fileTimeKey
    ) {

        List<Map<String, Object>> fileList = new ArrayList<>();

        if (!files.isEmpty()) {
            try {
                for (MultipartFile file : files) {

                    Map<String, Object> fileMap = new HashMap<>(); // 存储数据map

                    String oFilename = file.getOriginalFilename(); // 原文件名(包括类型)
                    String fileType = oFilename.substring(oFilename.lastIndexOf(".")).toLowerCase(); // 获取文件类型
                    String fileName = oFilename.substring(0, oFilename.lastIndexOf(".")); // 原文件名
                    String onlyFileName = StringUtils.getRandomFileName() + fileType; // 唯一值的文件名
                    String timePath = DateUtils.getDateTime("yyyyMMdd") + "/";

                    String path = locationPath + packagesPath + timePath + onlyFileName;
                    File dir = new File(locationPath + packagesPath + timePath);
                    //创建文件夹
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    File newFile = new File(path);
                    FileUtils.copyInputStreamToFile(file.getInputStream(), newFile);

                    fileMap.put(fileNameKey, fileName);
                    // fileMap.put(fileNameKey, fileName + fileType);
                    fileMap.put(filePathKey, packagesPath + timePath + onlyFileName);
                    fileMap.put(fileTypeKey, fileType.substring(1));
                    fileMap.put(fileTimeKey, DateUtils.getDateTime());
                    fileList.add(fileMap);
                }

            }catch (Exception e){
                e.printStackTrace();
                // LogUtils.error("文件处理错误!", e);
            }
        }

        return fileList;
    }


    /**
     * 判断文件路径的文件是否存在
     * @param filePath
     * @return
     */
    public static boolean isExist(String filePath) {
        filePath = filePath.replace("\\","/");
        String paths[] = filePath.split("/");
        String dir = paths[0];
        for (int i = 0; i < paths.length - 1; i++) {//注意此处循环的长度
            try {
                dir = dir + "/" + paths[i + 1];
                File dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                    //System.out.println("创建目录为：" + dir);
                }
            } catch (Exception err) {
                //System.err.println("文件夹创建发生异常");
            }
        }
        File fp = new File(filePath);
        if(!fp.exists()){
            return true; // 文件不存在，执行下载功能
        }else{
            return false; // 文件存在不做处理
        }
    }


}
