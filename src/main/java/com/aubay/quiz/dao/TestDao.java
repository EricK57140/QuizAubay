package com.aubay.quiz.dao;
import com.aubay.quiz.model.Person;
import com.aubay.quiz.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao extends JpaRepository<Test,Integer>{

    @Query("FROM Test t WHERE t.active =1")
    List<Test> getListTests();


}
