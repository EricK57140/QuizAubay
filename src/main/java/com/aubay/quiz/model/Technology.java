package com.aubay.quiz.model;

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
    private  Integer IdTechnology;

    @Column(unique = true)
    private String NameTechnology;

    @ManyToOne
    private Administrator administrator;
}
