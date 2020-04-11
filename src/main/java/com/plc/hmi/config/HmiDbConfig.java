package com.plc.hmi.config;

import com.plc.hmi.dal.base.dao.HmiSqlMapClientFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
public class HmiDbConfig {
//    @Bean(name = "dataSource")
//    @Qualifier("dataSource")
//    @ConfigurationProperties(prefix="spring.datasource")
//    public DataSource getDataSource() {
//        return DataSourceBuilder.create().build();
//    }

//    public HmiSqlMapClientFactoryBean getHmiSqlMapClientFactoryBean() {
//        HmiSqlMapClientFactoryBean hmiSqlMapClientFactoryBean = new HmiSqlMapClientFactoryBean();
////        hmiSqlMapClientFactoryBean.setConfigLocation();
//        hmiSqlMapClientFactoryBean.setDataSource(getDataSource());
////        hmiSqlMapClientFactoryBean.setMappingLocations();
//        return hmiSqlMapClientFactoryBean;
//    }
}
