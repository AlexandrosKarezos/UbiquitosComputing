package com.example.pizzaapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.text.DateFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView resDateTV = (TextView) getActivity().findViewById(R.id.tvResDate);
        resDateTV.setText(currentDateString);
        testReservation();
    }

    public void testReservation(){
        TextView nmbPersTV = (TextView) getActivity().findViewById(R.id.tvNmbPers);
        TextView resTimeTV = (TextView) getActivity().findViewById(R.id.tvResTime);
        TextView resDateTV = (TextView) getActivity().findViewById(R.id.tvResDate);
        TextView surnameET = (TextView) getActivity().findViewById(R.id.resSurnameInputET);
        TextView nameET = (TextView) getActivity().findViewById(R.id.resNameInputET);
        TextView emailET = (TextView) getActivity().findViewById(R.id.resEmailInputET);
        Button reservationButton = getActivity().findViewById(R.id.resConfirmButton);

        String anzPers = nmbPersTV.getText().toString();
        String resTime = resTimeTV.getText().toString();
        String resDate = resDateTV.getText().toString();
        String surname = surnameET.getText().toString();
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();

        if(!(anzPers.isEmpty() || resTime.isEmpty() || resDate.isEmpty() || surname.isEmpty() || name.isEmpty() || email.isEmpty())){
            reservationButton.setEnabled(true);
        }
        else{
            reservationButton.setEnabled(false);
        }
    }
}
