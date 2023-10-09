package com.aubay.quiz.model;

import com.aubay.quiz.View.ViewTest;
import com.aubay.quiz.View.ViewTestAssigned;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ViewTest.class,ViewTestAssigned.class})
    private Integer idTest;
    @JsonView({ViewTest.class,ViewTestAssigned.class})
    private String nameTest;
    @JsonView({ViewTest.class,ViewTestAssigned.class})
    private boolean active;
//    private  Date dateTest;
//    private String testDifficulty;
//    private Integer maximumScore;
//    private Integer totalTime;

    public Test() {

    }

    @ManyToOne
    private Hr hr;

//    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<TestQuestion> testQuestions;


//    @ManyToMany(cascade = CascadeType.ALL)
//    @JsonView(ViewTest.class)
//    @JoinTable(
//            name = "testQuestion",
//            joinColumns = @JoinColumn(name = "testId"),
//            inverseJoinColumns = @JoinColumn(name = "questionId")
//    )
//    private List<Questions> questionsList;



}
