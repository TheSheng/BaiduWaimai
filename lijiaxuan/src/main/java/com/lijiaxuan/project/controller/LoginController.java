package com.lijiaxuan.project.controller;

import com.lijiaxuan.project.domain.LoginUser;
import com.lijiaxuan.project.service.LoginService;
import com.lijiaxuan.project.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    LoginService loginService;


    @PostMapping("/login")
    public Response<LoginUser> login(@RequestBody LoginUser loginUser){
        return  loginService.login(loginUser);


    }
    @PostMapping("/user")
    public void  gai(@RequestBody LoginUser loginUser){
         loginService.save(loginUser);
    }
    @PostMapping("/register")
    public Response<String> register(@RequestBody LoginUser loginUser){
        return  loginService.register(loginUser);


    }


}
