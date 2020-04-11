package com.plc.hmi.dal.base.dao;

public class SqlMapClientContext {
    private  final static ThreadLocal<SqlMapClientContext> ctx = new ThreadLocal<SqlMapClientContext>() {
        @Override
        protected SqlMapClientContext initialValue(){
            return new SqlMapClientContext();
        }
    };

    public static enum Operation{
        SELECT,
        INSERT,
        UPDATE,
        DELETE
    };
    public static SqlMapClientContext ctx() {
        return ctx.get();
    }
    public static void reset() {
        ctx.remove();
    }

    private String sqlId;
    private Operation op;

    public SqlMapClientContext setSqlId(String sqlId) {
        this.sqlId = sqlId;
        return this;
    }
    public SqlMapClientContext select() {
        this.op = Operation.SELECT;
        return this;
    }

    public SqlMapClientContext insert() {
        this.op = Operation.INSERT;
        return this;
    }

    public SqlMapClientContext update() {
        this.op = Operation.UPDATE;
        return this;
    }

    public SqlMapClientContext delete() {
        this.op = Operation.DELETE;
        return this;
    }

    public String getSqlId() {
        return this.sqlId;
    }

    public String getOp() {
        if(op != null) {
            return op.name();
        } else {
            return null;
        }
    }

}
