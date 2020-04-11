package com.plc.hmi.service;

import com.plc.hmi.util.PropertiesUtil;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DbConnectionService {
    private Connection connection = null;


    public Connection getConn(){
        if(null != connection) {
            return  connection;
        }
        PropertiesUtil.loadFile("dbConfig.properties");
        String driver = PropertiesUtil.getPropertyValue("driver");
        String url = PropertiesUtil.getPropertyValue("url");
        String username  = PropertiesUtil.getPropertyValue("username");
        String password = PropertiesUtil.getPropertyValue("password");

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return connection;
    }


    public  void close(){
        try {
            if(null != connection ) {
                connection.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
