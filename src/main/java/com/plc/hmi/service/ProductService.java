package com.plc.hmi.service;

import com.plc.hmi.dal.dao.ProductDao;
import com.plc.hmi.dal.entity.ProductEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * 产品信息服务
 * 暂时没有使用
 */
@Service
@Component
public class ProductService extends AbstractBaseService{
    @Resource
    ProductDao productDao;

    public ProductEntity  getProduct(String productCode) {
        return productDao.getProduct(productCode);
    }

    public void insert(ProductEntity entity) {
        productDao.insert(entity);
    }

    public void delete(ProductEntity entity) {
        productDao.delete(entity);
    }
}
