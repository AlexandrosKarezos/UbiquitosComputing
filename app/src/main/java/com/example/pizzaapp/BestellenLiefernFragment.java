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

public class BestellenLiefernFragment extends Fragment implements View.OnClickListener{

    private Button returnButton, abortButton, kpBestellenButton;
    private RadioGroup radiogroupPayBL;
    private TextView creditCardDataTV;
    private EditText creditCardNoET, expireDateET, cvvET, surnameET, nameET, streetET, hNrET, plzET, cityET, emailET;
    private RadioButton radioButtonCreditCard, radioButtonCash, radioButtonPaypal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bestellen_liefern, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        returnButton = (Button) view.findViewById(R.id.returnButton);
        abortButton = (Button) view.findViewById(R.id.abortButton);
        kpBestellenButton = (Button) view.findViewById(R.id.kpBestellenButton);
        creditCardDataTV = (TextView) view.findViewById(R.id.textViewCreditCardBL);
        creditCardNoET = (EditText) view.findViewById(R.id.editTextCreditCardNoBL);
        expireDateET = (EditText) view.findViewById(R.id.editTextExpireDateBL);
        cvvET = (EditText) view.findViewById(R.id.editTextCVVBL);
        radiogroupPayBL = (RadioGroup) view.findViewById(R.id.radioGroup2);
        radioButtonCreditCard = (RadioButton) view.findViewById(R.id.radioButtonKreditkarte);
        radioButtonCash = (RadioButton) view.findViewById(R.id.radioButtonBargeld);
        radioButtonPaypal = (RadioButton) view.findViewById(R.id.radioButtonPayPal);
        surnameET = (EditText) view.findViewById(R.id.baSurname);
        nameET = (EditText) view.findViewById(R.id.blName);
        streetET = (EditText) view.findViewById(R.id.blStreet);
        hNrET = (EditText) view.findViewById(R.id.blHnr);
        plzET = (EditText) view.findViewById(R.id.blPlz);
        cityET = (EditText) view.findViewById(R.id.blOrt);
        emailET = (EditText) view.findViewById(R.id.blEmail);

        kpBestellenButton.setEnabled(false);
        radioButtonCash.setChecked(true);

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
        kpBestellenButton.setOnClickListener(this);
        radioButtonCreditCard.setOnClickListener(this);
        radioButtonCash.setOnClickListener(this);
        radioButtonPaypal.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.returnButton:
                ((MainActivity) getActivity()).setFragment(new LiefernFragment());
                break;
            case R.id.abortButton:
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                break;
            case R.id.kpBestellenButton:
                // do your code
                break;
            case R.id.radioButtonKreditkarte:
                if(radioButtonCreditCard.isChecked()){
                    creditCardDataTV.setVisibility(View.VISIBLE);
                    creditCardNoET.setVisibility(View.VISIBLE);
                    expireDateET.setVisibility(View.VISIBLE);
                    cvvET.setVisibility(View.VISIBLE);
                }
                testDeliveryBuy();
                break;
            case R.id.radioButtonBargeld:
            case R.id.radioButtonPayPal:
                if(radioButtonCash.isChecked() || radioButtonPaypal.isChecked()){
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

        if(radiogroupPayBL.getCheckedRadioButtonId() == R.id.radioButtonKreditkarte){
            if(!(surname.isEmpty() || name.isEmpty() || street.isEmpty() || hNr.isEmpty() || plz.isEmpty() || city.isEmpty() || email.isEmpty() || cardNumber.isEmpty() || expireDate.isEmpty() || cvvNumber.isEmpty())){
                kpBestellenButton.setEnabled(true);
            }
            else{
                kpBestellenButton.setEnabled(false);
            }
        }
        else {
            if (!(surname.isEmpty() || name.isEmpty() || street.isEmpty() || hNr.isEmpty() || plz.isEmpty() || city.isEmpty() || email.isEmpty())) {
                kpBestellenButton.setEnabled(true);
            } else {
                kpBestellenButton.setEnabled(false);
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