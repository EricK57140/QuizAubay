package com.aubay.quiz.dao;

import com.aubay.quiz.model.Candidate;
import com.aubay.quiz.model.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateDao  extends JpaRepository<Candidate, Integer> {


    @Query("SELECT MAX(numberCandidate) FROM Candidate")
    Integer numberCandidateMax();
}
