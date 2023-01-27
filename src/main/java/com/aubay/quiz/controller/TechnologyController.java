package com.aubay.quiz.controller;

import com.aubay.quiz.dao.AdministratorDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.TechnologyDao;
import com.aubay.quiz.model.Administrator;
import com.aubay.quiz.model.Person;
import com.aubay.quiz.model.Technology;
import com.aubay.quiz.model.Hr;
import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.io.Serializable;
import java.util.Optional;
import java.util.List;

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

    @Autowired
    HrDao hrDao;

    @PostMapping("/hr/technology/create")
    ResponseEntity<Void> createTechnology(@RequestBody Technology technology, @RequestHeader(value="Authorization") String autorisation ){
        String token = autorisation.substring(7);
        String idAdministrator = jwtUtil.getTokenBody(token).get("personId").toString();
        Hr h = hrDao.getByPersonId(Integer.parseInt(idAdministrator));
        technology.setHr(h);
        technology.setActive(true);
        try {
            this.technologyDao.save(technology);
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/hr/technology/{id}")
    public ResponseEntity<Integer> deleteTechnology(@PathVariable int id){

      Optional <Technology> technologyToDelete = technologyDao.findById(id);
//
//      if(technologyToDelete.isPresent()){
//           return ResponseEntity.ok(technologyToDelete.get().getIdTechnology());
//       } else {
//           return ResponseEntity.noContent().build();
//      }

    if(technologyDao.existsById(id)) {
           this.technologyDao.deleteById(id);
           return ResponseEntity.ok(id);
     } else{         return ResponseEntity.noContent().build();     }
    }

    @GetMapping("/hr/list-technology")
    public List<Technology> technologyList() {
        return this.technologyDao.getListTechnologyActive();
    }

    @PostMapping("/hr/technology/disable/{id}")
    public ResponseEntity<Void> disableTechnology(@PathVariable int id){
        Technology t  = technologyDao.getById(id);
        t.setActive(false);
        this.technologyDao.save(t);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hr/nameTechnologyCheck")
    public ResponseEntity<Void> checkEmail(@RequestParam("nameTechnology") String nameTechnology) {
        if (technologyDao.findByNameTechnology(nameTechnology).isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
