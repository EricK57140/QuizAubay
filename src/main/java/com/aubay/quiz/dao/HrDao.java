package com.aubay.quiz.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.aubay.quiz.model.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
public interface HrDao extends JpaRepository <Hr, Integer> {

    @Query("FROM Hr a WHERE a.personID = :personID")
    Optional<Hr> findByPersonId(@Param("personID") int personID);

    @Query("SELECT MAX(numberHr) FROM Hr")
    int number();

}
