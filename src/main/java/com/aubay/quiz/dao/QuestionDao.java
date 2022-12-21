package com.aubay.quiz.dao;


import com.aubay.quiz.model.Questions;
import com.aubay.quiz.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;



public interface QuestionDao extends JpaRepository<Questions,Integer>  {
}
