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
public class ProductDao {
    @Resource
    ProductMapper productMapper;

    public ProductEntity getProduct(String productCode) {
        return productMapper.getProduct(productCode);
    }

    public List<ProductEntity> getProducts() {
        return productMapper.getProducts();
    }



    public void insert(ProductEntity entity) {
        productMapper.insert(entity);
    }

    public void delete(ProductEntity entity) {
        productMapper.delete(entity);
    }


}
