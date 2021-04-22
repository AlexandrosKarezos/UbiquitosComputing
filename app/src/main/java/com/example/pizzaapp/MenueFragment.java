package com.example.pizzaapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MenueFragment extends Fragment {

    View view;
    TextView margerithaTv;
    Button bestellButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menue, container, false);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.menu_linear_layout);
        bestellButton = view.findViewById(R.id.buttonBestellung);

        bestellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new BestelluebersichtFragment());
            }
        });

        margerithaTv = new TextView(getActivity());
        margerithaTv.setText(getResources().getString(R.string.menu_margeritha));
        margerithaTv.setId(margerithaTv.generateViewId());
        margerithaTv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        margerithaTv.setClickable(true);
        margerithaTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuAuswahl(view);
            }
        });

        TextView salamiTv = new TextView(getActivity());
        salamiTv.setText(getResources().getString(R.string.menu_salami));
        salamiTv.setId(salamiTv.generateViewId());
        salamiTv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        salamiTv.setClickable(true);
        salamiTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuAuswahl(view);
            }
        });

        linearLayout.addView(margerithaTv);
        linearLayout.addView(salamiTv);

        CheckBox vegetarischCB = (CheckBox) view.findViewById(R.id.checkBoxVegetarisch);
        vegetarischCB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is checkbox checked?
                if (((CheckBox) v).isChecked()) {
                    //Case 1
                }
                else{
                    //Case 2
                }
            }
        });
    }

    public void menuAuswahl(View view){
        TextView test = (TextView) view;
        String name = test.getText().toString();
        String[] lines = name.split( "\n\t" );

        Fragment fragment = new MenueAuswahlFragment();
        Bundle bundle = new Bundle();
        bundle.putString("pizza", lines[1]);
        fragment.setArguments(bundle);

        ((MainActivity) getActivity()).setFragment(fragment);
    }
}