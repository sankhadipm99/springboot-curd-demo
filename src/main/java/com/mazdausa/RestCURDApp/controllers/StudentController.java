package com.mazdausa.RestCURDApp.controllers;

import com.mazdausa.RestCURDApp.entity.Student;
import com.mazdausa.RestCURDApp.exception.StudentException;
import com.mazdausa.RestCURDApp.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private List<Student> studentObjs;
    @PostConstruct
    public void loadData(){
        studentObjs = new ArrayList<>();
        studentObjs.add(new Student("sankhadip","mishra"));
        studentObjs.add(new Student("sankhadip1","mishra"));
        studentObjs.add(new Student("sankhadip2","mishra"));
    }
    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentObjs;
    }
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId){

        if ( (studentId >= studentObjs.size()) || (studentId < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return studentObjs.get(studentId);
    }
}
