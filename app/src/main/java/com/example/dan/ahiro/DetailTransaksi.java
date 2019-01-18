package com.example.dan.ahiro;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dan.ahiro.Model.Keranjang;
import com.example.dan.ahiro.Model.Order;
import com.example.dan.ahiro.adapter.ItemOrderAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailTransaksi extends AppCompatActivity {

    TextView dtNama, dtTanggal, dtStatus, dtSubtotalProduk, dtBiayaKirim, dtTotalBayar, dtAlamat, dtTelepon;
    RecyclerView rvItemOrder;
    Toolbar mToolbar;

    private String orderId = "";

    FirebaseDatabase database;
    FirebaseAuth mAuth;
    DatabaseReference Orders;
    FirebaseRecyclerOptions<Keranjang> options;
    FirebaseRecyclerAdapter<Keranjang, ItemOrderAdapter> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);

        dtNama = findViewById(R.id.dtNama);
        dtTanggal = findViewById(R.id.dtTanggal);
        dtStatus = findViewById(R.id.dtStatus);
        dtSubtotalProduk = findViewById(R.id.dtSubtotalProduk);
        dtBiayaKirim = findViewById(R.id.dtBiayaKirim);
        dtTotalBayar = findViewById(R.id.dtTotalbayar);
        dtAlamat = findViewById(R.id.dtAlamat);
        dtTelepon = findViewById(R.id.dtTelepon);
        mToolbar = findViewById(R.id.mToolbar);
        rvItemOrder = findViewById(R.id.rvItemOrder);

        mToolbar.setTitle("Detail Transaksi");
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Firebase
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        Orders = database.getReference().child("Orders").child(mAuth.getCurrentUser().getUid());

        //get orderId
        if (getIntent() != null)
            orderId = getIntent().getStringExtra("orderId");
        if (!orderId.isEmpty()){
            getDetailTransaksi(orderId);
        }

        //Query produk untuk tiap order
        Query query = Orders.child(orderId).child("products");
        //RecylcerView
        options = new FirebaseRecyclerOptions.Builder<Keranjang>()
                .setQuery(query, Keranjang.class).build();
        adapter = new FirebaseRecyclerAdapter<Keranjang, ItemOrderAdapter>(options) {
            @Override
            protected void onBindViewHolder(ItemOrderAdapter holder, int position, Keranjang model) {
                holder.tvItemNamaProduk.setText(model.getProductname());
                holder.tvItemHarga.setText("Rp. " + model.getPrice());
                holder.tvItemQty.setText(model.getQuantity());
                holder.tvItemSubtotal.setText("Rp. " + model.getSubtotal());
            }

            @Override
            public ItemOrderAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_order,parent,false);

                return new ItemOrderAdapter(view);
            }
        };

        rvItemOrder.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvItemOrder.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        rvItemOrder.setAdapter(adapter);
    }

    private void getDetailTransaksi(String orderId) {
        Orders.child(orderId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Order order = dataSnapshot.getValue(Order.class);
                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                dtNama.setText(order.getRecipient());
                dtTelepon.setText(order.getPhone());
                dtTanggal.setText(sfd.format(new Date(order.getTimestamp())));
                dtStatus.setText(order.getStatus());
                dtSubtotalProduk.setText("Rp. " + order.getProductfee());
                dtBiayaKirim.setText("Rp. " + order.getShipmentfee());
                dtTotalBayar.setText("Rp. " + order.getTotalpayment());
                dtAlamat.setText(order.getShipaddress());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
