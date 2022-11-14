package com.aubay.quiz.model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
//Signale à JPA que cette classe doit auto-générer une table
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class HR extends Person {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number_HR;

    public HR() {


    }
}