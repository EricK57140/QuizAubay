package com.aubay.quiz.model;

import com.aubay.quiz.View.ViewEmailCheck;
import com.aubay.quiz.View.ViewQuestions;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Person  implements  Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewQuestions.class)
    private Integer personID;
    private String name;
    private  String firstName;

    @Column(unique = true)
    @JsonView(ViewEmailCheck.class)
    private  String email;


    private String password;

    private boolean active;

    @ManyToOne
    private Hr hr;

    @ManyToOne
    private Administrator administrator;
}
