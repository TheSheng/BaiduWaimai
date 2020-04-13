package com.lijiaxuan.project.repository;

import com.lijiaxuan.project.domain.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginUserRespository extends JpaRepository<LoginUser,Integer> {
    LoginUser findByUsername(String username);
    LoginUser findByUsernameAndPassword(String username,String password);

}
