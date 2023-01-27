package com.aubay.quiz.model;

import com.aubay.quiz.View.ViewQuestions;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewQuestions.class)
    private  Integer IdTechnology;
    @JsonView(ViewQuestions.class)
//    @Column(unique = true)
    private String nameTechnology;


    private boolean active;
    @ManyToOne
    private Hr hr;
}
