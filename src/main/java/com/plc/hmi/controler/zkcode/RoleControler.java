package com.plc.hmi.controler.zkcode;

import com.plc.hmi.dal.entity.UserRoleEntity;
import com.plc.hmi.service.UserRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RoleControler {

    @Resource
    private UserRoleService userRoleService;
    @ResponseBody
    @GetMapping("getRole")
    public List<UserRoleEntity> getRole(){
        List<UserRoleEntity> userRoleList = userRoleService.getUserRoleList();
        return userRoleList;
    }
}
