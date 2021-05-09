package com.example.pizzaapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BestellenAbholenFragment extends Fragment implements View.OnClickListener {

    Button returnButton, abortButton, kpBestellenButton;
    RadioGroup radiogroupPay;
    TextView creditCardDataTV;
    EditText creditCardNoET, expireDateET, cvvET;
    RadioButton radioButtonBACreditCard, radioButtonBACash, radioButtonBAPaypal;

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
        radiogroupPay = (RadioGroup) view.findViewById(R.id.radioGroupPayment);
        creditCardDataTV = (TextView) view.findViewById(R.id.textViewCreditCard);
        creditCardNoET = (EditText) view.findViewById(R.id.editTextCardNumberBA);
        expireDateET = (EditText) view.findViewById(R.id.editTextExpireDateBA);
        cvvET = (EditText) view.findViewById(R.id.editTextCVVBA);
        radioButtonBACreditCard = (RadioButton) view.findViewById(R.id.radioButtonBAKreditkarte);
        radioButtonBACash = (RadioButton) view.findViewById(R.id.radioButtonBABargeld);
        radioButtonBAPaypal = (RadioButton) view.findViewById(R.id.radioButtonBAPayPal);

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
                if(radiogroupPay.getCheckedRadioButtonId() == R.id.radioButtonBAKreditkarte){
                    creditCardDataTV.setVisibility(View.VISIBLE);
                    creditCardNoET.setVisibility(View.VISIBLE);
                    expireDateET.setVisibility(View.VISIBLE);
                    cvvET.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.radioButtonBABargeld:
            case R.id.radioButtonBAPayPal:
                if(radiogroupPay.getCheckedRadioButtonId() == R.id.radioButtonBABargeld || radiogroupPay.getCheckedRadioButtonId() == R.id.radioButtonBAPayPal){
                    creditCardDataTV.setVisibility(View.INVISIBLE);
                    creditCardNoET.setVisibility(View.INVISIBLE);
                    expireDateET.setVisibility(View.INVISIBLE);
                    cvvET.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                break;
        }
    }
}