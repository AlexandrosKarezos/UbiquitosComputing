package com.example.pizzaapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BestelluebersichtFragment extends Fragment implements View.OnClickListener {

    RadioGroup bestellArtRG;
    Button weiterButton;
    RadioButton deliverButton;
    RadioButton pickupButton;
    Button abbrechenButton;
    Button returnButton;
    Button continueButton;
    LinearLayout bestellUebersichtLL;
    private ShoppingCart shopcart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bestelluebersicht, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopcart = new ShoppingCart();

        bestellArtRG = (RadioGroup) view.findViewById(R.id.radioGroup);
        weiterButton = (Button) view.findViewById(R.id.continueButton);
        deliverButton = (RadioButton) view.findViewById(R.id.radioButtonDeliver);
        pickupButton = (RadioButton) view.findViewById(R.id.radioButtonPickUp);
        abbrechenButton = (Button) view.findViewById(R.id.abortButton);
        returnButton = (Button) view.findViewById(R.id.returnButton);
        continueButton = (Button) view.findViewById(R.id.continueButton);
        bestellUebersichtLL = (LinearLayout) view.findViewById(R.id.linlayoutBU);

        weiterButton.setEnabled(false);

        deliverButton.setOnClickListener(this);
        pickupButton.setOnClickListener(this);
        abbrechenButton.setOnClickListener(this);
        returnButton.setOnClickListener(this);
        continueButton.setOnClickListener(this);

        TextView testTV;
        LinearLayout testLL;
        ImageView tempImgV;
        for(int i=0; i<shopcart.getUniqueItemCount(); i++)
        {
            tempImgV = new ImageView(getActivity());
            tempImgV.setImageResource(android.R.drawable.ic_delete);
            tempImgV.setClickable(true);
            tempImgV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tempImgV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("deleteTest");
                }
            });

            testLL =  new LinearLayout(getActivity());
            testLL.setOrientation(LinearLayout.HORIZONTAL);
            testLL.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));

            testTV = new TextView(getActivity());
            testTV.setTextSize(15);
            testTV.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            testTV.setId(View.generateViewId());
            testTV.setText(shopcart.getItem(i).getName()+"  "+shopcart.getItem(i).getAmount()+"  "+shopcart.getItem(i).getAmount()*shopcart.getItem(i).getPrice());
            testLL.addView(testTV);
            testLL.addView(tempImgV);

            bestellUebersichtLL.addView(testLL);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.radioButtonDeliver:
            case R.id.radioButtonPickUp:
                weiterButton.setEnabled(true);
                break;
            case R.id.abortButton:
                shopcart.clearCart();
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                break;
            case R.id.returnButton:
                ((MainActivity) getActivity()).setFragment(new MenueFragment());
                break;
            case R.id.continueButton:
                if(bestellArtRG.getCheckedRadioButtonId() == R.id.radioButtonDeliver){
                    //liefern
                    ((MainActivity) getActivity()).setFragment(new LiefernFragment());
                }
                else{
                    //abholen
                    ((MainActivity) getActivity()).setFragment(new AbholenFragment());
                }
                break;
        }
    }
}