package com.plc.hmi.service;

import com.plc.hmi.dal.dao.ProductParameterDao;
import com.plc.hmi.dal.entity.ProductParameterEntity;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 这个service已经废弃
 */
@Service
@Component
public class ProductParameterService extends AbstractBaseService{
    /*
    @Resource
    ProductParameterDao productParameterDao;

    public List<ProductParameterEntity> getProductParameterWithProductCode(String productCode) {
        return productParameterDao.getProductParameterWithProductCode(productCode);
    }

    public void insert(ProductParameterEntity entity) {
        productParameterDao.insert(entity);
    }

    public void update(ProductParameterEntity entity) {
        productParameterDao.update(entity);
    }

    public void delete(ProductParameterEntity entity) {
        productParameterDao.delete(entity);
    }
*/
}
