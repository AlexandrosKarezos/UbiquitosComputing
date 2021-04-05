package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);
    }

    public void menue(View view){
        Intent intent = new Intent(this, MenueActivity.class);
        startActivity(intent);
    }

    public void reservation(View view){
        Intent intent = new Intent(this, ReservationActivity.class);
        startActivity(intent);
    }

    public void feedback(View view){
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    public void login(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void margeritha(View view){
        Intent intent = new Intent(this, MenueAuswahlActivity.class);
        startActivity(intent);
    }

    public void prosciutto(View view){
        Intent intent = new Intent(this, MenueAuswahlActivity.class);
        startActivity(intent);
    }

    public void salami(View view){
        Intent intent = new Intent(this, MenueAuswahlActivity.class);
        startActivity(intent);
    }

    public void bestellung(View view){
        Intent intent = new Intent(this, UebersichtActivity.class);
        startActivity(intent);
    }

}