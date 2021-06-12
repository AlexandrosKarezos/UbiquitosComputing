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
    RatingBar ratingBarFB;
    Button abortButton;
    Button submitFbButton;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("Feedback");
    EditText commentET;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        commentET= view.findViewById(R.id.fbNoteInputMLT);
        ratingBarFB=view.findViewById(R.id.ratingBarFB);
        abortButton = view.findViewById(R.id.abortButton);
        submitFbButton = view.findViewById(R.id.submitFBButton);

        submitFbButton.setEnabled(false);

        abortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setFragment(new StartFragment());
            }
        });

        ratingBarFB.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                submitFbButton.setEnabled(true);
            }
        });

        submitFbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).setFragment(new StartFragment());
                if(FirebaseAuth.getInstance().getCurrentUser()==null)
                {
                    String ratingvalue=String.valueOf(ratingBarFB.getRating());
                    String text=commentET.getText().toString();
                    Feedback feedback= new Feedback(ratingvalue,text);
                    root.push().setValue(feedback);
                    Toast.makeText(getActivity(),"Thank you for your feedback!",Toast.LENGTH_LONG).show();
                }
                if(FirebaseAuth.getInstance().getCurrentUser()!=null)
                {

                    String email= user.getEmail();
                    String ratingvalue=String.valueOf(ratingBarFB.getRating());
                    String text=commentET.getText().toString();
                    Feedback feedback= new Feedback(ratingvalue,text,email);
                    root.push().setValue(feedback);
                    Toast.makeText(getActivity(),"Thank you for your feedback!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}