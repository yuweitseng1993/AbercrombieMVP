package com.example.abercrombiemvp.presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abercrombiemvp.model.ApiInterface;
import com.example.abercrombiemvp.model.PromoPojo;
import com.example.abercrombiemvp.model.ResultPojo;
import com.example.abercrombiemvp.view.MainActivity;
import com.example.abercrombiemvp.view.ViewContract;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class Presenter implements PresenterContract {
    private static final String TAG = "Presenter";
    private ViewContract view;
    private RequestQueue mRequestQueue;
    private String url = "https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json";
    private Gson gson;

    @Override
    public void onBindView(ViewContract view) {
        this.view = view;
    }

    @Override
    public void unBind() {
        view = null;
    }

    @Override
    public void volleyGetPromotion(final Context context) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        mRequestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            PromoPojo[] promos = gson.fromJson(response.toString(), PromoPojo[].class);
                            List<PromoPojo> promoList = new ArrayList<>();
                            for(int i = 0; i < promos.length; i++){
                                promoList.add(promos[i]);
                            }
                            onPromoDataSuccess(promoList);
                        }
                        else{
                            Toast.makeText(context, "Noting returned from the server...", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.i(TAG,"Error :" + error.toString());
                    }
                }
        );
        mRequestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onPromoDataSuccess(List<PromoPojo> promoItem) {
        view.getPromotionData(promoItem);
    }

    @Override
    public void onDataFailure(String errorMsg) {
        view.getFailureMessage(errorMsg);
    }
}
