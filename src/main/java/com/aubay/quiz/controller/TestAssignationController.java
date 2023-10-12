package com.aubay.quiz.controller;
import com.aubay.quiz.View.ViewQuestions;
import com.aubay.quiz.View.ViewTest;
import com.aubay.quiz.View.ViewTestAssigned;
import com.aubay.quiz.dao.CandidateDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.TestAssignationDao;
import com.aubay.quiz.dao.TestDao;
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
import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

@RestController
public class TestAssignationController {

    public TestAssignationDao testAssignationDao;

    public TestAssignationController(TestAssignationDao testAssignationDao) {
        this.testAssignationDao = testAssignationDao;
    }
    @Autowired
    TestDao testDAo;

    @Autowired
    CandidateDao candidateDao;
    @PostMapping("/hr/test/assign")
    ResponseEntity<Void> createTest(@Param("testId") int testId,
                                    @Param("candidateId") int candidateId){
        TestAssignation testAssignation = new TestAssignation();
        LocalDate today = LocalDate.now();
        testAssignation.setActive(true);
        testAssignation.setAssignationDate(today);
        Test test= testDAo.getReferenceById(testId);
        testAssignation.setTest(test);
        Candidate candidate=  candidateDao.getReferenceById(candidateId);
        testAssignation.setCandidate(candidate);


        try {
            this.testAssignationDao.save(testAssignation);
        }
        catch(Exception e){

            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hr/testassigned")
    @JsonView(ViewTestAssigned.class)
    public List<TestAssignation> listTestsAssigned(
                                          @Param("candidateId") int candidateId) {
        return this.testAssignationDao.getListTestAssigned(candidateId);
    }

    @GetMapping("/candidate/testAssigned")
    public List<TestAssignation> listTestsAssignedForCandidate(
            @Param("candidateId") int candidateId) {
        return this.testAssignationDao.getListTestAssigned(candidateId);
    }

    @PostMapping("/hr/delete-testAssigned")
    ResponseEntity<Void> deleteTestAssigned(
            @Param("idTestAssignation") int idTestAssignation){

       TestAssignation testAssignation = testAssignationDao.getById(idTestAssignation);
        testAssignation.setActive(false);
        try {
            this.testAssignationDao.save(testAssignation);

        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hr/verify-test-assignation")
    @JsonView(ViewTestAssigned.class)
    public ResponseEntity<Void> checkTestAssignation(@RequestParam("testId") Integer testId) {
        if (testAssignationDao.checkTestAssignation(testId).isPresent()) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
//    @GetMapping("/hr/verify-test-assignation")
//    public ResponseEntity<Void> checkTestAssignation(@RequestParam("testId") Integer testId) {
//
//        return this.test
//    }


}
