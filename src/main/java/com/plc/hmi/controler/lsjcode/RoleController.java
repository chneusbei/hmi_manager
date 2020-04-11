package com.plc.hmi.controler.lsjcode;

import com.plc.hmi.dal.entity.UserRoleEntity;
import com.plc.hmi.service.UserRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RoleController {
    @Resource
    UserRoleService userRoleService;
    @RequestMapping("/queryRole")
    @ResponseBody
    public List<UserRoleEntity> queryRole(){
        return userRoleService.getUserRoleList();
    }
}
