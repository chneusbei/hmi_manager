package com.plc.hmi.dal.dao;

import com.plc.hmi.dal.entity.ProductEntity;
import com.plc.hmi.dal.mapper.ProductMapper;
import com.plc.hmi.enumeration.ProductEntityEnum;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Repository
public class ProductDao extends AbstractHmiBaseDao {
    @Resource
    ProductMapper productMapper;

    public ProductEntity getProduct(String productCode) {
        List<ProductEntity> productList = this.getEntityList(productMapper.getProduct(productCode));
        if(productList != null && !productList.isEmpty()) {
            return productList.get(0);
        } else {
            return null;
        }
    }


    @Override
    protected ProductEntity getEntity(HashMap map) {
        ProductEntity entity = new ProductEntity();
        super.setEntityBase(entity, map);
        entity.setProductCode(HmiUtils.getString(map.get(ProductEntityEnum.PRODUCT_CODE.getCode())));
        entity.setProductType(HmiUtils.getString(map.get(ProductEntityEnum.PRODUCT_TYPE.getCode())));
        return entity;
    }

    public void insert(ProductEntity entity) {
        productMapper.insert(entity);
    }

    public void delete(ProductEntity entity) {
        productMapper.delete(entity);
    }


}
