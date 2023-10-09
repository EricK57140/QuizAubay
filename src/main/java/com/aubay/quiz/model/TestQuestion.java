package com.aubay.quiz.model;

import com.aubay.quiz.View.ViewTest;
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
@IdClass(TestQuestion.KeyTestQuestion.class)
public class TestQuestion {

    @Id
    @JsonView(ViewTest.class)
    private Integer testId;

    @Id
    @JsonView(ViewTest.class)
    private Integer questionId;
    @ManyToOne
    @MapsId("test_id")
    @JoinColumn(name = "test_id")
    private Test test;
    @ManyToOne
    @MapsId("question_id")
    @JoinColumn(name = "question_id")
    private Questions questions;

    @Getter
    @Setter
    @Embeddable
    public  static class KeyTestQuestion implements Serializable{

        @Column(name = "test_id")
        Integer testId;
        @Column(name = "question_id")
        Integer questionId;



    }

    public KeyTestQuestion getKeyTestQuestion() {
        KeyTestQuestion key = new KeyTestQuestion();
        key.setTestId(this.testId);
        key.setQuestionId(this.questionId);
        return key;
    }






}
