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




}
