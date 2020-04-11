package com.plc.hmi.service.plcService;

import com.plc.hmi.S7Connector.service.Plc4xConnectorService;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.TagsInfoEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.TagsInfoService;
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
    //设备信息builder
    protected  PlcReadRequest.Builder builder;
    //bulider 创建时间点
    protected  Long builderTime;
    //查询请求对象
    protected List<PlcEntity> readQueryList;
    //查询请求对象
    protected List<PlcEntity> writeQueryList;


    /**
     *  获取设 PlcReadRequest.Builder
     *  高频查询，需要先获得 PlcReadRequest.Builder
     */
    public PlcReadRequest.Builder initReadBuilder(List<PlcEntity> queryList, boolean force){
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
    protected List<PlcEntity>  getDataByBuilder() {
        if(null == builder) {
            this.initReadBuilder(readQueryList, false);
        }
        return plc4xConnectorService.queryData(builder);
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
    protected  boolean setPlcData() {
        //TODO
        return true;
    }

    /**
     * 查询queryList初始化
     * @param tagGroup
     */
    protected void initQuereyList(String tagGroup) {
        if(!CollectionUtils.isEmpty(readQueryList)) {
            return;
        }
        List<TagsInfoEntity> tags = tagsInfoService.getTagsByGroup(tagGroup);
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
            sb.append(HmiConstants.PLC_QUERY_PREFIX).append(tag.getTagAreaName()).append(tag.getAdderss()).append(HmiConstants.POINT).append(tag.getTagBit())
                    .append(":").append(tag.getTagTypeDes());
            query.setFieldQuery(sb.toString());
            query.setPosition(tag.getAdderss()+HmiConstants.POINT+tag.getTagBit());
            query.setDataType(tag.getTagTypeDes());
            readQueryList.add(query);
        }
    }



    /**
     * 查询queryList初始化
     * @param tagGroup
     */
    protected void initReadList(String tagGroup, Map<String, String> paraMap) {
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
            PlcEntity query = new PlcEntity();
            query.setName(tag.getTagEnName());
            StringBuffer sb =new StringBuffer();
            sb.append("%").append(tag.getTagAreaName()).append(tag.getAdderss()).append(".").append(tag.getTagBit());
            query.setFieldQuery(sb.toString());
            query.setPosition(tag.getAdderss()+"."+tag.getTagBit());
            query.setDataType(tag.getTagTypeDes());
            Object value = setUploadValue(tag.getTagTypeDes(), paraMap.get(tag.getTagEnName()));
            query.setValueOjb(value);
            readQueryList.add(query);
        }
    }



    private Object setUploadValue(String typeDes, String value) {
        if(StringUtils.isEmpty(typeDes) || StringUtils.isEmpty(value) ) {
            return null;
        }
        value = value.trim();
        Object objValue = null;
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
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.INT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ; //Java4个字节plc2个字节
        }else if(HmiConstants.PLC_DATA_TYPE.LINT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.LREAL.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.LWORD.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.REAL.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.SINT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.TIME.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.UINT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.ULINT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.USINT.getCode().equalsIgnoreCase(typeDes)){
//            objValue = ;
        }else if(HmiConstants.PLC_DATA_TYPE.WORD.getCode().equalsIgnoreCase(typeDes)){

        }

        return null;
    }



}
