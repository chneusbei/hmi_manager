package com.plc.hmi.dal.base.dao;

import com.ibatis.sqlmap.client.SqlMapClient;
import org.springframework.core.io.Resource;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;

import java.io.IOException;
import java.util.Properties;

public class HmiSqlMapClientFactoryBean extends SqlMapClientFactoryBean {
    protected SqlMapClient buildSqlMapClient(Resource[] configLocations,
                                             Resource[] mappingLocations,
                                             Properties properties) throws IOException {
        SqlMapClient client =super.buildSqlMapClient(configLocations,mappingLocations,properties);
        return new WrappedSqlMapClient(client);
    }
}
