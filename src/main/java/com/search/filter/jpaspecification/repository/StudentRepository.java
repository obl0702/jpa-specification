package com.search.filter.jpaspecification.repository;

import com.search.filter.jpaspecification.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {

    Student findByName(String name);

    List<Student> findByAddressCity(String city);

    List<Student> findBySubjectsName(String name);
}
