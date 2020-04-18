package com.plc.hmi.controler.zkcode;

import com.github.pagehelper.PageInfo;
import com.plc.hmi.dal.entity.UserEntity;
import com.plc.hmi.service.UserService;
import com.plc.hmi.util.HmiUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class UserControler {

    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping("/getUserList")
    public PageInfo<UserEntity> getUserList(@RequestParam (value = "userName") String userName,
                                            @RequestParam (value = "index")Integer index){

        return  userService.getUserList(userName,index);
    }


    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam Map<String,Object> map){
        Object o = map.get("id");
        Object o1 = map.get("updateBy");
        UserEntity userEntity =new UserEntity();
        userEntity.setId(o!=null?Long.valueOf(o.toString()):null);
        userEntity.setUpdateBy(o1!=null?o1.toString():"1");
        userService.delete(userEntity);
        return "accountManagement";
    }

    @ResponseBody
    @RequestMapping("/getUserSingle")
    public UserEntity operatorRole(@RequestParam("id") Long id){

        UserEntity user = userService.getUser(id);
        return user;
    }

    @RequestMapping("/updateUser")
    public String updateUser(String userName,String userPassword,int rid,long uid, HttpServletRequest request){
        userService.updateUser(rid,uid,userName,userPassword);
        return "accountManagement";
    }


    @RequestMapping("/addUser")
    public String addUser(int roleId,String userName,String userPassWord,String createby){
        UserEntity userEntity=new UserEntity();
        userEntity.setRoleId(roleId);
        userEntity.setCreateBy(createby);
        userEntity.setUserPassword(userPassWord);
        userEntity.setUserName(userName);
        UserEntity userEntity1=userService.getUser(userName);
        if(userEntity1==null){
            userService.insert(userEntity);
            return "accountManagement";
        }else{
            return "createAnAccount";
        }

    }
}
