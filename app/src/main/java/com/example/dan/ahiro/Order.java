package com.example.dan.ahiro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class Order extends AppCompatActivity {

    Toolbar toolbar;
//    TextView IDproduk;
    String productId;
    RecyclerView rvOrder;
    CardView cvOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        toolbar = findViewById(R.id.OToolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

//        IDproduk = findViewById(R.id.IDproduk);

        productId = getIntent().getStringExtra("productId");

//        IDproduk.setText("produk Id" + productId);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("productId", productId);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
    }
}
