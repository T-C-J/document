package com.tcj.jpa.multiplesources.entity;

import com.tcj.jpa.multiplesources.domain.primary.Student;
import com.tcj.jpa.multiplesources.domain.secondary.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class Resp {
    private List<Student> students;
    private List<Teacher> teachers;
}
