package com.plc.hmi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HmiUtils  {
    static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//注意月份是MM
    static final SimpleDateFormat dateFormatYYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");//注意月份是MM
    static final SimpleDateFormat dateFormatYYYYMMDDHHMMSSSSS = new SimpleDateFormat("yyyyMMddHHmmssSSS");//注意月份是MM
    static final SimpleDateFormat dateFormatYYYYMMDD = new SimpleDateFormat("yyyyMMdd");//注意月份是MM
    static final SimpleDateFormat dateFormatNormal = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//注意月份是MM
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

    public static String getFormatDateString() {
        return dateFormatYYYYMMDDHHMMSS.format(new Date());
    }

    public static String getFormatDateString(Date date) {
        return dateFormatNormal.format(date);
    }
    public static String getYYYYMMDDString(Date date) {
        return dateFormatYYYYMMDD.format(new Date());
    }

    public static String getMillFormatDateString(Date date) {
        return dateFormatYYYYMMDDHHMMSSSSS.format(date);
    }

    public static final String getString(Object obj) {
        if(obj == null) {
            return null;
        } else {
        return String.valueOf(obj);
        }
    }

    public static final String getStringNoNull(Object obj) {
        if(obj == null) {
            return "";
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

    public static final Integer getIntegerValue(Object obj) {
        if(obj == null) {
            return 0;
        } else {
            return Integer.valueOf(String.valueOf(obj));
        }
    }

    public static String hexStringToString(String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        s = s.replace(" ", "");
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "UTF-8");
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public static final Boolean getBooleanValue(Object obj) {
        if(obj == null) {
            return new Boolean(false);
        } else {
            if("true".equalsIgnoreCase(obj.toString()) || "1".equalsIgnoreCase(obj.toString())) {
                return new Boolean(true);
            } else {
                return new Boolean(false);
            }
        }
    }

    public static final BigDecimal getBigDicimal(Object obj) {
        if(obj == null) {
            return new BigDecimal(0);
        } else {
            try {
                return new BigDecimal(getString(obj));
            } catch (NumberFormatException numberFormatException) {
//                logger.info("number format error" + numberFormatException.getMessage());
                return new BigDecimal(0);
            }
        }
    }

    public static final String appendString(String s1,String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1).append(s2);
        return sb.toString();
    }
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public static String getProgrameCode(String headNo, String productId) {
        //获取公差窗口信息
        StringBuffer programCodeBuffer = new StringBuffer("p").append(headNo);
        if("1".equalsIgnoreCase(productId)) {
            //1是正压， 2是反压
            programCodeBuffer.append("+");
        } else if("2".equalsIgnoreCase(productId)) {
            programCodeBuffer.append("-");
        } else {
            programCodeBuffer.append("+");
        }
        return programCodeBuffer.toString();
    }

    /**
     * 第一个数如果大于等于第二个数， 返回true
     * @temperature 各个温度点
     * @warn1 温度阈值1
     * @warn2 温度阈值2
     * @return
     */
    public static boolean isBigThanOrEquals(BigDecimal temperature, BigDecimal warn1, BigDecimal warn2) {
        //温度值为空
        if(null == temperature) {
            return false;
        }

        //警戒温度都为空
        if(null == warn1 && null == warn2) {
            return false;
        }

        if(null == warn1) {
            warn1 = warn2;
        } else {
            warn2=warn1;
        }

        if(temperature.compareTo(warn1) >= 0 || temperature.compareTo(warn2) >= 0) {
            return true;
        } else {
            return false;
        }
    }
}
