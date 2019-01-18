package com.example.dan.ahiro;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.ahiro.Model.Order;
import com.example.dan.ahiro.adapter.OrderAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabBelumLunas extends Fragment {

    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Order> options;
    FirebaseRecyclerAdapter<Order, OrderAdapter> adapter;
    RecyclerView rvBelumlunas;

    public TabBelumLunas() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tab_belum_lunas,container,false);
        setHasOptionsMenu(true);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders").child(uid);
        Query query = databaseReference.orderByChild("status").equalTo("belum lunas");
        rvBelumlunas = v.findViewById(R.id.rvBelumlunas);

        //Recycler View
        options = new FirebaseRecyclerOptions.Builder<Order>()
                .setQuery(query, Order.class).build();
        adapter = new FirebaseRecyclerAdapter<Order, OrderAdapter>(options) {
            @Override
            protected void onBindViewHolder(OrderAdapter holder, final int position, Order model) {
                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                holder.tvTanggal.setText(sfd.format(new Date(model.getTimestamp())));
                holder.tvPenerima.setText(model.getRecipient());
                holder.tvHargaproduk.setText("Rp. " + model.getProductfee());
                holder.tvBiayakirim.setText("Rp. " + model.getShipmentfee());
                holder.tvTotalbayar.setText("Rp. " + model.getTotalpayment());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getContext(), DetailTransaksi.class);
                        intent.putExtra("orderId", adapter.getRef(position).getKey());//passing orderId
                        startActivity(intent);
                    }
                });
            }

            @Override
            public OrderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_transaksi, parent,false);

                return new OrderAdapter(view);
            }
        };

        rvBelumlunas.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rvBelumlunas.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        rvBelumlunas.setAdapter(adapter);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null){
            adapter.startListening();
        }
    }

    @Override
    public void onStop() {
        if (adapter != null){
            adapter.stopListening();
        }
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter != null){
            adapter.startListening();
        }
    }
}
