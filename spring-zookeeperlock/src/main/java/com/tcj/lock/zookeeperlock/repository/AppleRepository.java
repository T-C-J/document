package com.tcj.lock.zookeeperlock.repository;

import com.tcj.lock.zookeeperlock.entity.Apple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;

@Repository
public interface AppleRepository extends JpaRepository<Apple,Integer> {

    @Query("select number from Apple where name = 'apple'")
    int selectAppleNumber();


    @Transactional
    @Modifying
    @Query(value = "update apple a set a.number=a.number-1 where name = 'apple'",nativeQuery = true)
    int updateAppleNumber();

}
