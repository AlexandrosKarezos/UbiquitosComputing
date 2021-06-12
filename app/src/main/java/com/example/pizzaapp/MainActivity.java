package com.example.pizzaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment(new StartFragment());
    }

    public void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }

    public void menu(View view){
        setFragment(new MenuFragment());
    }

    public void reservation(View view){
        setFragment(new ReservationFragment());
    }

    public void feedback(View view){
        setFragment(new FeedbackFragment());
    }

    public void login(View view){

        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            setFragment(new UserProfilelFragment());
        }
        else{
            setFragment(new LoginFragment());
        }


    }

    public void paypal(View view){
        Intent intent = new Intent(this, PaypalActivity.class);
        startActivity(intent);
    }
}