package com.aubay.quiz.controller;

import com.aubay.quiz.dao.AdministratorDao;
import com.aubay.quiz.dao.TechnologyDao;
import com.aubay.quiz.model.Administrator;
import com.aubay.quiz.model.Technology;
import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Optional;


@RestController
public class TechnologyController  {

    public TechnologyDao technologyDao;

    public TechnologyController(TechnologyDao technologyDao) {
        this.technologyDao = technologyDao;
    }

    @Autowired
    UserDetailsServiceQuiz userDetailsServiceQuiz;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AdministratorDao administratorDao;

    @PostMapping("/admin/technology/create")
    public String createTechnology(@RequestBody Technology technology, @RequestHeader(value="Authorization") String autorisation ){
        String token = autorisation.substring(7);
        String idAdministrator = jwtUtil.getTokenBody(token).get("personId").toString();
        Administrator administrator = administratorDao.getByPersonId(Integer.parseInt(idAdministrator));
        technology.setAdministrator(administrator);

        try {
            this.technologyDao.save(technology);
        }
        catch(Exception e){
            return "This technology already exists";
        }
        return "The technology " + technology.getNameTechnology() + " has been created";
    }
}
