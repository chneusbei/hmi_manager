package com.plc.hmi.service.plcService;

import com.plc.hmi.S7Connector.service.Plc4xConnectorService;
import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PropertyEntity;
import com.plc.hmi.dal.entity.TagsInfoEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.PropertyService;
import com.plc.hmi.service.TagsInfoService;
import com.plc.hmi.util.HmiUtils;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.types.PlcClientDatatype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plc4xBaseService {
    @Autowired
    Plc4xConnectorService plc4xConnectorService;
    @Autowired
    private TagsInfoService tagsInfoService;
    @Autowired
    private PropertyService propertyService;
    //设备信息builder
    protected  PlcReadRequest.Builder builder;
    //bulider 创建时间点
    protected  Long builderTime;
    //查询请求对象
    protected List<PlcEntity> readQueryList;
    //写请求对象
    protected List<PlcEntity> writeQueryList;


    /**
     *  获取设 PlcReadRequest.Builder
     *  高频查询，需要先获得 PlcReadRequest.Builder
     */
    public  PlcReadRequest.Builder initReadBuilder(List<PlcEntity> queryList, boolean force){
        if(force || null == builder) {
            builder = plc4xConnectorService.getReadBuilder(queryList);
            builderTime = System.currentTimeMillis();
        }
        return builder;
    }

    /**
     * 通过 PlcReadRequest.Builder从PLC获取数据
     * 通用方法， 批量模式
     */
    protected List<PlcEntity> getDataByBuilder() {
        if (null == builder) {
            this.initReadBuilder(readQueryList, false);
        }
        if (null == builder) {
            return null;
        } else {
            return plc4xConnectorService.queryData(builder);
        }
    }

    /**
     * 从PLC获取数据， 通用方法， 批量模式
     */
    protected List<PlcEntity>  getData() {
        if(CollectionUtils.isEmpty(readQueryList)) {
            return null;
        }
        return plc4xConnectorService.queryData(readQueryList);
    }



    //修改PLC上数据， 通用方法， 支持批量模式
    protected   boolean setPlcData() {
        plc4xConnectorService.setData(writeQueryList);
        writeQueryList.clear();
        return true;
    }

    /**
     * 查询queryList初始化
     * @param tagGroup
     */
    protected  void initQuereyList(String tagGroup) {
//        System.out.println(">>>>>>>>>>>>>tagGroup"+tagGroup);
//        System.out.println(">>>>>>>>>>readQueryList.isEmpty()"+CollectionUtils.isEmpty(readQueryList));
        if(!CollectionUtils.isEmpty(readQueryList)) {
            return;
        }
        List<TagsInfoEntity> tags = tagsInfoService.getTagsByGroup(tagGroup);
        boolean isDubblePress = propertyService.isDubblePress();
        //当是曲线信息， 且双压头标识是开的，增加读取第二个压头的tagGroup
        if(HmiConstants.PLC_TAG_GROUP.CURVE_DATA.getCode().equalsIgnoreCase(tagGroup)
        && isDubblePress) {
            List<TagsInfoEntity> tags2 = tagsInfoService.getTagsByGroup(HmiConstants.PLC_TAG_GROUP.CURVE_DATA_2.getCode());
            tags.addAll(tags2);
        }
        setQueryList(tags);
    }

    /**
     * 查询queryList初始化
     * @param tags
     */
    protected void setQueryList(List<TagsInfoEntity> tags) {
        if(CollectionUtils.isEmpty(tags)) {
            return;
        }

        readQueryList = new ArrayList<PlcEntity>();
        for(TagsInfoEntity tag : tags) {
//            builder.addItem("shuru2", "%I0.1:BOOL");
            PlcEntity query = new PlcEntity();
            query.setName(tag.getTagEnName());
            StringBuffer sb =new StringBuffer();
//            %db298.0
            sb.append(HmiConstants.PLC_QUERY_PREFIX).append(tag.getTagAreaName()).append(tag.getAddress()).append(HmiConstants.POINT).append(tag.getTagBit())
                    .append(":").append(tag.getTagTypeDes());
            query.setFieldQuery(sb.toString());
            query.setPosition(tag.getAddress()+HmiConstants.POINT+tag.getTagBit());
            query.setDataType(tag.getTagTypeDes());
            readQueryList.add(query);
        }
    }

    /**
     * 查询曲线动态初始化
     */
    protected void setDynamicCurveQueryList(int pressHeadNo, int start,  int dataLength) {
        if(dataLength <= 0  || dataLength <= start) {
            return;
        }
        for(int i = start; i < dataLength; i ++) {
//            builder.addItem("shuru2", "%I0.1:BOOL");
            PlcEntity pressQuery = new PlcEntity();
            pressQuery.setName("curvePressNew"+i);
            StringBuffer sb =new StringBuffer();
            sb.append(HmiConstants.PLC_QUERY_PREFIX);
            if(pressHeadNo == 1) {
                sb.append("DB212.DBD");
            } else {
                sb.append("DB214.DBD");
            }
            sb.append(i*4)
                .append(HmiConstants.POINT)
                .append("0")
                .append(":").append("REAL");
            pressQuery.setFieldQuery(sb.toString());
            pressQuery.setPosition(i*4+HmiConstants.POINT+"0");
            pressQuery.setDataType("REAL");

            PlcEntity positionQuery = new PlcEntity();
            positionQuery.setName("curvePosNew"+i);
            StringBuffer sb1 =new StringBuffer();
            sb1.append(HmiConstants.PLC_QUERY_PREFIX);
            if(pressHeadNo == 1) {
                sb1.append("DB213.DBD");
            } else {
                sb1.append("DB215.DBD");
            }
            sb1.append(i*4)
                    .append(HmiConstants.POINT)
                    .append("0")
                    .append(":").append("REAL");
            positionQuery.setFieldQuery(sb1.toString());
            positionQuery.setPosition(i*4+HmiConstants.POINT+"0");
            positionQuery.setDataType("REAL");
            readQueryList.add(positionQuery);
        }
    }




    /**
     * 插入queryList初始化
     * @param tagGroup
     */
    protected  void initWriteList(String tagGroup, Map<String, String> paraMap) {
        if(!CollectionUtils.isEmpty(writeQueryList)) {
            return;
        }
        List<TagsInfoEntity> tags = tagsInfoService.getTagsByGroup(tagGroup);
        if(CollectionUtils.isEmpty(tags)) {
            return;
        }

        writeQueryList = new ArrayList<PlcEntity>();
        for(TagsInfoEntity tag : tags) {
//            builder.addItem("shuru2", "%I0.1:BOOL");
            if(null == paraMap.get(tag.getTagEnName())) {
                continue;
            }
            PlcEntity query = new PlcEntity();
            query.setName(tag.getTagEnName());
            StringBuffer sb =new StringBuffer();
//            %db298.0
            sb.append(HmiConstants.PLC_QUERY_PREFIX).append(tag.getTagAreaName()).append(tag.getAddress()).append(HmiConstants.POINT).append(tag.getTagBit())
                    .append(":").append(tag.getTagTypeDes());
            query.setFieldQuery(sb.toString());
            query.setPosition(tag.getAddress()+HmiConstants.POINT+tag.getTagBit());
            query.setDataType(tag.getTagTypeDes());
            Object value = setUploadValue(tag.getTagTypeDes(), paraMap.get(tag.getTagEnName()));
            query.setValueOjb(value);
            writeQueryList.add(query);
        }
    }



    private Object setUploadValue(String typeDes, String value) {
        if(StringUtils.isEmpty(typeDes) || StringUtils.isEmpty(value) ) {
            return null;
        }
        value = value.trim();
        Object objValue = value;
//        BOOL("BOOL", PlcClientDatatype.BOOLEAN),
//                BYTE("BYTE", PlcClientDatatype.BYTE),
//                CHAR("CHAR", PlcClientDatatype.STRING),
//                DATE("DATE", PlcClientDatatype.DATE),
//                DINT("DINT", PlcClientDatatype.DOUBLE),
//                DWORD("DWORD", PlcClientDatatype.DOUBLE),
//                INT("INT", PlcClientDatatype.INTEGER),
//                LINT("LINT", PlcClientDatatype.DOUBLE),
//                LREAL("LREAL", PlcClientDatatype.STRING),
//                LWORD("LWORD", PlcClientDatatype.DOUBLE),
//                REAL("REAL", PlcClientDatatype.STRING),
//                SINT("SINT", PlcClientDatatype.SHORT),
//                TIME("TIME", PlcClientDatatype.TIME),
//                UINT("UINT", PlcClientDatatype.INTEGER),
//                ULINT("ULINT", PlcClientDatatype.DOUBLE),
//                USINT("USINT", PlcClientDatatype.SHORT),
//                WORD("WORD", PlcClientDatatype.DOUBLE);
        if(HmiConstants.PLC_DATA_TYPE.BOOL.getCode().equalsIgnoreCase(typeDes)){
            if( "true".equalsIgnoreCase(value) || "1".equalsIgnoreCase(value)) {
                objValue= Boolean.TRUE;
            } else {
                objValue = Boolean.FALSE;
            }
        }else if(HmiConstants.PLC_DATA_TYPE.BYTE.getCode().equalsIgnoreCase(typeDes)){
            objValue = value.getBytes();
        }else if(HmiConstants.PLC_DATA_TYPE.CHAR.getCode().equalsIgnoreCase(typeDes)){
            objValue = value;
        }else if(HmiConstants.PLC_DATA_TYPE.DATE.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.DINT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.DWORD.getCode().equalsIgnoreCase(typeDes)){
            objValue = value;
        }else if(HmiConstants.PLC_DATA_TYPE.INT.getCode().equalsIgnoreCase(typeDes)){
            objValue= HmiUtils.getIntValue(value);
//            objValue = ; //Java4个字节plc2个字节
        }else if(HmiConstants.PLC_DATA_TYPE.LINT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.LREAL.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.LWORD.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.REAL.getCode().equalsIgnoreCase(typeDes)){
                objValue = value;
        }else if(HmiConstants.PLC_DATA_TYPE.SINT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.TIME.getCode().equalsIgnoreCase(typeDes)){
            objValue = value;
        }else if(HmiConstants.PLC_DATA_TYPE.UINT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.ULINT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.USINT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.WORD.getCode().equalsIgnoreCase(typeDes)){
             objValue = value;
        }

        return objValue;
    }

    public boolean isPlcConnected() {
        return this.plc4xConnectorService.isConnected();
    }


}
