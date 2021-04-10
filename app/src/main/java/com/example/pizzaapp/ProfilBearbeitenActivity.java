package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProfilBearbeitenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_bearbeiten);
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Profil bearbeiten", "Bestellungsverlauf", "Logout"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
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

    public void abort(View view){
        Intent intent = new Intent(this, BenutzerAngemeldetActivity.class);
        startActivity(intent);
    }

    public void accept(View view){
        Intent intent = new Intent(this, BenutzerAngemeldetActivity.class);
        startActivity(intent);
    }
}