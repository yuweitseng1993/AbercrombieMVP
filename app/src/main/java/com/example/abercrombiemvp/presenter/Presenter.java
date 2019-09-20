package com.example.abercrombiemvp.presenter;

import android.util.Log;

import com.example.abercrombiemvp.model.ApiInterface;
import com.example.abercrombiemvp.model.PromoPojo;
import com.example.abercrombiemvp.model.ResultPojo;
import com.example.abercrombiemvp.view.ViewContract;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Presenter implements PresenterContract {
    private static final String TAG = "Presenter";
    private ViewContract view;

    @Override
    public void onBindView(ViewContract view) {
        this.view = view;
    }

    @Override
    public void unBind() {
        view = null;
    }

    @Override
    public void retrofitGetPromotion() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.abercrombie.com/anf/nativeapp/qa/codetest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        retrofit.create(ApiInterface.class).getPromotions().enqueue(new Callback<ResultPojo>() {
//            @Override
//            public void onResponse(Call<ResultPojo> call, Response<ResultPojo> response) {
//                if(response.isSuccessful()){
//                    onPromoDataSuccess(response.body());
//                    Log.d(TAG, "onResponse: " + new GsonBuilder().setPrettyPrinting().create().toJson(response));
//                }
//                else{
//                    Log.d(TAG, "onResponse: retrofit failed");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResultPojo> call, Throwable t) {
//
//            }
//        });
        retrofit.create(ApiInterface.class).getPromotions().enqueue(new Callback<List<PromoPojo>>() {
            @Override
            public void onResponse(Call<List<PromoPojo>> call, Response<List<PromoPojo>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG, "onResponse: " + new GsonBuilder().setPrettyPrinting().create().toJson(response));
                    onPromoDataSuccess(response.body());
                }
                else{
                    Log.d(TAG, "onResponse: retrofit failed\n" + response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<PromoPojo>> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

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
