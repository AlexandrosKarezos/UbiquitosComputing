package com.example.pizzaapp;

public class Feedback {
    public String stars, comment,email;

    public Feedback(String stars, String comment) {
        this.stars = stars;
        this.comment = comment;
    }
    public Feedback(String stars, String comment,String email) {
        this.stars = stars;
        this.comment = comment;
        this.email=email;
    }
}
