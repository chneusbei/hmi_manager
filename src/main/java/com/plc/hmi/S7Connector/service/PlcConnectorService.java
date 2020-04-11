package com.plc.hmi.S7Connector.service;

import com.github.s7connector.api.DaveArea;
import com.github.s7connector.api.S7Connector;
import com.github.s7connector.api.S7Serializer;
import com.github.s7connector.api.factory.S7ConnectorFactory;
import com.github.s7connector.api.factory.S7SerializerFactory;
import com.github.s7connector.impl.serializer.converter.BitConverter;
import com.plc.hmi.S7Connector.dto.PlcDataDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.*;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.springframework.stereotype.Component;


@Component
public class PlcConnectorService {
    private final Log logger = LogFactory.getLog(PlcConnectorService.class);
    private static final String HOST = "192.168.1.1";
    private static final int DB_NUM = 100;
    private static final int BYTE_OFFSET = 0;
    private S7Connector connector = null;
//    private  boolean isRun = false;




    private void connect() {
        if (null == connector) {
            try {
//                isRun = true;
                connector = S7ConnectorFactory.buildTCPConnector().withHost(HOST).build();
            } catch (Exception e) {
                connector = null;
                logger.error ("new s7connector fail {}"+ e.getMessage(), e);
            }
        }
    }

    public void getData2() {
        PlcDriverManager plcDriverManager =new PlcDriverManager();
      try {
            PlcConnection plcConnection=  plcDriverManager.getConnection(HOST);
            plcConnection.connect();
            boolean isConn=  plcConnection.isConnected();
          PlcReadRequest.Builder builder = plcConnection.readRequestBuilder();
//          builder.

        } catch (PlcConnectionException e) {
            e.printStackTrace();
        }

    }

    public PlcDataDto getData() {
        this.connect();
        logger.info("connector = "+connector.toString());
        DaveArea daveArea;
//        connector.read(DaveArea.DB, 0,0,0);
//        byte[]  bs = connector.read(DaveArea.DB,DB_NUM,1,0);
//    logger.info("length="+bs.length + "btye []="+bs);

//        logger.info("length="+);
        S7Serializer serializer = S7SerializerFactory.buildSerializer(connector);
        PlcDataDto plcDataDto = serializer.dispense(PlcDataDto.class, DB_NUM, BYTE_OFFSET);

//        logger.info("查询到的下位机信息list："+plcDataDto.toString());
//        return plcDataDto;


        ;

        return null;
        /*
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (isRun) {
                        PlcDataDto plcDataDto = serializer.dispense(PlcDataDto.class, DB_NUM, BYTE_OFFSET);
                    } else {
                        cancel();
                    }
                } catch (Exception e) {
                    logger.error("send message timer fail {}"+ e.getMessage(), e);
                }
            }
        }, 2000, 200);
        */
    }


    /**
     * public static void main(String[] args) {
     * 	//方法只用选择PLC型号和传入PLC的IP地址即可
     * 	SiemensS7Net siemensS7Net = new SiemensS7Net(SiemensPLCS.S200Smart,"192.168.1.1");
     * 	//读取一个vw点位，是一个16位Int
     * 	 OperateResultExOne<Short> read = siemensS7Net.ReadInt16("V1128");
     * 	 //可以根据IsSuccess属性判断是否读取成功
     * 	  if(read.IsSuccess){
     * 	  //Content属性则是返回的内容
     * 	   short value=   read.Content;
     * 	     System.out.println("Value:"+value);
     *            }
     *   }

     */

    //    private void connectWithType{
//        //方法只用选择PLC型号和传入PLC的IP地址即可
//        SiemensS7Net siemensS7Net = new SiemensS7Net(SiemensPLCS.S200Smart,"192.168.1.1");
//        //读取一个vw点位，是一个16位Int
//        OperateResultExOne<Short> read = siemensS7Net.ReadInt16("V1128");
//        //可以根据IsSuccess属性判断是否读取成功
//        if(read.IsSuccess){
//            //Content属性则是返回的内容
//            short value=   read.Content;
//            System.out.println("Value:"+value);
//        }
//    }
/*
短连接
    SiemensS7Net siemens_net = new SiemensS7Net(SiemensPLCS.S1200,"192.168.1.195");
    System.out.println(siemens_net.ReadByte("M100").Content);
    长连接
SiemensS7Net siemens_net = new SiemensS7Net(SiemensPLCS.S1200,"192.168.1.195");
        OperateResult connect = siemens_net.ConnectServer();
        if(connect.IsSuccess){
            System.out.println("connect success!");
        }
        else {
            System.out.println("failed:"+connect.Message);
        }


        byte m100_byte = siemens_net.ReadByte("M100").Content;
short m100_short = siemens_net.ReadInt16("M100").Content;
int m100_int = siemens_net.ReadInt32("M100").Content;
long m100_long = siemens_net.ReadInt64("M100").Content;
float m100_float = siemens_net.ReadFloat("M100").Content;
double m100_double = siemens_net.ReadDouble("M100").Content;
String m100_string = siemens_net.ReadString("M100",(short) 10).Content;
以下演示了一些简单的常用的读写操作，不过并未对结果进行判断，实际生产代码中，需要对结果进行严格的判定
siemens_net.Write("M100",(byte) 123);
siemens_net.Write("M100",(short) 123);
siemens_net.Write("M100",(int) 123);
siemens_net.Write("M100",(long) 123);
siemens_net.Write("M100", 123.456f);
siemens_net.Write("M100", 123.456d);
siemens_net.Write("M100","1234567890");
下面说明复杂的数据操作，并对结果进行判断，以及批量化的数据操作，例如读取M100-M109
OperateResultExOne<byte[]> read = siemens_net.Read( "M100", (short) 10 );
{
    if(read.IsSuccess)
    {
        byte m100 = read.Content[0];
        byte m101 = read.Content[1];
        byte m102 = read.Content[2];
        byte m103 = read.Content[3];
        byte m104 = read.Content[4];
        byte m105 = read.Content[5];
        byte m106 = read.Content[6];
        byte m107 = read.Content[7];
        byte m108 = read.Content[8];
        byte m109 = read.Content[9];
    }
    else
    {
        // 发生了异常
    }
}


读写的数据类型，支持 M。I。Q，DB块，T、C

M   地址示例：M100

I 地址示例：I100

Q 地址示例 Q100

DB 块。DB1.100     对于smart200来说，V区100就是DB1.100

T 地址示例 T100

C 地址示例： C100
 */
}
