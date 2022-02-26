package com.plc.hmi.dal.entity.common;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;

import java.util.Date;

public class ResponseEntity {
    //返回码
    private String code;
    //返回信息
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
