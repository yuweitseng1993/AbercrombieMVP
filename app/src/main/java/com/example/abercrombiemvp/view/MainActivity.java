package com.example.abercrombiemvp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.abercrombiemvp.R;
import com.example.abercrombiemvp.model.PromoPojo;
import com.example.abercrombiemvp.presenter.Presenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ViewContract{
    private static final String TAG = "MainActivity";
    Presenter presenter;
    RecyclerView recyclerView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        onBindPresenter();
        retrievePromotion();
    }

    @Override
    public void onBindPresenter() {
        presenter = new Presenter();
        presenter.onBindView(this);
    }

    @Override
    public void initUI() {
        recyclerView = findViewById(R.id.rv_promotions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        customAdapter = new CustomAdapter(this);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    public void retrievePromotion() {
        presenter.volleyGetPromotion(this);
    }

    @Override
    public void getPromotionData(List<PromoPojo> promotionResults) {
        customAdapter.setDataset(promotionResults);
    }

    @Override
    public void getFailureMessage(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }
}
