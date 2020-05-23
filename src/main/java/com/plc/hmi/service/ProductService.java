package com.plc.hmi.service;

import com.plc.hmi.dal.dao.ProductDao;
import com.plc.hmi.dal.entity.ProductEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 产品信息服务
 * 暂时没有使用
 */
@Service
@Component
public class ProductService extends AbstractBaseService{
    @Resource
    ProductDao productDao;
    private static Map<String, ProductEntity> productMap;
    public ProductEntity  getProduct(String productCode) {
        return productDao.getProduct(productCode);
    }
    public List<ProductEntity> getProducts() {
        return productDao.getProducts();
    }
    public ProductEntity getStaticProduct(String productId) {
        if(CollectionUtils.isEmpty(productMap)) {
            List<ProductEntity> productList = productDao.getProducts();
            productMap = new HashMap<String, ProductEntity>();
            for(ProductEntity entity : productList) {
                productMap.put(entity.getProductId(), entity);
            }
        }
        return  productMap.get(productId);
    }

    public void insert(ProductEntity entity) {
        productDao.insert(entity);
    }

    public void delete(ProductEntity entity) {
        productDao.delete(entity);
    }
}
