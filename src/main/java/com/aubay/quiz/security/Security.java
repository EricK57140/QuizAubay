package com.aubay.quiz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;


@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {


    @Autowired
    private  UserDetailsService userDetailsServiceQuiz;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsServiceQuiz);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                //cors: Cross Origin Resource Sharing -> gère les accès des requêtes
                //applyPermitDefaultValues() -> autorise n'importe quelle requête avec n'importe quelle en-tête
                .and()
                .csrf().disable() //désactive le token de formulaire présent par défaut (permet de lutter contre les failles de sécurité CSAF)
                .authorizeRequests()
                .antMatchers("/connexion").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/hr/**").hasRole("HR")
                //.antMatchers("/test").permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //stateless -> désactive le management par session

        //méthode UsernamePasswordAuthenticationFilter va vérifier le nom d'utilisateur et le mot de passe
       http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    //Quand utilisation de la classe security, spring va générer une nouvelle instance des beans déclarés dans cette classe
    @Bean
    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}





