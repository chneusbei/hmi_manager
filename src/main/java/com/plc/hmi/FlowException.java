package com.plc.hmi;

public class FlowException extends RuntimeException{
    private String code;
    private String desc;
    private Object errorInfo;

    public FlowException(String message) {
        super(message);
    }

    public FlowException(String code, String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
    }


    public FlowException(String code, String desc, Throwable t) {
        super(desc,t);
        this.code = code;
        this.desc = desc;

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(Object errorInfo) {
        this.errorInfo = errorInfo;
    }



}
