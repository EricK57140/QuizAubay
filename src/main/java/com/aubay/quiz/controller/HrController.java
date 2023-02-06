package com.aubay.quiz.controller;

import com.aubay.quiz.View.ViewEmailCheck;
import com.aubay.quiz.dao.CandidateDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.PersonDao;
import com.aubay.quiz.model.Administrator;
import com.aubay.quiz.model.Candidate;
import com.aubay.quiz.model.Hr;
import com.aubay.quiz.model.Person;
import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Void> createCandidate(@RequestBody Candidate candidate , @RequestHeader(value="Authorization") String autorisation) {

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
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }


    /**
     * Logical delete of a candidate
     */
    @PostMapping("/hr/person/disable/{id}")
    public String disablePerson(@PathVariable int id){
        Person p  = personDao.getById(id);
        p.setActive(false);
        this.personDao.save(p);
        return "Person "+ p.getName() +" "+ p.getFirstName()+" is disabled ";
    }

    @GetMapping("/hr/email/{email}")
    public Hr hrByEmail(@PathVariable String email) {
        return this.hrDao.getByEmail(email);}


    @GetMapping("/hr/emailperson/{email}")
    public Person personByEmailPerson(@PathVariable String email) {
        return this.hrDao.getByEmailPerson(email);}

    @GetMapping("/hr/emailpersonstr/{email}")
    public String personByEmailPerson3(@PathVariable String email) {
        return this.hrDao.getByEmailPersonStr2(email);}


    @GetMapping("/hr/emailpersonTry/{email}")
    public Person personByEmailPerson2(@PathVariable String email) {
        return this.hrDao.getByEmailPersonStr(email);}

    @GetMapping("/hr/emailperson")
    public ResponseEntity<Void> checkEmail(@RequestParam("email") String email) {
        if (personDao.findByEmail(email).isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/hr/candidate/{id}")
    public Person PersonById(@PathVariable int id) {
        return this.personDao.getByPersonId(id);
    }

    @PostMapping("/hr/modifycandidate/{id}")
    ResponseEntity <Void> modifyCandidate(@PathVariable int id, @Param("name") String name
    ,@Param("firstName") String firstName


    ){

        Person p  = personDao.getById(id);

        p.setName(name);

        p.setFirstName(firstName);
        this.personDao.save(p);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/hr/modifycandidate/{id}/{name}")
    public String modifyCandidate5(@PathVariable int id,@PathVariable String name){
        Person p  = personDao.getById(id);
        p.setName(name);

        this.personDao.save(p);
        return "Person "+ p.getName() +" "+ p.getFirstName()+" is disabled ";
    }
    @PostMapping("/hr/modifycandidate2/{id}/{firstName}")
    public String modifyCandidateFirstname(@PathVariable int id,@PathVariable String firstName){
        Person p  = personDao.getById(id);

        p.setFirstName(firstName);
        this.personDao.save(p);
        return "Person "+ p.getName() +" "+ p.getFirstName()+" is disabled ";
    }

}
