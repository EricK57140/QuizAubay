package com.aubay.quiz.controller;

import com.aubay.quiz.dao.CandidateDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.model.Candidate;
import com.aubay.quiz.model.Hr;
import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


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




}
