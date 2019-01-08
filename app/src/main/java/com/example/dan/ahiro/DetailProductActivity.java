package com.example.dan.ahiro;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.dan.ahiro.Model.Produk;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class DetailProductActivity extends AppCompatActivity {

    private TextView tvDProduk, tvDDeskripsi, tvDBerat, tvDHarga, tvDStok;
    private TextView dialogNamaProduk, dialogHarga, dialogJumlah, dialogSubtotal;
    protected ImageView ivGambar, orderClose;
    ElegantNumberButton numberButton;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    private Dialog order_dialog;
    Button btnDOrder, btnLanjutbayar, btnOrderlagi;

    private String productId = "";

    FirebaseDatabase database;
    FirebaseAuth mAuth;
    DatabaseReference products, carts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        toolbar = findViewById(R.id.Dtoolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Firebase
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        products = database.getReference().child("Products");
        carts = database.getReference().child("Carts").child(mAuth.getCurrentUser().getUid());

        //Init View
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        tvDProduk = findViewById(R.id.tvDProduk);
        tvDDeskripsi = findViewById(R.id.tvDDeskripsi);
        tvDBerat = findViewById(R.id.tvDBerat);
        tvDHarga = findViewById(R.id.tvDHarga);
        tvDStok = findViewById(R.id.tvDStok);
        ivGambar = findViewById(R.id.ivGambar);
        numberButton = findViewById(R.id.number_button);
        order_dialog = new Dialog(this);
        btnDOrder = findViewById(R.id.btnDOrder);

//        collapsingToolbarLayout.setExpandedTitleColor(R.style.ExpandedAppbar);
//        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppbar);

        //get Id from intent
        if (getIntent() != null)
            productId = getIntent().getStringExtra("productId");
        if (!productId.isEmpty()){
            getDetailProduct(productId);
        }

        btnDOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpanKeranjang(productId);
            }
        });
    }

    private void showOderdialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.order_dialog, null);

        final AlertDialog alertDialog = new AlertDialog.Builder(this).setView(v).create();

        orderClose = v.findViewById(R.id.orderClose);
        btnOrderlagi = v.findViewById(R.id.btnOrderlagi);
        btnLanjutbayar = v.findViewById(R.id.btnLanjutbayar);

        dialogNamaProduk = v.findViewById(R.id.dialogNamaProduk);
        dialogHarga = v.findViewById(R.id.dialogHarga);
        dialogJumlah = v.findViewById(R.id.dialogJumlah);
        dialogSubtotal = v.findViewById(R.id.dialogSubtotal);

        Intent i = getIntent();
        String Namaproduk = i.getExtras().getString("productname");
        String Harga = i.getExtras().getString("price");

        dialogNamaProduk.setText(Namaproduk);
        dialogHarga.setText("Rp. " + Harga);
        dialogJumlah.setText(numberButton.getNumber());

        int jumlah = Integer.parseInt(numberButton.getNumber());
        int harga = Integer.parseInt(Harga);
        int Subtotal = harga * jumlah;

        dialogSubtotal.setText("Rp. " + Integer.toString(Subtotal));

        orderClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        btnLanjutbayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                intent.putExtra("productId", productId);
                startActivityForResult(intent,999);
            }
        });

        btnOrderlagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 999 && resultCode == RESULT_OK){
            productId = data.getStringExtra("productId");
        }
    }

    private void getDetailProduct(String productId) {
        products.child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Produk produk = dataSnapshot.getValue(Produk.class);

                //Receive Data
                Intent intent = getIntent();
                String productname = intent.getExtras().getString("productname");
                String description = intent.getExtras().getString("description");
                String weight = intent.getExtras().getString("weight");
                String price = intent.getExtras().getString("price");
                String stock = intent.getExtras().getString("stock");

                //Set Image
                Picasso.get().load(produk.getImage()).into(ivGambar);
                collapsingToolbarLayout.setTitle(produk.getProductname());

                //Set values
                tvDProduk.setText(productname);
                tvDDeskripsi.setText(description);
                tvDBerat.setText(weight);
                tvDHarga.setText("Rp. " + price);
                tvDStok.setText(stock);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void SimpanKeranjang(final String productId){
        products.child(productId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final DatabaseReference newCart = carts.child(productId);

                Intent Sintent = getIntent();
                String Sproductname = Sintent.getExtras().getString("productname");
                String Sprice = Sintent.getExtras().getString("price");

                int jumlah = Integer.parseInt(numberButton.getNumber());
                int harga = Integer.parseInt(Sprice);
                int Subtotal = harga * jumlah;

                final Map cartMap = new HashMap();
                cartMap.put("productname", Sproductname);
                cartMap.put("price", Sprice);
                cartMap.put("quantity", numberButton.getNumber());
                cartMap.put("subtotal", Integer.toString(Subtotal));

                Thread mainThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        newCart.setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(DetailProductActivity.this, "Ditambahkan ke keranjang"
                                            , Toast.LENGTH_SHORT).show();
                                    showOderdialog();
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
}
