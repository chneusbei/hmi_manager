package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.PropertyEntity;

import java.util.List;

//@Repository
public interface PropertyDao_bak {

    /**
     * insert
     * @param record
     * @return
     */
    public void insert(PropertyEntity record);

    /**
     * 更新
     * @param record
     * @return
     */
    public int updateById(PropertyEntity record);

    /**
     * 查询
     * @param id
     * @return
     */
    public PropertyEntity queryByid(Long id) ;

    /**
     * 查询
     * @param propName
     * @return
     */
    public PropertyEntity queryByName(String propName);

    /**
     * 按组查询
     * @param propGroup
     * @return
     */
    public List<PropertyEntity> queryByGroup(String propGroup);

}
