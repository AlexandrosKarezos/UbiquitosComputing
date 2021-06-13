package com.example.pizzaapp;

import java.util.ArrayList;
// Diese Klasse ist Schablone für das Objekt des Feedbacks
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
