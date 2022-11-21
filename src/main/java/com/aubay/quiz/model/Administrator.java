package com.aubay.quiz.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Administrator extends Person{

    @GeneratedValue(strategy = GenerationType.AUTO)
    private int numberAdministrator;



    public Administrator(){

    }

}
