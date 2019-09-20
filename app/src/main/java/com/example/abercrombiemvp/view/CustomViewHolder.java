package com.example.abercrombiemvp.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abercrombiemvp.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    ImageView ivBgImg;
    TextView tvTitle;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        ivBgImg = itemView.findViewById(R.id.iv_bg_img);
        tvTitle = itemView.findViewById(R.id.tv_title);
    }
}
