package com.example.dan.ahiro.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dan.ahiro.R;

public class ItemOrderAdapter extends RecyclerView.ViewHolder {
    public TextView tvItemNamaProduk, tvItemHarga, tvItemQty, tvItemSubtotal;
    public CardView cvItemOrder;

    public ItemOrderAdapter (View itemView){
        super(itemView);

        tvItemNamaProduk = itemView.findViewById(R.id.tvItemNamaProduk);
        tvItemHarga = itemView.findViewById(R.id.tvItemHarga);
        tvItemQty = itemView.findViewById(R.id.tvItemQty);
        tvItemSubtotal = itemView.findViewById(R.id.tvItemSubtotal);
        cvItemOrder = itemView.findViewById(R.id.cvItemOrder);
    }
}
