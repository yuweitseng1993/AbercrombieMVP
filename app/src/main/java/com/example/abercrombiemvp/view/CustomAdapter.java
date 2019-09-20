package com.example.abercrombiemvp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abercrombiemvp.R;
import com.example.abercrombiemvp.model.PromoPojo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder>{
    private List<PromoPojo> dataSet;
    private Context context;

    public CustomAdapter(Context context){
        this.context = context;
    }

    public void setDataset(List<PromoPojo> dataSet){
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvTitle.setText(dataSet.get(position).title);
        Picasso.get().load(dataSet.get(position).backgroundImage).into(holder.ivBgImg);
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }
}
