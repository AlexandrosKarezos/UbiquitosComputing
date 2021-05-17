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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class OrderPickupFragment extends Fragment implements View.OnClickListener {

    private Button returnButton, abortButton, orderButton;
    private RadioGroup rGPaymentOP;
    private TextView creditCardDataTV;
    private EditText creditCardNoET, expireDateET, cvvET, surnameET, nameET, streetET, hNrET, plzET, cityET, emailET;
    private RadioButton rbOPCreditCard, rbOPCash, rbOPPaypal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_pickup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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


        orderButton.setEnabled(false);
        rbOPCash.setChecked(true);

        addPickupBuyETChange(surnameET);
        addPickupBuyETChange(nameET);
        addPickupBuyETChange(streetET);
        addPickupBuyETChange(hNrET);
        addPickupBuyETChange(plzET);
        addPickupBuyETChange(cityET);
        addPickupBuyETChange(emailET);
        addPickupBuyETChange(creditCardNoET);
        addPickupBuyETChange(expireDateET);
        addPickupBuyETChange(cvvET);

        returnButton.setOnClickListener(this);
        abortButton.setOnClickListener(this);
        orderButton.setOnClickListener(this);
        rbOPCreditCard.setOnClickListener(this);
        rbOPCash.setOnClickListener(this);
        rbOPPaypal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.returnButton:
                ((MainActivity) getActivity()).setFragment(new PickupFragment());
                break;
            case R.id.abortButton:
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                break;
            case R.id.orderButton:
                // do your code
                break;
            case R.id.rbOPCreditcard:
                if(rGPaymentOP.getCheckedRadioButtonId() == R.id.rbOPCreditcard){
                    creditCardDataTV.setVisibility(View.VISIBLE);
                    creditCardNoET.setVisibility(View.VISIBLE);
                    expireDateET.setVisibility(View.VISIBLE);
                    cvvET.setVisibility(View.VISIBLE);
                }
                testPickupBuy();
                break;
            case R.id.rbOPCash:
            case R.id.rbOPPayPal:
                if(rGPaymentOP.getCheckedRadioButtonId() == R.id.rbOPCash || rGPaymentOP.getCheckedRadioButtonId() == R.id.rbOPPayPal){
                    creditCardDataTV.setVisibility(View.INVISIBLE);
                    creditCardNoET.setVisibility(View.INVISIBLE);
                    expireDateET.setVisibility(View.INVISIBLE);
                    cvvET.setVisibility(View.INVISIBLE);
                }
                testPickupBuy();
                break;
            default:
                break;
        }
    }

    public void testPickupBuy(){
        String surname = surnameET.getText().toString();
        String name = nameET.getText().toString();
        String street = streetET.getText().toString();
        String hNr = hNrET.getText().toString();
        String plz = plzET.getText().toString();
        String city = cityET.getText().toString();
        String email = emailET.getText().toString();
        String cardNumber = creditCardNoET.getText().toString();
        String expireDate = expireDateET.getText().toString();
        String cvvNumber = cvvET.getText().toString();

        if(rGPaymentOP.getCheckedRadioButtonId() == R.id.rbOPCreditcard){
            if(!(surname.isEmpty() || name.isEmpty() || street.isEmpty() || hNr.isEmpty() || plz.isEmpty() || city.isEmpty() || email.isEmpty() || cardNumber.isEmpty() || expireDate.isEmpty() || cvvNumber.isEmpty())){
                orderButton.setEnabled(true);
            }
            else{
                orderButton.setEnabled(false);
            }
        }
        else{
            if (!(surname.isEmpty() || name.isEmpty() || street.isEmpty() || hNr.isEmpty() || plz.isEmpty() || city.isEmpty() || email.isEmpty())) {
                orderButton.setEnabled(true);
            } else {
                orderButton.setEnabled(false);
            }
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