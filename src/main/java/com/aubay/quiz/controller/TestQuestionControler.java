package com.aubay.quiz.controller;

import com.aubay.quiz.dao.TestQuestionDao;
import com.aubay.quiz.model.Hr;
import com.aubay.quiz.model.Technology;
import com.aubay.quiz.model.TestQuestion;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestQuestionControler {

    public TestQuestionDao testQuestionDao;

    public TestQuestionControler(TestQuestionDao testQuestionDao) {
        this.testQuestionDao = testQuestionDao;
    }

    @PostMapping("/hr/add-question-to-test/")
    ResponseEntity<Void> addQuestion(@Param("testId") int testId, @Param("questionId") int questionId ){

        TestQuestion testQuestion = new TestQuestion();
        testQuestion.setTestId(testId);
        testQuestion.setQuestionId(questionId);
        try {
            this.testQuestionDao.save(testQuestion);

        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
         return ResponseEntity.ok().build();
    }
    @PostMapping("/hr/delete-question-from-test/")
    ResponseEntity<Void> deleteQuestion(@Param("testId") int testId, @Param("questionId") int questionId ){

        TestQuestion testQuestion = new TestQuestion();
        testQuestion.setTestId(testId);
        testQuestion.setQuestionId(questionId);
        try {
            this.testQuestionDao.delete(testQuestion);

        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }



}
