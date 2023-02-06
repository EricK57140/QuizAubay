package com.aubay.quiz.controller;

import com.aubay.quiz.View.ViewAnswers;
import com.aubay.quiz.View.ViewQuestions;
import com.aubay.quiz.dao.AnswersDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.QuestionDao;


import com.aubay.quiz.dao.TechnologyDao;
import com.aubay.quiz.model.*;


import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import com.fasterxml.jackson.annotation.JsonView;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.List;

@RestController
public class AnswerController {

    public AnswersDao answersDao;

    public AnswerController(AnswersDao answersDao) {
        this.answersDao = answersDao;
    }


    @Autowired
    UserDetailsServiceQuiz userDetailsServiceQuiz;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    QuestionDao questionDao;


    @PostMapping("/hr/answer/create")
    ResponseEntity<Void> createAnswer(@RequestBody Answers answers,  @RequestHeader(value="Authorization") String autorisation,
                                        @Param("idQuestions") int idQuestions
                                    //  @Param("correct") String answerCorrect


    ){

        answers.setActive(true);
        Questions questions= questionDao.getById(idQuestions);
        answers.setQuestions(questions);


        try {
            this.answersDao.save(answers);


        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hr/answer/{id}")
    @JsonView( ViewAnswers.class)
    public Optional<Answers> answerById(@PathVariable int id) {
        return this.answersDao.findById(id);
    }


    /**
     * Logical delete of an answer
     */
    @PostMapping("/hr/answer/disable/{id}")
    ResponseEntity<Void> disableAnswer(@PathVariable int id){
        Answers a  = answersDao.getById(id);
        a.setActive(false);
        this.answersDao.save(a);
        return ResponseEntity.ok().build();
    }
}
