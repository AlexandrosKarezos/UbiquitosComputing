package com.example.pizzaapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OrderPickupFragment extends Fragment implements View.OnClickListener {

    private Button returnButton, abortButton, orderButton;
    private RadioGroup rGPaymentOP;
    private TextView creditCardDataTV, totalPriceTV;
    private EditText creditCardNoET, expireDateET, cvvET, surnameET, nameET, streetET, hNrET, plzET, cityET, emailET;
    private RadioButton rbOPCreditCard, rbOPCash, rbOPPaypal;
    private ShoppingCart shopcart;
    private TableLayout ordersTL;
    ArrayList<ShoppingCartItem> articleList;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Order");
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private String paymentmethod;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_pickup, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopcart = new ShoppingCart();

        returnButton = (Button) view.findViewById(R.id.returnButton);
        abortButton = (Button) view.findViewById(R.id.abortButton);
        orderButton = (Button) view.findViewById(R.id.orderButton);
        rGPaymentOP = (RadioGroup) view.findViewById(R.id.rGOPPayment);
        creditCardDataTV = (TextView) view.findViewById(R.id.tvCreditCard);
        creditCardNoET = (EditText) view.findViewById(R.id.cardNumberPONT);
        expireDateET = (EditText) view.findViewById(R.id.expireDateOPDT);
        cvvET = (EditText) view.findViewById(R.id.cvvOPNT);
        rbOPCreditCard = (RadioButton) view.findViewById(R.id.rbOPCreditcard);
        rbOPCash = (RadioButton) view.findViewById(R.id.rbOPCash);
        rbOPPaypal = (RadioButton) view.findViewById(R.id.rbOPPayPal);

        surnameET = (EditText) view.findViewById(R.id.odSurnameInputET);
        nameET = (EditText) view.findViewById(R.id.opNameInputET);
        streetET = (EditText) view.findViewById(R.id.opStreetInputET);
        hNrET = (EditText) view.findViewById(R.id.opHNrInputET);
        plzET = (EditText) view.findViewById(R.id.opPlzInputET);
        cityET = (EditText) view.findViewById(R.id.opCityInputET);
        emailET = (EditText) view.findViewById(R.id.opEmailInputET);
        paymentmethod=rbOPCash.getText().toString();

        ordersTL = (TableLayout) view.findViewById(R.id.orders_tableOP);
        totalPriceTV = (TextView) view.findViewById(R.id.tvTotalPriceOP);

        articleList= new ArrayList<>();

        orderButton.setEnabled(true);
        rbOPCash.setChecked(true);

        addPickupBuyETChange(creditCardNoET);
        addPickupBuyETChange(expireDateET);
        addPickupBuyETChange(cvvET);

        returnButton.setOnClickListener(this);
        abortButton.setOnClickListener(this);
        orderButton.setOnClickListener(this);
        rbOPCreditCard.setOnClickListener(this);
        rbOPCash.setOnClickListener(this);
        rbOPPaypal.setOnClickListener(this);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            surnameET.setText(PickupFragment.surname1);
            nameET.setText(PickupFragment.name1);
            cityET.setText(PickupFragment.city1);
            plzET.setText(PickupFragment.plz1);
            hNrET.setText(PickupFragment.hNr1);
            emailET.setText(PickupFragment.email1);
            streetET.setText(PickupFragment.street1);
        }
        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            surnameET.setText(PickupFragment.surname1);
            nameET.setText(PickupFragment.name1);
            cityET.setText(PickupFragment.city1);
            plzET.setText(PickupFragment.plz1);
            hNrET.setText(PickupFragment.hNr1);
            emailET.setText(PickupFragment.email1);
            streetET.setText(PickupFragment.street1);
        }

        totalPriceTV.setText(Double.toString(shopcart.getTotalPrice())+"€");

        TextView nameTV;
        TextView amountTV;
        TextView preisTV;
        TableRow testTR;
        ImageView tempImgV;

        TableLayout.LayoutParams tableParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(30, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        TableRow.LayoutParams rowParamsName = new TableRow.LayoutParams(150, TableRow.LayoutParams.WRAP_CONTENT, 1f);

        for(int i=0; i<shopcart.getUniqueItemCount(); i++)
        {
            tempImgV = new ImageView(getActivity());
            tempImgV.setImageResource(android.R.drawable.ic_delete);
            tempImgV.setClickable(true);
            tempImgV.setLayoutParams(rowParams);

            testTR =  new TableRow(getActivity());
            testTR.setId(View.generateViewId());
            testTR.setLayoutParams(tableParams);

            nameTV = new TextView(getActivity());
            nameTV.setTextSize(16);
            nameTV.setLayoutParams(rowParamsName);
            nameTV.setId(View.generateViewId());
            nameTV.setText(shopcart.getItem(i).getName());

            amountTV = new TextView(getActivity());
            amountTV.setTextSize(16);
            amountTV.setLayoutParams(rowParams);
            amountTV.setId(View.generateViewId());
            amountTV.setText(Double.toString(shopcart.getItem(i).getAmount()));

            preisTV = new TextView(getActivity());
            preisTV.setTextSize(16);
            preisTV.setLayoutParams(rowParams);
            preisTV.setId(View.generateViewId());
            preisTV.setText(Double.toString(shopcart.getItem(i).getAmount() * shopcart.getItem(i).getPrice()));

            testTR.addView(nameTV);
            testTR.addView(amountTV);
            testTR.addView(preisTV);
            testTR.addView(tempImgV);

            ShoppingCartItem item = shopcart.getItem(i);
            TableRow finalTestTR = testTR;
            tempImgV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shopcart.removeItem(item);
                    ((ViewManager) finalTestTR.getParent()).removeView(finalTestTR);
                    totalPriceTV.setText(Double.toString(shopcart.getTotalPrice())+"€");
                    if(shopcart.getUniqueItemCount() == 0){
                        orderButton.setEnabled(false);
                    }
                }
            });

            ordersTL.addView(testTR);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.returnButton:
                if(shopcart.getUniqueItemCount() > 0){
                    ((MainActivity) getActivity()).setFragment(new PickupFragment());
                }
                else{
                    ((MainActivity) getActivity()).setFragment(new MenuFragment());
                }
                break;
            case R.id.abortButton:
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                break;
            case R.id.orderButton:
                for (int i = 0; i < shopcart.getUniqueItemCount(); i++) {
                    articleList.add(shopcart.getItem(i));
                }
                String currentTime= Calendar.getInstance().getTime().toString();
                Order order = new Order(PickupFragment.surname1,PickupFragment.name1,PickupFragment.plz1,PickupFragment.city1,PickupFragment.street1, PickupFragment.hNr1, PickupFragment.email1, paymentmethod, currentTime, articleList, false);
                root.push().setValue(order);
                Toast.makeText(getActivity(),"Danke für die Bestellung",Toast.LENGTH_LONG).show();
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                break;
            case R.id.rbOPCreditcard:
                if(rGPaymentOP.getCheckedRadioButtonId() == R.id.rbOPCreditcard){
                    creditCardDataTV.setVisibility(View.VISIBLE);
                    creditCardNoET.setVisibility(View.VISIBLE);
                    expireDateET.setVisibility(View.VISIBLE);
                    cvvET.setVisibility(View.VISIBLE);
                    paymentmethod=rbOPCreditCard.getText().toString();
                }
                testPickupBuy();
                break;
            case R.id.rbOPCash:
                if(rGPaymentOP.getCheckedRadioButtonId() == R.id.rbOPCash){
                    creditCardDataTV.setVisibility(View.INVISIBLE);
                    creditCardNoET.setVisibility(View.INVISIBLE);
                    expireDateET.setVisibility(View.INVISIBLE);
                    cvvET.setVisibility(View.INVISIBLE);
                    paymentmethod=rbOPCash.getText().toString();
                }
                testPickupBuy();
                break;
            case R.id.rbOPPayPal:
                if(rGPaymentOP.getCheckedRadioButtonId() == R.id.rbOPPayPal){
                    creditCardDataTV.setVisibility(View.INVISIBLE);
                    creditCardNoET.setVisibility(View.INVISIBLE);
                    expireDateET.setVisibility(View.INVISIBLE);
                    cvvET.setVisibility(View.INVISIBLE);
                    paymentmethod=rbOPPaypal.getText().toString();
                }
                testPickupBuy();
                break;
            default:
                break;
        }
    }

    public void testPickupBuy(){
        String cardNumber = creditCardNoET.getText().toString();
        String expireDate = expireDateET.getText().toString();
        String cvvNumber = cvvET.getText().toString();

        if(rGPaymentOP.getCheckedRadioButtonId() == R.id.rbOPCreditcard){
            if(!(cardNumber.isEmpty() || expireDate.isEmpty() || cvvNumber.isEmpty())){
                orderButton.setEnabled(true);
            }
            else{
                orderButton.setEnabled(false);
            }
        }
        else{
            orderButton.setEnabled(true);
        }
    }

    public void addPickupBuyETChange(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                testPickupBuy();
            }
        });
    }
}