package com.aubay.quiz.dao;

import com.aubay.quiz.model.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestQuestionDao extends JpaRepository<TestQuestion,Integer> {
}
