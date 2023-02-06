package com.aubay.quiz.controller;
import com.aubay.quiz.View.ViewAnswers;
import com.aubay.quiz.View.ViewTest;
import com.aubay.quiz.dao.AdministratorDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.TestDao;
import com.aubay.quiz.dao.TestQuestionDao;
import com.aubay.quiz.model.*;
import com.aubay.quiz.security.JwtUtil;
import com.aubay.quiz.security.UserDetailsServiceQuiz;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.io.Serializable;
import java.util.Optional;
import java.util.List;

@RestController
public class TestController {


    public TestDao testDao;



    public TestController(TestDao testDao) {
        this.testDao = testDao;
    }

    @Autowired
    UserDetailsServiceQuiz userDetailsServiceQuiz;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    HrDao hrDao;

    @PostMapping("/hr/test/create")
    ResponseEntity<Void> createTest(@RequestBody Test test, @RequestHeader(value="Authorization") String autorisation ){
        String token = autorisation.substring(7);
        String idHr = jwtUtil.getTokenBody(token).get("personId").toString();
        Hr hr = hrDao.getByPersonId(Integer.parseInt(idHr));
        test.setHr(hr);
test.setActive(true);
        try {
            this.testDao.save(test);
        }
        catch(Exception e){

                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().build();
    }

    /**
     * @param
     * @return
     */
    @GetMapping("/hr/list-test")
    @JsonView(ViewTest.class)
    public List<Test> testList() {
        return this.testDao.getListTests();
    }

    @GetMapping("/hr/test/{id}")
    @JsonView(ViewTest.class)
    public Optional<Test> testById(@PathVariable int id) {
        return this.testDao.findById(id);
    }


}
