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
import com.example.dan.ahiro.model.Lunas;

import java.util.List;

public class lunasAdapter extends RecyclerView.Adapter<lunasAdapter.MyViewHolder> {
    private Context lcontext;
    private List<Lunas>ldata;

    public lunasAdapter(Context lcontext,List<Lunas>ldata) {
        this.lcontext = lcontext;
        this.ldata = ldata;
    }

    @Override
    public lunasAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  ;
        LayoutInflater mInflater = LayoutInflater.from(lcontext);
        view = mInflater.inflate(R.layout.cardview_lunas,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvNamaProdukl.setText(ldata.get(position).getNamaprodukl());
        holder.tvHargal.setText(ldata.get(position).getHargal());
        holder.tvJumlahl.setText(ldata.get(position).getJumlahl());

        holder.cvlunas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lcontext, DetailProductActivity.class);

                //passing data ke DetailProdukActivity
                intent.putExtra("namaproduk",ldata.get(position).getNamaprodukl());
                intent.putExtra("deskripsi", ldata.get(position).getDeskripsil());
                intent.putExtra("berat", ldata.get(position).getBeratl());
                intent.putExtra("harga",ldata.get(position).getHargal());
                intent.putExtra("jumlah",ldata.get(position).getJumlahl());
                lcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ldata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaProdukl, tvHargal,tvJumlahl ;
        CardView cvlunas;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNamaProdukl = (TextView) itemView.findViewById(R.id.tvNamaProdukL);
            tvHargal = (TextView) itemView.findViewById(R.id.tvHargaL);
            tvJumlahl = (TextView) itemView.findViewById(R.id.tvJumlahL);
            cvlunas = (CardView) itemView.findViewById(R.id.cvlunas);
        }
    }
}

