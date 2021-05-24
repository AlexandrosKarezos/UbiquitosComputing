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

public class OrderDeliveryFragment extends Fragment implements View.OnClickListener{

    private Button returnButton, abortButton, orderButton;
    private RadioGroup rGPaymentOD;
    private TextView creditCardDataTV, totalPriceTV;
    private EditText creditCardNoET, expireDateET, cvvET, surnameET, nameET, streetET, hNrET, plzET, cityET, emailET, noteET,etageET;
    private RadioButton rbODCreditCard, rbODCash, rbODPaypal;
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
        return inflater.inflate(R.layout.fragment_order_delivery, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopcart = new ShoppingCart();

        returnButton = (Button) view.findViewById(R.id.returnButton);
        abortButton = (Button) view.findViewById(R.id.abortButton);
        orderButton = (Button) view.findViewById(R.id.orderButton);
        creditCardDataTV = (TextView) view.findViewById(R.id.textViewCreditCardBL);
        creditCardNoET = (EditText) view.findViewById(R.id.creditCardNoODNT);
        expireDateET = (EditText) view.findViewById(R.id.expireDateODDT);
        cvvET = (EditText) view.findViewById(R.id.cvvODNT);
        rGPaymentOD = (RadioGroup) view.findViewById(R.id.rGODPayment);
        rbODCreditCard = (RadioButton) view.findViewById(R.id.rbODCreditcard);
        rbODCash = (RadioButton) view.findViewById(R.id.rbODCash);
        rbODPaypal = (RadioButton) view.findViewById(R.id.rbODPayPal);
        surnameET = (EditText) view.findViewById(R.id.odSurnameInputET);
        nameET = (EditText) view.findViewById(R.id.odNameInputET);
        streetET = (EditText) view.findViewById(R.id.odStreetInputET);
        hNrET = (EditText) view.findViewById(R.id.odHnrInputET);
        plzET = (EditText) view.findViewById(R.id.odPlzInputET);
        cityET = (EditText) view.findViewById(R.id.odCityInputET);
        emailET = (EditText) view.findViewById(R.id.odEmailInputET);
        noteET=(EditText) view.findViewById(R.id.odNoteMLT);
        etageET=(EditText) view.findViewById(R.id.odLevelInputET);
        paymentmethod=rbODCash.getText().toString();

        ordersTL = (TableLayout) view.findViewById(R.id.orders_tableOD);
        totalPriceTV = (TextView) view.findViewById(R.id.tvTotalPriceOD);

        articleList= new ArrayList<>();

        orderButton.setEnabled(true);
        rbODCash.setChecked(true);

        addDeliveryBuyETChange(creditCardNoET);
        addDeliveryBuyETChange(expireDateET);
        addDeliveryBuyETChange(cvvET);

        returnButton.setOnClickListener(this);
        abortButton.setOnClickListener(this);
        orderButton.setOnClickListener(this);
        rbODCreditCard.setOnClickListener(this);
        rbODCash.setOnClickListener(this);
        rbODPaypal.setOnClickListener(this);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null)
        {
            surnameET.setText(DeliveryFragment.surname2);
            nameET.setText(DeliveryFragment.name2);
            cityET.setText(DeliveryFragment.city2);
            plzET.setText(DeliveryFragment.plz2);
            hNrET.setText(DeliveryFragment.hNr2);
            emailET.setText(DeliveryFragment.email2);
            streetET.setText(DeliveryFragment.street2);
            etageET.setText(DeliveryFragment.etage2);
            noteET.setText(DeliveryFragment.note2);
        }
        if(FirebaseAuth.getInstance().getCurrentUser()==null)
        {
            surnameET.setText(DeliveryFragment.surname2);
            nameET.setText(DeliveryFragment.name2);
            cityET.setText(DeliveryFragment.city2);
            plzET.setText(DeliveryFragment.plz2);
            hNrET.setText(DeliveryFragment.hNr2);
            emailET.setText(DeliveryFragment.email2);
            streetET.setText(DeliveryFragment.street2);
            etageET.setText(DeliveryFragment.etage2);
            noteET.setText(DeliveryFragment.note2);
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
                    ((MainActivity) getActivity()).setFragment(new DeliveryFragment());
                }
                else{
                    ((MainActivity) getActivity()).setFragment(new MenuFragment());
                }
                break;
            case R.id.abortButton:
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                break;
            case R.id.orderButton:
                String notes = noteET.getText().toString();
                for (int i = 0; i < shopcart.getUniqueItemCount(); i++) {
                    articleList.add(shopcart.getItem(i));
                }
                String currentTime= Calendar.getInstance().getTime().toString();
                Order order = new Order(DeliveryFragment.surname2,DeliveryFragment.name2,DeliveryFragment.plz2,DeliveryFragment.city2,DeliveryFragment.street2, DeliveryFragment.hNr2, DeliveryFragment.email2,DeliveryFragment.note2,currentTime, articleList, paymentmethod, DeliveryFragment.etage2, false);
                root.push().setValue(order);
                Toast.makeText(getActivity(),"Danke für die Bestellung",Toast.LENGTH_LONG).show();
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                break;
            case R.id.rbODCreditcard:
                if(rbODCreditCard.isChecked()){
                    creditCardDataTV.setVisibility(View.VISIBLE);
                    creditCardNoET.setVisibility(View.VISIBLE);
                    expireDateET.setVisibility(View.VISIBLE);
                    cvvET.setVisibility(View.VISIBLE);
                    paymentmethod=rbODCreditCard.getText().toString();
                }
                testDeliveryBuy();
                break;
            case R.id.rbODCash:
                if(rbODCash.isChecked()){
                    creditCardDataTV.setVisibility(View.GONE);
                    creditCardNoET.setVisibility(View.GONE);
                    expireDateET.setVisibility(View.GONE);
                    cvvET.setVisibility(View.GONE);
                    paymentmethod=rbODCash.getText().toString();
                }
                testDeliveryBuy();
            case R.id.rbODPayPal:
                if(rbODPaypal.isChecked()){
                    creditCardDataTV.setVisibility(View.GONE);
                    creditCardNoET.setVisibility(View.GONE);
                    expireDateET.setVisibility(View.GONE);
                    cvvET.setVisibility(View.GONE);
                    paymentmethod=rbODPaypal.getText().toString();
                }
                testDeliveryBuy();
                break;
            default:
                break;
        }
    }

    public void testDeliveryBuy(){
        String cardNumber = creditCardNoET.getText().toString();
        String expireDate = expireDateET.getText().toString();
        String cvvNumber = cvvET.getText().toString();

        if(rGPaymentOD.getCheckedRadioButtonId() == R.id.rbODCreditcard){
            if(!(cardNumber.isEmpty() || expireDate.isEmpty() || cvvNumber.isEmpty())){
                orderButton.setEnabled(true);
            }
            else{
                orderButton.setEnabled(false);
            }
        }
        else {
            orderButton.setEnabled(true);
        }
    }

    public void addDeliveryBuyETChange(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                testDeliveryBuy();
            }
        });
    }
}