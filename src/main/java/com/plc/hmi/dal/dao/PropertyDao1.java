package com.plc.hmi.dal.dao;

import com.google.common.collect.ImmutableMap;
import com.plc.hmi.dal.entity.PropertyEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//@Repository
public class PropertyDao1 extends AbstractDimDao<PropertyEntity> {
    private static final String  NAME_SPACE = "Property";
    private static final String  INSERT = "insert";
    private static final String  UPDATE_BY_ID = "updateById";
    private static final String  QUERY_BY_ID = "queryById";
    private static final String  QUERY_BY_PROPERTY_NAME = "queryByPropertyName";
    private static final String  QUERY_BY_PROPERTY_GROUP = "queryByPropertyGroup";
    private static final Logger logger = LoggerFactory.getLogger(PropertyDao1.class);

    @Override
    protected String nameSpace() {
        return NAME_SPACE;
    }

    /**
     * insert
     * @param record
     * @return
     */
    public PropertyEntity insert(PropertyEntity record) {
        return super.insert(INSERT, record);
    }

    /**
     * 更新
     * @param record
     * @return
     */
    public int updateById(PropertyEntity record) {
        return super.update(UPDATE_BY_ID, record);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    public PropertyEntity queryByid(Long id) {
        return super.query(QUERY_BY_ID, ImmutableMap.of("id",id));
    }

    /**
     * 查询
     * @param propName
     * @return
     */
    public PropertyEntity queryByName(String propName) {
        return super.query(QUERY_BY_PROPERTY_NAME, ImmutableMap.of("propName",propName));
    }

    /**
     * 按组查询
     * @param propGroup
     * @return
     */
    public List<PropertyEntity> queryByGroup(String propGroup){
        return super.queryForList(QUERY_BY_PROPERTY_GROUP, ImmutableMap.of("propGroup",propGroup));
    }

}
