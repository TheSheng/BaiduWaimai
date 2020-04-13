package com.lijiaxuan.project.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String time;
    private  String sex;
    private String name;
    private Integer age;
    private String  job;
    private String  tel;

    private Integer  money;
    private String  taste;
    private String  cafe;
    private String sys;
    private String  city;



}
