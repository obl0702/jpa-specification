package com.search.filter.jpaspecification.controller;

import com.search.filter.jpaspecification.domain.Student;
import com.search.filter.jpaspecification.dto.RequestDto;
import com.search.filter.jpaspecification.repository.StudentRepository;
import com.search.filter.jpaspecification.service.FiltersSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilterController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FiltersSpecification<Student> studentFiltersSpecification;

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

    /* General Concept
    @PostMapping("/specification")
    public List<Student> getStudents(){
        Specification<Student> specification = new Specification<Student>(){

            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("name"), "Pratik");
            }
        };

        List<Student> all =studentRepository.findAll(specification);
        return all;
    }
     */


    /* Example:
    {
        "globalOperator": "OR",
        "searchRequestDto": [
            {
                "column": "city",
                "value": "Nagpur",
                "operation": "JOIN",
                "joinTable": "address"
            },
            {
                "column": "id",
                "value": "4,5",
                "operation": "BETWEEN",
                "joinTable": ""
            }
        ]
    }
    */
    @PostMapping("/specification")
    public List<Student> getStudents(@RequestBody RequestDto requestDto){
        Specification<Student> searchSpecification = studentFiltersSpecification
                .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
        return studentRepository.findAll(searchSpecification);
    }
}
