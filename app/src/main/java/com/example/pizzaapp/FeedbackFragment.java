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
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedbackFragment extends Fragment {
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();;
    RatingBar ratingBar;
    Button abortButton;
    Button submitFbButton;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Feedback");
    EditText commentEditText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        commentEditText= view.findViewById(R.id.fBAnmerkungInput);
        ratingBar=view.findViewById(R.id.ratingBar);
        abortButton = view.findViewById(R.id.abortButton);
        submitFbButton = view.findViewById(R.id.submitFBButton);

        abortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setFragment(new StartFragment());
            }
        });

        submitFbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                if(FirebaseAuth.getInstance().getCurrentUser()==null)
                {
                    String ratingvalue=String.valueOf(ratingBar.getRating());
                    String text=commentEditText.getText().toString();
                    Feedback feedback= new Feedback(ratingvalue,text);
                    root.push().setValue(feedback);
                    Toast.makeText(getActivity(),"Thank you for your feedback!",Toast.LENGTH_LONG).show();
                }
                if(FirebaseAuth.getInstance().getCurrentUser()!=null)
                {

                    String email= user.getEmail();
                    String ratingvalue=String.valueOf(ratingBar.getRating());
                    String text=commentEditText.getText().toString();
                    Feedback feedback= new Feedback(ratingvalue,text,email);
                    root.push().setValue(feedback);
                    Toast.makeText(getActivity(),"Thank you for your feedback!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}