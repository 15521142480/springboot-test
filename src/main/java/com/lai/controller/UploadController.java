package com.lai.controller;

import com.lai.common.enums.SysCarBlackListEnum;
import com.lai.common.util.DateUtils;
import com.lai.common.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description: 上传文件模块
 * @Author: laixues
 * @Date: 2020/12/4
 */
@RestController
@Api(tags = "上传文件模块模块")
@RequestMapping(value = "pc/upload")
public class UploadController {


    @Value("${static.web.images-path}")
    private String webImagePath;

    private static final String modulePackages = "xxx";


    /**
     * 上传文件
     * @return
     */
    @PostMapping("/file")
    @ApiOperation(value = "上传文件", notes = "上传")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userid", value = "app用户编号"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "page", value = "查询页数"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "pageSize", value = "查询条数"),
    })
    public String uploadImgList(){
        return "文件名";
    }


    @PostMapping("/image") // , headers = "content-type=multipart/form-data"
    @ApiOperation(value = "上传图片", notes = "上传")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userId", value = "app用户编号"),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "files", value = "文件流集合"),
    })
    public Object uploadFile(HttpServletRequest request, @RequestParam Map<String, Object> map) throws IOException {

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> dataMap = new HashMap<>();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
        String userId = String.valueOf(map.get("userId"));
        String userName = String.valueOf(map.get("userName"));
        String dirPath = "";
        String filePath = "";

        try {
            for (MultipartFile file : files){

                String originalFilename = file.getOriginalFilename();
                dirPath = webImagePath +
                        modulePackages + File.separator +
                        DateUtils.getDateTime("yyyyMMdd");
                filePath = dirPath + File.separator + originalFilename;
                File dir = new File(dirPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File imageFile = new File(filePath);
                file.transferTo(imageFile);
                // FileCopyUtils.copy(file.getBytes(), imageFile);

            }
            dataMap.put("userId", userId);
            dataMap.put("userName", userName);
            dataMap.put("filePath", filePath);
            resultMap.put("resultCode", "0");
            resultMap.put("resultMsg", "保存成功");
            resultMap.put("data", dataMap);
        } catch (IOException e) {
            resultMap.put("resultCode", "300");
            resultMap.put("resultMsg", "上传文件异常");
        }catch (Exception e){
            resultMap.put("resultCode", "300");
            resultMap.put("resultMsg", "系统繁忙");
        }
        return resultMap;
    }


    /**
     * 工作计划导入(excel内容读取)
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @PostMapping("/import")
    @ResponseBody
    @ApiOperation(value = "工作计划导入", notes = "新增")
    @ApiImplicitParams({
            // @ApiImplicitParam(paramType = "query", dataType = "String", name = "file", value = "file")
    })
    public Object readExcelUpload(MultipartFile file, HttpServletRequest request) throws IOException {

        Map<String, Object> parametersMap = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();

        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        Iterator<String> itr = multipartHttpServletRequest.getFileNames();
        MultipartFile mpf = null;

        while (itr.hasNext()) {
            mpf = multipartHttpServletRequest.getFile(itr.next());
            InputStream excelInputStream = mpf.getInputStream();

            // 判断上传的文件的格式是否符合要求
            int lastIndexOf = mpf.getOriginalFilename().lastIndexOf('.');
            String fileSuffix = mpf.getOriginalFilename().substring(lastIndexOf + 1, mpf.getOriginalFilename().length());
            if (fileSuffix.equalsIgnoreCase("xlsx")
                    || fileSuffix.equalsIgnoreCase("xls")
                    || fileSuffix.equalsIgnoreCase("csv")) {
                List<Map> list = new ArrayList<>();
                try {

                    parametersMap.put("exceltype", fileSuffix);

                    Map<String, Object> optionResultMap = new HashMap<>();
                    String exceltype = String.valueOf(parametersMap.get("exceltype"));
                    Map<String, Object> enumMap = SysCarBlackListEnum.getSysCarBlackListNameByMap();
                    Map<String, Object> requiresMap = SysCarBlackListEnum.getSysCarBlackListRequiresByMap();
                    optionResultMap = ExcelUtils.handleExcelInfo(excelInputStream, exceltype, enumMap, requiresMap); // excel内容读取

                    System.out.println(optionResultMap);

                    resultMap.put("resultCode", 0);
                    resultMap.put("resultMsg", "文件上传成功");

                } catch (Exception e) {
                    e.printStackTrace();
                    resultMap.put("resultCode", 0);
                    resultMap.put("resultMsg", "文件内容不符合要求，请检查。");
                } finally {
                    excelInputStream.close();
                }
            } else {
                resultMap.put("resultCode", 0);
                resultMap.put("resultMsg", "文件格式不对，请检查！");
            }
        }
        return resultMap;
    }



}
