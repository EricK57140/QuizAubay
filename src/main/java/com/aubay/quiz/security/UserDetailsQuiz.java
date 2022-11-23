package com.aubay.quiz.security;

import com.aubay.quiz.model.Person;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserDetailsQuiz implements UserDetails {

    private final Person person;

    private final boolean isAdministrator;
    private final boolean isHr;
    private final boolean isCandidate;

    public UserDetailsQuiz(Person person, boolean isAdministrator, boolean isHr, boolean isCandidate) {

        this.person = person;
        this.isAdministrator = isAdministrator;
        this.isHr = isHr;
        this.isCandidate = isCandidate;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

            ArrayList<SimpleGrantedAuthority> listAuthorities = new ArrayList<>();

            if (isAdministrator && person.isActive()) {

                listAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            } else if (isHr && person.isActive()) {

                listAuthorities.add(new SimpleGrantedAuthority("ROLE_HR"));

            } else if (isCandidate && person.isActive()) {

                listAuthorities.add(new SimpleGrantedAuthority("ROLE_CANDIDATE"));
            }
            return listAuthorities;

    }
    public Person getPerson(){return person;}
    public boolean isAdministrator() {
        return isAdministrator;
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getEmail();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return person.isActive();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
