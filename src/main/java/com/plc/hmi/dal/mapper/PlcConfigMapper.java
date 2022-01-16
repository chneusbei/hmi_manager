package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.PlcConfigEntity;
import com.plc.hmi.dal.entity.PropertyEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PlcConfigMapper {
    @Select("select * from plc_config where is_deleted='0'")
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "PLC_NAME",property ="plcName"),
            @Result(column = "PLC_SERVER_IP",property ="plcServerIp"),
            @Result(column = "PLC_SERVER_ROCK",property ="plcServerRock"),
            @Result(column = "PLC_SERVER_SLOT",property ="plcServerSlot"),
            @Result(column = "CREATE_BY",property = "createBy"),
            @Result(column = "UPDATE_BY",property = "updateBy"),
            @Result(column = "CREATE_TIME",property = "createTime"),
            @Result(column = "UPDATE_TIME",property = "updateTime"),
            @Result(column = "IS_DELETED",property = "isDeleted"),
    })
    List<PlcConfigEntity> getPlcConfigList();

    @Insert({"insert into plc_config(id, PLC_NAME, PLC_SERVER_IP, PLC_SERVER_ROCK, PLC_SERVER_SLOT, IS_DELETED, CREATE_BY, UPDATE_BY,CREATE_TIME,UPDATE_TIME) values(null, #{plcName}, #{plcServerIp}, #{plcServerRock}, #{plcServerSlot},'0', #{createBy}, #{createBy}, now(), now())"})
    void insert(PlcConfigEntity entity);

    @Update({"update plc_config set PLC_NAME=#{plcName},PLC_SERVER_IP=#{plcServerIp}, PLC_SERVER_ROCK=#{plcServerRock},PLC_SERVER_SLOT=#{plcServerSlot},UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void update(PlcConfigEntity entity);

    @Update({"update plc_config set  IS_DELETED='1', UPDATE_BY= #{updateBy}, UPDATE_TIME=now() where ID=#{id}"})
    void delete(PlcConfigEntity entity);
}
