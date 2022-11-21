package com.aubay.quiz.dao;

import com.aubay.quiz.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface AdministratorDao extends JpaRepository <Administrator, Integer> {

    @Query("FROM Administrator a WHERE a.personID = :personID")
    Optional<Administrator> findByPersonId(@Param("personID") int personID);

    @Query("SELECT MAX(numberAdministrator) FROM Administrator")
    Integer numberAdministratorMax();
}
