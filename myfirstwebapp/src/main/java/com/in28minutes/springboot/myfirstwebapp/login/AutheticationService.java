package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service
public class AutheticationService {

    public boolean authenticate(String userName, String password) {

        boolean isValidUserName = userName.equalsIgnoreCase("phy");
        boolean isValidPassword =  password.equalsIgnoreCase("123");

        return isValidUserName && isValidPassword;
    }
}
