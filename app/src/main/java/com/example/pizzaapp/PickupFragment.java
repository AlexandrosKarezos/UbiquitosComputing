package com.example.pizzaapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PickupFragment extends Fragment {

    Button submitButton, returnButton, abortButton;
    private EditText surnameET, nameET, streetET, hNrET, plzET, cityET, emailET;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    public static String surname1,name1,street1,hNr1,plz1,city1,email1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pickup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        submitButton = (Button) view.findViewById(R.id.continuePickupButton);
        returnButton = (Button) view.findViewById(R.id.returnPickupButton);
        abortButton = (Button) view.findViewById(R.id.abortPickupButton);
        surnameET = (EditText) view.findViewById(R.id.surnameInputET);
        nameET = (EditText) view.findViewById(R.id.nameInputET);
        streetET = (EditText) view.findViewById(R.id.streetInputET);
        hNrET = (EditText) view.findViewById(R.id.hNrInputET);
        plzET = (EditText) view.findViewById(R.id.plzInputET);
        cityET = (EditText) view.findViewById(R.id.cityInputET);
        emailET = (EditText) view.findViewById(R.id.emailInputET);

        submitButton.setEnabled(false);

        addPickupETChange(surnameET);
        addPickupETChange(nameET);
        addPickupETChange(streetET);
        addPickupETChange(hNrET);
        addPickupETChange(plzET);
        addPickupETChange(cityET);
        addPickupETChange(emailET);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new OrderPickupFragment());
                surname1=surnameET.getText().toString();
                name1=nameET.getText().toString();
                plz1=plzET.getText().toString();
                street1=streetET.getText().toString();
                hNr1=hNrET.getText().toString();
                email1=emailET.getText().toString();
                city1=cityET.getText().toString();
            }
        });

        abortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new StartFragment());
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new OrderOverviewFragment());
            }
        });

        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            showAllUserData();
        }
    }

    public void addPickupETChange(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String surname = surnameET.getText().toString();
                String name = nameET.getText().toString();
                String street = streetET.getText().toString();
                String hNr = hNrET.getText().toString();
                String plz = plzET.getText().toString();
                String city = cityET.getText().toString();
                String email = emailET.getText().toString();

                if(!(surname.isEmpty() || name.isEmpty() || street.isEmpty() || hNr.isEmpty() || plz.isEmpty() || city.isEmpty() || email.isEmpty())){
                    submitButton.setEnabled(true);
                }
                else{
                    submitButton.setEnabled(false);
                }
            }
        });
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
                if(FirebaseAuth.getInstance().getCurrentUser()!=null)
                {

                    String surname = userProfile.lastName;
                    String name = userProfile.firstName;
                    String email = userProfile.email;
                    String hnr=userProfile.streetNumber;
                    String plz=userProfile.postCode;
                    String stadt=userProfile.city;
                    String strasse=userProfile.street;
                    cityET.setText(stadt);
                    nameET.setText(name);
                    plzET.setText(plz);
                    hNrET.setText(hnr);
                    streetET.setText(strasse);
                    surnameET.setText(surname);
                    emailET.setText(email);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}