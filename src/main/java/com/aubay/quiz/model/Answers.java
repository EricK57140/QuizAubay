package com.aubay.quiz.model;

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
    private Integer idAnswers;

    private String answerWording;
    private String answerCorrect;

    public Answers() {
    }
}
