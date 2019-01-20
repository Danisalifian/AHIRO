package com.example.dan.ahiro;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.example.dan.ahiro.Model.Keranjang;
import com.example.dan.ahiro.adapter.keranjangAdapter;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class OrderActivity extends AppCompatActivity {

    Toolbar toolbar;
    String productId;
    RecyclerView rvListorder;
    MaterialEditText metRecipient, metPhone, metAddress;
    TextView tvTotalbayar;
    DatabaseReference databaseReference, carts, orders;
    FirebaseRecyclerOptions<Keranjang> options;
    FirebaseRecyclerAdapter<Keranjang, keranjangAdapter> adapter;
    Button btnRorder;

    private AlertDialog dialog;
    GenericTypeIndicator<List<Keranjang>> genericTypeIndicator;
    List<Keranjang> keranjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        toolbar = findViewById(R.id.OToolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        productId = getIntent().getStringExtra("productId");
        rvListorder = findViewById(R.id.rvListorder);
        metRecipient = findViewById(R.id.metRecipient);
        metAddress = findViewById(R.id.metAddress);
        metPhone = findViewById(R.id.metPhone);
        btnRorder = findViewById(R.id.btnRorder);
        tvTotalbayar = findViewById(R.id.tvTotalbayar);
        dialog = new SpotsDialog.Builder().setContext(OrderActivity.this).build();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Carts")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        rvListorder = findViewById(R.id.rvListorder);

        getDetailOrder();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int sum = 0;

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Map<String, Object> map = (Map<String, Object>)ds.getValue();
                    Object subtotal = map.get("subtotal");

                    int pValue = Integer.parseInt(String.valueOf(subtotal));
                    sum += pValue;

                    tvTotalbayar.setText(String.valueOf(sum));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnRorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(metRecipient.getText().toString().trim())){
                    Toast.makeText(OrderActivity.this,"Silahkan isi nama penerima", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(metPhone.getText().toString().trim())){
                    Toast.makeText(OrderActivity.this, "Silahkan isi nomor telepon", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(metAddress.getText().toString().trim())){
                    Toast.makeText(OrderActivity.this,"Silahkan isi Alamat penerima", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.show();
                    SimpanOrder();
                }
            }
        });

    }

    private void getDetailOrder() {

        options = new FirebaseRecyclerOptions.Builder<Keranjang>()
                .setQuery(databaseReference, Keranjang.class).build();
        adapter = new FirebaseRecyclerAdapter<Keranjang, keranjangAdapter>(options) {
            @Override
            protected void onBindViewHolder(keranjangAdapter holder, final int position, final Keranjang model) {
                holder.tvNamaProduk.setText(model.getProductname());
                holder.tvHarga.setText("Rp. " +model.getPrice());
                holder.tvQty.setText(model.getQuantity());
                Picasso.get().load(model.getImage()).into(holder.ivGambar, new Callback() {

                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Toast.makeText(getApplicationContext(),"You clicked view", Toast.LENGTH_SHORT).show();
                        String productname = model.getProductname();
                        String price = model.getPrice();
                        String quantity = model.getQuantity();
                        String subtotal = model.getSubtotal();

                        Intent intent = new Intent(getApplicationContext(), DetailProductActivity.class);
                        intent.putExtra("productId",adapter.getRef(position).getKey());//passing productId
                        intent.putExtra("productname", productname);
                        intent.putExtra("price", price);
                        intent.putExtra("quantity",quantity);
                        intent.putExtra("subtotal",subtotal);
                        startActivity(intent);
                    }
                });

                holder.btnhapus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        AlertDialog.Builder alert = new AlertDialog.Builder(OrderActivity.this);
                        alert.setTitle("Pemberitahuan");
                        alert.setMessage("Apakah anda yakin ingin menghapus ?");
                        alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //hapus data
                                FirebaseDatabase.getInstance().getReference().child("Carts")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .child(adapter.getRef(position).getKey()).removeValue();
                            }
                        });
                        alert.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        alert.show();
                    }

                });
            }

            @Override
            public keranjangAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_keranjang,parent,false);

                return new keranjangAdapter(view);
            }
        };

        rvListorder.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvListorder.setLayoutManager(gridLayoutManager);
        adapter.startListening();
        rvListorder.setAdapter(adapter);

    }

    private void SimpanOrder(){
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        carts = FirebaseDatabase.getInstance().getReference().child("Carts");
        orders = FirebaseDatabase.getInstance().getReference().child("Orders").child(uid);

        carts.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final DatabaseReference newOrder = orders.push();
                List<Keranjang> listkeranjang = new ArrayList<>();

                int i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    Keranjang Mkeranjang = ds.getValue(Keranjang.class);
                    listkeranjang.add(Mkeranjang);
                }

                final Map orderMap = new HashMap();
                orderMap.put("recipient", metRecipient.getText().toString().trim());
                orderMap.put("shipaddress", metAddress.getText().toString().trim());
                orderMap.put("phone", metPhone.getText().toString().trim());
                orderMap.put("status","belum lunas");
                orderMap.put("productfee", tvTotalbayar.getText().toString().trim());
                orderMap.put("shipmentfee", "0");
                orderMap.put("products",listkeranjang);
                orderMap.put("information", "menuggu kalkulasi biaya kirim");
                int productfee = Integer.parseInt(tvTotalbayar.getText().toString().trim());
                int totalpayment = productfee + 0;
                orderMap.put("totalpayment", Integer.toString(totalpayment));
                orderMap.put("timestamp", ServerValue.TIMESTAMP);

                Thread mainThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        newOrder.setValue(orderMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){

                                    AlertDialog.Builder alert = new AlertDialog.Builder(OrderActivity.this);
                                    alert.setTitle("Pemberitahuan");
                                    alert.setMessage("Permintaan order berhasil dikirim");
                                    alert.setNeutralButton("Kembali ke beranda", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //hapus keranjang setelah order disimpan
                                            FirebaseDatabase.getInstance().getReference().child("Carts")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();

                                            startActivity(new Intent(OrderActivity.this, MainActivity.class));
                                        }
                                    });
                                    dialog.dismiss();
                                    alert.setCancelable(false);
                                    alert.show();

                                } else {
                                    Toast.makeText(OrderActivity.this, "Error" + task.getException()
                                            .getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

                mainThread.start();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("productId", productId);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default :
                return super.onOptionsItemSelected(item);
        }
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
