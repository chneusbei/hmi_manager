package com.plc.hmi.controler.lsjcode;

import com.plc.hmi.dal.entity.UserEntity;
import com.plc.hmi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Resource
    UserService userService;
    @RequestMapping("loginUser")
    public String loginUser(String username, String password, HttpServletRequest request){
        UserEntity userEntity=userService.loginUser(username,password);
        if(userEntity!=null){
            request.getSession().setAttribute("userid",userEntity.getId());
            return "index";
        }else{
            return "logins";
        }

    }
    @RequestMapping("/UserQuery")
    @ResponseBody
    public UserEntity UserQuery(String username, String password){
        UserEntity userEntity=userService.loginUser(username,password);
        return userEntity;
    }
    @RequestMapping("/createUser")
    public String insertUser(int id,String name,String pwd,String createname){
        UserEntity userEntity=new UserEntity();
        userEntity.setRoleId(id);
        userEntity.setUserName(name);
        userEntity.setCreateBy(createname);
        userEntity.setUserPassword(pwd);

        UserEntity userEntity1=userService.getUser(name);
        if(userEntity1==null){
            userService.insert(userEntity);
        }
        return "logins";
    }
    @RequestMapping("/UserFlag")
    @ResponseBody
    public String flag(String uname){
        UserEntity userEntity1=userService.getUser(uname);
        if(userEntity1==null){
           return "创建成功！";
        }else{
            return "用户名已存在，创建失败！";

        }
    }
}
