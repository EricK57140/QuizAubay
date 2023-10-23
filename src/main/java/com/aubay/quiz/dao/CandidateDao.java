package com.aubay.quiz.dao;

import com.aubay.quiz.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateDao  extends JpaRepository<Candidate, Integer> {

    @Query("FROM Candidate a WHERE a.personID = :personID")
    Optional<Candidate> findByPersonId(@Param("personID") int personID);
    @Query("SELECT MAX(numberCandidate) FROM Candidate")
    Integer numberCandidateMax();

    @Query("FROM Candidate a WHERE a.active =1")
    List<Candidate> getListCandidateActive();

    @Query("FROM Candidate a WHERE a.active =1 and a.hr.personID = :id")
    List<Candidate> getListCandidateActiveByIdHr(@Param("id") int id);

    @Query("FROM Person p WHERE p.email = :email")
    Candidate getCandidateByEmail(@Param("email") String email);

    @Query("SELECT p.personID FROM Person p WHERE p.email = :email")
    Integer getCandidateIdByEmail(@Param("email") String email);

    @Query("FROM Candidate a WHERE a.active =1 and (a.name LIKE %:search% or a.firstName LIKE %:search%)")
    List<Candidate> getListCandidateSearchBar(String search);


}
