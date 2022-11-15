package com.aubay.quiz.security;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

//classe qui va gérer les autorisation de connexion
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceQuizAubay userDetailsServiceQuizAubay;

//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;

    //Surcharge pour dire la manière de s'authentifier (Vérification existance de l'utilisateur et de son rôle)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceQuizAubay);
    }

    //configuration des autorisations (accès à différentes url)
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                //cors: Cross Origin Resource Sharing -> gère les accès des requêtes
                //applyPermitDefaultValues() -> autorise n'importe quelle requête avec n'importe quelle en-tête
                .and()
                .csrf().disable() //désactive le token de formulaire présent par défaut (permet de lutter contre les failles de sécurité CSAF)
                .authorizeRequests()
                .antMatchers("/connexion").permitAll()
                .antMatchers("/admin/**").hasRole("ADMINISTRATOR")
               // .antMatchers("/**").hasAnyRole("UTILISATEUR","GESTION")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //stateless -> désactive le management par session

        //méthode UsernamePasswordAuthenticationFilter va vérifier le nom d'utilisateur et le mot de passe
        //http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    //Quand utilisation de la classe security, spring va générer une nouvelle instance des beans déclarés dans cette classe
    @Bean
    public PasswordEncoder getPasswordEncoder(){
     return NoOpPasswordEncoder.getInstance();
       // return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


}
