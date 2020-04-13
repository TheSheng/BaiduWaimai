package com.lijiaxuan.project.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.lijiaxuan.project.service.util.NameUtil;
import com.lijiaxuan.project.vo.ApiResponse;
import com.lijiaxuan.project.vo.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service
@EnableScheduling
@EnableAsync
@Slf4j

public class OrderService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    KafkaTemplate kafkaTemplate;


  //前端每次请求产生一条新订单
    public  Order getNewOrder() throws Exception{
        List<String> list=NameUtil.getAddr();


        Random random=new Random();
        String city=list.get(random.nextInt(list.size()));
        Integer age= NameUtil.getAge();
        String job=NameUtil.zhiye();
        Integer money=NameUtil.qian();
        String taste=NameUtil.kouwei();
        String cafe=NameUtil.fandain();
        String sys=NameUtil.getPhone();
        String tel=NameUtil.getTel();
        String name=NameUtil.getChineseName();
        String sex=NameUtil.getSex();
        String time=NameUtil.time();

        Order order = new Order(time,sex,name, age, job, tel, money, taste, cafe, sys, city);

        File fout = new File("order.txt");
        if(!fout.exists()){
            fout.createNewFile();

        }
        String json = JSON.toJSONString(order);
        FileOutputStream fos = new FileOutputStream(fout,true);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        bw.write(json);
        bw.newLine();

        bw.close();
        ListenableFuture order1 = kafkaTemplate.send("order", json);
        order1.addCallback(y-> System.out.println("成功"),throwable -> {
            log.error("发送失败");
        });
        return  order;


    }

    //读取数据机所有内容
//    @Scheduled(cron = "30 8 18 * *  ?")
//    public  void  sendMessage() throws Exception{
//        Path data=Paths.get("order.txt");
//        List<String> list = Files.readAllLines(data);
//        list.forEach(x->{
//            ListenableFuture order = kafkaTemplate.send("order", x);
//            order.addCallback(y-> System.out.println("成功"),throwable -> {
//                log.error("发送失败");
//            });
//            try {
//                Thread.sleep(1000);
//            }catch (InterruptedException e){
//                log.error(e.getMessage());
//            }
//
//
//        });
//
//    }

}
