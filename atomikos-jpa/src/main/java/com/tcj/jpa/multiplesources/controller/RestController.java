package com.tcj.jpa.multiplesources.controller;

import com.tcj.jpa.multiplesources.domain.primary.Student;
import com.tcj.jpa.multiplesources.entity.Resp;
import com.tcj.jpa.multiplesources.repository.primary.StudentRepository;
import com.tcj.jpa.multiplesources.repository.secondary.TeacherRepository;
import com.tcj.jpa.multiplesources.service.AllService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("api")
@Data
public class RestController {

    @Autowired
    private  StudentRepository studentRepository;
    @Autowired
    private  TeacherRepository teacherRepository;
    @Autowired
    private AllService allService;


    @RequestMapping("/see")
    public Resp primary() {
        return allService.seeTAndS();
    }

    @RequestMapping("/addt")
    public String addt() {
        try {
            allService.addTAndS();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }
}
