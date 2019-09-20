package com.example.abercrombiemvp.presenter;

import com.example.abercrombiemvp.model.PromoPojo;
import com.example.abercrombiemvp.model.ResultPojo;
import com.example.abercrombiemvp.view.ViewContract;

import java.util.List;

public interface PresenterContract {
    void onBindView(ViewContract view);
    void unBind();
    void retrofitGetPromotion();
    void onPromoDataSuccess(List<PromoPojo> promoItem);
    void onDataFailure(String errorMsg);
}
