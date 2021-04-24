package com.example.pizzaapp;

public class User {
    public String firstName, lastName,email,postCode,street,streetNumber,city,username;
    public User(){

    }
    public User(String lastName, String firstName, String email, String postCode, String street, String streetNumber, String city, String username){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.postCode=postCode;
        this.street=street;
        this.streetNumber=streetNumber;
        this.city=city;
        this.username=username;
    }
}
