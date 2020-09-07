package com.example.stock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



// Written by Cade Cottrell for CS4301.002, Stock Assignment
// netid: cac160030


// Custom RecyclerView Adapter
// Takes StockLine data and the Main Activity Context, and displays in Viewholder

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<StockLine> textViewItems;
    Context mContext;

    // Constructor
    public RecyclerViewAdapter(ArrayList<StockLine> textViewItems, Context context) {
        this.textViewItems = textViewItems;
        mContext = context;
    }


    // Create viewholder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_stocklist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //Set up the viewholder, by adding string to the textviews
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(textViewItems.get(position).getDate());
        holder.open.setText(textViewItems.get(position).getOpen());
        holder.high.setText(textViewItems.get(position).getHigh());
        holder.low.setText(textViewItems.get(position).getLow());
        holder.close.setText(textViewItems.get(position).getClose());
        holder.volume.setText(textViewItems.get(position).getVolume());
        holder.adjClose.setText(textViewItems.get(position).getAdjClose());

    }

    @Override
    public int getItemCount() {
        return textViewItems.size();
    }


    // Our viewholder it holds the layout, and the textviews to display stock data
    // this is our "row" in the recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        TextView date;
        TextView open;
        TextView high;
        TextView low;
        TextView close;
        TextView volume;
        TextView adjClose;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            date = itemView.findViewById(R.id.Date);
            open = itemView.findViewById(R.id.Open);
            high = itemView.findViewById(R.id.High);
            low = itemView.findViewById(R.id.Low);
            close = itemView.findViewById(R.id.Close);
            volume = itemView.findViewById(R.id.Volume);
            adjClose = itemView.findViewById(R.id.AdjClose);

        }
    }
}
