package com.aubay.quiz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//méthodes de la classe appelées à chaque requête
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceQuiz userDetailsServiceQuiz;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Récupération du token dans l'en-tête de la requête
        String token = request.getHeader("Authorization");

        //On vérifie que le token commence par "Bearer" puis on supprime le début du token "Bearer "
        if(token != null && token.startsWith("Bearer")){
            String jwt = token.substring(7);
            //récupération de l'adresse-mail contenu dans le token
            String email = jwtUtil.getTokenBody(jwt).getSubject();

            //vérification existence de l'utilisateur dans la bdd et récupération de ses données
            UserDetails userDetails = this.userDetailsServiceQuiz.loadUserByUsername(email);

            //vérification validité du token par comparaison de l'utilisateur et de l'utilisateur contenu dans le token(.tokenValide())
          //  if(jwtUtil.tokenValide(jwt,userDetails)){
                //récupération des autorisations de l'utilisateur
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //on charge les userDetails dans le contexte de sécurité pour gérer les autorisations des urls
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
        filterChain.doFilter(request, response);
    }
}
