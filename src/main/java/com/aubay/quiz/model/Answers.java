package com.aubay.quiz.model;

import com.aubay.quiz.View.ViewAnswers;
import com.aubay.quiz.View.ViewQuestions;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({ViewQuestions.class,ViewAnswers.class})
    private Integer idAnswers;
    @JsonView({ViewQuestions.class,ViewAnswers.class})
    private String answerWording;
    @JsonView({ViewQuestions.class, ViewAnswers.class})
    private boolean active;
    @JsonView({ViewQuestions.class,ViewAnswers.class})
    private boolean correct;


    @ManyToOne
    @JsonView(ViewAnswers.class)
    private Questions questions;





    public Answers() {
    }
}
