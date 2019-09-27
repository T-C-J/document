package com.tcj.jpa.multiplesources.service;

import com.tcj.jpa.multiplesources.domain.primary.Student;
import com.tcj.jpa.multiplesources.repository.primary.StudentRepository;
import com.tcj.jpa.multiplesources.service.baseservice.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public void add(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student select(Student student) {
        return studentRepository.queryById(student.getId());
    }

    @Override
    public List<Student> selectAll() {
        return studentRepository.findAll();
    }
}
