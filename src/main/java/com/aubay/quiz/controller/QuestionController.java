package com.aubay.quiz.controller;


import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.QuestionDao;


import com.aubay.quiz.model.Hr;
import com.aubay.quiz.model.Questions;


import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.List;

@RestController
public class QuestionController {

    public QuestionDao questionDao;

    public QuestionController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }



    @Autowired
    UserDetailsServiceQuiz userDetailsServiceQuiz;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    HrDao hrDao;

    @PostMapping("/hr/question/create")
    public String createQuestion(@RequestBody Questions questions, @RequestHeader(value="Authorization") String autorisation ){
        String token = autorisation.substring(7);
        String idHr = jwtUtil.getTokenBody(token).get("personId").toString();
        Hr hr = hrDao.getByPersonId(Integer.parseInt(idHr));
        questions.setHr(hr);

        try {
            this.questionDao.save(questions);
        }
        catch(Exception e){
            return "This technology already exists";
        }
        return "The question" + questions.getQuestionTitle() + " has been created";
    }

    @DeleteMapping("/hr/question/{id}")
    public ResponseEntity<Integer> deleteQuestion(@PathVariable int id){
      Optional<Questions> questionToDelete = questionDao.findById(id);
//
//      if(technologyToDelete.isPresent()){
//           return ResponseEntity.ok(technologyToDelete.get().getIdTechnology());
//       } else {
//           return ResponseEntity.noContent().build();
//      }

        if(questionDao.existsById(id)) {
            this.questionDao.deleteById(id);
            return ResponseEntity.ok(id);
        } else{         return ResponseEntity.noContent().build();     }
    }
    @GetMapping("/hr/list-questions")

    public List<Questions> technologyList() {
        return this.questionDao.findAll();
    }


}
