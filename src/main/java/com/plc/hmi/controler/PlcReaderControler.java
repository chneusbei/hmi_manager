package com.plc.hmi.controler;

import com.plc.hmi.dal.entity.plc.PlcInfoResponseEntity;
import com.plc.hmi.service.PlcReaderService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.*;


@RestController
@RequestMapping("/plc/plcReader")
@Validated
public class PlcReaderControler {
    private final Log logger = LogFactory.getLog(PlcReaderControler.class);
    @Autowired
    PlcReaderService plcReaderService;


    /**
     * PLC下位机监控数据读取
     *
     * @return 返回
     */

    @ApiOperation(value = "PLC下位机监控数据读取", notes = "plc下位机中信息实时读取，用于监控显示")
    @GetMapping("/plcDataInfo")
    @ApiImplicitParams({ })
//    public Object getPlcDataInfo(@ApiIgnore PlcInfoQueryEntity plcInfoQueryEntity) throws Exception{
    public Object getPlcDataInfo() throws Exception{
        logger.info("开始PLC下位机监控数据读取...");
        PlcInfoResponseEntity plcInfoResponseEntity = plcReaderService.getPlcDataInfo();
        logger.info("PLC下位机监控数据读取完成, plcInfoResponseEntity:"+plcInfoResponseEntity.toString());
        return plcInfoResponseEntity;
    }
}
