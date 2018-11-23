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
import com.example.dan.ahiro.model.BelumLunas;

import java.util.List;

public class belumlunasAdapter extends RecyclerView.Adapter <belumlunasAdapter.MyViewHolder> {

    private Context bcontext;
    private List<BelumLunas> bdata;


    public belumlunasAdapter(Context bcontext, List<BelumLunas> bdata) {
        this.bcontext = bcontext;
        this.bdata = bdata;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  ;
        LayoutInflater mInflater = LayoutInflater.from(bcontext);
        view = mInflater.inflate(R.layout.cardview_belum_lunas,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvNamaProdukb.setText(bdata.get(position).getNamaprodukb());
        holder.tvHargab.setText(bdata.get(position).getHargab());
        holder.tvJumlahb.setText(bdata.get(position).getJumlahb());

        holder.cvBlunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bcontext, DetailProductActivity.class);

                //passing data ke DetailProdukActivity
                intent.putExtra("namaproduk",bdata.get(position).getNamaprodukb());
                intent.putExtra("deskripsi", bdata.get(position).getDeskripsib());
                intent.putExtra("berat", bdata.get(position).getBeratb());
                intent.putExtra("harga",bdata.get(position).getHargab());
                intent.putExtra("jumlah",bdata.get(position).getJumlahb());
                bcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bdata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvNamaProdukb, tvHargab,tvJumlahb ;
        CardView cvBlunas;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNamaProdukb = (TextView) itemView.findViewById(R.id.tvNamaProdukB);
            tvHargab = (TextView) itemView.findViewById(R.id.tvHargaB);
            tvJumlahb = (TextView) itemView.findViewById(R.id.tvJumlahB);
            cvBlunas = (CardView) itemView.findViewById(R.id.cvBlunas);
        }
    }
}

