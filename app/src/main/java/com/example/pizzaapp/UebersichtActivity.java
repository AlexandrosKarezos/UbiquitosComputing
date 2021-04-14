package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.shuhart.stepview.StepView;

public class UebersichtActivity extends AppCompatActivity {

    RadioGroup bestellArtRG;
    Button weiterButton;
    RadioButton deliverButton;
    RadioButton pickupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebersicht);

        bestellArtRG = (RadioGroup) findViewById(R.id.radioGroup);
        weiterButton = (Button) findViewById(R.id.submitButton);
        deliverButton = (RadioButton) findViewById(R.id.radioButtonDeliver);
        pickupButton = (RadioButton) findViewById(R.id.radioButtonPickUp);

        weiterButton.setEnabled(false);
        deliverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weiterButton.setEnabled(true);
            }
        });

        pickupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weiterButton.setEnabled(true);
            }
        });
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
        Intent intent;
        if(bestellArtRG.getCheckedRadioButtonId() == R.id.radioButtonDeliver){
            intent = new Intent(this, LiefernActivity.class);
        }
        else{
            intent = new Intent(this, AbholenActivity.class);
        }
        startActivity(intent);
    }
}