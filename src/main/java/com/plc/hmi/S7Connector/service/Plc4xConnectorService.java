package com.plc.hmi.S7Connector.service;

import com.plc.hmi.constants.ConfigConstants;
import com.plc.hmi.constants.HmiConstants;
import com.plc.hmi.dal.entity.PlcConfigEntity;
import com.plc.hmi.dal.entity.plc.PlcEntity;
import com.plc.hmi.service.PropertyService;

import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.apache.plc4x.java.api.messages.PlcWriteRequest;
import org.apache.plc4x.java.api.messages.PlcWriteResponse;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


@Component
public class Plc4xConnectorService {
    @Resource
    private PropertyService propertyService;
    private static Logger logger = LoggerFactory.getLogger(Plc4xConnectorService.class);
//    private String HOST =  null; //"s7://192.168.1.1/2/1"; 参数 第一个是机架rock, 第二个是插槽slot
    public Map<String, PlcConnection> plcConnectionMap = new HashMap<String, PlcConnection>();

    /**
     * 连接到PLC
     * @param plcConfigEntity
     * @return
     */
    private  boolean connect2Plc(PlcConfigEntity plcConfigEntity) {
        if(null != plcConfigEntity) {
            PlcDriverManager plcDriverManager =new PlcDriverManager();
            String host = getHostString(plcConfigEntity);
            try {
                PlcConnection plcConnection =  plcDriverManager.getConnection(host);
                plcConnectionMap.put(plcConfigEntity.getPlcServerIp(), plcConnection);
            } catch (PlcConnectionException e) {
                logger.info("connect to server error, PLC ip = {}", plcConfigEntity.getPlcServerIp());
//                e.printStackTrace();
                return false;
            } catch (Exception e) {
                logger.info("connect to server error, PLC ip = {}", plcConfigEntity.getPlcServerIp() );
                e.printStackTrace();
                return false;
            }
            logger.info("connect to server ip = {},status  = {}",plcConfigEntity.getPlcServerIp(), isConnected(plcConfigEntity));
        }
        return true;
    }

    /**
     * 获取PLC主机连接串
     * @param plcConfigEntity
     * @return
     */
    private String getHostString(PlcConfigEntity plcConfigEntity) {
        String host = null;
        StringBuffer buff = new StringBuffer();
        //"s7://192.168.1.1/2/1";
        host = buff.append(ConfigConstants.PLC_SERVER_HOST_PREFIX).
                append(plcConfigEntity.getPlcServerIp()).
                append(HmiConstants.SEPARATE).
                append(plcConfigEntity.getPlcServerRock()).
                append(HmiConstants.SEPARATE).
                append(plcConfigEntity.getPlcServerSlot()).toString();
        return host;
    }

    /**
     * 获取PLC连接状态
     * @param plcConfigEntity
     * @return
     */
    public boolean isConnected(PlcConfigEntity plcConfigEntity) {
        if(CollectionUtils.isEmpty(plcConnectionMap)) {
            return false;
        }
        PlcConnection plcConnection  = plcConnectionMap.get(plcConfigEntity.getPlcServerIp());
        if(null == plcConnection) {
            return false;
        }
        return plcConnection.isConnected();
    }

    /**
     * 获取读BUILDER
     * @param queryList
     * @return
     */
    public  PlcReadRequest.Builder getReadBuilder(List<PlcEntity> queryList, PlcConfigEntity plcConfigEntity) {
        if(queryList == null || queryList.size() == 0) {
            logger.info("PlcEntity is empty!");
        }

        if (!isConnected(plcConfigEntity)) {
           boolean connected = connect2Plc(plcConfigEntity);
           if(!connected) {
               return null;
           }
        }
    //        System.out.println(plcConnection);
        PlcConnection plcConnection = plcConnectionMap.get(plcConfigEntity.getPlcServerIp());
        if (!plcConnection.getMetadata().canRead()) {/*判断数据是否可以读取*/
            logger.info("This connection doesn't support reading.");
            return null;
        }

        PlcReadRequest.Builder builder = plcConnection.readRequestBuilder();
        for(PlcEntity queryEntity : queryList) {
            builder.addItem(queryEntity.getName(), queryEntity.getFieldQuery());
//            builder.addItem("shuru2", "%I0.1:BOOL");
        }
        return builder;
    }

    /**
     * 每次都需要重新创建builder， 不适用于频繁查询场景
     * @param queryList
     */
    public List<PlcEntity> queryData(List<PlcEntity> queryList, PlcConfigEntity plcConfigEntity ) {
        PlcReadRequest.Builder builder = getReadBuilder(queryList, plcConfigEntity);
        return  queryData(builder);
    }

    /**
     * 不需要重复创建builder,适用于频繁查询场景
     * @param builder
     */
    public  List<PlcEntity> queryData(PlcReadRequest.Builder builder) {
        PlcReadRequest readRequest = builder.build();
        PlcReadResponse response =null;
        try {
            response = readRequest.execute().get();
        } catch (InterruptedException e) {
            logger.error("execute readRequeset error：" + e.getStackTrace());
            e.printStackTrace();
        } catch (ExecutionException e) {
            logger.error("execute readRequeset error:" + e.getStackTrace());
            e.printStackTrace();
        }
        return  analizyResponse(response);
    }


