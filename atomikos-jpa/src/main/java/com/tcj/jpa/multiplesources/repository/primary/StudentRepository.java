package com.tcj.jpa.multiplesources.repository.primary;

import com.tcj.jpa.multiplesources.domain.primary.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student queryById(Integer id);
}
