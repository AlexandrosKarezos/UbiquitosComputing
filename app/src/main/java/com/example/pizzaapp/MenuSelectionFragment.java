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

public class MenuSelectionFragment extends Fragment {

    Button confirmButton;
    Button abortButton;
    TextView pizzaTV;
    private String itemName;
    private double itemPrice;
    NumberPicker numberPicker;
    TextView selectionTV;
    private ShoppingCart shopcart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_selection, container, false);
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopcart = new ShoppingCart();

        confirmButton = view.findViewById(R.id.confirmButton);
        abortButton = view.findViewById(R.id.abortButton);
        pizzaTV = view.findViewById(R.id.msPizzaname);
        selectionTV = view.findViewById(R.id.msSelection);
        numberPicker = view.findViewById(R.id.msNumberPicker);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(1000);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                selectionTV.setText("Menge: " + newVal);
            }
        });

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            itemName = bundle.getString("pizza", "Pizza");
            itemPrice = bundle.getDouble("price");
        }

        pizzaTV.setText(itemName);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shopcart.addItem(new ShoppingCartItem(itemName, itemPrice, numberPicker.getValue()));
                ((MainActivity) getActivity()).setFragment(new MenuFragment());
            }
        });

        abortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setFragment(new MenuFragment());
            }
        });
    }
}