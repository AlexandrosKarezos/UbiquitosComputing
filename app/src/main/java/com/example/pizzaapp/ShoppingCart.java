package com.example.pizzaapp;

import java.util.ArrayList;

public class ShoppingCart {
    private static ArrayList<ShoppingCartItem> articleList = new ArrayList<>();

    public ShoppingCart() {}

    public void addItem(ShoppingCartItem item){
        articleList.add(item);
    }

    public void removeItem(ShoppingCartItem item){
        articleList.remove(item);
    }

    public ShoppingCartItem getItem(int i){
        return articleList.get(i);
    }

    public double getTotalPrice(){
        double totalPrice = 0;
        for(ShoppingCartItem item : articleList){
            totalPrice += item.getPrice() * item.getAmount();
        }
        return totalPrice;
    }


}
