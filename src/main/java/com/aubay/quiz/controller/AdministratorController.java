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

    @PostMapping("/admin/createadminaccount")
    public String createAdminAccount(@RequestBody Administrator administrator){

        if(administratorDao.numberAdministratorMax() == null){
            administrator.setNumberAdministrator(1);
        }else {
            administrator.setNumberAdministrator(administratorDao.numberAdministratorMax() + 1);
        }
        administrator.setPassword(encoder.encode(administrator.getPassword()));
        try{
            this.administratorDao.save(administrator);
        }
        catch(Exception e){
            return "Cette adresse mail est déjà utilisée par un compte utilisateur";
        }
        return "L'utilisateur "+administrator.getName()+" "+administrator.getFirstName()+" est créé avec le mot de passe "+administrator.getPassword();
    }


}
