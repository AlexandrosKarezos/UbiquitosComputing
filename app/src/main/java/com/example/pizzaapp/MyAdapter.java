package com.example.pizzaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyAdapter extends FirebaseRecyclerAdapter<Meal, MyAdapter.myviewholder>
{
    private ObservableSnapshotArray<Meal> mMeals;
    private OnCardListener mOnCardListener;


    public MyAdapter(@NonNull FirebaseRecyclerOptions<Meal> options, OnCardListener onCardListener) {
        super(options);
        mMeals = options.getSnapshots();
        this.mOnCardListener = onCardListener;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Meal model)
    {
        holder.name.setText(model.getName());
        holder.price.setText(String.valueOf(model.getPrice()));
        holder.topping.setText(model.getTopping());
        holder.veg.setText(String.valueOf(model.isVeg()));
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);

        return new myviewholder(view, mOnCardListener);
    }

    class myviewholder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        CircleImageView img;
        TextView name,price,topping,veg;
        OnCardListener mOnCardListener;

        public myviewholder(@NonNull View itemView, OnCardListener onCardListener)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.nametext);
            price=(TextView)itemView.findViewById(R.id.pricetext);
            topping=(TextView)itemView.findViewById(R.id.toppingtext);
            veg=(TextView)itemView.findViewById(R.id.vegtext);
            mOnCardListener = onCardListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnCardListener.onCardClick(getAdapterPosition());
        }
    }

    public interface OnCardListener{
        void onCardClick(int position);
    }
}
