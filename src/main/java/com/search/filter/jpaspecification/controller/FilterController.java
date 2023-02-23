package com.search.filter.jpaspecification.controller;

import com.search.filter.jpaspecification.domain.Student;
import com.search.filter.jpaspecification.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilterController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/{name}")
    public Student getStdByName(@PathVariable(name = "name") String name){

        return studentRepository.findByName(name);
    }

    @GetMapping("/city/{city}")
    public List<Student> getStdByCity(@PathVariable(name = "city") String city){

        return studentRepository.findByAddressCity(city);
    }

    @GetMapping("/subject/{sub}")
    public List<Student> getStdBySubject(@PathVariable(name = "sub") String subject){

        return studentRepository.findBySubjectsName(subject);
    }
}
