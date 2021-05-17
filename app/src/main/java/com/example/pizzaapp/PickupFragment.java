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

public class PickupFragment extends Fragment {

    Button submitButton, returnButton, abortButton;
    private EditText surnameET, nameET, streetET, hNrET, plzET, cityET, emailET;

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
}