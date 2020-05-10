package com.plc.hmi.controler;

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
//        System.out.println("=======================startDate======="+startDate + "=======end====="+endDate);
        startDate = null==startDate ? null : startDate.replace("-","");
        endDate =  null==endDate ? null : endDate.replace("-","");
//        System.out.println("=======================startDate======="+startDate + "=======end====="+endDate);


        // 查询需要导出的数据
        List<PressureCurveEntity> dataList  = pressureCurveService.getHisDate(startDate, endDate);

        //获取对应的pressure_data
        List<PressureDataEntity> pressureDateList = pressureDataService.getPressureData(startDate+"000000000", endDate+"999999999");
        java.util.concurrent.ConcurrentHashMap<String, PressureDataEntity> pressureDateMap = new  java.util.concurrent.ConcurrentHashMap<String, PressureDataEntity>();
        if(!CollectionUtils.isEmpty(pressureDateList)) {
            for(PressureDataEntity dataEntity: pressureDateList) {
                pressureDateMap.put(dataEntity.getRecordId(), dataEntity);
            }
        }

        if (CollectionUtils.isEmpty(dataList)) {
            return  "no datas found";
        }

//        String curveKeys = "ID, RECORD_ID, PRODUCT_ID, RECORD_NO, POSITION, PRESS_FORCE, CUR_SPEED, PRESS_DATE, HANDLE_DATE, CREATE_TIME";
//        String dataKeys = "ID, PRODUCT_ID, RECORD_ID, PRODUCT_NO, PRESS_RESULT, MAX_PRESS, POSITION_OF_MAX_PRESS, CREATE_BY,CREATE_TIME ";

        // 设置导出文件名
        String fileName = "curveData_"+(null==startDate?"#":startDate)+"_TO_"+(null==endDate?"#":endDate)+"_"+ HmiUtils.getMillFormatDateString(new Date())+ ".csv";

        // 文件导出
        try {
//            OutputStream os = response.getOutputStream();
//            CsvExportUtil.responseSetProperties(fileName, response);
            CsvExportUtil.doExport(dataList,pressureDateMap, fileName);
//            os.close();
        } catch (Exception e) {
            logger.error("导出失败", e.getStackTrace());
            e.printStackTrace();
            return  "导出失败";
        }
        return  "OK, export success to D:/csv/";
    }

}
