package com.aubay.quiz.dao;
import com.aubay.quiz.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDao extends JpaRepository<Test,Integer>{
}
