package com.plc.hmi.util;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.LongTypeHandler;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.plugin.com.Utils;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HmiUtils extends LongTypeHandler {
    static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//注意月份是MM
    private static Logger logger = LoggerFactory.getLogger(HmiUtils.class);
    public static final Date getDate(Object obj) {
        if(null == obj) {
            return null;
        }
        try {
            return simpleDateFormat.parse(String.valueOf(obj));
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("DateFormat ERROR ",e.getStackTrace());
            return null;
        }
    }

    public static final Date getDate(String str) {
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("DateFormat ERROR ",e.getStackTrace());
          return null;
        }
    }

    public static final String getString(Object obj) {
        if(obj == null) {
            return null;
        } else {
        return String.valueOf(obj);
        }
    }

    public static final long getLongValue(Object obj) {
        if(obj == null) {
            return 0;
        } else {
            return Long.valueOf(String.valueOf(obj)).longValue();
        }
    }

    public static final int getIntValue(Object obj) {
        if(obj == null) {
            return 0;
        } else {
            return Integer.valueOf(String.valueOf(obj)).intValue();
        }
    }

    public static final BigDecimal getBigDicimal(Object obj) {
        if(obj == null) {
            return new BigDecimal(0);
        } else {
            try {
                return new BigDecimal(getString(obj));
            } catch (NumberFormatException numberFormatException) {
                logger.info("number format error" + numberFormatException.getStackTrace());
                return new BigDecimal(0);
            }
        }
    }

    public static final String appendString(String s1,String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1).append(s2);
        return sb.toString();
    }







}
