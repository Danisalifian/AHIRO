package com.example.dan.ahiro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.dan.ahiro.model.Produk;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailProductActivity extends AppCompatActivity {

    private TextView tvDProduk, tvDDeskripsi, tvDBerat, tvDHarga, tvDStok;
    ImageView ivGambar;
    ElegantNumberButton numberButton;
    CollapsingToolbarLayout collapsingToolbarLayout;

    String productId = "";

    FirebaseDatabase database;
    DatabaseReference products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        //Firebase
        database = FirebaseDatabase.getInstance();
        products = database.getReference("Products");

        //Init View
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        tvDProduk = findViewById(R.id.tvDProduk);
        tvDDeskripsi = findViewById(R.id.tvDDeskripsi);
        tvDBerat = findViewById(R.id.tvDBerat);
        tvDHarga = findViewById(R.id.tvDHarga);
        tvDStok = findViewById(R.id.tvDStok);
        ivGambar = findViewById(R.id.ivGambar);
        numberButton = findViewById(R.id.number_button);

        collapsingToolbarLayout.setExpandedTitleColor(R.style.ExpandedAppbar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //get Id from intent
        if (getIntent() != null)
            productId = getIntent().getStringExtra("productId");
        if (!productId.isEmpty()){
            getDetailProduct(productId);
        }
    }

    private void getDetailProduct(String productId) {
        products.child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Produk produk = dataSnapshot.getValue(Produk.class);

                //Receive Data
                Intent intent = getIntent();
                String productname = intent.getExtras().getString("productname");
                String description = intent.getExtras().getString("description");
                String weight = intent.getExtras().getString("weight");
                String price = intent.getExtras().getString("price");
                String stock = intent.getExtras().getString("stock");

                //Set Image
                Picasso.get().load(produk.getImage()).into(ivGambar);
                collapsingToolbarLayout.setTitle(produk.getProductname());

                //Set values
                tvDProduk.setText(productname);
                tvDDeskripsi.setText(description);
                tvDBerat.setText(weight);
                tvDHarga.setText(price);
                tvDStok.setText(stock);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
