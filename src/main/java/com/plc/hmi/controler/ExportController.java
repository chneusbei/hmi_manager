package com.plc.hmi.controler;

import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.dal.entity.PressureDataEntity;
import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.service.PressureDataService;
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

    private static Logger logger = LoggerFactory.getLogger(ExportController.class);

    @ResponseBody
    @RequestMapping("/curveDataExport")
    public String export( //HttpServletResponse response,
                         @RequestParam(value = "startDate",required = false) String startDate,
                         @RequestParam(value = "endDate",required = false) String endDate) {
        startDate = null==startDate ? null : startDate.replace("-","");
        endDate =  null==endDate ? null : endDate.replace("-","");
        System.out.println("=======================startDate======="+startDate + "=======end====="+endDate);

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
            if (!headDir.exists()) {
                headDir.mkdir();
            }

            //获取单条曲线数据
            List<PressureCurveEntity> dataList = pressureCurveService.getHisDate(pressureDate.getRecordId(), pressureDate.getPressureHeadNo());

            if (CollectionUtils.isEmpty(dataList)) {
                continue;
            }
            // 设置导出文件名
            String fileName = "curveData_"+pressureDate.getRecordId()+"_"+pressureDate.getStartDate()+"_"+ ".csv";
            // 文件导出
            try {
//            OutputStream os = response.getOutputStream();
//            CsvExportUtil.responseSetProperties(fileName, response);
                CsvExportUtil.doExport(dataList,pressureDate, fileName, fileDir);
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
