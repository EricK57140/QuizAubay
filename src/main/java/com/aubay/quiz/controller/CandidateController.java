package com.aubay.quiz.controller;

import com.aubay.quiz.View.ViewQuestions;
import com.aubay.quiz.dao.CandidateDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.model.Candidate;
import com.aubay.quiz.model.Questions;
import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CandidateController {

    public CandidateDao candidateDao;

    @Autowired
    UserDetailsServiceQuiz userDetailsServiceQuiz;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    HrDao hrDao;

    public CandidateController(CandidateDao candidateDao) {
        this.candidateDao = candidateDao;
    }

    @GetMapping("/hr/list-candidate")
    public List<Candidate> listcandidate() {
        return this.candidateDao.getListCandidateActive();
    }

    @GetMapping("/hr/list-candidate-hr/{id}")
    public List<Candidate> listcandidatebyidhr(@PathVariable int id) {
        return this.candidateDao.getListCandidateActiveByIdHr(id);
    }

    @GetMapping("/candidate/email/{email}")
    public Candidate candidateByEmail(@PathVariable String email) {
        return this.candidateDao.getCandidateByEmail(email);}
    @GetMapping("/candidate/id/{email}")
    public Integer candidateId(@PathVariable String email) {
        return this.candidateDao.getCandidateIdByEmail(email);}

    @GetMapping("/hr/candidates-by-searchbar")
    public List<Candidate> listCandidate( @Param("search") String search) {

       if (search == "" ){
          return this.candidateDao.getListCandidateActive();}
        else if(search !=""){

            return this.candidateDao.getListCandidateSearchBar(search);

     }



        return null;
    }

}
