package com.example.pizzaapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class UserProfilelFragment extends Fragment {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    Button abortButton, acceptButton, orderHistoryButton, logoutButton, buttonLogin;
    Switch aSwitch;
    EditText firstnameET, lastnameET, streetET, postcodeET, streetNumberET, cityET, emailET, usernameET, passwordET;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_userprofile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonLogin = (Button) ((MainActivity) getActivity()).findViewById(R.id.buttonLogin);
        abortButton = (Button) view.findViewById(R.id.abortButton);
        acceptButton = (Button) view.findViewById(R.id.acceptButton);
        orderHistoryButton = (Button) view.findViewById(R.id.buttonOrderHistory);
        logoutButton = (Button) view.findViewById(R.id.buttonLogout);
        firstnameET = (EditText) view.findViewById(R.id.pNameInputET);
        lastnameET = (EditText) view.findViewById(R.id.pSurnameInputET);
        streetET = (EditText) view.findViewById(R.id.pStreetInputET);
        postcodeET = (EditText) view.findViewById(R.id.pPLZInputET);
        streetNumberET = (EditText)view.findViewById(R.id.pHNrInputET);
        cityET = (EditText)view.findViewById(R.id.pCityInputET);
        emailET = (EditText)view.findViewById(R.id.pEmailInputET);
        usernameET = (EditText)view.findViewById(R.id.pNewUsernameInputET);
        passwordET = (EditText)view.findViewById(R.id.pNewPwInputET);
        aSwitch = (Switch)view.findViewById(R.id.switchEditProfile);

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

        orderHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new OrderHistoryFragment());
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
                    firstnameET.setEnabled(true);
                    lastnameET.setEnabled(true);
                    streetNumberET.setEnabled(true);
                    streetET.setEnabled(true);
                    cityET.setEnabled(true);
                    postcodeET.setEnabled(true);
                    emailET.setEnabled(true);
                    usernameET.setEnabled(true);
                    passwordET.setEnabled(true);
                    acceptButton.setEnabled(true);
                }
                if(!isChecked)
                {
                    firstnameET.setEnabled(false);
                    lastnameET.setEnabled(false);
                    streetNumberET.setEnabled(false);
                    streetET.setEnabled(false);
                    cityET.setEnabled(false);
                    postcodeET.setEnabled(false);
                    emailET.setEnabled(false);
                    usernameET.setEnabled(false);
                    passwordET.setEnabled(false);
                    acceptButton.setEnabled(false);
                }
            }
        });
        if(!aSwitch.isChecked())
        {
            firstnameET.setEnabled(false);
            lastnameET.setTextColor(Color.BLACK);
            firstnameET.setTextColor(Color.BLACK);
            streetET.setTextColor(Color.BLACK);
            streetNumberET.setTextColor(Color.BLACK);
            cityET.setTextColor(Color.BLACK);
            postcodeET.setTextColor(Color.BLACK);
            emailET.setTextColor(Color.BLACK);
            usernameET.setTextColor(Color.BLACK);
            passwordET.setTextColor(Color.BLACK);
            lastnameET.setEnabled(false);
            streetNumberET.setEnabled(false);
            streetET.setEnabled(false);
            cityET.setEnabled(false);
            postcodeET.setEnabled(false);
            emailET.setEnabled(false);
            passwordET.setEnabled(false);
            usernameET.setEnabled(false);
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

                    firstnameET.setText(firstname);
                    lastnameET.setText(lastname);
                    streetET.setText(street);
                    streetNumberET.setText(number);
                    cityET.setText(city);
                    postcodeET.setText(postcode);
                    emailET.setText(email);
                    usernameET.setText(username);
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
        reference.child(userID).child("firstName").setValue(firstnameET.getText().toString());
        reference.child(userID).child("lastName").setValue(lastnameET.getText().toString());
        reference.child(userID).child("street").setValue(streetET.getText().toString());
        reference.child(userID).child("streetNumber").setValue(streetNumberET.getText().toString());
        reference.child(userID).child("city").setValue(cityET.getText().toString());
        reference.child(userID).child("postCode").setValue(postcodeET.getText().toString());
        reference.child(userID).child("email").setValue(emailET.getText().toString());
        reference.child(userID).child("username").setValue(usernameET.getText().toString());
        Toast.makeText(getActivity(),"Userdata has been updated successfully!",Toast.LENGTH_LONG).show();
    }
}