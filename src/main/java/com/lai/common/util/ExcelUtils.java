package com.lai.common.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: Excel工具包
 * @Author: laixues
 * @Date: 2021/2/4
 */
public class ExcelUtils {

    /**
     * 处理Excel文件数据信息并返回其(按枚举标准)数据信息数组和操作信息
     *
     * @param inputStream     文件流
     * @param excelType       文件类型
     * @param enumNameMap     枚举字段对象
     * @param enumRequiresMap 枚举必填标准
     * @return map {dataList: excel文件里的信息数组, optionData: 操作信息}
     * @throws IOException
     */
    public static Map<String, Object> handleExcelInfo(InputStream inputStream, String excelType, Map<String, Object> enumNameMap, Map<String, Object> enumRequiresMap) throws IOException {

        Map<String, Object> resultMap = new HashMap<>(); // 最终数据
        Map<String, Object> optionData = new HashMap<>(); // 操作数据
        List<Map<String, Object>> optionErrList = new ArrayList<>(); // 操作错误数据信息
        List<Map> dataList = new ArrayList<Map>(); // excel数据信息
        Integer optionSuccessCount = 0;
        Integer optionErrCount = 0;

        Workbook workbook = null;// 工作簿对象
        Cell cell = null;// 单元格对象
        // 获取Excel文件对象
        if (excelType.equalsIgnoreCase("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (excelType.equalsIgnoreCase("xlsx") || excelType.equalsIgnoreCase("csv")) {
            workbook = new XSSFWorkbook(inputStream);
        }

        // 获取文件的指定工作表，默认的第一个
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null || sheet.getLastRowNum() < 1) {
            return new HashMap<>();
        }
        Map headMap = new HashMap();

        for (int i = 0; i < 1; i++) {
            // 创建一个数组，用来存储每一列的值
            String[] strArray = new String[sheet.getRow(i).getPhysicalNumberOfCells()];
            for (int j = 0; j < strArray.length; j++) {
                // 获取第i行，第j列的值
                cell = sheet.getRow(i).getCell(j);
                strArray[j] = cell.getStringCellValue();
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(strArray[j]);
                strArray[j] = m.replaceAll("");
                headMap.put(j, strArray[j]);
            }
        }

        int allRowNum = sheet.getLastRowNum(); // 最后一行的下标
        //int allRowNum2 = sheet.getPhysicalNumberOfRows(); // 总行数

        for (int i = 1; i <= allRowNum; i++) { // 表头的目录不需要，从1开始

            Map map = new HashMap(); // 当前行excel的数据
            Map<String, Object> errData = new HashMap<>(); // 当前行错误信息
            StringBuilder errMessageSb = new StringBuilder(); // 当前行错误信息
            StringBuilder errLocationSb = new StringBuilder("第" + (i + 1) + "行: "); // 当前行错误信息

            // int cellCount = sheet.getRow(i).getPhysicalNumberOfCells(); // 某行的总列数(如果当前行的后面列为空,则不会统计)
            int cellCount = sheet.getRow(0).getPhysicalNumberOfCells(); // 某行的总列数(固定用表头的第一行的总列数)

            for (int j = 0; j < cellCount; j++) {

                // 获取第i行，第j列的值
                cell = sheet.getRow(i).getCell(j);
                String cellValue = "";

                if (cell != null) {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC: // 数字类型
                            if (DateUtil.isCellDateFormatted(cell)) { // 日期数字类型
                                cellValue = DateUtils.format(cell.getDateCellValue(), "yyyy-MM-dd HH:mm:ss"); // 日期
                            } else { // 普通数字类型
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cellValue = cell.getStringCellValue();
                            }
                            break;
                        case Cell.CELL_TYPE_STRING: // 字符类型
                            cellValue = cell.getStringCellValue();
                            break;
                        case Cell.CELL_TYPE_FORMULA: // 公式类型
                            cellValue = cell.getCellFormula();
                            break;
                        case Cell.CELL_TYPE_BOOLEAN: // 布尔类型
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_ERROR: // 错误类型
                            cellValue = String.valueOf(cell.getErrorCellValue());
                            break;
                    }
                }

                String headItem = String.valueOf(headMap.get(j)); // 当前格子对应的头部中文标题
                String enumName = String.valueOf(enumNameMap.get(headItem)); // 字段
                String requires = String.valueOf(enumRequiresMap.get(enumName)); // 字段必填

                if (StringUtils.isBlank(cellValue) && requires.equals("1")) { // 验证数据/字段必填
                    errMessageSb.append(headItem).append("为空").append(",");
                    errLocationSb.append("第").append(j + 1).append("列").append("、");
                    errData.put("errorMessage", "");
                    errData.put("errorLocation", "");
                    continue;
                }
                map.put(enumName, cellValue);
            }

            if (!errData.isEmpty()) {
                String errorMessage = errMessageSb.toString();
                String errorLocation = errLocationSb.toString();
                errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
                errorLocation = errorLocation.substring(0, errorLocation.length() - 1);
                errData.put("errorMessage", errorMessage);
                errData.put("errorLocation", errorLocation);
                optionErrList.add(errData);
                optionErrCount++;
                continue;
            }
            dataList.add(map); // 存储当前行excel的数据
            optionSuccessCount++;

        }

        optionData.put("optionSuccessCount", optionSuccessCount);
        optionData.put("optionErrCount", optionErrCount);
        optionData.put("optionErrList", optionErrList);
        resultMap.put("dataList", dataList);
        resultMap.put("optionData", optionData);

        return resultMap;
    }


}
