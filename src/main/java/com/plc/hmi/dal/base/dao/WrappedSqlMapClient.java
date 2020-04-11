package com.plc.hmi.dal.base.dao;

import com.ibatis.common.util.PaginatedList;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.ibatis.sqlmap.client.event.RowHandler;
import com.ibatis.sqlmap.engine.execution.BatchException;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.result.ResultObjectFactory;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class WrappedSqlMapClient implements SqlMapClient, ExtendedSqlMapClient {

    private SqlMapClient client;

    public WrappedSqlMapClient(){

    }
    public WrappedSqlMapClient(SqlMapClient client){
        super();
        this.client = client;
    }

    @Override
    public void startTransaction() throws SQLException {
        client.startTransaction();
    }

    @Override
    public void startTransaction(int transcationIsolation) throws SQLException {
        client.startTransaction(transcationIsolation);
    }

    @Override
    public void commitTransaction() throws SQLException {
        client.commitTransaction();
    }

    @Override
    public void endTransaction() throws SQLException {
        client.endTransaction();
    }

    public  Object insert(String id, Object parameterObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).insert();
            return client.insert(id, parameterObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public Object insert(String id) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).insert();
            return client.insert(id);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public int update(String id, Object parameterObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).update();
            return client.update(id, parameterObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public void setUserConnection(Connection conection) throws SQLException {
        client.setUserConnection(conection);
    }

    public int update(String id) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).update();
            return client.update(id);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public int delete(String id, Object parameterObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).delete();
            return client.delete(id, parameterObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public Connection getUserConnection() throws SQLException {
        return client.getUserConnection();
    }

    public SqlMapSession openSession() {
        SqlMapSession session = client.openSession();
        if(!(session instanceof WrappedSqlMapSession)) {
            session = new WrappedSqlMapSession(session);
        }
        return session;
    }

    public int delete(String id) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).delete();
            return client.delete(id);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public Connection getCurrentConnection() throws SQLException {
        return client.getCurrentConnection();
    }

    public Object queryForObject(String id, Object parameterObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return client.queryForObject(id, parameterObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public DataSource getDataSource() {
        return client.getDataSource();
    }

    public SqlMapSession openSession(Connection conn) {
        SqlMapSession session = client.openSession(conn);
        if(!(session instanceof WrappedSqlMapSession)) {
            session = new WrappedSqlMapSession(session);
        }
        return session;
    }

    public Object queryForObject(String id) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return client.queryForObject(id);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public Object queryForObject(String id, Object parameterObject, Object resultObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return client.queryForObject(id, parameterObject, resultObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public SqlMapSession getSession() {
        return client.getSession();
    }

    public void flushDataCache() {
        client.flushDataCache();
    }

    public List queryForList(String id, Object parameterObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return client.queryForList(id, parameterObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public void flushDataCache(String cacheId) {
        client.flushDataCache(cacheId);
    }

    public List queryForList(String id) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return client.queryForList(id);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public List queryForList(String id, Object parameterObject, int skip, int max) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return client.queryForList(id, parameterObject, skip, max);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public List queryForList(String id, int skip, int max) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return client.queryForList(id, skip, max);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public void queryWithRowHandler(String id, Object parameterObject, RowHandler rowHandler) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            client.queryWithRowHandler(id, parameterObject, rowHandler);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public void queryWithRowHandler(String id, RowHandler rowHandler) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            client.queryWithRowHandler(id, rowHandler);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public PaginatedList queryForPaginatedList(String id, Object parameterObject, int pageSize) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return client.queryForPaginatedList(id, parameterObject, pageSize);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public PaginatedList queryForPaginatedList(String id, int pageSize) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return client.queryForPaginatedList(id, pageSize);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public Map queryForMap(String id, Object parameterObject, String keyProp) throws SQLException  {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return client.queryForMap(id, parameterObject, keyProp);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public Map queryForMap(String id, Object parameterObject, String keyProp, String valueProp) throws SQLException  {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return client.queryForMap(id, parameterObject, keyProp, valueProp);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    public void startBatch() throws SQLException {
        client.startBatch();
    }

    public int executeBatch() throws SQLException {
        return client.executeBatch();
    }

    public List executeBatchDetailed() throws SQLException, BatchException {
        return client.executeBatchDetailed();
    }

    @Override
    public MappedStatement getMappedStatement(String id) {
       if(client instanceof ExtendedSqlMapClient) {
           return ((ExtendedSqlMapClient)client).getMappedStatement(id);
       } else {
           return null;
       }
    }

    @Override
    public boolean isLazyLoadingEnabled() {
        if(client instanceof ExtendedSqlMapClient) {
            return ((ExtendedSqlMapClient)client).isLazyLoadingEnabled();
        } else {
            return false;
        }
    }

    @Override
    public boolean isEnhancementEnabled() {
        if(client instanceof ExtendedSqlMapClient) {
            return ((ExtendedSqlMapClient)client).isEnhancementEnabled();
        } else {
            return false;
        }
    }

    @Override
    public SqlExecutor getSqlExecutor() {
        if(client instanceof ExtendedSqlMapClient) {
            return ((ExtendedSqlMapClient)client).getSqlExecutor();
        } else {
            return null;
        }
    }

    @Override
    public SqlMapExecutorDelegate getDelegate() {
        if(client instanceof ExtendedSqlMapClient) {
            return ((ExtendedSqlMapClient)client).getDelegate();
        } else {
            return null;
        }
    }

    @Override
    public ResultObjectFactory getResultObjectFactory() {
        if(client instanceof ExtendedSqlMapClient) {
            return ((ExtendedSqlMapClient)client).getResultObjectFactory();
        } else {
            return null;
        }
    }

}
