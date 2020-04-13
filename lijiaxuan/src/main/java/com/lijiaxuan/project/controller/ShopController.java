package com.lijiaxuan.project.controller;

import com.alibaba.fastjson.JSONPObject;
import com.lijiaxuan.project.domain.Shop;
import com.lijiaxuan.project.service.OrderService;
import com.lijiaxuan.project.service.ShopService;

import com.lijiaxuan.project.vo.ApiResponse;
import com.lijiaxuan.project.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.*;

@RestController
public class ShopController {
    @Autowired
    RedisTemplate  redisTemplate;
    @Autowired
    ShopService  shopService;
    @Autowired
    OrderService orderService;
    @GetMapping("/zhanbi")
    public Map getZhanbi(){
        return  redisTemplate.opsForHash().entries("zong");
    }
    @GetMapping("/gaoxiaozhanbi")
    public Map  getSchoolZhanbi(){
        Map schoolZhanbi = redisTemplate.opsForHash().entries("schoolZhanbi");
        Map<String, List> rs=new HashMap();
        rs.put("keys",new LinkedList(schoolZhanbi.keySet()));
        List<Double> key1=new LinkedList<>();

        schoolZhanbi.keySet().forEach(x->{
            DecimalFormat df = new DecimalFormat("#.00");
            String[] split = schoolZhanbi.get(x).toString().split(":");
            Double rank = ((new Double(split[0])) * 100) / new Double(split[1]).floatValue();
            key1.add(new Double(df.format(rank)));


        });
        rs.put("key1",key1);

        return  rs;

    }
    @GetMapping("/top")
    public  List<Shop> getTop(){
        return    shopService.getTop5();
    }
    @GetMapping("/last")
    public  List<Shop> getLast(){
        return    shopService.getLast5();
    }
//    @GetMapping("/demo")
//    public Order demo() throws  Exception{
//      return orderService.getAdd();
//    }

}
