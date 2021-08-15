package com.plc.hmi.controler;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.dal.entity.TemperatureEntity;
import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.service.PressureDataService;
import com.plc.hmi.service.TemperatureService;
import com.plc.hmi.service.plcService.Plc4xTemperatureService;
import com.plc.hmi.util.CsvExportUtil;
import com.plc.hmi.util.HmiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

@RestController
public class ExportController {
    @Resource
    PressureCurveService pressureCurveService;
    @Resource
    PressureDataService pressureDataService;
    @Resource
    TemperatureService temperatureService;

    private static Logger logger = LoggerFactory.getLogger(ExportController.class);
    @ResponseBody
    @RequestMapping("/temperatureExport")
    public String export( @RequestParam(value = "plcName",required = false) String plcName,
                          @RequestParam(value = "status",required = false) String status,
                          @RequestParam(value = "startDate",required = false) String startDate,
                          @RequestParam(value = "endDate",required = false) String endDate) {
        startDate = null==startDate ? null : startDate.replace("-","");
        endDate = null==endDate ? null : endDate.replace("-","");
//        System.out.println("=======================startDate======="+startDate + "=======end====="+endDate);

        //获取对应的pressure_data
        List<TemperatureEntity> TemperatureEntityList = temperatureService.getTemperatureWithParam(startDate, endDate, plcName, status);;
        if (CollectionUtils.isEmpty(TemperatureEntityList)) {
            return  "temperature export no data found !";
        }


        File dir = new File(HmiConstants.CSV_OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //创建压头文件夹
        String fileDir = HmiConstants.CSV_OUTPUT_PATH
                + HmiConstants.TEMPERATURE_CSV_OUTPUT_PATH_;
        File headDir = new File(fileDir);

        if (!headDir.exists()) {
            headDir.mkdir();
        }
/*
            //创建OK，NOK包
            File dirTemperatureOK = new File(fileDir+HmiConstants.CSV_OUTPUT_PATH_OK);
            if(!dirTemperatureOK.exists()){
                dirTemperatureOK.mkdir();
            }
            File dirTemperatureNOK = new File(fileDir+HmiConstants.CSV_OUTPUT_PATH_NOK);
            if(!dirTemperatureNOK.exists()){
                dirTemperatureNOK.mkdir();
            }
*/
        // 设置导出文件名
        StringBuffer sb = new StringBuffer();
        sb.append("temperature_")
                .append(plcName)
                .append("_")
                .append(startDate)
                .append("_")
                .append(endDate)
                .append(HmiUtils.getFormatDateString())
                .append(".csv");
        String fileName = sb.toString();
        String tempFileDir = fileDir;

        // 文件导出
        try {
//            OutputStream os = response.getOutputStream();
//            CsvExportUtil.responseSetProperties(fileName, response);
            CsvExportUtil.doTemperatureExport(TemperatureEntityList,fileName, tempFileDir);
//            os.close();
        } catch (Exception e) {
            logger.error("导出失败", e.getStackTrace());
            e.printStackTrace();
            return  "导出失败";
        }

        return  "OK, export success to D:/csv/temperature";
    }

    @ResponseBody
    @RequestMapping("/curveDataExport")
    public String export_bak( //HttpServletResponse response,
                         @RequestParam(value = "startDate",required = false) String startDate,
                         @RequestParam(value = "endDate",required = false) String endDate) {
        startDate = null==startDate ? null : startDate.replace("-","");
        endDate =  null==endDate ? null : endDate.replace("-","");
//        System.out.println("=======================startDate======="+startDate + "=======end====="+endDate);

        //获取对应的pressure_data
        List<PressureDataEntity> pressureDateList = pressureDataService.getPressureData(startDate+"000000000", endDate+"999999999");
        if (CollectionUtils.isEmpty(pressureDateList)) {
            return  "no datas found";
        }
        //        java.util.concurrent.ConcurrentHashMap<String, PressureDataEntity> pressureDateMap = new  java.util.concurrent.ConcurrentHashMap<String, PressureDataEntity>();
//        if(!CollectionUtils.isEmpty(pressureDateList)) {
//            for(PressureDataEntity dataEntity: pressureDateList) {
//                pressureDateMap.put(dataEntity.getRecordId(), dataEntity);
//            }
//        }

        // 查询需要导出的数据
//        List<PressureCurveEntity> dataList  = pressureCurveService.getHisDate(startDate, endDate);
//        String curveKeys = "ID, RECORD_ID, PRODUCT_ID, RECORD_NO, POSITION, PRESS_FORCE, CUR_SPEED, PRESS_DATE, HANDLE_DATE, CREATE_TIME";
//        String dataKeys = "ID, PRODUCT_ID, RECORD_ID, PRODUCT_NO, PRESS_RESULT, MAX_PRESS, POSITION_OF_MAX_PRESS, CREATE_BY,CREATE_TIME ";

        /**
         *  1.每条曲线一个文件
         * 2.不同的压头在不同的文件夹
         */
        File dir = new File(HmiConstants.CSV_OUTPUT_PATH);
        if (!dir.exists()) {
            dir.mkdir();
        }

        for(PressureDataEntity pressureDate : pressureDateList) {
            //创建压头文件夹
            String fileDir = HmiConstants.CSV_OUTPUT_PATH
                    + HmiConstants.CSV_PRESSURE_HEAD_PATH_PREFIX
                    + pressureDate.getPressureHeadNo();
            File headDir = new File(fileDir);
            String fileDirPositive = fileDir+HmiConstants.CSV_OUTPUT_PATH_POSITIVE;
            String fileDirNegative = fileDir+HmiConstants.CSV_OUTPUT_PATH_NEGATIVE;

            if (!headDir.exists()) {
                headDir.mkdir();
            }

            //创建正压反压分包
            File dirPositive = new File(fileDirPositive);
            File dirNegative = new File(fileDirNegative);
            if(!dirPositive.exists()){
                dirPositive.mkdir();
            }

            if(!dirNegative.exists()){
                dirNegative.mkdir();
            }

            //创建OK，NOK包
            File dirPositiveOK = new File(fileDirPositive+HmiConstants.CSV_OUTPUT_PATH_OK);
            if(!dirPositiveOK.exists()){
                dirPositiveOK.mkdir();
            }
            File dirPositiveNOK = new File(fileDirPositive+HmiConstants.CSV_OUTPUT_PATH_NOK);
            if(!dirPositiveNOK.exists()){
                dirPositiveNOK.mkdir();
            }
            File dirNegativeOK = new File(fileDirNegative+HmiConstants.CSV_OUTPUT_PATH_OK);
            if(!dirNegativeOK.exists()){
                dirNegativeOK.mkdir();
            }
            File dirNegativeNOK = new File(fileDirNegative+HmiConstants.CSV_OUTPUT_PATH_NOK);
            if(!dirNegativeNOK.exists()){
                dirNegativeNOK.mkdir();
            }



            //获取单条曲线数据
            List<PressureCurveEntity> dataList = pressureCurveService.getHisDate(pressureDate.getRecordId(), pressureDate.getPressureHeadNo());

            if (CollectionUtils.isEmpty(dataList)) {
                continue;
            }
            // 设置导出文件名
            String traceCode = dataList.get(0).getTraceCode();
            traceCode = traceCode == null ? "":traceCode.trim() ;
//            String fileName = "curveData_"+pressureDate.getRecordId()+"_"+pressureDate.getStartDate()+"_"+ traceCode+".csv";
            StringBuffer sb = new StringBuffer();
            sb.append("curveData_")
                    .append(pressureDate.getRecordId())
                    .append("_")
                    .append(pressureDate.getStartDate())
                    .append("_")
                    .append(traceCode)
                    .append(".csv");
            String fileName = sb.toString();
            // 文件导出
            try {
//            OutputStream os = response.getOutputStream();
//            CsvExportUtil.responseSetProperties(fileName, response);
                String tempFileDir = fileDir;
                if(!CollectionUtils.isEmpty(dataList)) {
                    PressureCurveEntity curveEntity1 = dataList.get(0);
                    if(curveEntity1.getPressFlag() == 1) {
                        //正压
                        tempFileDir = fileDirPositive;
                    } else {
                        //反压
                        tempFileDir = fileDirNegative;
                    }
                    //判断曲线是否是成功
                    if("1".equalsIgnoreCase(pressureDate.getPressResult())) {
                        //成功
                        tempFileDir = tempFileDir + HmiConstants.CSV_OUTPUT_PATH_OK;
                    } else {
                        //失败
                        tempFileDir = tempFileDir + HmiConstants.CSV_OUTPUT_PATH_NOK;
                    }
                }
                CsvExportUtil.doExport(dataList,pressureDate, fileName, tempFileDir);
//            os.close();
            } catch (Exception e) {
                logger.error("导出失败", e.getStackTrace());
                e.printStackTrace();
                return  "导出失败";
            }



        }


        return  "OK, export success to D:/csv/";
    }

}
