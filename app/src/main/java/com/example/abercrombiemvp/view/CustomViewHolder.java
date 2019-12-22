package com.example.abercrombiemvp.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abercrombiemvp.R;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    ImageView ivBgImg;
    TextView tvTitle, tvPromoMsg, tvTopDescp, tvBtmDescpTxt, tvBtmDescpLink;
    LinearLayout llBtmDescp, llContentBtn;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        ivBgImg = itemView.findViewById(R.id.iv_bg_img);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvPromoMsg = itemView.findViewById(R.id.tv_promo_msg);
        tvTopDescp = itemView.findViewById(R.id.tv_top_descipt);
        tvBtmDescpTxt = itemView.findViewById(R.id.tv_btm_description_text);
        tvBtmDescpLink = itemView.findViewById(R.id.tv_btm_description_link);
        llBtmDescp = itemView.findViewById(R.id.ll_btm_description);
        llContentBtn = itemView.findViewById(R.id.ll_content_buttons);
    }
}
