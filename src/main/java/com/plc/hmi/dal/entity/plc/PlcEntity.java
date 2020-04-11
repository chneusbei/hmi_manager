package com.plc.hmi.dal.entity.plc;

import com.plc.hmi.constants.HmiConstants;
import org.apache.plc4x.java.api.types.PlcClientDatatype;

import java.io.Serializable;

/**
 * 查询实体类的抽象类
 */
public class PlcEntity implements Serializable {
    private String name;
    private PlcClientDatatype plcDataType;
    private String dataType;
    private String Position;
    private Object valueOjb;
    private String fieldQuery;
    public String getFieldQuery() {
        return fieldQuery;
    }
    public void setFieldQuery(String fieldQuery) {
        this.fieldQuery = fieldQuery;
    }

    public Object getValueOjb() {
        return valueOjb;
    }

    public void setValueOjb(Object valueOjb) {
        this.valueOjb = valueOjb;
    }

    public PlcClientDatatype getPlcDataType() {
        return plcDataType;
    }

    public void setPlcDataType(PlcClientDatatype plcDataType) {
        this.plcDataType = plcDataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
        this.setPlcDataType(HmiConstants.PLC_DATA_TYPE.getTypeByCode(dataType));
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

     /*
       //name:shuru2,fieldQuery:%I,Position:0.1,dataType:BOOL
        builder.addItem("shuru2", "%I0.1:BOOL");
        builder.addItem("Tag_1", "%M0.1:BOOL");
        builder.addItem("shuru2", "%I0.1:BOOL");
        builder.addItem("shuru3", "%I0.2:BOOL");
        builder.addItem("shuru4", "%I0.3:BOOL");
        builder.addItem("shuru5", "%I0.4:BOOL");
        builder.addItem("shuru6", "%I0.5:BOOL");
        builder.addItem("shuru7", "%I0.6:BOOL");
        builder.addItem("shuru8", "%I0.7:BOOL");
        builder.addItem("value-1", "%Q0.4:BOOL");
        builder.addItem("value-2", "%Q0:BYTE");
        builder.addItem("value-3", "%I0.2:BOOL");
        builder.addItem("value-4", "%DB.DB1.4:INT");
         */

}