    private List<PlcEntity> analizyResponse(PlcReadResponse response ) {
        if(response == null || response.getFieldNames() == null) {
            return null;
        }

        List<PlcEntity> responstList = new ArrayList<PlcEntity>();
        for (String fieldName : response.getFieldNames()) {
            if (response.getResponseCode(fieldName) == PlcResponseCode.OK) {
                PlcEntity   responseEntity = new PlcEntity();
                responseEntity.setName(fieldName);
//                logger.info("get value from plc, NAME = "+fieldName +",value = " + response.getObject(fieldName));
                int numValues = response.getNumberOfValues(fieldName);
                if (numValues == 1) {
                    // If it's just one element, output just one single line.
                    responseEntity.setValueOjb(response.getObject(fieldName));
//                    logger.info("Value[" + fieldName + "]: " + response.getObject(fieldName));
                } else {
                    // If it's more than one element, output each in a single row.
                    ArrayList<Object> valeList = new ArrayList<Object>();
//                    logger.info("Value[" + fieldName + "]:");
                    for (int i = 0; i < numValues; i++) {
                        valeList.add(response.getObject(fieldName, i));
//                        logger.info(" - " + response.getObject(fieldName, i));
                    }
                    responseEntity.setValueOjb(valeList);
                }
                responstList.add(responseEntity);
            } else {
                // Something went wrong, to output an error message instead.
                logger.error("Error[" + fieldName + "]: " + response.getResponseCode(fieldName).name());
            }
        }
        return responstList;
    }


    /**
     * 写入PLC
     * @param queryList
     */
    public void setData(List<PlcEntity> queryList, PlcConfigEntity plcConfigEntity) {
        if (!isConnected(plcConfigEntity)) {
                connect2Plc(plcConfigEntity);
        }
        if (!isConnected(plcConfigEntity)) {
            logger.error("PLC 连接失败！");
            return;
        }
        PlcConnection plcConnection = plcConnectionMap.get(plcConfigEntity.getPlcServerIp());
        if (!plcConnection.getMetadata().canWrite()) {
            logger.info("This connection doesn't support write.");
        }
        PlcWriteRequest.Builder writeBuilder = plcConnection.writeRequestBuilder();
        for(PlcEntity query : queryList) {
//            query.setFieldQuery("%DB300.DBX3.0:BOOL");
            if("REAL".equalsIgnoreCase(query.getDataType())) {
                /*
                builder.addItem("value-1", "%Q0.4:BOOL", true);
                builder.addItem("value-2", "%Q0:BYTE", (byte) 0xFF);
                builder.addItem("value-4", "%DB.DB1.4:INT[3]", 7, 23, 42);
                 */
//                ByteBuf buf = Unpooled.buffer(4);
//                buf.writeFloat(Float.valueOf(query.getValueOjb().toString()).floatValue());
//                byte[] bytes = buf.array();
//                writeBuilder.addItem(query.getName(), query.getFieldQuery(), new BigDecimal(query.getValueOjb().toString()));
//                writeBuilder.addItem(query.getName(), query.getFieldQuery(), Integer.valueOf(query.getValueOjb().toString()).intValue());
//                writeBuilder.addItem(query.getName(), query.getFieldQuery(), bytes);
                  writeBuilder.addItem(query.getName(), query.getFieldQuery(),Float.valueOf(query.getValueOjb().toString()).floatValue());

            } else if("BOOL".equalsIgnoreCase(query.getDataType())) {
                writeBuilder.addItem(query.getName(), query.getFieldQuery(), query.getValueOjb());
            }  else if("CHAR".equalsIgnoreCase(query.getDataType())) {
//                String str = query.getFieldQuery();
//                str = str.replace("CHAR","BOOL");
//                str = str.replace("DBB","DBX");
//                str = str.replace("CHAR","REAL");
//                str = str.replace("DBB","DBD");
//                writeBuilder.addItem(query.getName(), query.getFieldQuery(),ca);
//                writeBuilder.addItem(query.getName(),str,Float.valueOf("100").floatValue());
//                writeBuilder.addItem("traceCode0","%DB300.DBX84.0:BOOL", query.getValueOjb());
                //%DB300.DBX84.0:BOOL
                //%DB300.DBX3.0:BOOL
            }   else if("INT".equalsIgnoreCase(query.getDataType())) {
//                String str = query.getFieldQuery();
//                str = str.replace("CHAR","BOOL");
//                str = str.replace("DBB","DBX");
//                str = str.replace("CHAR","REAL");
//                str = str.replace("DBB","DBD");
//                writeBuilder.addItem(query.getName(), query.getFieldQuery(),ca);
//                writeBuilder.addItem(query.getName(),str,Float.valueOf("100").floatValue());
//                writeBuilder.addItem("traceCode0","%DB300.DBX84.0:BOOL", query.getValueOjb());
                //%DB300.DBX84.0:BOOL
                //%DB300.DBX3.0:BOOL
                writeBuilder.addItem(query.getName(), query.getFieldQuery(),Integer.valueOf(query.getValueOjb().toString()).shortValue());

            }
        }
        PlcWriteRequest writeRequest = writeBuilder.build();
        PlcWriteResponse response = null;

        try {
            response = writeRequest.execute().get();
            java.util.LinkedHashSet fieldNames = (java.util.LinkedHashSet) response.getFieldNames();
            if(!CollectionUtils.isEmpty(fieldNames)) {
                for(Object fieldName : fieldNames) {
                    if (!"systemFlagTag".equals(fieldName)) {
                        logger.info("fieldName = " + fieldName + ",  returnCode= " + response.getResponseCode(String.valueOf(fieldName)));
                    }
                }
            }

        } catch (InterruptedException e) {
            logger.error("execute writeRequest error." + e.getStackTrace());
            e.printStackTrace();
        } catch (ExecutionException e) {
            logger.error("execute writeRequest error." + e.getStackTrace());
            e.printStackTrace();
        }
    }


}
