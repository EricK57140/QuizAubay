package com.aubay.quiz.dao;
import com.aubay.quiz.model.Answers;
import com.aubay.quiz.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersDao extends JpaRepository <Answers, Integer>{



    @Query("FROM Answers a WHERE a.questions.idQuestions=:idQuestions and a.active =1")
    List<Answers> getListAnswers(@Param("idQuestions") int idQuestions);


}
