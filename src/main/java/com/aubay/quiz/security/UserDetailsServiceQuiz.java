package com.aubay.quiz.security;

import com.aubay.quiz.dao.PersonDao;
import com.aubay.quiz.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceQuiz implements UserDetailsService {

    private PersonDao personDao;

    @Autowired
    UserDetailsServiceQuiz(PersonDao personDao){
        this.personDao= personDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Person person = personDao
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("nono"));
        UserDetailsQuiz userDetailsQuiz = new UserDetailsQuiz(person);
        return userDetailsQuiz;
    }
}
