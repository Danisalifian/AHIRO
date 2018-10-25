package com.example.dan.ahiro.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dan.ahiro.DetailProdukActivity;
import com.example.dan.ahiro.R;
import com.example.dan.ahiro.model.Produk;

import java.util.List;

public class produkAdapter extends RecyclerView.Adapter<produkAdapter.MyViewHolder> {

    private Context mContext;
    private List<Produk> mData;

    public produkAdapter(Context mContext, List<Produk> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_produk, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvNamaProduk.setText(mData.get(position).getNamaproduk());
        holder.tvHarga.setText(mData.get(position).getHarga());

        //Set click listener
        holder.cvProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailProdukActivity.class);

                //passing data ke DetailProdukActivity
                intent.putExtra("namaproduk", mData.get(position).getNamaproduk());
                intent.putExtra("deskripsi", mData.get(position).getDeskripsi());
                intent.putExtra("berat", mData.get(position).getBerat());
                intent.putExtra("harga", mData.get(position).getHarga());
                intent.putExtra("stok", mData.get(position).getStok());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvNamaProduk, tvHarga;
        CardView cvProduk;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNamaProduk = (TextView)itemView.findViewById(R.id.tvNamaProduk);
            tvHarga = (TextView)itemView.findViewById(R.id.tvHarga);
            cvProduk = (CardView)itemView.findViewById(R.id.cvProduk);
        }
    }
}
