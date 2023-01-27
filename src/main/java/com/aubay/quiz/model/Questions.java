package com.aubay.quiz.model;

import com.aubay.quiz.View.ViewAnswers;
import com.aubay.quiz.View.ViewQuestions;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView({ViewQuestions.class,ViewAnswers.class})
    private Integer idQuestions;
    @JsonView(ViewQuestions.class)
    private String questionTitle;
    @JsonView(ViewQuestions.class)
    private Integer scoreByQuestion;
    @JsonView(ViewQuestions.class)
    private Integer timer;
    @JsonView(ViewQuestions.class)
    private boolean active;

    public Questions() {
    }

    @ManyToOne
    private Hr hr;
    @ManyToOne
    @JsonView(ViewQuestions.class)
    private Technology technology;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "questions")
    @JsonView({ViewQuestions.class})
    private List<Answers> listAnswers  ;

}
