package com.plc.hmi.S7Connector.dto;

import com.github.s7connector.api.annotation.S7Variable;
import com.github.s7connector.impl.utils.S7Type;
import com.plc.hmi.dto.AbstractDto;

/**
 * PLC下位机中信息对象
 */
public class PlcDataDto extends AbstractDto {
    @S7Variable(type = S7Type.BOOL, byteOffset = 0, bitOffset = 0)
    public boolean db111;
  /*
    @S7Variable(type = S7Type.BOOL, byteOffset = 0, bitOffset = 1)
    public boolean db112;
    @S7Variable(type = S7Type.BOOL, byteOffset = 0, bitOffset = 2)
    public boolean db113;
    @S7Variable(type = S7Type.BOOL, byteOffset = 0, bitOffset = 3)
    public boolean db114;
    @S7Variable(type = S7Type.BOOL, byteOffset = 0, bitOffset = 4)
    public boolean db115;
    @S7Variable(type = S7Type.BOOL, byteOffset = 0, bitOffset = 5)
    public boolean db116;
    @S7Variable(type = S7Type.BOOL, byteOffset = 0, bitOffset = 6)
    public boolean db117;
    @S7Variable(type = S7Type.BOOL, byteOffset = 0, bitOffset = 7)
    public boolean db118;
    @S7Variable(type = S7Type.BOOL, byteOffset = 1, bitOffset = 0)
    public boolean db119;

    @S7Variable(type = S7Type.WORD, byteOffset = 2, bitOffset = 0)
    public boolean dddd;
    @S7Variable(type = S7Type.REAL, byteOffset = 4, bitOffset = 0)
    public boolean eeee;
    @S7Variable(type = S7Type.REAL, byteOffset = 8, bitOffset = 0)
    public boolean eeee_1;
    @S7Variable(type = S7Type.REAL, byteOffset = 12, bitOffset = 0)
    public boolean eeee_2;
    @S7Variable(type = S7Type.REAL, byteOffset = 16, bitOffset = 0)
    public boolean eeee_3;
    @S7Variable(type = S7Type.REAL, byteOffset = 20, bitOffset = 0)
    public boolean eeee_4;
    @S7Variable(type = S7Type.REAL, byteOffset = 24, bitOffset = 0)
    public boolean eeee_5;
    */
}
