package com.aubay.quiz.controller;

import com.aubay.quiz.dao.AdministratorDao;
import com.aubay.quiz.dao.CandidateDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.PersonDao;
import com.aubay.quiz.model.Administrator;
import com.aubay.quiz.model.Candidate;
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
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private CandidateDao candidateDao;


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
        administrator.setActive(true);

        try{
            this.administratorDao.save(administrator);
        }
        catch(Exception e){
            return "Cette adresse mail est déjà utilisée par un compte utilisateur";
        }
        return "L'utilisateur "+administrator.getName()+" "+administrator.getFirstName()+" est créé avec le mot de passe "+administrator.getPassword();
    }

    @PostMapping("/admin/createhraccount")
    public  String createHr(@RequestBody Hr hr, @RequestHeader(value="Authorization") String autorisation) {

        String token = autorisation.substring(7);
        String idAdministrator = jwtUtil.getTokenBody(token).get("personId").toString();
        Administrator administrator = administratorDao.getByPersonId(Integer.parseInt(idAdministrator));
        hr.setAdministrator(administrator
        );


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

    @PostMapping("/admin/createcandidateaccount")
    public  String createCandidate(@RequestBody Candidate candidate , @RequestHeader(value="Authorization") String autorisation) {

        String token = autorisation.substring(7);
        String idAdministrator = jwtUtil.getTokenBody(token).get("personId").toString();
        Administrator administrator = administratorDao.getByPersonId(Integer.parseInt(idAdministrator));
        candidate.setAdministrator(administrator);

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




    @PostMapping("/admin/person/disable/{id}")
    public String disablePerson(@PathVariable int id){
        Person p  = personDao.getById(id);
        p.setActive(false);
        this.personDao.save(p);
        return "Person "+ p.getName() +" "+ p.getFirstName()+" is disabled ";
    }
}
