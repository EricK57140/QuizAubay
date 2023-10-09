package com.aubay.quiz.model;
import com.aubay.quiz.View.ViewTest;
import com.aubay.quiz.View.ViewTestAssigned;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class TestAssignation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ViewTestAssigned.class)
    private Integer idTestAssignation;
    @JsonView(ViewTestAssigned.class)
    private LocalDate assignationDate;
    private boolean active;

    @ManyToOne
    @JsonView(ViewTestAssigned.class)
    private Test test;

    @ManyToOne
    @JsonView(ViewTestAssigned.class)
    private Candidate candidate;

}
