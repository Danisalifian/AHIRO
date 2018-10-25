package com.example.dan.ahiro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailProdukActivity extends AppCompatActivity {

    private TextView tvDProduk, tvDDeskripsi, tvDBerat, tvDHarga, tvDStok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        tvDProduk = (TextView)findViewById(R.id.tvDProduk);
        tvDDeskripsi = (TextView)findViewById(R.id.tvDDeskripsi);
        tvDBerat = (TextView)findViewById(R.id.tvDBerat);
        tvDHarga = (TextView)findViewById(R.id.tvDHarga);
        tvDStok = (TextView)findViewById(R.id.tvDStok);

        //Receive Data
        Intent intent = getIntent();
        String namaproduk = intent.getExtras().getString("namaproduk");
        String deskripsi = intent.getExtras().getString("deskripsi");
        String berat = intent.getExtras().getString("berat");
        String harga = intent.getExtras().getString("harga");
        String stok = intent.getExtras().getString("stok");

        //Set values
        tvDProduk.setText(namaproduk);
        tvDDeskripsi.setText(deskripsi);
        tvDBerat.setText(berat);
        tvDHarga.setText(harga);
        tvDStok.setText(stok);
    }
}
