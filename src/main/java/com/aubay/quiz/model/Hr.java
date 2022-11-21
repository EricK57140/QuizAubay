package com.aubay.quiz.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Hr extends Person{
    @Column(unique = true)
    private int numberHr;


    public Hr(){

    }


}
