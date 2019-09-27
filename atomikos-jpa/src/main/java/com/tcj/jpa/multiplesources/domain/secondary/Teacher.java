package com.tcj.jpa.multiplesources.domain.secondary;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TEACHER")
@Data
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "class")
    private String clazz;
    @Column(name = "student")
    private String student;
    @Column(name = "age")
    private Integer age;
}
