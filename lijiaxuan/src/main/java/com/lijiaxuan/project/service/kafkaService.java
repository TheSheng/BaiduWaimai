package com.lijiaxuan.project.service;

import com.alibaba.fastjson.JSON;
import com.lijiaxuan.project.domain.Shop;
import com.lijiaxuan.project.repository.ShopRespository;
import com.mysql.cj.log.LogFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Optional;


@Component
@EnableScheduling
@EnableAsync
@Slf4j
public class kafkaService {
    @Autowired
    KafkaTemplate kafkaTemplate;
    @Autowired
    ShopRespository shopRespository;
    @Autowired
    RedisTemplate redisTemplate;

//    @Async
//    @Scheduled(cron = "0/2 * * * *  ?")
//    public void send(){
//         //从redis拿索引
//
//
//        Object rs = redisTemplate.opsForValue().get("shopIndex");
//        //把rs赋值给index
//        Long index=null==rs?0l:new Long(rs.toString());
//
//        //
//        if(index<shopRespository.findMaxId()) {
//            index=index+1;
//            redisTemplate.opsForValue().set("shopIndex",index.toString());
//
//            Shop shop = shopRespository.findById(index).get();
//
//
//            ListenableFuture send = kafkaTemplate.send("test", JSON.toJSONString(shop));
//            send.addCallback(success-> System.out.println("发送成功"), throwable -> System.out.println("失败")
//            );
//        }
//    }

}
