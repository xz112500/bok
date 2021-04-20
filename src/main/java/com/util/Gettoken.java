package com.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class Gettoken {
    public String gettoken(String username,String password){
        String token="";
        token= JWT.create().withAudience(username).sign(Algorithm.HMAC256(password));
        return token;
    }
}
