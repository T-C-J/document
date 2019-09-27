package com.tcj.jpa.multiplesources.service;

import com.tcj.jpa.multiplesources.domain.secondary.Teacher;
import com.tcj.jpa.multiplesources.repository.secondary.TeacherRepository;
import com.tcj.jpa.multiplesources.service.baseservice.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void add(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher select(Teacher teacher) {
        return teacherRepository.queryById(teacher.getId());
    }

    @Override
    public List<Teacher> selectAll() {
        return teacherRepository.findAll();
    }
}
