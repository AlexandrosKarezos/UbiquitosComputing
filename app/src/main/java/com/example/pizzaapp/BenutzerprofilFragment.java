package com.example.pizzaapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BenutzerprofilFragment extends Fragment {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    Button abortButton, acceptButton, bestellungsVButton, logoutButton, buttonLogin;
    Switch aSwitch;
    EditText firstnameEditText, lastnameEditText, streetEditText, postcodeEditText, streetNumberEditText, cityEditText, emailEditText, usernameEditText, passwordEditText;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_benutzerprofil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonLogin = (Button) ((MainActivity) getActivity()).findViewById(R.id.buttonLogin);
        abortButton = (Button) view.findViewById(R.id.abortButton);
        acceptButton = (Button) view.findViewById(R.id.acceptButton);
        bestellungsVButton = (Button) view.findViewById(R.id.buttonBestellungsverlauf);
        logoutButton = (Button) view.findViewById(R.id.buttonLogout);
        firstnameEditText = (EditText) view.findViewById(R.id.pNameInput);
        lastnameEditText = (EditText) view.findViewById(R.id.pSurnameInput);
        streetEditText = (EditText) view.findViewById(R.id.pStreetInput);
        postcodeEditText = (EditText) view.findViewById(R.id.pPLZInput);
        streetNumberEditText = (EditText)view.findViewById(R.id.pHNrInput);
        cityEditText = (EditText)view.findViewById(R.id.pCityInput);
        emailEditText = (EditText)view.findViewById(R.id.pEmailInput);
        usernameEditText = (EditText)view.findViewById(R.id.pNewUserInput);
        passwordEditText = (EditText)view.findViewById(R.id.pNewPwInput);
        aSwitch = (Switch)view.findViewById(R.id.switchProfilBearb);

        acceptButton.setEnabled(false);

        abortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserData();
                showAllUserData();
            }
        });

        bestellungsVButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new BestellungsverlaufFragment());
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                buttonLogin.setText("Login");
                ((MainActivity) getActivity()).setFragment(new LoginFragment());
            }
        });
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    firstnameEditText.setEnabled(true);
                    lastnameEditText.setEnabled(true);
                    streetNumberEditText.setEnabled(true);
                    streetEditText.setEnabled(true);
                    cityEditText.setEnabled(true);
                    postcodeEditText.setEnabled(true);
                    emailEditText.setEnabled(true);
                    usernameEditText.setEnabled(true);
                    passwordEditText.setEnabled(true);
                    acceptButton.setEnabled(true);
                }
                if(!isChecked)
                {
                    firstnameEditText.setEnabled(false);
                    lastnameEditText.setEnabled(false);
                    streetNumberEditText.setEnabled(false);
                    streetEditText.setEnabled(false);
                    cityEditText.setEnabled(false);
                    postcodeEditText.setEnabled(false);
                    emailEditText.setEnabled(false);
                    usernameEditText.setEnabled(false);
                    passwordEditText.setEnabled(false);
                    acceptButton.setEnabled(false);
                }
            }
        });
        if(!aSwitch.isChecked())
        {
            firstnameEditText.setEnabled(false);
            lastnameEditText.setTextColor(Color.BLACK);
            firstnameEditText.setTextColor(Color.BLACK);
            streetEditText.setTextColor(Color.BLACK);
            streetNumberEditText.setTextColor(Color.BLACK);
            cityEditText.setTextColor(Color.BLACK);
            postcodeEditText.setTextColor(Color.BLACK);
            emailEditText.setTextColor(Color.BLACK);
            usernameEditText.setTextColor(Color.BLACK);
            passwordEditText.setTextColor(Color.BLACK);
            lastnameEditText.setEnabled(false);
            streetNumberEditText.setEnabled(false);
            streetEditText.setEnabled(false);
            cityEditText.setEnabled(false);
            postcodeEditText.setEnabled(false);
            emailEditText.setEnabled(false);
            passwordEditText.setEnabled(false);
            usernameEditText.setEnabled(false);
        }
        showAllUserData();
    }
    private void showAllUserData()
    {
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue((User.class));
                if(userProfile!=null)
                {

                    String username=userProfile.username;
                    String firstname=userProfile.firstName;
                    String lastname=userProfile.lastName;
                    String street=userProfile.street;
                    String number=userProfile.streetNumber;
                    String city=userProfile.city;
                    String postcode=userProfile.postCode;
                    String email=userProfile.email;

                    firstnameEditText.setText(firstname);
                    lastnameEditText.setText(lastname);
                    streetEditText.setText(street);
                    streetNumberEditText.setText(number);
                    cityEditText.setText(city);
                    postcodeEditText.setText(postcode);
                    emailEditText.setText(email);
                    usernameEditText.setText(username);
                    buttonLogin.setText(username);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void updateUserData()
    {
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();
        reference.child(userID).child("firstName").setValue(firstnameEditText.getText().toString());
        reference.child(userID).child("lastName").setValue(lastnameEditText.getText().toString());
        reference.child(userID).child("street").setValue(streetEditText.getText().toString());
        reference.child(userID).child("streetNumber").setValue(streetNumberEditText.getText().toString());
        reference.child(userID).child("city").setValue(cityEditText.getText().toString());
        reference.child(userID).child("postCode").setValue(postcodeEditText.getText().toString());
        reference.child(userID).child("email").setValue(emailEditText.getText().toString());
        reference.child(userID).child("username").setValue(usernameEditText.getText().toString());
        Toast.makeText(getActivity(),"Userdata has been updated successfully!",Toast.LENGTH_LONG).show();
    }
}