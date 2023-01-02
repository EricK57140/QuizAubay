package com.aubay.quiz.dao;

import com.aubay.quiz.model.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.aubay.quiz.model.Hr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
@Repository
public interface HrDao extends JpaRepository <Hr, Integer> {

    @Query("FROM Hr a WHERE a.personID = :personID")
    Optional<Hr> findByPersonId(@Param("personID") int personID);

    @Query("FROM Hr a WHERE a.personID = :personID")
    Hr getByPersonId(@Param("personID") int personID);

    @Query("SELECT MAX(numberHr) FROM Hr")
    Integer numberHrMax();

    @Query("FROM Person p WHERE p.email = :email")
    Hr getByEmail(@Param("email") String email);

    @Query("FROM Person p WHERE p.email = :email")
    Person getByEmailPerson(@Param("email") String email);

    @Query("SELECT email FROM Person p WHERE p.email = :email")
    Person getByEmailPersonStr(@Param("email") String email);

    @Query("SELECT email FROM Person p WHERE p.email = :email")
    String getByEmailPersonStr2(@Param("email") String email);

    @Modifying
    @Query("UPDATE Person p set p.name=:name WHERE p.personID = :personID")
    void modifyCandidate(@Param("personID")int personID,@Param("name") String name);
}
