package com.aubay.quiz.model;


import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public   class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Person;

    private String name;

    private String firstName;

    private String password;

    @Column(unique = true)
    private String email;

    private boolean active;

    public Person(){}

}
