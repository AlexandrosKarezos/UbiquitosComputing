package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MenueAuswahlActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue_auswahl);

        NumberPicker numberPicker = findViewById(R.id.numberPicker);
        textView = findViewById(R.id.auswahl);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(1000);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                textView.setText("Menge: " + newVal);
            }
        });
    }
    //Menge muss in Variable geschrieben werden und weitergeleitet werden
    public void confirm(View view){
        Intent intent = new Intent(this, MenueActivity.class);
        startActivity(intent);
    }

    public void abort(View view){
        Intent intent = new Intent(this, MenueActivity.class);
        startActivity(intent);
    }
}