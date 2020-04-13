package com.lijiaxuan.project.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "shop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "logo")
    private String logo;
    @Column(name = "name")
    private String name;
    @Column(name = "peisongfei")
    private String peisongfei;
    @Column(name = "qisongjia")
    private String qisongjia;
    @Column(name = "time")
    private String time;
    @Column(name = "rate")
    private String rate;
    @Column(name = "address")
    private String address;
    @Column(name = "yingyetime")
    private String yingyetime;
    @Column(name = "five")
    private String five;
    @Column(name = "four")
    private String four;
    @Column(name = "three")
    private String three;
    @Column(name = "two")
    private String two;
    @Column(name = "one")
    private String one;
    @Column(name = "type")
    private String type;


}
