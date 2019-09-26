package com.tcj.jpa.multiplesources.entity.primary;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "STUDENT")
@Data
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "class")
    private String clazz;
    @Column(name = "teacher")
    private String teacher;
    @Column(name = "age")
    private Integer age;
}
