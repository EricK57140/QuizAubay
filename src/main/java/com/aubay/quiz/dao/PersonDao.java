package com.aubay.quiz.dao;
import com.aubay.quiz.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonDao extends JpaRepository<Person, Integer> {

    @Query("FROM Person u WHERE u.email = :email")
    Optional <Person> findByEmail(@Param("email") String email) ;



}
