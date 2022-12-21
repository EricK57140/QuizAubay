package com.aubay.quiz.controller;
import com.aubay.quiz.dao.AdministratorDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.TestDao;
import com.aubay.quiz.model.Hr;
import com.aubay.quiz.model.Questions;
import com.aubay.quiz.model.Test;
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
    public String createQuestion(@RequestBody Test test, @RequestHeader(value="Authorization") String autorisation ){
        String token = autorisation.substring(7);
        String idHr = jwtUtil.getTokenBody(token).get("personId").toString();
        Hr hr = hrDao.getByPersonId(Integer.parseInt(idHr));
        test.setHr(hr);

        try {
            this.testDao.save(test);
        }
        catch(Exception e){
            return "This technology already exists";
        }
        return "The question" + test.getNameTest() + " has been created";
    }
}
