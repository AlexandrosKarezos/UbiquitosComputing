package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AbholenActivity extends AppCompatActivity {

    String name, surname, street, sNumber, city, email;
    int plz;

    EditText surnameInput;
    EditText nameInput;
    EditText streetInput;
    EditText hNrInput;
    EditText plzInput;
    EditText cityInput;
    EditText emailInput;

    Button submitButton, returnButton, abortButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abholen);

        surnameInput = (EditText) findViewById(R.id.surnameInput);
        nameInput = (EditText) findViewById(R.id.nameInput);
        streetInput = (EditText) findViewById(R.id.streetInput);
        hNrInput = (EditText) findViewById(R.id.hNrInput);
        plzInput = (EditText) findViewById(R.id.plzInput);
        cityInput = (EditText) findViewById(R.id.cityInput);
        emailInput = (EditText) findViewById(R.id.emailInput);

        submitButton = (Button) findViewById(R.id.submitButton);
        abortButton = (Button) findViewById(R.id.abortButton);
        returnButton = (Button) findViewById(R.id.returnButton);

    }

    private void showToast(String text){
        Toast.makeText(AbholenActivity.this, text, Toast.LENGTH_SHORT).show();
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

    public void abort(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void back(View view){
        Intent intent = new Intent(this, MenueActivity.class);
        startActivity(intent);
    }

    public void forward(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}