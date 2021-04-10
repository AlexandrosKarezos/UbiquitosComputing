package com.example.pizzaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class BestellungsverlaufActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestellungsverlauf);
        Spinner dropdown = findViewById(R.id.spinner1);
        String[] items = new String[]{"Profil bearbeiten", "Bestellungsverlauf", "Logout"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.bestellungsverlauf_linear_layout);

        TextView tableTopTv = new TextView(this);
        tableTopTv.setText(getResources().getString(R.string.bestellungsverlauf_TabTop));
        tableTopTv.setId(tableTopTv.generateViewId());
        tableTopTv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tableTopTv.setClickable(true);
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

    public void back(View view){
        Intent intent = new Intent(this, BenutzerAngemeldetActivity.class);
        startActivity(intent);
    }
}