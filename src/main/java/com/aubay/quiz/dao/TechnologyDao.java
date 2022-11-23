package com.aubay.quiz.dao;

import com.aubay.quiz.model.Person;
import com.aubay.quiz.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnologyDao extends JpaRepository<Technology,Integer> {
}
