package com.aubay.quiz.controller;

import com.aubay.quiz.dao.AdministratorDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.PersonDao;
import com.aubay.quiz.model.Administrator;
import com.aubay.quiz.model.Hr;
import com.aubay.quiz.model.Person;
import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Properties;

@RestController
public class AdministratorController {


    public AdministratorDao administratorDao;

    @Autowired
    private HrDao hrDao;

    @Autowired
    private UserDetailsServiceQuiz userDetailsServiceQuiz;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private PersonDao personDao;


    public AdministratorController(AdministratorDao administratorDao) {
        this.administratorDao = administratorDao;
    }

//    @PostMapping("/admin/hr
//    /create")
//    public String createHr(@RequestBody Person person, @RequestHeader(value="Authorization") String autorisation){
//
//        person.setPassword(encoder.encode(person.getPassword()));
//        try{
//            this.personDao.save(person);
//        }
//        catch(Exception e){
//            return "Cette adresse mail est déjà utilisée par un compte utilisateur";
//        }
//        return "L'utilisateur "+person.getName()+" "+person.getFirstName()+" est créé avec le mot de passe "+person.getPassword();
//    }

    @PostMapping("/admin/hr/create")
    public String createHr(@RequestBody Hr hr, @RequestHeader(value="Authorization") String autorisation){

        hr.setPassword(encoder.encode(hr.getPassword()));
        try{
            this.personDao.save(hr);
        }
        catch(Exception e){
            return "Cette adresse mail est déjà utilisée par un compte utilisateur";
        }
        return "L'utilisateur "+hr.getName()+" "+hr.getFirstName()+" est créé avec le mot de passe "+hr.getPassword();
    }
}
