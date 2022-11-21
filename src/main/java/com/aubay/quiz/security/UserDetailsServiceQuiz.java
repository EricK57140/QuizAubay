package com.aubay.quiz.security;

import com.aubay.quiz.dao.AdministratorDao;
import com.aubay.quiz.dao.HrDao;
import com.aubay.quiz.dao.PersonDao;
import com.aubay.quiz.model.Administrator;
import com.aubay.quiz.model.Hr;
import com.aubay.quiz.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceQuiz implements UserDetailsService {

    private PersonDao personDao;
    private AdministratorDao administratorDao;
    private HrDao hrDao;
    @Autowired
    UserDetailsServiceQuiz(PersonDao personDao, AdministratorDao administratorDao, HrDao hrDao) {

        this.personDao= personDao;
        this.administratorDao = administratorDao;
        this.hrDao = hrDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Person person = personDao
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("nono"));

        Optional<Administrator> administrator = administratorDao.findByPersonId(person.getPersonID());
        Optional<Hr> hr = hrDao.findByPersonId(person.getPersonID());

        UserDetailsQuiz userDetailsQuiz = new UserDetailsQuiz(person, administrator.isPresent(), hr.isPresent());
        return userDetailsQuiz;
    }
}
