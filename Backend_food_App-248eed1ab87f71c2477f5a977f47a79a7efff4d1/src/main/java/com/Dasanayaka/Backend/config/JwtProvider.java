package com.Dasanayaka.Backend.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service

public class JwtProvider {

  private   SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

  public  String generatedToken(Authentication auth){

      Collection<? extends GrantedAuthority> authorities =auth.getAuthorities();
      String roles= populateAuthorities(authorities);
      String Jwt = Jwts.builder().setIssuedAt(new Date(()))
              .setExpiration((new Date((new Date().getTime()+86400000))))
              .claim("email", auth.getName())
              .claim("authorities", roles)
              .signWith(key)
              .compact();
      return  Jwt;
  }

    private String populateAuthorities(Collection<? extends  GrantedAuthority> authorities) {
      Set<String> auths= new HashSet<>();
      for(GrantedAuthority authority: authorities){
          auths.add(authority.getAuthority());
      }
      return  String.join(",",auths);
    }
}
