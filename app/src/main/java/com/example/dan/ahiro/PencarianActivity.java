package com.example.dan.ahiro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dan.ahiro.Model.Produk;
import com.example.dan.ahiro.adapter.produkAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PencarianActivity extends AppCompatActivity {

    Toolbar Ptoolbar;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Produk> options;
    FirebaseRecyclerAdapter<Produk, produkAdapter> adapter;
    RecyclerView rvCari;
    ImageButton btnCari;
    EditText textcari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencarian);

        Ptoolbar = findViewById(R.id.Ptoolbar);
        setSupportActionBar(Ptoolbar);
        setTitle("Pencarian");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Products");
        textcari = findViewById(R.id.textcari);
        rvCari = findViewById(R.id.rvCari);
        btnCari = findViewById(R.id.btnCari);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();

                String katakunci = textcari.getText().toString();

                Searching(katakunci);
            }
        });

//        Searching();
    }

    private void Searching(String katakunci){

        Query querycari = databaseReference.orderByChild("productname").startAt(katakunci).endAt(katakunci + "\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<Produk>()
                .setQuery(querycari,Produk.class).build();

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
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

                holder.btnOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String productname = model.getProductname();
                        String description = model.getDescription();
                        String weight = model.getWeight();
                        String price = model.getPrice();
                        String stock = model.getStock();

                        Intent intent = new Intent(getApplicationContext(), DetailProductActivity.class);
                        intent.putExtra("productId",adapter.getRef(position).getKey());//passing productId
                        intent.putExtra("productname", productname);
                        intent.putExtra("description", description);
                        intent.putExtra("weight", weight);
                        intent.putExtra("price", price);
                        intent.putExtra("stock", stock);
                        startActivity(intent);
                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(getContext(),"You clicked view", Toast.LENGTH_SHORT).show();
                        String productname = model.getProductname();
                        String description = model.getDescription();
                        String weight = model.getWeight();
                        String price = model.getPrice();
                        String stock = model.getStock();

                        Intent intent = new Intent(getApplicationContext(), DetailProductActivity.class);
                        intent.putExtra("productId",adapter.getRef(position).getKey());//passing productId
                        intent.putExtra("productname", productname);
                        intent.putExtra("description", description);
                        intent.putExtra("weight", weight);
                        intent.putExtra("price", price);
                        intent.putExtra("stock", stock);
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

        rvCari.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        rvCari.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        rvCari.setAdapter(adapter);
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
