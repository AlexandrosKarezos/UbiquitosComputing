package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.shuhart.stepview.StepView;

public class UebersichtActivity extends AppCompatActivity {

    StepView stepView;
    TextView stepTextView;
    TextView descriptionTextView;

    int stepIndex = 0;
    String[] stepTexts = {"Schritt 1", "Schritt 2", "Schritt 3"};
    String[] descriptionTexts = {
           "b1",
           "b2",
           "b3"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uebersicht);

        stepTextView = findViewById(R.id.stepTextView);
        descriptionTextView = findViewById(R.id.descriptionTextView);
        stepView = findViewById(R.id.step_view);
        stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .stepsNumber(3)
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .commit();

        nextStep();
    }

    public void nextStep(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stepIndex++;

                if(stepIndex < stepTexts.length){
                    stepTextView.setText(stepTexts[stepIndex]);
                    descriptionTextView.setText(descriptionTexts[stepIndex]);
                    stepView.go(stepIndex, true);
                    nextStep();
                }
            }
        }, 3000);
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