package com.example.pizzaapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.google.firebase.database.FirebaseDatabase;

public class RegistrierenFragment extends Fragment {

    private Button abortButton, returnButton, registerButton;
    private FirebaseAuth mAuth;
    private EditText editTextLastName,editTextFirstName,editTextStreet,editTextStreetNumber,editTextPostCode,editTextCity,editTextPassword,editTextUsername,editTextEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registrieren, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        abortButton = (Button) view.findViewById(R.id.abortButton);
        returnButton = (Button) view.findViewById(R.id.returnButton);
        registerButton = (Button) view.findViewById(R.id.registerButton);

        editTextLastName= (EditText) view.findViewById(R.id.rSurnameInput);
        editTextFirstName= (EditText) view.findViewById(R.id.rNameInput);
        editTextStreet= (EditText) view.findViewById(R.id.rStreetInput);
        editTextPostCode= (EditText) view.findViewById(R.id.rPlzInput);
        editTextStreetNumber= (EditText) view.findViewById(R.id.rHnrInput);
        editTextCity= (EditText) view.findViewById(R.id.rCityInput);
        editTextPassword= (EditText) view.findViewById(R.id.rPasswordInput);
        editTextUsername= (EditText) view.findViewById(R.id.rUsernameInput);
        editTextEmail= (EditText) view.findViewById(R.id.rEmailInput);

        abortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new StartFragment());
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new LoginFragment());
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

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
                                        Toast.makeText(getActivity(),"User has been registered sucessfully!",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(getActivity(),"Failed to register User! Try again!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });

    }
}