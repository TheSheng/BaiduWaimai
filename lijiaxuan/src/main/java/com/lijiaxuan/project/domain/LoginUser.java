package com.lijiaxuan.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//告诉jpa这是对应一个表的实体类
@Entity
@Table(name = "loginUser")
//lombok  不用写getset
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "userName")
    private String username;

    @Column(name = "passWord")
    private String password;


    @Column(name="imgUrl",columnDefinition = "longtext")

    private  String imgUrl;



}
