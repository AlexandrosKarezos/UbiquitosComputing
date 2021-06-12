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

public class DeliveryFragment extends Fragment {

    Button continueButton, abortButton, returnButton;
    private EditText surnameET, nameET, streetET, hNrET, plzET, cityET, emailET, etageET, noteET;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    public static String surname2,name2,street2,hNr2,plz2,city2,email2,etage2,note2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delivery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        continueButton = (Button) view.findViewById(R.id.continueDButton);
        abortButton = (Button) view.findViewById(R.id.abortButton);
        returnButton = (Button) view.findViewById(R.id.returnButton);
        surnameET = (EditText) view.findViewById(R.id.dSurnameInputET);
        nameET = (EditText) view.findViewById(R.id.dNameInputET);
        streetET = (EditText) view.findViewById(R.id.dStreetInputET);
        hNrET = (EditText) view.findViewById(R.id.dHnrInputET);
        plzET = (EditText) view.findViewById(R.id.dPlzInputET);
        cityET = (EditText) view.findViewById(R.id.dCityInputET);
        emailET = (EditText) view.findViewById(R.id.dEmailInputET);
        etageET= (EditText) view.findViewById(R.id.dLevelInputET);
        noteET=(EditText) view.findViewById(R.id.dNoteInputMLT);

        continueButton.setEnabled(false);

        addDeliveryETChange(surnameET);
        addDeliveryETChange(nameET);
        addDeliveryETChange(streetET);
        addDeliveryETChange(hNrET);
        addDeliveryETChange(plzET);
        addDeliveryETChange(cityET);
        addDeliveryETChange(emailET);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new OrderDeliveryFragment());
                surname2=surnameET.getText().toString();
                name2=nameET.getText().toString();
                plz2=plzET.getText().toString();
                street2=streetET.getText().toString();
                hNr2=hNrET.getText().toString();
                email2=emailET.getText().toString();
                city2=cityET.getText().toString();
                etage2=etageET.getText().toString();
                note2=noteET.getText().toString();
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

    public void addDeliveryETChange(EditText editText){
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
                    continueButton.setEnabled(true);
                }
                else{
                    continueButton.setEnabled(false);
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