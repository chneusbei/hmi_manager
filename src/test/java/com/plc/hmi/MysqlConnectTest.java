package com.plc.hmi;
import com.plc.hmi.dal.entity.*;
import com.plc.hmi.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class MysqlConnectTest {

    @Autowired
    private DbConnectionService dbConnectionService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private UserService userService;
    @Autowired
    private PressureCurveService pressureCurveService;
    @Autowired
    private PressureProgramService pressureProgramService;
    @Autowired
    private ProductParameterService productParameterService;

    @Test
    public void testSql() {
        //        ProductParameterEntity  entity = new ProductParameterEntity();
//        entity.setId(1L);
//        entity.setCreateBy("chensubei");
//        entity.setProductCode("ProCode");
//        entity.setMaxDistance(new BigDecimal(4.1));
//        entity.setMaxForce(new BigDecimal(5.1));
//        entity.setMaxSpeed(new BigDecimal(10.1));
//        entity.setDefaultBackSpeed(new BigDecimal(6.1));
//        entity.setUpdateBy("admin");
//        List<ProductParameterEntity>  list =  productParameterService.getProductParameterWithProductCode("ProCode");
//        if( !list.isEmpty()) {
//            int size = list.size();
//        }
//        productParameterService.add(entity);
//        productParameterService.update(entity);
//        productParameterService.delete(entity);
//        PressureProgramEntity entity = new PressureProgramEntity();
//        entity.setId(1L);
//        entity.setCreateBy("chensubei");
//        entity.setProgramName("ProNameNew");
//        entity.setProgramType("T2");
//        entity.setStep(1);
//        entity.setProgramValue(new BigDecimal(10.4));
//        entity.setUpdateBy("admin");
////        pressureProgramService.add(entity);
//        pressureProgramService.update(entity);
//        List<PressureProgramEntity>  list= pressureProgramService.getPressureProgramWithName("ProName");
//        PressureCurveEntity entity = new PressureCurveEntity();
//        entity.setCreateBy("chensubei");
//        entity.setProductCode("PROD001");
//        entity.setPosition(new BigDecimal(101));
//        entity.setPressForce(new BigDecimal(10.3));
//        entity.setSpeed(new BigDecimal(75));
//        entity.setHandleDate("20200202");
//        pressureCurveService.insert(entity);
//        List<PressureCurveEntity>  list1= pressureCurveService.getPressureCurvesWithProductCode("PROD001");
//        List<PressureCurveEntity>  list2=pressureCurveService.getPressureCurveWithDate("20200201");
//        List<PressureCurveEntity>  list3= pressureCurveService.getPressureCurveWithDateStart("20200201");

        UserEntity entity = new UserEntity();
        entity.setUserName("admin");
        entity.setUserPassword("adminNew");
        entity.setUpdateBy("chensubei");
        entity.setCreateBy("chensubei");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
//        userService.addUser(entity);
//        userService.updateUserRole(entity);
        userService.updatePassword(entity);
//        userService.deleteUser(entity);
//        UserEntity userEntity =userService.getUser("admin");

//        PropertyEntity entity = new PropertyEntity();
//        entity.setPropGroup("groupT");
//        entity.setPropName("testPropNEW");
//        entity.setPropValue("testValueNEW");
//        entity.setUpdateBy("NEW");
//        entity.setCreateBy("chensubei");
//        entity.setCreateTime(new Date());
//        entity.setUpdateTime(new Date());
//        propertyService.update(entity);
//        propertyService.insert(entity);
//        List<PropertyEntity> list = propertyService.getProperties();
//        List<PropertyEntity> list2 = propertyService.getPropertyWithGroup("group1");

        //        List<PropertyEntity> propertyList =  propertyService.getProperty();
//        List<PropertyEntity> propertyList =  propertyService.getPropertyWithGroup("group2");
//        if(propertyList != null) {
//            System.out.println(propertyList.size());
//        }
//        System.out.println("");
    }

    public void testDbConnect() {
        /*
        Connection conn  = dbConnectionService.getConn();
        if(conn != null) {
            System.out.println("连接成功");
        } else {
            System.out.println("连接失败");
        }
        */

          /*
        String url = "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
        String user = "root";
        String password = "root";
        try {
            //1. 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获取连接
            Connection conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("连接成功");

         */
    }

}
