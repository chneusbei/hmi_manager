package com.plc.hmi.controler;

import com.plc.hmi.dal.entity.UserEntity;
import com.plc.hmi.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hmi/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping//查
    public UserEntity getList(@PathVariable("userName") String userName) {
        return userService.getUser(userName);
    }

    @PostMapping//改和增
    public void addUser(@RequestBody UserEntity user) {
        userService.insert(user);
    }

    @DeleteMapping(value = "/{uid}")//删
    public void delUser(@PathVariable("uid") Integer uid
    ) {
//        userService.deleteUser(uid);
    }
}