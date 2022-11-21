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

        return Jwts.builder()
                .setClaims(data) //on personnalise le body du token
                .setSubject(userDetails.getUsername()) //on récupère l'adresse mail par la méthode getUsername (cf. UserDetailsLocParc) et on le rentre dans le body du token
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }
//    public String generateToken(UserDetailsLocParc userDetailsLocParc){
//
//
//
//
//        //Création de la map pour rentrer l'id du gestionnaire dans le token
//        Map<String, Object> data = new HashMap<>();
//        data.put("idUtilisateur", userDetailsLocParc.getUtilisateur().getIdUtilisateur());
//
//        //listeDroit =
//
//        String listeDroit = userDetailsLocParc
//                .getAuthorities()
//                .stream()
//                .map(role -> role.getAuthority())
//                .collect(Collectors.joining(","));
//
//        data.put("droits", listeDroit);
//
//        Calendar dateAjourdhui = Calendar.getInstance();
//
//        long dateAujourdhuiEnMilliseconde = dateAjourdhui.getTimeInMillis();
//        Date dateExpiration = new Date(dateAujourdhuiEnMilliseconde + 100000000);
////        40 * 60 * 1000
//
//
//        data.put("numeroToken", userDetailsLocParc.getUtilisateur().getNumeroToken());
//
//        return Jwts.builder()
//                .setClaims(data) //on personnalise le body du token
//                .setSubject(userDetailsLocParc.getUsername()) //on récupère l'adresse mail par la méthode getUsername (cf. UserDetailsLocParc) et on le rentre dans le body du token
//                .signWith(SignatureAlgorithm.HS256,secret)
//                .compact();
//    }
//
//    public boolean tokenValide(String token, UserDetails userDetails){
//        Claims claims = getTokenBody(token);
//        //vérifie si l'utilisateur qui se connecte est le même que celui contenu dans le token
//        return (claims.getSubject().equals(userDetails.getUsername()));
//    }


}
