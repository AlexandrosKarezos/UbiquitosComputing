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

public class BestellenAbholenFragment extends Fragment implements View.OnClickListener {

    private Button returnButton, abortButton, kpBestellenButton;
    private RadioGroup radiogroupPayBA;
    private TextView creditCardDataTV;
    private EditText creditCardNoET, expireDateET, cvvET, surnameET, nameET, streetET, hNrET, plzET, cityET, emailET;
    private RadioButton radioButtonBACreditCard, radioButtonBACash, radioButtonBAPaypal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bestellen_abholen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        returnButton = (Button) view.findViewById(R.id.returnButton);
        abortButton = (Button) view.findViewById(R.id.abortButton);
        kpBestellenButton = (Button) view.findViewById(R.id.kpBestellenButton);
        radiogroupPayBA = (RadioGroup) view.findViewById(R.id.radioGroupPayment);
        creditCardDataTV = (TextView) view.findViewById(R.id.textViewCreditCard);
        creditCardNoET = (EditText) view.findViewById(R.id.editTextCardNumberBA);
        expireDateET = (EditText) view.findViewById(R.id.editTextExpireDateBA);
        cvvET = (EditText) view.findViewById(R.id.editTextCVVBA);
        radioButtonBACreditCard = (RadioButton) view.findViewById(R.id.radioButtonBAKreditkarte);
        radioButtonBACash = (RadioButton) view.findViewById(R.id.radioButtonBABargeld);
        radioButtonBAPaypal = (RadioButton) view.findViewById(R.id.radioButtonBAPayPal);

        surnameET = (EditText) view.findViewById(R.id.baSurname);
        nameET = (EditText) view.findViewById(R.id.baName);
        streetET = (EditText) view.findViewById(R.id.baStreet);
        hNrET = (EditText) view.findViewById(R.id.baHNr);
        plzET = (EditText) view.findViewById(R.id.baPlz);
        cityET = (EditText) view.findViewById(R.id.baOrt);
        emailET = (EditText) view.findViewById(R.id.baEmail);


        kpBestellenButton.setEnabled(false);
        radioButtonBACash.setChecked(true);

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
        kpBestellenButton.setOnClickListener(this);
        radioButtonBACreditCard.setOnClickListener(this);
        radioButtonBACash.setOnClickListener(this);
        radioButtonBAPaypal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.returnButton:
                ((MainActivity) getActivity()).setFragment(new AbholenFragment());
                break;
            case R.id.abortButton:
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                break;
            case R.id.kpBestellenButton:
                // do your code
                break;
            case R.id.radioButtonBAKreditkarte:
                if(radiogroupPayBA.getCheckedRadioButtonId() == R.id.radioButtonBAKreditkarte){
                    creditCardDataTV.setVisibility(View.VISIBLE);
                    creditCardNoET.setVisibility(View.VISIBLE);
                    expireDateET.setVisibility(View.VISIBLE);
                    cvvET.setVisibility(View.VISIBLE);
                }
                testPickupBuy();
                break;
            case R.id.radioButtonBABargeld:
            case R.id.radioButtonBAPayPal:
                if(radiogroupPayBA.getCheckedRadioButtonId() == R.id.radioButtonBABargeld || radiogroupPayBA.getCheckedRadioButtonId() == R.id.radioButtonBAPayPal){
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

        if(radiogroupPayBA.getCheckedRadioButtonId() == R.id.radioButtonBAKreditkarte){
            if(!(surname.isEmpty() || name.isEmpty() || street.isEmpty() || hNr.isEmpty() || plz.isEmpty() || city.isEmpty() || email.isEmpty() || cardNumber.isEmpty() || expireDate.isEmpty() || cvvNumber.isEmpty())){
                kpBestellenButton.setEnabled(true);
            }
            else{
                kpBestellenButton.setEnabled(false);
            }
        }
        else{
            if (!(surname.isEmpty() || name.isEmpty() || street.isEmpty() || hNr.isEmpty() || plz.isEmpty() || city.isEmpty() || email.isEmpty())) {
                kpBestellenButton.setEnabled(true);
            } else {
                kpBestellenButton.setEnabled(false);
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