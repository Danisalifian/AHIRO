package com.example.dan.ahiro.adapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.dan.ahiro.R;


public class keranjangAdapter extends RecyclerView.ViewHolder {
    public TextView tvNamaProduk, tvHarga;
    public CardView cvProduk;
    public ImageView ivGambar;

    public  keranjangAdapter(View itemView){
        super(itemView);

        tvNamaProduk = itemView.findViewById(R.id.tvNamaProduk);
        tvHarga = itemView.findViewById(R.id.tvHarga);
        cvProduk = itemView.findViewById(R.id.cvProduk);
        ivGambar = itemView.findViewById(R.id.ivGambar);
    }

}

