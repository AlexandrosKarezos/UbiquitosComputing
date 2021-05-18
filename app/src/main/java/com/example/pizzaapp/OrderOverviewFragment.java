package com.example.pizzaapp;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class OrderOverviewFragment extends Fragment implements View.OnClickListener {

    RadioGroup rGOOOrderType;
    RadioButton deliverButton;
    RadioButton pickupButton;
    Button abortButton;
    Button returnButton;
    Button continueButton;
    TableLayout orderOverviewTL;
    TextView totalPriceTV;
    private ShoppingCart shopcart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orderoverview, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopcart = new ShoppingCart();

        rGOOOrderType = (RadioGroup) view.findViewById(R.id.rGOrderType);
        continueButton = (Button) view.findViewById(R.id.continueButton);
        deliverButton = (RadioButton) view.findViewById(R.id.rbOODeliver);
        pickupButton = (RadioButton) view.findViewById(R.id.rbOOPickup);
        abortButton = (Button) view.findViewById(R.id.abortButton);
        returnButton = (Button) view.findViewById(R.id.returnButton);
        orderOverviewTL = (TableLayout) view.findViewById(R.id.tableLayoutOO);
        totalPriceTV = (TextView) view.findViewById(R.id.tvOOTotalPrice);

        continueButton.setEnabled(false);

        deliverButton.setOnClickListener(this);
        pickupButton.setOnClickListener(this);
        abortButton.setOnClickListener(this);
        returnButton.setOnClickListener(this);
        continueButton.setOnClickListener(this);

        totalPriceTV.setText(Double.toString(shopcart.getTotalPrice()));

        TextView nameTV;
        TextView amountTV;
        TextView preisTV;
        TableRow testTR;
        ImageView tempImgV;

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tableParams.leftMargin = 50;
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
        rowParams.rightMargin = 110;
        for(int i=0; i<shopcart.getUniqueItemCount(); i++)
        {
            tempImgV = new ImageView(getActivity());
            tempImgV.setImageResource(android.R.drawable.ic_delete);
            tempImgV.setClickable(true);
            tempImgV.setLayoutParams(rowParams);
            tempImgV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("deleteTest");
                }
            });

            testTR =  new TableRow(getActivity());
            testTR.setLayoutParams(tableParams);

            nameTV = new TextView(getActivity());
            nameTV.setTextSize(20);
            nameTV.setLayoutParams(rowParams);
            nameTV.setId(View.generateViewId());
            nameTV.setText(shopcart.getItem(i).getName());

            amountTV = new TextView(getActivity());
            amountTV.setTextSize(20);
            amountTV.setLayoutParams(rowParams);
            amountTV.setId(View.generateViewId());
            amountTV.setText(Double.toString(shopcart.getItem(i).getAmount()));

            preisTV = new TextView(getActivity());
            preisTV.setTextSize(20);
            preisTV.setLayoutParams(rowParams);
            preisTV.setId(View.generateViewId());
            preisTV.setText(Double.toString(shopcart.getItem(i).getAmount() * shopcart.getItem(i).getPrice()));

            testTR.addView(nameTV);
            testTR.addView(amountTV);
            testTR.addView(preisTV);
            testTR.addView(tempImgV);


            orderOverviewTL.addView(testTR);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.rbOODeliver:
            case R.id.rbOOPickup:
                continueButton.setEnabled(true);
                break;
            case R.id.abortButton:
                shopcart.clearCart();
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                break;
            case R.id.returnButton:
                ((MainActivity) getActivity()).setFragment(new MenuFragment());
                break;
            case R.id.continueButton:
                if(rGOOOrderType.getCheckedRadioButtonId() == R.id.rbOODeliver){
                    //liefern
                    ((MainActivity) getActivity()).setFragment(new DeliveryFragment());
                }
                else{
                    //abholen
                    ((MainActivity) getActivity()).setFragment(new PickupFragment());
                }
                break;
        }
    }
}