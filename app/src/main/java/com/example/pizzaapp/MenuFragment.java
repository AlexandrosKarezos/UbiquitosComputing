package com.example.pizzaapp;

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

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.FirebaseDatabase;

public class MenuFragment extends Fragment implements MyAdapter.OnCardListener {

    View view;
    Button toOrderButton;
    RecyclerView recviewMenu;
    MyAdapter adapter;
    FirebaseRecyclerOptions<Meal> options;
    private ObservableSnapshotArray<Meal> mMeals;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_menu, container, false);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recviewMenu=(RecyclerView)view.findViewById(R.id.recviewMenu);
        recviewMenu.setLayoutManager(new LinearLayoutManager(view.getContext()));

        options = new FirebaseRecyclerOptions.Builder<Meal>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Meal"), Meal.class)
                        .build();

        mMeals = options.getSnapshots();

        adapter=new MyAdapter(options, this);
        recviewMenu.setAdapter(adapter);

        toOrderButton = view.findViewById(R.id.mToOrderButton);
        toOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).setFragment(new OrderOverviewFragment());
            }
        });


        CheckBox vegetarischCB = (CheckBox) view.findViewById(R.id.cbMVegetarian);
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

        Fragment fragment = new MenuSelectionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("pizza", mMeals.get(position).getName());
        bundle.putDouble("price", mMeals.get(position).getPrice());
        fragment.setArguments(bundle);
        ((MainActivity) getActivity()).setFragment(fragment);
    }
}