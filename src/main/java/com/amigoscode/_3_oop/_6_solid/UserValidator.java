package com.amigoscode._3_oop._6_solid;

public class UserValidator {

    public void validate(String name, String email) {
        if(name == null || name.isEmpty()) throw new IllegalArgumentException("Invalid name");
        if(email == null || !email.contains("@")) throw new IllegalArgumentException("Invalid email");
    }
}
