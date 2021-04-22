package com.example.pizzaapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class BestelluebersichtFragment extends Fragment {

    RadioGroup bestellArtRG;
    Button weiterButton;
    RadioButton deliverButton;
    RadioButton pickupButton;
    Button abbrechenButton;
    Button returnButton;
    Button continueButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bestelluebersicht, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bestellArtRG = (RadioGroup) view.findViewById(R.id.radioGroup);
        weiterButton = (Button) view.findViewById(R.id.continueButton);
        deliverButton = (RadioButton) view.findViewById(R.id.radioButtonDeliver);
        pickupButton = (RadioButton) view.findViewById(R.id.radioButtonPickUp);
        abbrechenButton = (Button) view.findViewById(R.id.abortButton);
        returnButton = (Button) view.findViewById(R.id.returnButton);
        continueButton = (Button) view.findViewById(R.id.continueButton);

        weiterButton.setEnabled(false);
        deliverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weiterButton.setEnabled(true);
            }
        });

        pickupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weiterButton.setEnabled(true);
            }
        });

        abbrechenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setFragment(new StartFragment());
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setFragment(new MenueFragment());
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bestellArtRG.getCheckedRadioButtonId() == R.id.radioButtonDeliver){
                   //liefern
                    ((MainActivity) getActivity()).setFragment(new LiefernFragment());
                }
                else{
                    //abholen
                    ((MainActivity) getActivity()).setFragment(new AbholenFragment());
                }

            }
        });


    }
}