package com.example.pizzaapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MenueActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.menu_linear_layout);

        TextView margerithaTv = new TextView(this);
        margerithaTv.setText(getResources().getString(R.string.menu_margeritha));
        margerithaTv.setId(margerithaTv.generateViewId());
        margerithaTv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        margerithaTv.setClickable(true);
        margerithaTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuAuswahl(view);
            }
        });

        TextView salamiTv = new TextView(this);
        salamiTv.setText(getResources().getString(R.string.menu_salami));
        salamiTv.setId(salamiTv.generateViewId());
        salamiTv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        salamiTv.setClickable(true);
        salamiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuAuswahl(view);
            }
        });

        linearLayout.addView(margerithaTv);
        linearLayout.addView(salamiTv);
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

    public void menuAuswahl(View view){
        Intent intent = new Intent(this, MenueAuswahlActivity.class);
        startActivity(intent);
    }

    public void bestellung(View view){
        Intent intent = new Intent(this, UebersichtActivity.class);
        startActivity(intent);
    }

}