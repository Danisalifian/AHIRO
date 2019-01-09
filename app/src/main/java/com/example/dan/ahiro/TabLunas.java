package com.example.dan.ahiro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.example.dan.ahiro.adapter.belumlunasAdapter;
import com.example.dan.ahiro.Model.Order;
import com.example.dan.ahiro.adapter.OrderAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabLunas extends Fragment {

    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Order> options;
    FirebaseRecyclerAdapter<Order, OrderAdapter> adapter;
    RecyclerView rvLunas;

    public TabLunas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_tab_lunas, container, false);
        setHasOptionsMenu(true);

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Orders").child(uid);
        Query query = databaseReference.orderByChild("status").equalTo("lunas");
        rvLunas = v.findViewById(R.id.rvLunas);

        options = new FirebaseRecyclerOptions.Builder<Order>()
                .setQuery(query, Order.class).build();
        adapter = new FirebaseRecyclerAdapter<Order, OrderAdapter>(options) {
            @Override
            protected void onBindViewHolder(OrderAdapter holder, int position, Order model) {
                holder.tvTanggal.setText(model.getTimestamp());
                holder.tvPenerima.setText(model.getRecipient());
                holder.tvHargaproduk.setText("Rp. " + model.getProductfee());
                holder.tvBiayakirim.setText("Rp. " + model.getShipmentfee());
                holder.tvTotalbayar.setText("Rp. " + model.getTotalpayment());
            }

            @Override
            public OrderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_transaksi, parent,false);

                return new OrderAdapter(view);
            }
        };

        rvLunas.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        rvLunas.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        rvLunas.setAdapter(adapter);

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
