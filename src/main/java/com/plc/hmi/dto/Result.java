package com.plc.hmi.dto;

public class Result {
    private String itemId;// 监控位置
    private Object value;// 监控值

    public Result(String itemId, Object value) {
        this.itemId = itemId;
        this.value = value;
    }

    public String getItemId() {
        return itemId;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "[itemId=" + itemId + ", value=" + value + "]";
    }
}
