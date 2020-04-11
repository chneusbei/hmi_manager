package com.plc.hmi.dal.base.dao;

import com.ibatis.common.util.PaginatedList;
import com.ibatis.sqlmap.client.SqlMapSession;
import com.ibatis.sqlmap.client.event.RowHandler;
import com.ibatis.sqlmap.engine.execution.BatchException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class WrappedSqlMapSession implements SqlMapSession {
    private SqlMapSession session;
    public WrappedSqlMapSession(SqlMapSession session) {
        this.session = session;
    }

    @Override
    public  Object insert(String id, Object parameterObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).insert();
            return session.insert(id, parameterObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public  Object insert(String id) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).insert();
            return session.insert(id);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public int update(String id, Object parameterObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).update();
            return session.update(id, parameterObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public int update(String id) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).update();
            return session.update(id);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public int delete(String id, Object parameterObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).delete();
            return session.delete(id, parameterObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public int delete(String id) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).delete();
            return session.delete(id);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public Object queryForObject(String id, Object parameterObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return session.queryForObject(id, parameterObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public Object queryForObject(String id) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return session.queryForObject(id);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public Object queryForObject(String id, Object parameterObject, Object resultObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return session.queryForObject(id, parameterObject, resultObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public List queryForList(String id, Object parameterObject) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return session.queryForList(id, parameterObject);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public List queryForList(String id) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return session.queryForList(id);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public List queryForList(String id, Object parameterObject, int skip, int max) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return session.queryForList(id, parameterObject, skip, max);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public List queryForList(String id, int skip, int max) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return session.queryForList(id, skip, max);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public void queryWithRowHandler(String id, Object parameterObject, RowHandler rowHandler) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            session.queryWithRowHandler(id, parameterObject, rowHandler);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public void queryWithRowHandler(String id, RowHandler rowHandler) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            session.queryWithRowHandler(id, rowHandler);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public PaginatedList queryForPaginatedList(String id, Object parameterObject, int pageSize) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return session.queryForPaginatedList(id, parameterObject, pageSize);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public PaginatedList queryForPaginatedList(String id, int pageSize) throws SQLException {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return session.queryForPaginatedList(id, pageSize);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public Map queryForMap(String id, Object parameterObject, String keyProp) throws SQLException  {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return session.queryForMap(id, parameterObject, keyProp);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public Map queryForMap(String id, Object parameterObject, String keyProp, String valueProp) throws SQLException  {
        try {
            SqlMapClientContext.ctx().setSqlId(id).select();
            return session.queryForMap(id, parameterObject, keyProp, valueProp);
        } finally {
            SqlMapClientContext.reset();
        }
    }

    @Override
    public void startBatch() throws SQLException {
        session.startBatch();
    }

    @Override
    public int executeBatch() throws SQLException {
        return session.executeBatch();
    }

    @Override
    public List executeBatchDetailed() throws SQLException, BatchException {
        return session.executeBatchDetailed();
    }

    @Override
    public void startTransaction() throws SQLException {
        session.startTransaction();
    }

    @Override
    public void startTransaction(int paramInt) throws SQLException {
        session.startTransaction(paramInt);
    }

    @Override
    public void commitTransaction() throws SQLException {
        session.commitTransaction();
    }

    @Override
    public void endTransaction() throws SQLException {
        session.endTransaction();
    }

    @Override
    public void setUserConnection(Connection connection) throws SQLException {
        session.setUserConnection(connection);
    }

    @Override
    public Connection getUserConnection() throws SQLException {
        return session.getUserConnection();
    }

    @Override
    public Connection getCurrentConnection() throws SQLException {
        return session.getCurrentConnection();
    }

    @Override
    public DataSource getDataSource() {
        return session.getDataSource();
    }

    @Override
    public void close() {
       session.close();
    }

}
