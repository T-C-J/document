package com.tcj.lock.zookeeperlock.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "APPLE")
@Entity
@Data
public class Apple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private Integer number;

}
