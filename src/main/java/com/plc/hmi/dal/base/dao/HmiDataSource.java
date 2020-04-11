package com.plc.hmi.dal.base.dao;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@MapperScan(value = "com.plc.hmi.dal.dim.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class HmiDataSource {

    //读取配置文件信息
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String password;


    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;


    @Primary
    @Bean(name = "dateSource")
    public DataSource dataSource() {
        return getDataSource(url, userName, password, driverClassName);
    }

    //实例化
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dateSource") DataSource ds) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);
        return sqlSessionFactoryBean.getObject();
    }

    //这里用到了HikariDataSource连接池，定义一个连接池
    private HikariDataSource getDataSource(String url, String userName, String password, String driverClassName) {
        final HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        ds.setDriverClassName(driverClassName);
        return ds;
    }

}