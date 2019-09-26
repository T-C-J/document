package com.tcj.jpa.multiplesources.controller;

import com.tcj.jpa.multiplesources.entity.primary.Student;
import com.tcj.jpa.multiplesources.entity.secondary.Teacher;
import com.tcj.jpa.multiplesources.repository.primary.StudentRepository;
import com.tcj.jpa.multiplesources.repository.secondary.TeacherRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api")
@Data
public class RestController {

    @Autowired
    private  StudentRepository studentRepository;
    @Autowired
    private  TeacherRepository teacherRepository;


    @RequestMapping("/see")
    public List<Student> primary() {
        return studentRepository.findAll();
    }

    @RequestMapping("/seet")
    public List<Teacher> primaryt() {
        return teacherRepository.findAll();
    }
    @RequestMapping("/add")
    public String add() {
        Student student = new Student();
        student.setAge(12);
        student.setClazz("一班");
        student.setName("小明");
        student.setTeacher("laoming ");
        studentRepository.save(student);
        return "ok";
    }


    @RequestMapping("/addt")
    public String addt() {
        Teacher student = new Teacher();
        student.setAge(12);
        student.setClazz("一班");
        student.setName("小明");
        student.setStudent("laoming ");
        teacherRepository.save(student);
        return "ok";
    }
}
