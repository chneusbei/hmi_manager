package com.plc.hmi.util;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.dal.entity.TemperatureEntity;
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
     *  导出温度监控数据文件
     */
    public static void doTemperatureExport(List<TemperatureEntity> TemperatureEntityList,
                                           String fileName, String path) throws Exception {
        // 设置导出文件temp名
        String tempName = fileName+ ".temp";

        //定义文件名格式并创建
        File csvFile = new File(path+HmiConstants.SEPARATE+tempName.trim());
//        System.out.println("csvFile path = "+(path+HmiConstants.SEPARATE+tempName.trim()));
        // UTF-8使正确读取分隔符","
        BufferedWriter csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                csvFile), "GBK"), 2048);

        // 组装曲线表头
        String[] curveTitleArr = HmiConstants.TEMPERATURE_CURVE_TITLES;
        StringBuffer curveTitleBuffer = new StringBuffer();
        for (String title : curveTitleArr) {
            curveTitleBuffer.append(title).append(HmiConstants.COMMA);
        }
        curveTitleBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);
        // 组装数据
        if (!CollectionUtils.isEmpty(TemperatureEntityList)) {
            int i=0;
            for (TemperatureEntity data : TemperatureEntityList) {
                //Index
                csvFileOutputStream.write(String.valueOf(i++));
                //BLANK
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getPlcIp()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getHandleDate()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getStatus()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getLowSpeedAxisEccentricCopperSleeveTemperature1().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getLowSpeedAxisEccentricCopperSleeveTemperature2().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getLowSpeedAxisEccentricCopperSleeveTemperature3().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getLowSpeedAxisEccentricCopperSleeveTemperature4().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getHighSpeedAxisEccentricCopperSleeveTemperature1().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getHighSpeedAxisEccentricCopperSleeveTemperature2().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getFlywheelSupportBigAxisTemperature1().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getFlywheelSupportBigAxisTemperature2().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getDrawbarBothEndsCopperSleeveTemperature1().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getDrawbarBothEndsCopperSleeveTemperature2().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getDrawbarBothEndsCopperSleeveTemperature3().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getDrawbarBothEndsCopperSleeveTemperature4().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getDrawbarBothEndsCopperSleeveTemperature5().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getDrawbarBothEndsCopperSleeveTemperature6().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getDrawbarBothEndsCopperSleeveTemperature7().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getDrawbarBothEndsCopperSleeveTemperature8().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getSmallBeltWheelSupportAxisTemperature1().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getSmallBeltWheelSupportAxisTemperature2().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getTripodBottomCopperSleeveTemperature1().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getTripodBottomCopperSleeveTemperature2().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getTripodBottomCopperSleeveTemperature3().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getTripodBottomCopperSleeveTemperature4().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getTripodEccentricBigCopperSleeveTemperature1().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getTripodEccentricBigCopperSleeveTemperature2().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getTripodEccentricBigCopperSleeveTemperature3().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getTripodEccentricBigCopperSleeveTemperature4().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature0().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature1().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature2().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature3().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature4().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature5().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature6().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature7().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature8().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature9().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature10().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getBackupTemperature11().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getTemperatureWarningValue1().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(data.getTemperatureWarningValue2().toString());
                csvFileOutputStream.write(HmiConstants.COMMA);

                //换行
                csvFileOutputStream.newLine();
            }
        }
        // 写文件
        csvFileOutputStream.flush();
        csvFileOutputStream.close();
        File realFile = new File(path+File.separator+fileName);
        if(realFile.exists()) {
            realFile.delete();
        }
        csvFile.renameTo(new File(path+File.separator+fileName));
    }


    /**
     * @param dataList 集合数据
     */
    public static void doExport(List<PressureCurveEntity> dataList, PressureDataEntity  pressureDataEntity,
                                String fileName, String path) throws Exception {
        // 设置导出文件temp名
        String tempName = fileName+ ".temp";

        //定义文件名格式并创建
        File csvFile = new File(path+HmiConstants.SEPARATE+tempName.trim());
//        System.out.println("csvFile path = "+(path+HmiConstants.SEPARATE+tempName.trim()));
        // UTF-8使正确读取分隔符","
        BufferedWriter csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                csvFile), "GBK"), 2048);

        /*
        // 组装压装信息表头
        String[] pressDataTitleArr = HmiConstants.DATA_TITLES.split(HmiConstants.COMMA);
        StringBuffer pressDateBuffer = new StringBuffer();
        for (String title : pressDataTitleArr) {
            pressDateBuffer.append(title).append(HmiConstants.COMMA);
        }
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);
    */
        //组装压装信息
        StringBuffer pressDateBuffer = new StringBuffer();
        pressDateBuffer.append("ProgramID").append(HmiConstants.COMMA);
        pressDateBuffer.append("32").append(HmiConstants.COMMA);
        pressDateBuffer.append("CurveVersion").append(HmiConstants.COMMA);
        pressDateBuffer.append("K100").append(HmiConstants.COMMA);
        pressDateBuffer.append("SoftwareVersion").append(HmiConstants.COMMA);
        pressDateBuffer.append("5.1.20 2.83").append(HmiConstants.COMMA);
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);
        pressDateBuffer.append("ProgramName").append(HmiConstants.COMMA);
        pressDateBuffer.append("Bew_N1").append(HmiConstants.COMMA);
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);
        pressDateBuffer.append("DateTime").append(HmiConstants.COMMA);
        pressDateBuffer.append(HmiUtils.getFormatDateString(pressureDataEntity.getCreateTime())).append(HmiConstants.COMMA);
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);
        pressDateBuffer.append("XAxisUnit").append(HmiConstants.COMMA);
        pressDateBuffer.append("mm").append(HmiConstants.COMMA);
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);
        pressDateBuffer.append("YAxisUnit").append(HmiConstants.COMMA);
        pressDateBuffer.append("kN").append(HmiConstants.COMMA);
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);
        pressDateBuffer.append("Tolerance Windows").append(HmiConstants.COMMA);
        //逗号 转义 &#44
        pressDateBuffer.append("\""+"2,180480,252160,8959,20000,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0," +
                "-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0," +
                "-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0," +
                "-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0," +
                "-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0," +
                "-1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0," +
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,128,0,400000,0,30000,0,4,0,250000,0,0,0,0,0,0,0," +
                "0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0," +
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0," +
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0," +
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0," +
                "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0"+"\"").append(HmiConstants.COMMA);
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);
        pressDateBuffer.append("ProductSN").append(HmiConstants.COMMA);
        pressDateBuffer.append(pressureDataEntity.getRecordId()).append(HmiConstants.COMMA);
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);
        pressDateBuffer.append("ComponentSN").append(HmiConstants.COMMA);
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);
        pressDateBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);

        // 组装曲线表头
        String[] curveTitleArr = HmiConstants.CURVE_TITLES.split(HmiConstants.COMMA);
        StringBuffer curveTitleBuffer = new StringBuffer();
        //四个空格
        curveTitleBuffer.append(HmiConstants.COMMA).append(HmiConstants.COMMA).append(HmiConstants.COMMA).append(HmiConstants.COMMA);
        for (String title : curveTitleArr) {
            curveTitleBuffer.append(title).append(HmiConstants.COMMA);
        }
        curveTitleBuffer.append(HmiConstants.CSV_ROW_SEPARATOR);

        //写两个头信息
        csvFileOutputStream.write(pressDateBuffer.toString());
        csvFileOutputStream.write(curveTitleBuffer.toString());

        // 组装数据
        if (!CollectionUtils.isEmpty(dataList)) {
            int i=0;
            for (PressureCurveEntity data : dataList) {
                //四个空格
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiConstants.COMMA);
                /*
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
                */
//                CURVE_TITLES = "ID, 压力曲线ID, 产品ID, 位置点序号, 位置, 压力, 速度, 压装时间点";
                //Index
                csvFileOutputStream.write(String.valueOf(i++));
                //BLANK
                csvFileOutputStream.write(HmiConstants.COMMA);
                csvFileOutputStream.write(HmiConstants.COMMA);
                //Position
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getPosition()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                //Force
                csvFileOutputStream.write(HmiUtils.getStringNoNull(data.getPressForce()));
                csvFileOutputStream.write(HmiConstants.COMMA);
                //Analog
                csvFileOutputStream.write("0");
                csvFileOutputStream.write(HmiConstants.COMMA);
                //Time
                csvFileOutputStream.write("0");
                csvFileOutputStream.write(HmiConstants.COMMA);
                //Speed
                csvFileOutputStream.write("0");
                csvFileOutputStream.write(HmiConstants.COMMA);
                /*
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
                */
                //换行
                csvFileOutputStream.newLine();
            }
        }
        // 写文件
        csvFileOutputStream.flush();
        csvFileOutputStream.close();
        File realFile = new File(path+File.separator+fileName);
        if(realFile.exists()) {
            realFile.delete();
        }
        csvFile.renameTo(new File(path+File.separator+fileName));
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
