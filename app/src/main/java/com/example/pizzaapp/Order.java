package com.example.pizzaapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Order {
    public String surname,name,plz,city,street,email, notes,paymentmethod, etage;
    public Date currentTime= Calendar.getInstance().getTime();
    public ArrayList<ShoppingCartItem> articleList;
    public boolean isDone = false;

    public Order(String surname, String name, String plz, String city, String street, String email, String paymentmethod, Date currentTime, ArrayList<ShoppingCartItem> articleList, boolean isDone) {
        this.surname = surname;
        this.name = name;
        this.plz = plz;
        this.city = city;
        this.street = street;
        this.email = email;
        this.paymentmethod = paymentmethod;
        this.currentTime = currentTime;
        this.articleList = articleList;
        this.isDone = isDone;
    }

    public Order(String surname, String name, String plz, String city, String street, String email, String notes, Date currentTime, ArrayList<ShoppingCartItem> articleList, String paymentmethod, String etage, boolean isDone) {
        this.surname = surname;
        this.name = name;
        this.plz = plz;
        this.city = city;
        this.street = street;
        this.email = email;
        this.notes = notes;
        this.currentTime = currentTime;
        this.articleList = articleList;
        this.paymentmethod=paymentmethod;
        this.etage = etage;
        this.isDone = isDone;
    }
}

