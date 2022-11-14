package com.aubay.quiz.dao;
import com.aubay.quiz.model.Administrator;
import com.aubay.quiz.model.HR;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface HRDao extends JpaRepository <HR,Integer> {

    @Query("FROM HR g WHERE g.id_Person= :id_Person")
    Optional<HR> findById_Person(@Param("id_Person")Integer id_person) ;


}
