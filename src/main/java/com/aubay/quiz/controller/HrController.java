package com.aubay.quiz.controller;

import com.aubay.quiz.dao.CandidateDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.PersonDao;
import com.aubay.quiz.model.Administrator;
import com.aubay.quiz.model.Candidate;
import com.aubay.quiz.model.Hr;
import com.aubay.quiz.model.Person;
import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Optional;

@RestController
public class HrController  {

    public HrDao hrDao;

    @Autowired
   UserDetailsServiceQuiz userDetailsServiceQuiz;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    PersonDao personDao;

    @Autowired
    private CandidateDao candidateDao;



    public HrController(HrDao hrDao) {
        this.hrDao = hrDao;
    }

    @PostMapping("/hr/createhraccount")
    public  String createHr(@RequestBody Hr hr, @RequestHeader(value="Authorization") String autorisation) {

        String token = autorisation.substring(7);
        String idAdministrator = jwtUtil.getTokenBody(token).get("personId").toString();
        Hr h = hrDao.getByPersonId(Integer.parseInt(idAdministrator));
        hr.setHr(h);


        if(hrDao.numberHrMax() == null){
            hr.setNumberHr(1);
        }else {
            hr.setNumberHr(hrDao.numberHrMax() + 1);
        }
        hr.setPassword(encoder.encode(hr.getPassword()));
        hr.setActive(true);
        try {
            this.hrDao.save(hr);
        }
        catch(Exception e){
            return "Cette adresse mail est déjà utilisée par un compte utilisateur";
        }
       // return "L'utilisateur "+person.getName()+" "+person.getFirstName()+" est créé avec le mot de passe "+person.getPassword();
        return "L'utilisateur "+hr.getName()+" "+hr.getFirstName()+" est créé avec le mot de passe "+hr.getPassword();
    }

    @PostMapping("/hr/createcandidateaccount")
    public  String createCandidate(@RequestBody Candidate candidate , @RequestHeader(value="Authorization") String autorisation) {

        String token = autorisation.substring(7);
        String idAdministrator = jwtUtil.getTokenBody(token).get("personId").toString();
        Hr h = hrDao.getByPersonId(Integer.parseInt(idAdministrator));
        candidate.setHr(h);

        if(candidateDao.numberCandidateMax() == null){
            candidate.setNumberCandidate(1);
        }else {
            candidate.setNumberCandidate(candidateDao.numberCandidateMax() + 1);
        }
        candidate.setPassword(encoder.encode(candidate.getPassword()));
        candidate.setActive(true);
        try {
            this.candidateDao.save(candidate);
        }
        catch(Exception e){
            return "Cette adresse mail est déjà utilisée par un compte utilisateur";
        }

        return "L'utilisateur "+candidate.getName()+" "+candidate.getFirstName()+" est créé avec le mot de passe "+candidate.getPassword()
                + " numéro de candidat " + candidate.getNumberCandidate();
    }

    @PostMapping("/hr/modifycandidateaccount")
    public  String modifyCandidate(@RequestBody Candidate candidate){
        this.candidateDao.save(candidate);
        return candidate.getName() + " has been modify";
    }

    @GetMapping("/hr/email/{email}")
    public Hr hrByEmail(@PathVariable String email) {
        return this.hrDao.getByEmail(email);}
}
