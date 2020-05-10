package com.plc.hmi.util;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PressureDataEntity;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CsvExportUtil {
    /**
     * @param dataList 集合数据
     */
    public static void doExport(List<PressureCurveEntity> dataList, java.util.concurrent.ConcurrentHashMap<String, PressureDataEntity> pressureDateMap, String fileName) throws Exception {
        // 设置导出文件temp名
        String tempName = fileName+ ".temp";
        File dir = new File(HmiConstants.CSV_OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //定义文件名格式并创建
        File csvFile = new File(HmiConstants.CSV_OUTPUT_PATH+File.separator+tempName);
        // UTF-8使正确读取分隔符","
        BufferedWriter csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                csvFile), "GBK"), 2048);

        // 组装压装信息表头
        String[] pressDataTitleArr = HmiConstants.DATA_TITLES.split(HmiConstants.COMMA);
        StringBuffer pressDateBuffer = new StringBuffer();
        for (String title : pressDataTitleArr) {
            pressDateBuffer.append(title).append(HmiConstants.COMMA);
        }
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);


        // 组装曲线表头
        String[] curveTitleArr = HmiConstants.CURVE_TITLES.split(HmiConstants.COMMA);
        StringBuffer curveTitleBuffer = new StringBuffer();
        for (String title : curveTitleArr) {
            curveTitleBuffer.append(title).append(HmiConstants.COMMA);
        }
        curveTitleBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);


        // 组装数据
        if (!CollectionUtils.isEmpty(dataList)) {
            for (PressureCurveEntity data : dataList) {
//                String curveTitles = "ID, RECORD_ID, PRODUCT_ID, RECORD_NO, POSITION, PRESS_FORCE, CUR_SPEED, HANDLE_DATE, CREATE_TIME";
               //组装pressDate信息
                PressureDataEntity pressDate = pressureDateMap.get(data.getRecordId());
                if (null != pressDate) {
                    csvFileOutputStream.write(pressDateBuffer.toString());
                    csvFileOutputStream.write(HmiUtils.getStringNoNull(pressDate.getId()));
                    csvFileOutputStream.write(HmiConstants.COMMA);
                    csvFileOutputStream.write(HmiUtils.getStringNoNull(pressDate.getProductId()));
                    csvFileOutputStream.write(HmiConstants.COMMA);
                    csvFileOutputStream.write(HmiUtils.getStringNoNull(pressDate.getRecordId()));
                    csvFileOutputStream.write(HmiConstants.COMMA);
                    csvFileOutputStream.write(HmiUtils.getStringNoNull(pressDate.getPressResult()));
                    csvFileOutputStream.write(HmiConstants.COMMA);
                    csvFileOutputStream.write(HmiUtils.getStringNoNull(pressDate.getMaxPress()));
                    csvFileOutputStream.write(HmiConstants.COMMA);
                    csvFileOutputStream.write(HmiUtils.getStringNoNull(pressDate.getPositionOfMaxPress()));
                    csvFileOutputStream.write(HmiConstants.COMMA);
                    csvFileOutputStream.write(HmiUtils.getStringNoNull(pressDate.getStartDate()));
                    csvFileOutputStream.write(HmiConstants.COMMA);
                    csvFileOutputStream.write(HmiUtils.getStringNoNull(pressDate.getEndDate()));
                    csvFileOutputStream.write(HmiConstants.COMMA);
                    csvFileOutputStream.write(HmiConstants.CSV_ROW_SEPARATOR);
                    csvFileOutputStream.write(curveTitleBuffer.toString());
                    pressureDateMap.remove(data.getRecordId());
                }
//                CURVE_TITLES = "ID, 压力曲线ID, 产品ID, 位置点序号, 位置, 压力, 速度, 压装时间点";
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getId()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getRecordId()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getProductId()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getRecordNo()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getPosition()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getPressForce()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getCurSpeed()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiUtils.getStringNoNull(HmiUtils.getMillFormatDateString(data.getCreateTime())));
                csvFileOutputStream.write(HmiConstants.COMMA);

                //换行
                csvFileOutputStream.newLine();
            }
        }
        // 写文件
        csvFileOutputStream.flush();
        csvFileOutputStream.close();
        csvFile.renameTo(new File(HmiConstants.CSV_OUTPUT_PATH+File.separator+fileName));
    }

    /**
     * 设置Header
     *
     * @param fileName
     * @param response
     * @throws UnsupportedEncodingException
     */
    public static void responseSetProperties(String fileName, HttpServletResponse response) throws UnsupportedEncodingException {
        // 读取字符编码
        String utf = "UTF-8";

        // 设置响应
        response.setContentType("application/ms-txt.numberformat:@");
        response.setCharacterEncoding(utf);
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=30");
        response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, utf));
    }

}
