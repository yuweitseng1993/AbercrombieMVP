package com.example.abercrombiemvp.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterface {
    //https://www.abercrombie.com/anf/nativeapp/qa/codetest/codeTest_exploreData.json
    @GET()
    Call<List<PromoPojo>> getPromotions(@Url String endpoint);
}
