package com.tcj.jpa.multiplesources.repository.secondary;

import com.tcj.jpa.multiplesources.entity.secondary.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
}
