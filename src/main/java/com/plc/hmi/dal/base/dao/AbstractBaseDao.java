package com.plc.hmi.dal.base.dao;

import com.plc.hmi.dal.entity.base.AbstractBaseEntity;
import org.springframework.orm.ibatis.SqlMapClientTemplate;

import java.util.List;
import java.util.Map;

public abstract class AbstractBaseDao<T extends AbstractBaseEntity> {
    //书库批量操作条数
    public static  final  int DB_BATCH_SIZE =1000;
    //Spring的数据库操作对象
    protected abstract SqlMapClientTemplate getSqlMapClientTemplate();
    //命名空间
    protected abstract String  nameSpace();

    /**
     *  获取真正的SQLID
     */

    private String getSqlId(String sqlId) {
        return String.format("%s.%s",nameSpace(), sqlId);
    }

    /**
     * 插入
     */
    protected T insert(String sqlId,T dto) {
        String sqlString =getSqlId(sqlId);
        dto.setId((Long) getSqlMapClientTemplate().insert(sqlString, dto));
        return dto;
    }

    /**
     * 插入
     */
    protected void insert(String sqlId, Map<String, Object> param) {
        String sqlString =getSqlId(sqlId);
       getSqlMapClientTemplate().insert(sqlString, param);
    }
    /**
     * 批量修改入库 ， 如需要事务， 需要在调用处添加事务
     * @param sqlId sql语句id
     * @param list 需要更新的对象列表
     * @return 批量更新结果
     */
    protected DaoBatchResult batchInsert(final String sqlId, final List<T> list){
        final DaoInnerParan param = new DaoInnerParan();
        int num = calculateBatchNum(list);
        int successCnt = 0;
        DaoBatchResult daoBatchResult = new DaoBatchResult();
        try {
            for(int i=0; i<num; ++i){
                successCnt += (int) getSqlMapClientTemplate().execute(executor ->{
                    int successCnt1 = 0;
                    executor.startBatch();
                    //到达第一个批次最大值即执行
                    int i1 = param.getIndex();
                    for(;i1<param.getIndex()+DB_BATCH_SIZE && i1<list.size(); ++i1) {
                        executor.insert(getSqlId(sqlId), list.get(i1));
                        successCnt1++;
                    }
                    executor.executeBatch();
                    param.setIndex(i1);
                    return (Object) successCnt1;
                });
                daoBatchResult.setSuccessCnt(successCnt);
            }
            daoBatchResult.setResult(true);
        }catch(Exception e) {
            daoBatchResult.setResult(false);
            daoBatchResult.setException(e);
        }

        return daoBatchResult;
    }


    /**
     * 批量修改入库 ， 如需要事务， 需要在调用处添加事务
     * @param sqlId sql语句id
     * @param list 需要更新的对象列表
     * @return 批量更新结果
     */
    protected DaoBatchResult batchUpdate(final String sqlId, final List<T> list){
        final DaoInnerParan param = new DaoInnerParan();
        int num = calculateBatchNum(list);
        int successCnt = 0;
        DaoBatchResult daoBatchResult = new DaoBatchResult();
        try {
            for(int i=0; i<num; ++i){
                successCnt += (int) getSqlMapClientTemplate().execute(executor ->{
                    int successCnt1 = 0;
                    executor.startBatch();
                    //到达第一个批次最大值即执行
                    int i1 = param.getIndex();
                    for(;i1<param.getIndex()+DB_BATCH_SIZE && i1<list.size(); ++i1) {
                        executor.insert(getSqlId(sqlId), list.get(i1));
                        successCnt1++;
                    }
                    executor.executeBatch();
                    param.setIndex(i1);
                    return (Object) successCnt1;
                });
                daoBatchResult.setSuccessCnt(successCnt);
            }
            daoBatchResult.setResult(true);
        }catch(Exception e) {
            daoBatchResult.setResult(false);
            daoBatchResult.setException(e);
        }

        return daoBatchResult;
    }

    /**
     * 修改
     * @param sqlId sql 语句 ID
     * @param dto 需要更新的对象
     * @return 更新成功的条数
     */
    protected int update (String sqlId, T dto) {
        String  str = getSqlId(sqlId) ;
        return getSqlMapClientTemplate().update(str, dto);
    }

    /**
     *
     * @param sqlId sql 语句 ID
     * @param param 更新的条件和更新的字段
     * @return 更新成功的条数
     */
    protected int update (String sqlId, Map<String, Object> param) {
        String  str = getSqlId(sqlId) ;
        return getSqlMapClientTemplate().update(str, param);
    }

    /**
     * 单条查询
     * @param sqlId
     * @param param 查询条件
     * @return 查询结果
     */
    protected  T query(String sqlId,   Object  param) {
        String  str = getSqlId(sqlId) ;
        return (T) getSqlMapClientTemplate().queryForObject(str, param);
    }

    /**
     * 查询一个非对象结果
     * @param sqlId
     * @param param 查询条件
     * @return 查询结果
     */
    protected Object queryForObject(String sqlId,  Object  param) {
        String  str = getSqlId(sqlId) ;
        return getSqlMapClientTemplate().queryForObject(str, param);
    }

    /**
     * 批量查询
     * @param sqlId
     * @param param 查询条件
     * @return 查询结果
     */
    protected List<T> queryForList(String sqlId,  Object  param) {
        String  str = getSqlId(sqlId) ;
        return getSqlMapClientTemplate().queryForList(str, param);
    }


    /**
     * 批量查询
     * @param sqlId
     * @param param 查询条件
     * @return 查询结果
     */
    protected <R> List<R> queryForObjectList(String sqlId,  Object  param) {
        String  str = getSqlId(sqlId) ;
        return getSqlMapClientTemplate().queryForList(str, param);
    }




    private int calculateBatchNum(List<T> list) {
        int num = list.size()/DB_BATCH_SIZE;
        if(list.size()%DB_BATCH_SIZE > 0) {
            num++;
        }
        return num;
    }


    public static class DaoBatchResult {
        private boolean result = false;
        private Throwable exception;
        private int successCnt = 0;
        public boolean isResult() {
            return result;
        }
        public void setResult(boolean result) {
            this.result = result;
        }

        public Throwable getException() {
            return exception;
        }

        public void setException(Throwable exception) {
            this.exception = exception;
        }

        public int getSuccessCnt() {
            return successCnt;
        }

        public void setSuccessCnt(int successCnt) {
            this.successCnt = successCnt;
        }
    }

    public static class DaoInnerParan {
        private int index =0;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

}
