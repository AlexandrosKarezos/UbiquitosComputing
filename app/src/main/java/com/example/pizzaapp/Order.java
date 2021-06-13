package com.example.pizzaapp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
// Diese Klasse ist Schablone für die Bestellung
public class Order {
    public String surname,name,plz,city,street, houseNr,email, notes,paymentmethod, etage;
    public String currentTime;
    public ArrayList<ShoppingCartItem> articleList;
    public boolean isDone = false;
// Konstruktor für Abholen
    public Order(String surname, String name, String plz, String city, String street, String houseNr, String email, String paymentmethod, String currentTime, ArrayList<ShoppingCartItem> articleList, boolean isDone) {
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
        this.houseNr = houseNr;
    }
// Konstruktor für Lieferung (mit Etage)
    public Order(String surname, String name, String plz, String city, String street, String houseNr, String email, String notes, String currentTime, ArrayList<ShoppingCartItem> articleList, String paymentmethod, String etage, boolean isDone) {
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
        this.houseNr = houseNr;
    }
}

