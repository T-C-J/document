package com.tcj.jpa.multiplesources.service.baseservice;

import com.tcj.jpa.multiplesources.domain.secondary.Teacher;

import java.util.List;

public interface TeacherService {

    void add(Teacher teacher);

    Teacher select(Teacher teacher);

    List<Teacher> selectAll();
}
