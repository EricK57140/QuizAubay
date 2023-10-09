package com.aubay.quiz.dao;


import com.aubay.quiz.model.Questions;
import com.aubay.quiz.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Questions,Integer>  {


    @Query("SELECT tq.questions FROM TestQuestion tq WHERE tq.test.id = :testId")
    List<Questions> listQuestionsByTest(@Param("testId") int testId);

    @Query("FROM Questions q WHERE q.id NOT IN (SELECT tq.questions.id FROM TestQuestion tq WHERE tq.test.id = :testId)")
    List<Questions> listQuestionsPageTest(@Param("testId") int testId);

    @Query("FROM Questions q WHERE q.id NOT IN (SELECT tq.questions.id FROM TestQuestion tq WHERE tq.test.id = :testId) and  q.technology.IdTechnology=:idTechno")
    List<Questions> findQuestionsByTyping(@Param("testId") int testId,@Param("idTechno") Integer idTechno);

    @Query("FROM Questions q WHERE q.id NOT IN (SELECT tq.questions.id FROM TestQuestion tq WHERE tq.test.id = :testId) and q.questionTitle LIKE %:search% ")
    List<Questions> findQuestionsByTypingSearch(@Param("testId") int testId,@Param("search") String search);

    @Query("FROM Questions q WHERE q.id NOT IN (SELECT tq.questions.id FROM TestQuestion tq WHERE tq.test.id = :testId) and q.questionTitle LIKE %:search% and  q.technology.IdTechnology=:idTechno")
    List<Questions> findQuestionsByTypingComplete(@Param("testId") int testId,@Param("search") String search,@Param("idTechno") Integer idTechno);

    @Query("FROM Questions q WHERE  q.questionTitle LIKE %:search% ")
    List<Questions> findQuestionsByTypingSearchSimple(@Param("search") String search);

}
