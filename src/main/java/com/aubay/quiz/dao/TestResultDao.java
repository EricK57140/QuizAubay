package com.aubay.quiz.dao;
import com.aubay.quiz.model.Answers;
import com.aubay.quiz.model.Questions;
import com.aubay.quiz.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestResultDao extends JpaRepository<TestResult,Integer>{


    @Query("SELECT SUM(a.score) FROM TestResult tr JOIN tr.answers a WHERE tr.testTakenId = :testId ")
    Integer scoreCalculation(@Param("testId") Integer testId);

    @Query("SELECT SUM(a.score)\n" +
            "FROM Answers a\n" +
            "JOIN a.questions q\n" +
            "JOIN TestQuestion tq ON tq.questions.idQuestions = q.idQuestions\n" +
            "WHERE tq.testId = :testId ")
    Integer scoreTotal(@Param("testId") Integer testId);

    @Query("FROM TestResult  WHERE testTakenId = :testId ")
    List <TestResult> listSelected(@Param("testId") Integer testId);

    @Query("FROM TestResult  WHERE testTakenId = :testId ")
    List <TestResult> listSelectedAnswer(@Param("testId") Integer testId);

}
