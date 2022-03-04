package com.plc.hmi.dal.mapper;

import com.plc.hmi.dal.entity.TemperatureAlarmEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TemperatureAlarmMapper {

    @Select({"<script>",
            "select * from temperature_alarm where HANDLE_DATE between  #{startDate} and #{endDate} and LINE_TYPE = #{lineType}  and  IS_DELETED='0' ",
            "order by id desc ",
            "<if test='limit>0' >",
            " limit #{limit}",
            "</if>",
            "</script>" })
    @Results({
            @Result(id=true,column = "ID",property = "id" ),
            @Result(column = "PLC_NAME",property ="plcName"),
            @Result(column = "IS_WIRELESS",property ="isWireless"),
            @Result(column = "HANDLE_DATE",property ="handleDate"),
            @Result(column = "LINE_TYPE",property ="lineType"),
            @Result(column = "TEMPERATURE_ID",property ="temperatureId"),
            @Result(column = "TEMPERATURE_NAME",property ="temperatureName"),
            @Result(column = "TEMPERATURE_VALUE",property ="temperatureValue"),
            @Result(column = "TEMPERATURE_WARNING_VALUE",property ="temperatureWarningValue"),
            @Result(column = "CREATE_TIME",property ="createTime"),
    })
    List<TemperatureAlarmEntity> getTemperatureAlarmWithParam(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("lineType") String lineType, @Param("limit") int limit);


    @Insert({"INSERT INTO temperature_alarm(ID,PLC_NAME,IS_WIRELESS, HANDLE_DATE,LINE_TYPE, TEMPERATURE_ID, TEMPERATURE_NAME, TEMPERATURE_VALUE, TEMPERATURE_WARNING_VALUE, CREATE_BY, UPDATE_BY, CREATE_TIME, UPDATE_TIME, IS_DELETED) values(null,#{entity.plcName},#{entity.isWireless},#{entity.handleDate},#{entity.lineType},#{entity.temperatureId},#{entity.temperatureName},#{entity.temperatureValue}, #{entity.temperatureWarningValue}, #{entity.createBy}, #{entity.updateBy}, now(), now(), '0')"})
    void insert(@Param("entity") TemperatureAlarmEntity entity);

    @Delete({"DELETE FROM temperature_alarm WHERE  HANDLE_DATE <= date_format(DATE_SUB(now(), INTERVAL #{months} MONTH),'%Y%m%d')"})
    void deleteHistoryData(int months);
}
