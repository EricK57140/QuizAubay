package com.aubay.quiz.controller;

import com.aubay.quiz.model.Person;
import com.aubay.quiz.security.UserDetailsQuiz;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PersonController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceQuiz userDetailsServiceQuiz;

    @PostMapping("/connexion")
    public String authentification(@RequestBody Person person) throws  Exception{
        try {
            //récupère les infos de connexion dans le request body et vérifie
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            person.getEmail(), person.getPassword()));
        }
        catch(BadCredentialsException e){
//            throw new Exception(e);
//            Map<String,String> retour = new HashMap<>();
//            retour.put("erreur","Mauvais login / mot de passe");
            return "bad";
        }
//
//        UserDetailsQuiz userDetailsQuiz = (UserDetailsQuiz) userDetailsServiceQuiz
//                .loadUserByUsername(person.getFirstName());

        return "ok" ;
    }


    }

