package com.aubay.quiz.dao;

import com.aubay.quiz.model.Candidate;
import com.aubay.quiz.model.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateDao  extends JpaRepository<Candidate, Integer> {

    @Query("FROM Candidate a WHERE a.personID = :personID")
    Optional<Candidate> findByPersonId(@Param("personID") int personID);
    @Query("SELECT MAX(numberCandidate) FROM Candidate")
    Integer numberCandidateMax();
}
