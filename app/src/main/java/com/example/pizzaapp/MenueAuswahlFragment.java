package com.example.pizzaapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MenueAuswahlFragment extends Fragment {

    Button bestaetigenButton;
    Button abortButton;
    TextView pizzaTV;
    private String itemName;
    private double itemPrice;
    NumberPicker numberPicker;
    TextView auswahlTV;
    private ShoppingCart shopcart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menue_auswahl, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopcart = new ShoppingCart();

        bestaetigenButton = view.findViewById(R.id.buttonBestaetigen);
        abortButton = view.findViewById(R.id.abortButton);
        pizzaTV = view.findViewById(R.id.pizzaname);
        auswahlTV = view.findViewById(R.id.auswahl);
        numberPicker = view.findViewById(R.id.numberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(1000);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                auswahlTV.setText("Menge: " + newVal);
            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            itemName = bundle.getString("pizza", "Pizza");
            itemPrice = bundle.getDouble("price");
        }

        pizzaTV.setText(itemName);

        bestaetigenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopcart.addItem(new ShoppingCartItem(itemName, itemPrice, numberPicker.getValue()));
                ((MainActivity) getActivity()).setFragment(new MenueFragment());
            }
        });

        abortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setFragment(new MenueFragment());
            }
        });
    }
}