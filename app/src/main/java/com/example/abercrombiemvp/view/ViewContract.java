package com.example.abercrombiemvp.view;

import com.example.abercrombiemvp.model.PromoPojo;

import java.util.List;

public interface ViewContract {
    void onBindPresenter();
    void initUI();
    void retrievePromotion();
    void getPromotionData(List<PromoPojo> promotionResults);
    void getFailureMessage(String errorMsg);
}
