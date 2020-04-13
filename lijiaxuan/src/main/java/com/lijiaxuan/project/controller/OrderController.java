package com.lijiaxuan.project.controller;

import com.lijiaxuan.project.service.OrderService;
import com.lijiaxuan.project.vo.Order;
import com.lijiaxuan.project.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController

public class OrderController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    OrderService orderService;

    public  String getDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return  df.format(new Date());
    }


    @GetMapping("/income")
    public Response<String> getIncome(){
       Double allIncome=redisTemplate.opsForZSet().score("income","incomeAll");
       String date=getDate();
       Double dateIncome=redisTemplate.opsForZSet().score("income",date+"income");

       return  new Response<String>(allIncome.intValue()+":"+dateIncome.intValue(),200);

    }
    @GetMapping("/minuteSys/{minute}")

    public Response<String> getSysMinute(@PathVariable String minute){
        Double anValue=redisTemplate.opsForZSet().score(minute+"sys","安卓");
        Double iosValue=redisTemplate.opsForZSet().score(minute+"sys","ios");
         try {
             return  new Response<String>(anValue.intValue()+":"+iosValue.intValue(),200);
         }catch (Exception e){
             return  new Response<String>("0:0",200);
         }



    }
    @GetMapping("/getCityIncome")
    public List<List<String>>  getCityIncome(){
        Set<ZSetOperations.TypedTuple> cityIncome = redisTemplate.opsForZSet().reverseRangeWithScores("cityIncome",0,6);
        List<List<String>> rs=new LinkedList<>();
        List<String> provinces=new LinkedList<>();
        List<String> values=new LinkedList<>();
        cityIncome.forEach(x->{
            provinces.add(x.getValue().toString());
            values.add(x.getScore().toString());
        });
        rs.add(provinces);
        rs.add(values);
        return  rs;


    }
    @GetMapping("/getChina")
    public Map<String,Integer> getChina(){
        Set<ZSetOperations.TypedTuple> cityIncome = redisTemplate.opsForZSet().reverseRangeWithScores("cityRank",0,-1);
        Map<String,Integer> china=new HashMap<>();
        cityIncome.forEach(x->{
            china.put(x.getValue().toString(),x.getScore().intValue());
        });

        return  china;


    }
    @GetMapping("/getCityCount")
    public List<List<String>>  getCityCount(){
        Set<ZSetOperations.TypedTuple> cityIncome = redisTemplate.opsForZSet().reverseRangeWithScores("cityRank",0,6);
        List<List<String>> rs=new LinkedList<>();
        List<String> provinces=new LinkedList<>();
        List<String> values=new LinkedList<>();
        cityIncome.forEach(x->{
            provinces.add(x.getValue().toString());
            String s = x.getScore().toString();
             s = s.substring(0, s.lastIndexOf("."));
            values.add(s);
        });
        rs.add(provinces);
        rs.add(values);
        return  rs;


    }
    @GetMapping("/getIndexSex/{hour}")
    public  List<List> getIndexSex(@PathVariable String hour){
       Set set=redisTemplate.opsForZSet().reverseRange(hour+"cityNum", 0, 12);
       List rs=new LinkedList();
       List key=new LinkedList();
       List nan=new LinkedList();
        List nv=new LinkedList();
       set.forEach(x->{
           key.add(x.toString());
           Double nano=redisTemplate.opsForZSet().score(hour+"男",x.toString());
           Double nvo=redisTemplate.opsForZSet().score(hour+"女",x.toString());
         nan.add(null==nano?0:nano.intValue());
           nv.add(null==nvo?0:nvo.intValue());

       });
       rs.add(key);
       rs.add(nan);
       rs.add(nv);
       return  rs;

    }
    @GetMapping("/getLastCity")
    public  List<List> getLastCity(){
        Set set=redisTemplate.opsForZSet().range("cityRank", 0, 10);
        List rs=new LinkedList();
        List key=new LinkedList();
        List count=new LinkedList();
        List income=new LinkedList();
        set.forEach(x->{
            key.add(x);
           try {
               count.add(redisTemplate.opsForZSet().score("cityRank",x.toString()).intValue());

               income.add(redisTemplate.opsForZSet().score("cityIncome",x.toString()).intValue());
           }catch (Exception e){
               count.add(0);
               income.add(0);
           }

        });
        rs.add(key);
        rs.add(count);
        rs.add(income);
        return  rs;

    }
    @GetMapping("/getIndexJob/{hour}")
    public  Map getIndexJob(@PathVariable String hour){
        Map<String,Integer> map= new HashMap<>();
        Set<ZSetOperations.TypedTuple> ages = redisTemplate.opsForZSet().rangeWithScores(hour+"jobNum", 0, -1);
        ages.forEach(x->{
            map.put(x.getValue().toString(),x.getScore().intValue());
        });
        return  map;

    }
    @GetMapping("/getIndexCity/{hour}")
    public  Map getIndexCity(@PathVariable String hour){
        Map<String,Integer> map= new HashMap<>();
        Set<ZSetOperations.TypedTuple> ages = redisTemplate.opsForZSet().reverseRangeWithScores(hour+"cityNum", 0, 4);
        ages.forEach(x->{
            map.put(x.getValue().toString(),x.getScore().intValue());
        });
        return  map;

    }

    @GetMapping("/getAge")
    public  Map getAge(){
        Map<String,Integer> map= new HashMap<>();
        Set<ZSetOperations.TypedTuple> ages = redisTemplate.opsForZSet().rangeWithScores("ages", 0, -1);
        ages.forEach(x->{
            map.put(x.getValue().toString(),x.getScore().intValue());
        });
        return  map;

    }
    @GetMapping("/getJob")
    public  Map getJob(){
        Map<String,Integer> map= new HashMap<>();
        Set<ZSetOperations.TypedTuple> ages = redisTemplate.opsForZSet().rangeWithScores("jobs", 0, -1);
        ages.forEach(x->{
            map.put(x.getValue().toString(),x.getScore().intValue());
        });
        return  map;

    }
    @GetMapping("/getSex")
    public  Map getSex(){
        Map<String,Integer> map= new HashMap<>();
        Set<ZSetOperations.TypedTuple> ages = redisTemplate.opsForZSet().rangeWithScores("sex", 0, -1);
        ages.forEach(x->{
            map.put(x.getValue().toString(),x.getScore().intValue());
        });
        return  map;

    }

    @GetMapping("/getHourIncome/{hour}")
    public  List<List<String>> getHourIncome(@PathVariable String hour){
        Set<ZSetOperations.TypedTuple> cityIncome = redisTemplate.opsForZSet().reverseRangeWithScores(hour+"cityIncome",0,6);
        List<List<String>> rs=new LinkedList<>();
        List<String> provinces=new LinkedList<>();
        List<String> values=new LinkedList<>();
        cityIncome.forEach(x->{
            provinces.add(x.getValue().toString());
            String s = x.getScore().toString();
            s = s.substring(0, s.lastIndexOf("."));
            values.add(s);
        });
        rs.add(provinces);
        rs.add(values);
        return  rs;
    }
    @GetMapping("/getHourTaste/{hour}")
    public  List<List<String>> getHourTaste(@PathVariable String hour){
        Set<ZSetOperations.TypedTuple> cityIncome = redisTemplate.opsForZSet().reverseRangeWithScores(hour+"tasteNum",0,6);
        List<List<String>> rs=new LinkedList<>();
        List<String> provinces=new LinkedList<>();
        List<String> values=new LinkedList<>();
        cityIncome.forEach(x->{
            provinces.add(x.getValue().toString());
            String s = x.getScore().toString();
            s = s.substring(0, s.lastIndexOf("."));
            values.add(s);
        });
        rs.add(provinces);
        rs.add(values);
        return  rs;
    }
    @GetMapping("/getNewOrder")
    public Order getNewOrder() throws Exception{
        return  orderService.getNewOrder();
    }

}
