package com.example.abercrombiemvp.presenter;

import android.content.Context;

import com.example.abercrombiemvp.model.PromoPojo;
import com.example.abercrombiemvp.model.ResultPojo;
import com.example.abercrombiemvp.view.ViewContract;

import java.util.List;

public interface PresenterContract {
    void onBindView(ViewContract view);
    void unBind();
    void volleyGetPromotion(Context context);
    void onPromoDataSuccess(List<PromoPojo> promoItem);
    void onDataFailure(String errorMsg);
}
