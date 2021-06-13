package com.example.pizzaapp;
// Klasse für die Reservierung
public class Reservation {
    public String anzPers,resTime,resDate,surname,name,email;
    public boolean isDone;


    public Reservation(String anzPers, String resTime, String resDate, String surname, String name, String email, boolean isDone) {
        this.anzPers = anzPers;
        this.resTime = resTime;
        this.resDate = resDate;
        this.surname = surname;
        this.name = name;
        this.email = email;
        this.isDone = isDone;
    }
}
