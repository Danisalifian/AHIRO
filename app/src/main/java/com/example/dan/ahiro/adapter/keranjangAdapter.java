package com.example.dan.ahiro.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dan.ahiro.DetailProductActivity;

import com.example.dan.ahiro.R;
import com.example.dan.ahiro.model.Keranjang;

import java.util.List;

public class keranjangAdapter extends RecyclerView.Adapter <keranjangAdapter.MyViewHolder> {

    private Context kcontext;
    private List<Keranjang> kdata;


    public keranjangAdapter(Context kcontext, List<Keranjang> kdata) {
        this.kcontext = kcontext;
        this.kdata = kdata;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  ;
        LayoutInflater mInflater = LayoutInflater.from(kcontext);
        view = mInflater.inflate(R.layout.cardview_keranjang,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvNamaProdukK.setText(kdata.get(position).getNamaprodukk());
        holder.tvHargaK.setText(kdata.get(position).getHargak());
        holder.tvJumlahK.setText(kdata.get(position).getHargak());

        holder.cvKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(kcontext, DetailProductActivity.class);

                //passing data ke DetailProdukActivity
                intent.putExtra("namaproduk",kdata.get(position).getNamaprodukk());
                intent.putExtra("harga",kdata.get(position).getHargak());
                intent.putExtra("jumlah",kdata.get(position).getJumlahk());
                kcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvNamaProdukK, tvHargaK,tvJumlahK ;
        CardView cvKeranjang;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNamaProdukK = (TextView) itemView.findViewById(R.id.tvNamaProdukK);
            tvHargaK = (TextView) itemView.findViewById(R.id.tvHargaK);
            tvJumlahK = (TextView) itemView.findViewById(R.id.tvJumlahK);
            cvKeranjang = (CardView) itemView.findViewById(R.id.cvKeranjang);
        }
    }
}

