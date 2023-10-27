package com.aubay.quiz.dao;
import com.aubay.quiz.model.Person;
import com.aubay.quiz.model.Questions;
import com.aubay.quiz.model.Test;
import com.aubay.quiz.model.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao extends JpaRepository<Test,Integer>{

    @Query("FROM Test t WHERE t.active =1")
    List<Test> getListTests();

    @Query("FROM Test t WHERE t.active =1 and t.hr.personID = :id")
    List<Test> getListTestsById(@Param("id")int id);

    @Query("FROM Test t WHERE t.nameTest LIKE %:search% ")
    List<Test> findTestsByTypingSearch(@Param("search") String search);

    @Query("FROM Test t WHERE t.nameTest LIKE %:search% and t.active =1 and t.hr.personID = :id")
    List<Test> findTestsByTypingSearch1(@Param("search") String search,@Param("id")int id);


}
