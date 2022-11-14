package com.aubay.quiz.dao;
import com.aubay.quiz.model.Administrator;
import com.aubay.quiz.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministratorDao extends JpaRepository<Administrator,Integer> {
    @Query("FROM Administrator g WHERE g.id_Person = :id_Person")
    Optional<Administrator> findById_Person(@Param("id_Person") Integer id_person);
}
