package com.lijiaxuan.project.service;

import com.lijiaxuan.project.domain.Shop;
import com.lijiaxuan.project.repository.ShopRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class ShopService {
    @Autowired
    ShopRespository shopRespository;
    @Autowired
    RedisTemplate redisTemplate;
    public List<Shop>  getTop5(){
        Set<Long> top5=new HashSet<>();

        redisTemplate.opsForZSet().reverseRange("top", 0, 4).forEach(x->{
            top5.add(new Long(x.toString()));
        });


        List<Shop> allByIdIn = shopRespository.findAllByIdIn(top5);
        Collections.sort(allByIdIn, new Comparator<Shop>() {
            @Override
            public int compare(Shop o1, Shop o2) {

                return - (new Integer(o1.getFive())-new Integer(o2.getFive()));
            }
        });
        return
            allByIdIn;



    }
    public List<Shop>  getLast5(){
        Set<Long> top5=new HashSet<>();
        redisTemplate.opsForZSet().reverseRange("last", 0, 4).forEach(x->{
            top5.add(new Long(x.toString()));
        });

        List<Shop> allByIdIn = shopRespository.findAllByIdIn(top5);
        Collections.sort(allByIdIn, new Comparator<Shop>() {
            @Override
            public int compare(Shop o1, Shop o2) {
                return -(new Integer(o1.getOne())-new Integer(o2.getOne()));
            }
        });
        return
            allByIdIn;




    }

}
