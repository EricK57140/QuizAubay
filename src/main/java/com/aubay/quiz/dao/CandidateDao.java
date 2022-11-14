package com.aubay.quiz.dao;
import com.aubay.quiz.model.Administrator;
import com.aubay.quiz.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CandidateDao extends JpaRepository<Candidate,Integer> {

    @Query("FROM Candidate g WHERE g.id_Person= :id_Person")
    Optional<Candidate> findById_Person(@Param("id_Person")Integer id_person) ;


}
