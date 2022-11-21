package com.aubay.quiz.controller;

import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.PersonDao;
import com.aubay.quiz.model.Hr;
import com.aubay.quiz.model.Person;
import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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



    public HrController(HrDao hrDao) {
        this.hrDao = hrDao;
    }

    @PostMapping("/hr/create")
    public  String createHr(@RequestBody Hr hr) {


        hr.setNumberHr(hrDao.number() + 1);
        hr.setPassword(encoder.encode(hr.getPassword()));

        try {


            this.hrDao.save(hr);

        }
        catch(Exception e){
            return "Cette adresse mail est déjà utilisée par un compte utilisateur";
        }
       // return "L'utilisateur "+person.getName()+" "+person.getFirstName()+" est créé avec le mot de passe "+person.getPassword();
        return "L'utilisateur "+hr.getName()+" "+hr.getFirstName()+" est créé avec le mot de passe "+hr.getPassword();
    }

}
