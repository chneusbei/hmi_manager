package com.plc.hmi.controler;

import com.plc.hmi.dal.entity.PressureCurveEntity;
import com.plc.hmi.service.PressureCurveService;
import com.plc.hmi.service.PressureProgramService;
import com.plc.hmi.util.CsvExportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ExportController {
    @Resource
    PressureCurveService pressureCurveService;
    @Resource
    PressureProgramService pressureProgramService;

    private static Logger logger = LoggerFactory.getLogger(ExportController.class);
    @GetMapping("/export")
    public String export(HttpServletResponse response,
                         @RequestParam(value = "startDate",required = false) String startDate,
                         @RequestParam(value = "endDate",required = false) String endDate) {

        // 查询需要导出的数据
        List<Map<String, Object>> dataList  = null;
//        List<PressureCurveEntity> dataList  = pressureCurveService.getHisDate(startDate, endDate);



        if (CollectionUtils.isEmpty(dataList)) {
            return   "无可用数据";
        }

        // 构造导出数据结构
        String titles = "组号,组名,时间";  // 设置表头
        String keys = "no,name,time";  // 设置每列字段

        // 构造导出数据
        List<Map<String, Object>> datas = new ArrayList<>();
        Map<String, Object> map = null;
        for (Map<String, Object> data : dataList) {
            map = new HashMap<>();
//            map.put("no", data.getNo());
//            map.put("name", data.getName());
//            map.put("time", data.getTime());
            datas.add(map);
        }

        // 设置导出文件前缀
        String fName = "data_";

        // 文件导出
        try {
            OutputStream os = response.getOutputStream();
            CsvExportUtil.responseSetProperties(fName, response);
            CsvExportUtil.doExport(dataList, titles, keys, os);
            os.close();
        } catch (Exception e) {
            logger.error("导出失败", e.getMessage());
            return  "导出失败";
        }
        return  "OK";
    }

}
