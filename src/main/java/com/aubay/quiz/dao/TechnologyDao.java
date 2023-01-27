package com.aubay.quiz.dao;

import com.aubay.quiz.model.Person;
import com.aubay.quiz.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TechnologyDao extends JpaRepository<Technology,Integer> {

    @Query("FROM Technology t WHERE t.active =1")
    List<Technology> getListTechnologyActive();

    @Query("FROM Technology t WHERE  t.nameTechnology = :nameTechnology")
    Optional<Technology> findByNameTechnology(@Param("nameTechnology") String nameTechnology);

}
