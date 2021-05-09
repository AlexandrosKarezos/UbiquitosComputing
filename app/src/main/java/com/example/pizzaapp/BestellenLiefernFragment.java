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
import android.widget.TextView;

public class BestellenLiefernFragment extends Fragment implements View.OnClickListener{

    private Button returnButton, abortButton, kpBestellenButton;
    private TextView creditCardDataTV;
    private EditText creditCardNoET, expireDateET, cvvET;
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
        radioButtonCreditCard = (RadioButton) view.findViewById(R.id.radioButtonKreditkarte);
        radioButtonCash = (RadioButton) view.findViewById(R.id.radioButtonBargeld);
        radioButtonPaypal = (RadioButton) view.findViewById(R.id.radioButtonPayPal);

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
                break;
            case R.id.radioButtonBargeld:
            case R.id.radioButtonPayPal:
                if(radioButtonCash.isChecked() || radioButtonPaypal.isChecked()){
                    creditCardDataTV.setVisibility(View.GONE);
                    creditCardNoET.setVisibility(View.GONE);
                    expireDateET.setVisibility(View.GONE);
                    cvvET.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }
}