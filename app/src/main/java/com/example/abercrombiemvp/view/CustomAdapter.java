package com.example.abercrombiemvp.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.abercrombiemvp.R;
import com.example.abercrombiemvp.model.PromoPojo;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder>{
    private static final String TAG = "CustomAdapter";
    private List<PromoPojo> dataSet;
    private Context context;
    private boolean btnAdded;

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
        btnAdded = false;
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.promotion_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        final PromoPojo promoItem = dataSet.get(position);
        holder.tvTitle.setText(promoItem.title);
        Picasso.get().load(promoItem.backgroundImage).into(holder.ivBgImg);
        if(promoItem.promoMessage != null){
            holder.tvPromoMsg.setText(promoItem.promoMessage);
            holder.tvPromoMsg.setVisibility(View.VISIBLE);
        }
        if(promoItem.topDescription != null){
            holder.tvTopDescp.setText(promoItem.topDescription);
            holder.tvTopDescp.setVisibility(View.VISIBLE);
        }
        if(promoItem.bottomDescription != null){
            Document document = Jsoup.parse(promoItem.bottomDescription);

            final Element link = document.select("a").first();
            String entireText = document.body().text();
            String linkText = link.text();
            String nonLinkText = entireText.replace(linkText, "");
            final String linkAttr = link.attr("href").replaceAll("[\\\\\"]", "");
            Log.d(TAG, "onBindViewHolder: linkAttr -> " + linkAttr);
            Log.d(TAG, "onBindViewHolder: nonLinkText -> " + nonLinkText);
            holder.llBtmDescp.setVisibility(View.VISIBLE);
            holder.tvBtmDescpTxt.setText(nonLinkText);
            holder.tvBtmDescpLink.setText(Html.fromHtml(link.toString()));
            holder.tvBtmDescpLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PromoLink.class);
                    intent.putExtra("url", linkAttr);
                    context.startActivity(intent);
                }
            });

        }
        if(promoItem.content != null){
            if(!btnAdded){
                holder.llContentBtn.setVisibility(View.VISIBLE);
                for(int i = 0; i < promoItem.content.size() ; i++){
                    Button contentBtn = new Button(context);
                    contentBtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    contentBtn.setText(promoItem.content.get(i).title);
                    contentBtn.setAllCaps(false);
                    GradientDrawable gd = new GradientDrawable();
                    gd.setStroke(2, 0xFF000000);
                    contentBtn.setBackground(gd);
                    LinearLayout.LayoutParams parameter = (LinearLayout.LayoutParams) contentBtn.getLayoutParams();
                    parameter.setMargins(0, 8, 0, 8);
                    contentBtn.setLayoutParams(parameter);
                    holder.llContentBtn.addView(contentBtn);

                    final int finalI = i;
                    contentBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, PromoLink.class);
                            intent.putExtra("url", promoItem.content.get(finalI).target);
                            Log.d(TAG, "onClick: button target -> " + promoItem.content.get(finalI).target);
                            context.startActivity(intent);
                        }
                    });
                }
                btnAdded = true;
            }
        }

    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }
}
