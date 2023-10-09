package com.aubay.quiz.dao;

import com.aubay.quiz.model.Test;
import com.aubay.quiz.model.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestQuestionDao extends JpaRepository<TestQuestion,Integer> {


    @Query("FROM TestQuestion tq WHERE tq.testId = :testId")
    List<TestQuestion> getListQuestionsByTest(@Param("testId") int testId);


    List<TestQuestion> findByTestId(int testId);
}
