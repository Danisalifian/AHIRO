package com.example.dan.ahiro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.dan.ahiro.adapter.keranjangAdapter;
import com.example.dan.ahiro.Model.Keranjang;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;



/**
 * A simple {@link Fragment} subclass.
 */
public class KeranjangFragment extends Fragment {

    private Toolbar toolbar;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Keranjang> options;
    FirebaseRecyclerAdapter<Keranjang, keranjangAdapter> adapter;
    Button btnLanjut;

    public KeranjangFragment() {
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
        View v = inflater.inflate(R.layout.fragment_keranjang, container, false);
        setHasOptionsMenu(true);

        toolbar = (Toolbar) v.findViewById(R.id.tbKeranjang);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Carts")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        RecyclerView rvKeranjang = (RecyclerView) v.findViewById(R.id.rvKeranjang);
        btnLanjut = v.findViewById(R.id.btnLanjut);

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Keranjang");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), OrderActivity.class));
            }
        });

        //Recycler View
        options = new FirebaseRecyclerOptions.Builder<Keranjang>()
                .setQuery(databaseReference, Keranjang.class).build();
        adapter = new FirebaseRecyclerAdapter<Keranjang,keranjangAdapter>(options) {
            @Override
            protected void onBindViewHolder(keranjangAdapter holder, final int position, final Keranjang model) {
                holder.tvNamaProduk.setText(model.getProductname());
                holder.tvHarga.setText("Rp. " +model.getPrice());
                holder.tvQty.setText(model.getQuantity());
                holder.tvSubtotal.setText("Rp. " + model.getSubtotal());
                Picasso.get().load(model.getImage()).into(holder.ivGambar, new Callback() {

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

                holder.btnhapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                        alert.setTitle("Pemberitahuan");
                        alert.setMessage("Apakah anda yakin ingin menghapus ?");
                        alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //hapus
                                FirebaseDatabase.getInstance().getReference().child("Carts")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .child(adapter.getRef(position).getKey()).removeValue();
                            }
                        });
                        alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                Toast.makeText(getContext(), "Batal Menghapus",Toast.LENGTH_SHORT).show();
                            }
                        });
                        alert.show();

//                        Toast.makeText(getContext(), "Clicked",Toast.LENGTH_SHORT).show();
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
            public keranjangAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_keranjang,parent,false);

                return new keranjangAdapter(view);
            }
        };

        rvKeranjang.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),1);
        rvKeranjang.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        rvKeranjang.setAdapter(adapter);
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