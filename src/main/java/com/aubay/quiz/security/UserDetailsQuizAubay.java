package com.aubay.quiz.security;
import com.aubay.quiz.dao.AdministratorDao;
import com.aubay.quiz.dao.CandidateDao;
import com.aubay.quiz.dao.HRDao;
import com.aubay.quiz.model.Administrator;
import com.aubay.quiz.model.Candidate;
import com.aubay.quiz.model.HR;
import com.aubay.quiz.model.Person;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.aubay.quiz.dao.PersonDao;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;


public class UserDetailsQuizAubay implements UserDetails {

    private  Person person;
    private AdministratorDao administratorDao;
    private HRDao hrDao;
    private CandidateDao candidateDao;

    public UserDetailsQuizAubay(Person person, AdministratorDao administratorDao, HRDao hrDao, CandidateDao candidateDao) {
        this.person = person;
        this.administratorDao = administratorDao;
        this.hrDao = hrDao;
        this.candidateDao = candidateDao;
    }

    public UserDetailsQuizAubay(Person person) {
    }

    //Identify the role of the person
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> listeAuthorities = new ArrayList<>();

        //is Administrator

        Optional<Administrator> administrator = administratorDao.findById(person.getId_Person());
        if (administrator.isPresent()){
            listeAuthorities.add(new SimpleGrantedAuthority("ADMINISTRATOR"));
        }
        //if HR
        Optional<HR> hr = hrDao.findById(person.getId_Person());
        if (hr.isPresent()){
            listeAuthorities.add(new SimpleGrantedAuthority("HR"));
        }
        //if Candidate
        Optional<Candidate> candidate = candidateDao.findById(person.getId_Person());
        if (administrator.isPresent()){
            listeAuthorities.add(new SimpleGrantedAuthority("CANDIDATE"));
        }
        return listeAuthorities;
    }

    //récupération de person contenu dans le UserDetails
    public Person getPerson(){return person;}


    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return person.isActive();
    }

}
