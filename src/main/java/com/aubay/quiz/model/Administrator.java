package com.aubay.quiz.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Administrator extends Person{

    @Column(unique = true)
    private int numberAdministrator;



    public Administrator(){

    }

}
