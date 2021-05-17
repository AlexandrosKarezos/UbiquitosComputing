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

public class OrderDeliveryFragment extends Fragment implements View.OnClickListener{

    private Button returnButton, abortButton, orderButton;
    private RadioGroup rGPaymentOD;
    private TextView creditCardDataTV;
    private EditText creditCardNoET, expireDateET, cvvET, surnameET, nameET, streetET, hNrET, plzET, cityET, emailET;
    private RadioButton rbODCreditCard, rbODCash, rbODPaypal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_delivery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        orderButton.setEnabled(false);
        rbODCash.setChecked(true);

        addDeliveryBuyETChange(surnameET);
        addDeliveryBuyETChange(nameET);
        addDeliveryBuyETChange(streetET);
        addDeliveryBuyETChange(hNrET);
        addDeliveryBuyETChange(plzET);
        addDeliveryBuyETChange(cityET);
        addDeliveryBuyETChange(emailET);
        addDeliveryBuyETChange(creditCardNoET);
        addDeliveryBuyETChange(expireDateET);
        addDeliveryBuyETChange(cvvET);

        returnButton.setOnClickListener(this);
        abortButton.setOnClickListener(this);
        orderButton.setOnClickListener(this);
        rbODCreditCard.setOnClickListener(this);
        rbODCash.setOnClickListener(this);
        rbODPaypal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.returnButton:
                ((MainActivity) getActivity()).setFragment(new DeliveryFragment());
                break;
            case R.id.abortButton:
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                break;
            case R.id.orderButton:
                // do your code
                break;
            case R.id.rbODCreditcard:
                if(rbODCreditCard.isChecked()){
                    creditCardDataTV.setVisibility(View.VISIBLE);
                    creditCardNoET.setVisibility(View.VISIBLE);
                    expireDateET.setVisibility(View.VISIBLE);
                    cvvET.setVisibility(View.VISIBLE);
                }
                testDeliveryBuy();
                break;
            case R.id.rbODCash:
            case R.id.rbODPayPal:
                if(rbODCash.isChecked() || rbODPaypal.isChecked()){
                    creditCardDataTV.setVisibility(View.GONE);
                    creditCardNoET.setVisibility(View.GONE);
                    expireDateET.setVisibility(View.GONE);
                    cvvET.setVisibility(View.GONE);
                }
                testDeliveryBuy();
                break;
            default:
                break;
        }
    }

    public void testDeliveryBuy(){
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

        if(rGPaymentOD.getCheckedRadioButtonId() == R.id.rbODCreditcard){
            if(!(surname.isEmpty() || name.isEmpty() || street.isEmpty() || hNr.isEmpty() || plz.isEmpty() || city.isEmpty() || email.isEmpty() || cardNumber.isEmpty() || expireDate.isEmpty() || cvvNumber.isEmpty())){
                orderButton.setEnabled(true);
            }
            else{
                orderButton.setEnabled(false);
            }
        }
        else {
            if (!(surname.isEmpty() || name.isEmpty() || street.isEmpty() || hNr.isEmpty() || plz.isEmpty() || city.isEmpty() || email.isEmpty())) {
                orderButton.setEnabled(true);
            } else {
                orderButton.setEnabled(false);
            }
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