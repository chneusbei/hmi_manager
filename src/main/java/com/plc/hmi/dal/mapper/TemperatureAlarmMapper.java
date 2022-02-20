package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.TemperatureAlarmEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TemperatureAlarmMapper {

    @Select({"<script>",
            "select * from temperature_alarm where HANDLE_DATE between  #{startDate} and #{endDate} and LINE_TYPE = #{lineType}  and  IS_DELETED='0' ",
            "order by id asc",
            "</script>" })
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "HANDLE_DATE",property ="handleDate"),
            @Result(column = "LINE_TYPE",property ="lineType"),
            @Result(column = "TEMPERATURE_ID",property ="temperatureId"),
            @Result(column = "TEMPERATURE_NAME",property ="temperatureName"),
            @Result(column = "TEMPERATURE_VALUE",property ="temperatureValue"),
            @Result(column = "TEMPERATURE_WARNING_VALUE",property ="temperatureWarningValue"),
            @Result(column = "CREATE_TIME",property ="createTime"),
    })
    List<TemperatureAlarmEntity> getTemperatureAlarmWithParam(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("lineType") String lineType);


    @Insert({"INSERT INTO temperature_alarm(ID, HANDLE_DATE,LINE_TYPE, TEMPERATURE_ID, TEMPERATURE_NAME, TEMPERATURE_VALUE, TEMPERATURE_WARNING_VALUE, CREATE_BY, UPDATE_BY, CREATE_TIME, UPDATE_TIME, IS_DELETED) values(null,#{entity.handleDate},#{entity.lineType},#{entity.temperatureId},#{entity.temperatureName},#{entity.temperatureValue}, #{entity.temperatureWarningValue}, #{entity.createBy}, #{entity.updateBy}, now(), now(), '0')"})
    void insert(@Param("entity") TemperatureAlarmEntity entity);

}
