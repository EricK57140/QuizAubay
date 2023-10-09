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

    @Autowired
    TechnologyDao technologyDao;

    @Autowired
    AnswersDao answersDao;
//    @Transactional
//    public Questions createParentWithChildren(Questions questions, List<Answers> answers) {
//        questions.setListAnswers(answers);
//        answers.forEach(c -> c.setQuestions(questions));
//        questionDao.save(questions);
//        return questions;
//    }
    @PostMapping("/hr/question/create")
    ResponseEntity<Void> createQuestion(@RequestBody Questions questions,  @RequestHeader(value="Authorization") String autorisation,
                                        @Param("idTechnology") int idTechnology ){
        String token = autorisation.substring(7);
        String idHr = jwtUtil.getTokenBody(token).get("personId").toString();
        Hr hr = hrDao.getByPersonId(Integer.parseInt(idHr));
        questions.setHr(hr);
        questions.setActive(true);
        Technology technology = technologyDao.getById(idTechnology);
        questions.setTechnology(technology);



        try {
            int id = this.questionDao.save(questions).getIdQuestions();
            return new ResponseEntity(id, HttpStatus.CREATED);

        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
       // return ResponseEntity.ok().build();
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
    @JsonView(ViewQuestions.class)
    public List<Questions> technologyList() {
        return this.questionDao.findAll();
    }

    @GetMapping("/hr/list-questions-by-test/{id}")
    @JsonView(ViewQuestions.class)
    public List<Questions> listQuestionsByTest(@PathVariable int id) {
        return this.questionDao.listQuestionsByTest(id);
    }
    @GetMapping("/candidate/list-questions-by-test/{id}")
    @JsonView(ViewQuestions.class)
    public List<Questions> listQuestionsByTestCandidate(@PathVariable int id) {
        return this.questionDao.listQuestionsByTest(id);
    }

    @GetMapping("/hr/list-questions-test/{id}")
    @JsonView(ViewQuestions.class)
    public List<Questions> listQuestionsPageTest(@PathVariable int id) {
        return this.questionDao.listQuestionsPageTest(id);
    }


    /**
     * @param id
     * @return
     */
    @GetMapping("/hr/list-answers/{id}")
    @JsonView(ViewAnswers.class)
    public List<Answers> answersList(@PathVariable int id) {

        return this.answersDao.getListAnswers(id);

    }

    @GetMapping("/candidate/list-answers/{id}")
    @JsonView(ViewAnswers.class)
    public List<Answers> answersListByCandidate(@PathVariable int id) {

        return this.answersDao.getListAnswers(id);

    }

    @GetMapping("/candidate/{id}")
    @JsonView(ViewAnswers.class)
    public List<Answers> answersListCandidate(@PathVariable int id) {

        return this.answersDao.getListAnswers(id);

    }

    @GetMapping("/hr/question/{id}")
    @JsonView(ViewQuestions.class)
    public Optional<Questions> QuestionById(@PathVariable int id) {
        return this.questionDao.findById(id);
    }

    @GetMapping("/hr/questions-by-test-searchbar/{id}")
    @JsonView(ViewQuestions.class)
    public List<Questions> listQuestionsByTest(@PathVariable int id,@Param("search") String search,
                                               @Param("idTechno") int idTechno                            ) {

        if (search == "" && idTechno == 0){
            return this.questionDao.listQuestionsPageTest(id);}

        else if(search == ""){
        return this.questionDao.findQuestionsByTyping(id,idTechno);}
        else if(search !="" && idTechno != 0){

        return this.questionDao.findQuestionsByTypingComplete(id,search,idTechno);

        }
        else if( idTechno == 0){
            return this.questionDao.findQuestionsByTypingSearch(id,search);

        }


        return null;
    }
    @GetMapping("/hr/questions-by--searchbar/")
    @JsonView(ViewQuestions.class)
    public List<Questions> listQuestionsBySearch(@Param("search") String search
                                                                 ) {

        if (search == ""){
            return this.questionDao.findAll();}


        else if(search !="" ){

            return this.questionDao.findQuestionsByTypingSearchSimple(search);

        }



        return null;
    }
}
