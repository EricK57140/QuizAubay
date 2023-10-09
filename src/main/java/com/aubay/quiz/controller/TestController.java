package com.aubay.quiz.controller;
import com.aubay.quiz.View.ViewAnswers;
import com.aubay.quiz.View.ViewAnswersResult;
import com.aubay.quiz.View.ViewTest;
import com.aubay.quiz.dao.*;
import com.aubay.quiz.model.TestResult;
import com.aubay.quiz.model.TestQuestion;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @Autowired
    TestResultDao testResultDao;

    @Autowired
    TestQuestionDao testQuestionDao;

    @PostMapping("/hr/test/create")
    ResponseEntity<Void> createTest(@RequestBody Test test, @RequestHeader(value = "Authorization") String autorisation) {
        String token = autorisation.substring(7);
        String idHr = jwtUtil.getTokenBody(token).get("personId").toString();
        Hr hr = hrDao.getByPersonId(Integer.parseInt(idHr));
        test.setHr(hr);
        test.setActive(true);
        try {
            this.testDao.save(test);
        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/hr/test/copy/{testId}")
    ResponseEntity<Void> copyTest(@PathVariable("testId") int testId, @RequestHeader(value = "Authorization") String autorisation) {
        String token = autorisation.substring(7);
        String idHr = jwtUtil.getTokenBody(token).get("personId").toString();
        Hr hr = hrDao.getByPersonId(Integer.parseInt(idHr));
        Optional<Test> originalTest = testDao.findById(testId);

        if (!originalTest.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Test newTest = new Test();
        LocalDateTime now = LocalDateTime.now(); // get the current date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // define the desired date-time format
        String formattedDateTime = now.format(formatter); // format the date and time as a string
        newTest.setNameTest(originalTest.get().getNameTest() + " (" + formattedDateTime + ")"); // add the formatted date and time to the test name
      //  newTest.setNameTest(originalTest.get().getNameTest() + "(" + LocalDate.now() + ")");
        newTest.setHr(hr);
        newTest.setActive(true);
        newTest = this.testDao.save(newTest);

        List<TestQuestion> testQuestions = testQuestionDao.findByTestId(testId);
        List<TestQuestion> copiedTestQuestions = new ArrayList<>();
        for (TestQuestion testQuestion : testQuestions) {
            TestQuestion copiedTestQuestion = new TestQuestion();
            copiedTestQuestion.setTestId(newTest.getIdTest());
            copiedTestQuestion.setQuestionId(testQuestion.getQuestionId());
            copiedTestQuestions.add(copiedTestQuestion);
        }
        testQuestionDao.saveAll(copiedTestQuestions);

        return ResponseEntity.ok().build();
    }


    @GetMapping("hr/listquestionbytest/{id}")
    @JsonView(ViewTest.class)
    public List<TestQuestion> testquestion(@PathVariable int id){



        return this.testQuestionDao.getListQuestionsByTest(id);
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


    @GetMapping("/hr/list-test-by-search")
    public List<Test> testListBysearch(@Param("search") String search) {
        return this.testDao.findTestsByTypingSearch(search);
    }

    @PostMapping("/candidate/save-question-answer")
    ResponseEntity<Void> saveQuestionAnswer(@Param("testId") int testId, @Param("questionId") int questionId, @Param("answerId") int answerId) {

        TestResult testResult = new TestResult();
        testResult.setTestTakenId(testId);
        testResult.setQuestionTakenId(questionId);
        testResult.setAnswerGivenId(answerId);
        try {
            this.testResultDao.save(testResult);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/hr/score")
    Integer scoreTest(@Param("testId") int testId) {
        int x = this.testResultDao.scoreCalculation(testId);
        int y = this.testResultDao.scoreTotal(testId);
        return ((x * 100)/y );
    }

    @GetMapping("/hr/testquestions/{id}")
    @JsonView(ViewAnswers.class)
    public List<TestResult> testQuestionsList(@PathVariable int id) {
        return this.testResultDao.listSelectedAnswer(id);
    }



}

