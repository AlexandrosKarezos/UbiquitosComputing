package com.example.pizzaapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReservationFragment extends Fragment{

    TextView anzPersTV;
    TextView resTimeTV;
    TextView resDateTV;
    int hour, minute;
    NumberPicker numberPicker;
    Button dateButton;
    Button timeButton;
    Button bookButton;
    Button abortButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anzPersTV = view.findViewById(R.id.textViewAnzPers);
        resTimeTV = view.findViewById(R.id.textViewResTime);
        dateButton = view.findViewById(R.id.buttonDate);
        timeButton = view.findViewById(R.id.timeButton);
        numberPicker = view.findViewById(R.id.numberPicker);
        resDateTV = view.findViewById(R.id.textViewResDate);
        bookButton = view.findViewById(R.id.buttonReservieren);
        abortButton = view.findViewById(R.id.abortButton);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(), "date picker");
            }
        });

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(20);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                anzPersTV.setText("Anzahl Personen: " + newVal);
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hour = selectedHour;
                        minute = selectedMinute;
                        resTimeTV.setText(String.format(Locale.getDefault(), "%02d:%02d Uhr", hour,minute));
                    }
                };
                int style = AlertDialog.THEME_HOLO_DARK;
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), style, onTimeSetListener, hour, minute, true);
                timePickerDialog.setTitle("Uhrzeit waehlen");
                timePickerDialog.show();
            }
        });

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new StartFragment());
            }
        });

        abortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new StartFragment());
            }
        });
    }
}