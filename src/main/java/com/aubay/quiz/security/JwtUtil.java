package com.aubay.quiz.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtUtil {

    @Value("${secret}") //va récupérer dans application-properties la valeur de secret
    private String secret;

    //Claims = objet qui va stocker les infos du token
    public Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails){

        //pour avoir accès à la fonction getUtilisateur, nécessaire de caster avec UserDetailsLocParc
        UserDetailsQuiz userDetailsQuiz = (UserDetailsQuiz) userDetails;

        //Création de la map pour rentrer l'id du gestionnaire dans le token
        Map<String, Object> data = new HashMap<>();
        data.put("personId", userDetailsQuiz.getPerson().getPersonID());

        String listRights = userDetailsQuiz
                .getAuthorities()
                .stream()
                .map(role -> role.getAuthority())
                .collect(Collectors.joining(","));

        data.put("rights", listRights);

        return Jwts.builder()
                .setClaims(data) //on personnalise le body du token
                .setSubject(userDetails.getUsername()) //on récupère l'adresse mail par la méthode getUsername (cf. UserDetailsLocParc) et on le rentre dans le body du token
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

}
