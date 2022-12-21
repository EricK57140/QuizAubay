package com.aubay.quiz.model;

import java.util.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTest;

    private String nameTest;
    private  Date dateTest;
    private String testDifficulty;
    private Integer maximumScore;
    private Integer totalTime;

    public Test() {

    }

    @ManyToOne
    private Hr hr;



}
