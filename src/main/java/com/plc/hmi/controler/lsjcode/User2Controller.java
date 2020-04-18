package com.plc.hmi.controler.lsjcode;

import com.plc.hmi.dal.entity.UserEntity;
import com.plc.hmi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class User2Controller {
    @Resource
    private UserService userService;
    @RequestMapping("/insertUser")
    public String insertUser(UserEntity userEntity){
        userService.insert(userEntity);
        return "accountManagement";
    }
    @RequestMapping("/updateOperatorRole")
    public String updateOperatorRole(long id, HttpServletRequest request){
        request.setAttribute("id",id);
        return "OperatorRole";
    }

}
