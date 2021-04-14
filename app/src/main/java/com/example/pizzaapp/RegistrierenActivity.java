package com.example.pizzaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class RegistrierenActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView banner,registerUser;
    private FirebaseAuth mAuth;
    private EditText editTextLastName,editTextFirstName,editTextStreet,editTextStreetNumber,editTextPostCode,editTextCity,editTextPassword,editTextUsername,editTextEmail;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrieren);
        mAuth = FirebaseAuth.getInstance();
        registerUser= (Button) findViewById(R.id.registerButton);
        registerUser.setOnClickListener(this);
        editTextLastName= (EditText) findViewById(R.id.rSurnameInput);
        editTextFirstName= (EditText) findViewById(R.id.rNameInput);
        editTextStreet= (EditText) findViewById(R.id.rStreetInput);
        editTextPostCode= (EditText) findViewById(R.id.rPlzInput);
        editTextStreetNumber= (EditText) findViewById(R.id.rHnrInput);
        editTextCity= (EditText) findViewById(R.id.rCityInput);
        editTextPassword= (EditText) findViewById(R.id.rPasswordInput);
        editTextUsername= (EditText) findViewById(R.id.rUsernameInput);
        editTextEmail= (EditText) findViewById(R.id.rEmailInput);

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

    public void register(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registerButton:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email=editTextEmail.getText().toString().trim();
        String firstName=editTextFirstName.getText().toString().trim();
        String lastName=editTextLastName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String postCode =editTextPostCode.getText().toString().trim();
        String street= editTextStreet.getText().toString().trim();
        String streetNumber=editTextStreetNumber.getText().toString().trim();
        String city = editTextCity.getText().toString().trim();
        String username =editTextUsername.getText().toString().trim();

        if(firstName.isEmpty()){
            editTextFirstName.setError("First name is required!");
            editTextFirstName.requestFocus();
            return;
        }
        if(lastName.isEmpty()){
            editTextLastName.setError("Last name is required!");
            editTextLastName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextLastName.setError("Please provide valid email!");
            editTextLastName.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length()<6) {
            editTextPassword.setError("password length should be atleast 6 characters!");
            editTextPassword.requestFocus();
            return;
        }
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user = new User(lastName, firstName,street,streetNumber,postCode,city,email,username);
                                FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(RegistrierenActivity.this,"User has been registered sucessfully!",Toast.LENGTH_LONG).show();
                                        }else{
                                            Toast.makeText(RegistrierenActivity.this,"Failed to register User! Try again!",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }
                    });

    }
}