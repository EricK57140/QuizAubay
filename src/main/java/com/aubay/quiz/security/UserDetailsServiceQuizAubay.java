package com.aubay.quiz.security;
import com.aubay.quiz.dao.CandidateDao;
import com.aubay.quiz.dao.PersonDao;
import com.aubay.quiz.dao.HRDao;
import com.aubay.quiz.dao.AdministratorDao;
import com.aubay.quiz.model.HR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.aubay.quiz.model.Person;
import com.aubay.quiz.model.Administrator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceQuizAubay implements UserDetailsService {

    private PersonDao personDao;
    @Autowired
    public UserDetailsServiceQuizAubay(PersonDao personDao) {

        this.personDao = personDao;
    }


    //vérification de l'existance de l'utilisateur qui tente de se connecter dans la base de données et si oui, récupération de se
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personDao.
                findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email and/or password doesn't exist"));

        UserDetailsQuizAubay userDetailsQuizAubay = new UserDetailsQuizAubay(person);
        return userDetailsQuizAubay;



    }
}
