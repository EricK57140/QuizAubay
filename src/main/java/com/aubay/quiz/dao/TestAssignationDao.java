package com.aubay.quiz.dao;
import com.aubay.quiz.model.Test;
import com.aubay.quiz.model.TestAssignation;
import com.aubay.quiz.model.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestAssignationDao extends JpaRepository<TestAssignation,Integer> {


    @Query("From TestAssignation ta WHERE ta.candidate.id = :candidateId")
    List<TestAssignation> getListTestAssigned(@Param("candidateId") int candidateId);

    @Query("From TestAssignation ta WHERE ta.idTestAssignation = :testId")
    Optional<TestAssignation> checkTestAssignation(@Param("testId") int testId);

     @Query("FROM TestResult tr WHERE tr.testTakenId.id = :testId")
    List<TestResult> getTestResult(@Param("testId") int testId);
}
