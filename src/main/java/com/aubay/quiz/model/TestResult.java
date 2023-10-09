package com.aubay.quiz.model;
import com.aubay.quiz.View.ViewAnswers;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@IdClass(TestResult.KeyTestResult.class)
public class TestResult {
    @Id
    private Integer testTakenId;
    @Id
    private Integer questionTakenId;
    @Id
    private Integer answerGivenId;

    @ManyToOne
    @MapsId("test_taken_id")
    @JoinColumn(name = "test_taken_id")
    private TestAssignation testAssignation;
    @ManyToOne
    @MapsId("question_taken_id")
    @JoinColumn(name = "question_taken_id")
    private Questions questions;
    @ManyToOne
    @MapsId("answer_given_id")
    @JoinColumn(name = "answer_given_id")
    @JsonView(ViewAnswers.class)
    private Answers answers;

    @Getter
    @Setter
    @Embeddable
    public  static class KeyTestResult implements Serializable{

        @Column(name = "test_taken_id")
        Integer testTakenId;
        @Column(name = "question_taken_id")
        Integer questionTakenId;

        @Column(name = "answer_given_id")
        Integer answerGivenId;;

    }

}
