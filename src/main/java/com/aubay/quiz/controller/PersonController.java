package com.aubay.quiz.controller;

import com.aubay.quiz.dao.AdministratorDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.PersonDao;
import com.aubay.quiz.model.Hr;
import com.aubay.quiz.model.Person;
import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsQuiz;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    public PersonDao personDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AdministratorDao administratorDao;

    @Autowired
    HrDao hrDao;

    @Autowired
    UserDetailsServiceQuiz userDetailsServiceQuiz;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public  PersonController(PersonDao personDao){
        this.personDao = personDao;
    }

    @PostMapping("/connexion")
    public Map<String, String> authentification(@RequestBody Person person) throws  Exception{
        try {
            //récupère les infos de connexion dans le request body et vérifie
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            person.getEmail(), person.getPassword()));

        }
        catch(BadCredentialsException e){
//            throw new Exception(e);
         Map<String,String> retour = new HashMap<>();
          retour.put("erreur","Mauvais login / mot de passe");
            return retour;
        }
//
      UserDetailsQuiz userDetails = (UserDetailsQuiz) userDetailsServiceQuiz
              .loadUserByUsername(person.getEmail());
        Map<String,String> retour = new HashMap<>();

        retour.put("token",jwtUtil.generateToken(userDetails));
        return retour;
    }

    @GetMapping("/admin")
    public String test(){
        String s = "test admin ok";
        return s;
    }

    @GetMapping("/hr")
    public String testHr(){
        String s = "test HR ok";
        return s;
    }
//    @PostMapping("/hr/create")
//    public  String createHr(@RequestBody Person person) {
//
//
//
//        person.setPassword(encoder.encode(person.getPassword()));
//
//        try {
//
//            this.personDao.save(person);
//
//        }
//        catch(Exception e){
//            return "Cette adresse mail est déjà utilisée par un compte utilisateur";
//        }
//        // return "L'utilisateur "+person.getName()+" "+person.getFirstName()+" est créé avec le mot de passe "+person.getPassword();
//        return "L'utilisateur "+person.getName()+" "+person.getFirstName()+" est créé avec le mot de passe "+person.getPassword();
//    }

    @GetMapping("/hr/list-person")

    public List<Person> listPerson() {
        return this.personDao.findAll();
    }



}

