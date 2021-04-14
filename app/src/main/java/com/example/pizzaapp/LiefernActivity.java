package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LiefernActivity extends AppCompatActivity {

    Button weiterB = (Button) findViewById(R.id.acceptButton);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liefern);
        weiterB.setEnabled(false);

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

    public void checkContent(View view){
        EditText usernameEditText = (EditText) view;
        String text = usernameEditText.getText().toString();
        if (text.matches("")) {
            Toast.makeText(this, "Du hast nicht alle Felder ausgefüllt", Toast.LENGTH_SHORT).show();
        }
    }
}