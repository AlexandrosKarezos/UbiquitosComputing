package com.example.pizzaapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.FirebaseDatabase;

public class MenueFragment extends Fragment implements myadapter.OnCardListener {

    View view;
    Button bestellButton;
    RecyclerView recview;
    myadapter adapter;
    FirebaseRecyclerOptions<Meal> options;
    private ObservableSnapshotArray<Meal> mMeals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menue, container, false);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recview=(RecyclerView)view.findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(view.getContext()));

        options = new FirebaseRecyclerOptions.Builder<Meal>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Meal"), Meal.class)
                        .build();

        mMeals = options.getSnapshots();

        adapter=new myadapter(options, this);
        recview.setAdapter(adapter);

        bestellButton = view.findViewById(R.id.buttonBestellung);
        bestellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new BestelluebersichtFragment());
            }
        });


        CheckBox vegetarischCB = (CheckBox) view.findViewById(R.id.checkBoxVegetarisch);
        vegetarischCB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is checkbox checked?
                if (((CheckBox) v).isChecked()) {
                    options = new FirebaseRecyclerOptions.Builder<Meal>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Meal").orderByChild("veg").equalTo(true), Meal.class)
                            .build();
                    mMeals = options.getSnapshots();
                    adapter.updateOptions(options);
                }
                else{
                    options = new FirebaseRecyclerOptions.Builder<Meal>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("Meal"), Meal.class)
                            .build();
                    mMeals = options.getSnapshots();
                    adapter.updateOptions(options);
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public void onCardClick(int position) {

        Fragment fragment = new MenueAuswahlFragment();
        Bundle bundle = new Bundle();
        bundle.putString("pizza", mMeals.get(position).getName());
        bundle.putDouble("price", mMeals.get(position).getPrice());
        fragment.setArguments(bundle);
        ((MainActivity) getActivity()).setFragment(fragment);
    }
}