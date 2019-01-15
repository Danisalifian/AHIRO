package com.example.dan.ahiro;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dan.ahiro.Model.Produk;
import com.example.dan.ahiro.adapter.produkAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment{

    private Toolbar toolbar;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Produk> options;
    FirebaseRecyclerAdapter<Produk, produkAdapter> adapter;
    RecyclerView rvProduk;

    public BerandaFragment() {
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
        View v = inflater.inflate(R.layout.fragment_beranda, container, false);
        setHasOptionsMenu(true);

        toolbar = (Toolbar)v.findViewById(R.id.tbBeranda);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Products");
        rvProduk = (RecyclerView)v.findViewById(R.id.rvProduk);

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Beranda");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        //Recycler View
        options = new FirebaseRecyclerOptions.Builder<Produk>()
                .setQuery(databaseReference,Produk.class).build();
        adapter = new FirebaseRecyclerAdapter<Produk, produkAdapter>(options) {
            @Override
            protected void onBindViewHolder(produkAdapter holder, final int position, final Produk model) {
                holder.tvNamaProduk.setText(model.getProductname());
                holder.tvHarga.setText("Rp. " + model.getPrice());
                Picasso.get().load(model.getImage()).into(holder.ivGambar, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

                holder.btnOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getContext(), DetailProductActivity.class);
                        intent.putExtra("productId",adapter.getRef(position).getKey());//passing productId
                        startActivity(intent);
                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getContext(), DetailProductActivity.class);
                        intent.putExtra("productId",adapter.getRef(position).getKey());//passing productId
                        startActivity(intent);
                    }
                });

            }

            @Override
            public produkAdapter onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_produk,parent,false);

                return new produkAdapter(view);
            }
        };

        rvProduk.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        rvProduk.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        rvProduk.setAdapter(adapter);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                startActivity(new Intent(getContext(), PencarianActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
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
