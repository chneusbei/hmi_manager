package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.TemperatureAlarmEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TemperatureAlarmMapper {

    @Select({"<script>",
            "select * from temperature_alarm where IS_DELETED='0' and HANDLE_DATE between  #{startDate} and #{endDate} ",
            "order by id asc",
            "</script>" })
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "HANDLE_DATE",property ="handleDate"),
            @Result(column = "TEMPERATURE_BATCH_ID",property ="temperatureBatchId"),
            @Result(column = "TEMPERATURE_NAME",property ="temperatureName"),
            @Result(column = "TEMPERATURE_VALUE",property ="temperatureValue"),
            @Result(column = "TEMPERATURE_WARNING_VALUE",property ="temperatureWarningValue"),
            @Result(column = "CREATE_TIME",property ="createTime"),
    })
    List<TemperatureAlarmEntity> getTemperatureAlarmWithParam(@Param("startDate") String startDate, @Param("endDate") String endDate);


    @Insert({"INSERT INTO temperature_alarm(ID, HANDLE_DATE, TEMPERATURE_BATCH_ID, TEMPERATURE_NAME, TEMPERATURE_VALUE, TEMPERATURE_WARNING_VALUE, CREATE_BY, UPDATE_BY, CREATE_TIME, UPDATE_TIME, IS_DELETED) values(null,#{entity.handleDate},#{entity.temperatureBatchId},#{entity.temperatureName},#{entity.temperatureValue}, #{entity.temperatureWarningValue}, #{entity.createBy}, #{entity.updateBy}, now(), now(), '0')"})
    void insert(@Param("entity") TemperatureAlarmEntity entity);

}
