package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.PressureProgramEntity;
import com.plc.hmi.dal.mapper.PressureProgramMapper;
import com.plc.hmi.enumeration.PressureProgramEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PressureProgramDao {
    @Resource
    PressureProgramMapper pressureProgramMapper;

    public PressureProgramEntity getWithProgramCode(String programCode) {
        return pressureProgramMapper.getWithProgramCode(programCode);
    }

    public Map<Long, PressureProgramEntity> getAllDatas() {
        List<PressureProgramEntity> programList = pressureProgramMapper.getAllDatas();
        if(CollectionUtils.isEmpty(programList)) {
            return null;
        }
        Map<Long, PressureProgramEntity> programMap = new HashMap<Long, PressureProgramEntity>();

        for(PressureProgramEntity program : programList) {
            programMap.put(program.getProductId(), program);
        }
        return programMap;
    }

    public void insert(PressureProgramEntity entity) {
        pressureProgramMapper.insert(entity);
    }

    public void update(PressureProgramEntity entity) {
        pressureProgramMapper.update(entity);
    }

    public void delete(PressureProgramEntity entity) {
        pressureProgramMapper.delete(entity);
    }



}
