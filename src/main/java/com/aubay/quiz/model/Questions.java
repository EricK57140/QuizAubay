package com.aubay.quiz.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.*;
import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuestions;

    private String questionTitle;
    private Integer ScoreByQuestion;
    private Integer timer;

    public Questions() {
    }

    @ManyToOne
    private Hr hr;


}
