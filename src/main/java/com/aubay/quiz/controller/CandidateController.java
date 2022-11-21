package com.aubay.quiz.controller;

import com.aubay.quiz.dao.CandidateDao;
import com.aubay.quiz.model.Candidate;
import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    public CandidateController(CandidateDao candidateDao) {
        this.candidateDao = candidateDao;
    }

    @PostMapping("/hr/createcandidateaccount")
    public  String createCandidate(@RequestBody Candidate candidate) {

        if(candidateDao.numberCandidateMax() == null){
            candidate.setNumberCandidate(1);
        }else {
            candidate.setNumberCandidate(candidateDao.numberCandidateMax() + 1);
        }
        candidate.setPassword(encoder.encode(candidate.getPassword()));

        try {
            this.candidateDao.save(candidate);
        }
        catch(Exception e){
            return "Cette adresse mail est déjà utilisée par un compte utilisateur";
        }

        return "L'utilisateur "+candidate.getName()+" "+candidate.getFirstName()+" est créé avec le mot de passe "+candidate.getPassword()
                + " numéro de candidat " + candidate.getNumberCandidate();
    }
}
