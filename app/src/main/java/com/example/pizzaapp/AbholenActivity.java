package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

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

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                surname = surnameInput.getText().toString();
                name = nameInput.getText().toString();
                street = streetInput.getText().toString();
                sNumber = hNrInput.getText().toString();
                plz = Integer.valueOf(plzInput.getText().toString());
                city = cityInput.getText().toString();
                email = emailInput.getText().toString();

                showToast(surname);
                showToast(name);
                showToast(street);
                showToast(sNumber);
                showToast(String.valueOf(plz));
                showToast(city);
                showToast(email);
            }
        });

    }

    private void showToast(String text){
        Toast.makeText(AbholenActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}