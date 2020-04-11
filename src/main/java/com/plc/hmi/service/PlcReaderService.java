package com.plc.hmi.service;

import com.plc.hmi.S7Connector.dto.PlcDataDto;
import com.plc.hmi.S7Connector.service.PlcConnectorService;
import com.plc.hmi.dal.entity.plc.PlcInfoResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlcReaderService {
    @Autowired
    PlcConnectorService plcConnectorService;
    /**
     * 获取PLC中监控信息
     *
     * @param
     * @return
     */
    public PlcInfoResponseEntity getPlcDataInfo() {
        PlcDataDto plcDataDto = plcConnectorService.getData();
        PlcInfoResponseEntity plcInfoResponseEntity = new PlcInfoResponseEntity();
        plcInfoResponseEntity.setAutoModel(true);
        return plcInfoResponseEntity;
    }
}
