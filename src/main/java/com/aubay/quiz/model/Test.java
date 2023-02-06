package com.aubay.quiz.model;

import java.util.*;

import com.aubay.quiz.View.ViewAnswers;
import com.aubay.quiz.View.ViewQuestions;
import com.aubay.quiz.View.ViewTest;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewTest.class)
    private Integer idTest;
    @JsonView(ViewTest.class)
    private String nameTest;
    @JsonView(ViewTest.class)
    private boolean active;
//    private  Date dateTest;
//    private String testDifficulty;
//    private Integer maximumScore;
//    private Integer totalTime;

    public Test() {

    }

    @ManyToOne
    private Hr hr;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JsonView(ViewTest.class)
//    @JoinTable(
//            name = "testQuestion",
//            joinColumns = @JoinColumn(name = "testId"),
//            inverseJoinColumns = @JoinColumn(name = "questionId")
//    )
//    private List<Questions> questionsList;



}
