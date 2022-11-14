package com.aubay.quiz.controller;


import com.aubay.quiz.dao.AdministratorDao;
import com.aubay.quiz.dao.CandidateDao;
import com.aubay.quiz.dao.HRDao;
import com.aubay.quiz.dao.PersonDao;
import com.aubay.quiz.model.Person;
import com.aubay.quiz.security.UserDetailsQuizAubay;
import com.aubay.quiz.security.UserDetailsServiceQuizAubay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class PersonController {

    public PersonDao personDao;

    @Autowired
    AdministratorDao administratorDao;

    @Autowired
    CandidateDao candidateDao;

    @Autowired
    HRDao hrDao;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceQuizAubay userDetailsServiceQuizAubay;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    public PersonController(PersonDao personDao){this.personDao= personDao;}

    @PostMapping("/connexion")
    public Map<String, String>  authentification(@RequestBody Person person) throws  Exception {
        try {
            //récupère les infos de connexion dans le request body et vérifie
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            person.getEmail(), person.getPassword())
            );
        } catch (BadCredentialsException e) {
//            throw new Exception(e);
            Map<String, String> retour = new HashMap<>();
            retour.put("erreur", "Mauvais login / mot de passe");
            return retour;
        }
        UserDetailsQuizAubay userDetails = (UserDetailsQuizAubay) userDetailsServiceQuizAubay
                .loadUserByUsername(person.getEmail());

//        Map<String,String> retour = new HashMap<>();
//        retour.put("token",jwtUtil.generateToken(userDetails));

        return null;

    }
}
