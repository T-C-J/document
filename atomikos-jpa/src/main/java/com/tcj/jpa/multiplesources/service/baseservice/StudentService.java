package com.tcj.jpa.multiplesources.service.baseservice;

import com.tcj.jpa.multiplesources.domain.primary.Student;

import java.util.List;

public interface StudentService {

    void add(Student student);

    Student select(Student student);

    List<Student> selectAll();
}
