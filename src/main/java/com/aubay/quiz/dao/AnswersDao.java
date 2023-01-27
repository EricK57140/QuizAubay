package com.aubay.quiz.dao;
import com.aubay.quiz.model.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersDao extends JpaRepository <Answers, Integer>{
}
