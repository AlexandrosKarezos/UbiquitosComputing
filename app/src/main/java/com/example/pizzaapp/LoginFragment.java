package com.example.pizzaapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {

    Button loginButton;
    Button registerButton;
    private EditText emailET,passwordET;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginButton = (Button) view.findViewById(R.id.loginButton);
        registerButton = (Button) view.findViewById(R.id.lRegisterButton);
        emailET = (EditText) view.findViewById(R.id.lEmailInputET);
        passwordET = (EditText) view.findViewById(R.id.lPasswordInputET);
        mAuth= FirebaseAuth.getInstance();

        loginButton.setEnabled(false);

        addLoginETChange(emailET);
        addLoginETChange(passwordET);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new RegisterFragment());
            }
        });
    }

    private void userLogin() {
        String email=emailET.getText().toString().trim();
        String password=passwordET.getText().toString().trim();
        if(email.isEmpty()){
            emailET.setError("Email is required!");
            emailET.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailET.setError("Please enter a valid email!");
            emailET.requestFocus();
            return;
        }
        if(password.isEmpty()){
            passwordET.setError("Password is required!");
            passwordET.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //redirect to profile
                    ((MainActivity) getActivity()).setFragment(new UserProfilelFragment());
                }else{
                    Toast.makeText(getActivity(),"Failed to login! Please check your credentials",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void addLoginETChange(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = emailET.getText().toString();
                String passwort = passwordET.getText().toString();

                if(!(passwort.isEmpty() || email.isEmpty())){
                    loginButton.setEnabled(true);
                }
                else{
                    loginButton.setEnabled(false);
                }
            }
        });
    }
}