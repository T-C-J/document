package com.tcj.jpa.multiplesources.service;

import com.tcj.jpa.multiplesources.domain.primary.Student;
import com.tcj.jpa.multiplesources.domain.secondary.Teacher;
import com.tcj.jpa.multiplesources.entity.Resp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class AllService {

    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private TeacherServiceImpl teacherService;


    @Transactional(rollbackOn=Throwable.class)
    public void addTAndS() throws IOException {
        Student student = new Student();
        student.setAge(12);
        student.setClazz("一班");
        student.setName("小明");
        student.setTeacher("laoming ");


        Teacher teacher = new Teacher();
        teacher.setAge(12);
        teacher.setClazz("一班");
        teacher.setName("小明");
        teacher.setStudent("laoming ");
        teacherService.add(teacher);
        studentService.add(student);
        int a =10/0;
    }

    @Transactional
    public void ad() throws IOException {
        addTAndS();
    }


    public Resp seeTAndS(){
        Resp resp = new Resp();
        List<Teacher> teachers = teacherService.selectAll();
        List<Student> students = studentService.selectAll();
        resp.setStudents(students);
        resp.setTeachers(teachers);
        return resp;
    }


}
