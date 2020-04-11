package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.ProductParameterEntity;
import com.plc.hmi.dal.mapper.ProductParameterMapper;
import com.plc.hmi.enumeration.ProductParameterEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class ProductParameterDao extends AbstractHmiBaseDao {
    @Resource
    ProductParameterMapper productParameterMapper;

    public List<ProductParameterEntity> getProductParameterWithProductCode(String prodctCode) {
        return this.getEntityList(productParameterMapper.getProductParameterWithProductCode(prodctCode));
    }

    @Override
    protected ProductParameterEntity getEntity(HashMap map) {
        ProductParameterEntity entity = new ProductParameterEntity();
        super.setEntityBase(entity, map);
//        entity.setProductCode(HmiUtils.getString(map.get(ProductParameterEntityEnum.PRODUCT_CODE.getCode())));
//        entity.setMaxDistance(HmiUtils.getBigDicimal(map.get(ProductParameterEntityEnum.MAX_DISTANCE.getCode())));
//        entity.setMaxForce(HmiUtils.getBigDicimal(map.get(ProductParameterEntityEnum.MAX_FORCE.getCode())));
//        entity.setMaxSpeed(HmiUtils.getBigDicimal(map.get(ProductParameterEntityEnum.MAX_SPEED.getCode())));
//        entity.setDefaultBackSpeed(HmiUtils.getBigDicimal(map.get(ProductParameterEntityEnum.DEFAULT_BACK_SPEED.getCode())));
        return entity;
    }

    public void insert(ProductParameterEntity entity) {
        productParameterMapper.insert(entity);
    }

    public void update(ProductParameterEntity entity) {
        productParameterMapper.update(entity);
    }

    public void delete(ProductParameterEntity entity) {
        productParameterMapper.delete(entity);
    }



}
