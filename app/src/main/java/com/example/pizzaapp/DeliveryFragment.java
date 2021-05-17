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

public class DeliveryFragment extends Fragment {

    Button continueButton, abortButton, returnButton;
    private EditText surnameET, nameET, streetET, hNrET, plzET, cityET, emailET;

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
}