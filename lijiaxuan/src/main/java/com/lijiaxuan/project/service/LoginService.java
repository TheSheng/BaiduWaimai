package com.lijiaxuan.project.service;

import com.lijiaxuan.project.domain.LoginUser;
import com.lijiaxuan.project.repository.LoginUserRespository;
import com.lijiaxuan.project.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginUserRespository loginUserRespository;

     public void save(LoginUser loginUser){
         loginUserRespository.save(loginUser);
     }
    public Response<String> register(LoginUser loginUser){
        LoginUser user = loginUserRespository.findByUsername(loginUser.getUsername());
        if(null==user){
           loginUserRespository.save(loginUser);
           return new Response<String>("注册成功",200);


        }else{


            return new Response<String>("已被注册！",500);
        }

    }
    public Response<LoginUser> login(LoginUser loginUser){
        LoginUser login = loginUserRespository.findByUsernameAndPassword(loginUser.getUsername(), loginUser.getPassword());
        if(null==login){
            return  new Response<LoginUser>(null,500);
        }
        else{
            return  new Response<LoginUser>(login,200);

        }
    }



}
