package com.example.pizzaapp;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class ReservationFragment extends Fragment{

    TextView nmbPersTV;
    TextView resTimeTV;
    TextView resDateTV;
    int hour, minute;
    NumberPicker numberPicker;
    Button dateButton;
    Button timeButton;
    Button reservationButton;
    Button abortButton;
    EditText surnameET, nameET, emailET;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Reservation");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nmbPersTV = view.findViewById(R.id.tvNmbPers);
        resTimeTV = view.findViewById(R.id.tvResTime);
        dateButton = view.findViewById(R.id.resDateButton);
        timeButton = view.findViewById(R.id.resTimeButton);
        numberPicker = view.findViewById(R.id.msNumberPicker);
        resDateTV = view.findViewById(R.id.tvResDate);
        reservationButton = view.findViewById(R.id.resConfirmButton);
        abortButton = view.findViewById(R.id.abortButton);
        surnameET = view.findViewById(R.id.resSurnameInputET);
        nameET = view.findViewById(R.id.resNameInputET);
        emailET = view.findViewById(R.id.resEmailInputET);

        addReservationETChange(surnameET);
        addReservationETChange(nameET);
        addReservationETChange(emailET);

        reservationButton.setEnabled(false);

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
                nmbPersTV.setText("Anzahl Personen: " + newVal);
                testReservation();
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
                testReservation();
            }
        });

        reservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                String anzPers = nmbPersTV.getText().toString();
                String resTime = resTimeTV.getText().toString();
                String resDate = resDateTV.getText().toString();
                String surname = surnameET.getText().toString();
                String name = nameET.getText().toString();
                String email = emailET.getText().toString();
                Reservation reservierung= new Reservation(anzPers,resTime,resDate,surname,name,email,false);
                root.push().setValue(reservierung);
                Toast.makeText(getActivity(),"Danke für Ihre Reservierung!",Toast.LENGTH_LONG).show();
            }
        });

        abortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new StartFragment());
            }
        });


    }

    public void testReservation(){
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

    public void addReservationETChange(EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                testReservation();
            }
        });
    }

    private void showAllUserData()
    {
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue((User.class));
                if(FirebaseAuth.getInstance().getCurrentUser()!=null)
                {

                    String surname = userProfile.lastName;
                    String name = userProfile.firstName;
                    String email = userProfile.email;
                    nameET.setText(name);
                    surnameET.setText(surname);
                    emailET.setText(email);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
