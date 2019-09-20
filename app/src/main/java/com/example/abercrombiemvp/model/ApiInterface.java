package com.example.abercrombiemvp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    //https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json
    @GET("codeTest_exploreData.json")
    Call<List<PromoPojo>> getPromotions();
}
