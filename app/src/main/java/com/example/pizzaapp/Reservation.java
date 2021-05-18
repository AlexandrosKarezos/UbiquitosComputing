package com.example.pizzaapp;

public class Reservation {
    public String anzPers,resTime,resDate,surname,name,email;


    public Reservation(String anzPers, String resTime, String resDate, String surname, String name, String email) {
        this.anzPers = anzPers;
        this.resTime = resTime;
        this.resDate = resDate;
        this.surname = surname;
        this.name = name;
        this.email = email;
    }
}
