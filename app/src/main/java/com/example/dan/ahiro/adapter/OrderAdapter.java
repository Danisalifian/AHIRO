package com.example.dan.ahiro.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dan.ahiro.R;

public class OrderAdapter extends RecyclerView.ViewHolder {

    public TextView tvTanggal, tvHargaproduk, tvBiayakirim, tvTotalbayar, tvPenerima;

    public OrderAdapter(View itemView) {
        super(itemView);

        tvHargaproduk = itemView.findViewById(R.id.tvHargaproduk);
        tvTanggal = itemView.findViewById(R.id.tvTanggal);
        tvBiayakirim = itemView.findViewById(R.id.tvBiayakirim);
        tvTotalbayar = itemView.findViewById(R.id.tvTotalbayar);
        tvPenerima = itemView.findViewById(R.id.tvPenerima);
    }
}
