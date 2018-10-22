package com.example.dan.ahiro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.ahiro.model.Produk;
import com.example.dan.ahiro.adapter.produkAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {

    List<Produk> produkList;


    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_beranda, container, false);

        produkList = new ArrayList<>();
        produkList.add(new Produk("Produk 1", "10000"));
        produkList.add(new Produk("Produk 2", "20000"));
        produkList.add(new Produk("Produk 3", "30000"));
        produkList.add(new Produk("Produk 4", "40000"));
        produkList.add(new Produk("Produk 5", "50000"));
        produkList.add(new Produk("Produk 6", "60000"));
        produkList.add(new Produk("Produk 7", "70000"));
        produkList.add(new Produk("Produk 8", "80000"));

        RecyclerView rvProduk = (RecyclerView)v.findViewById(R.id.rvProduk);
        produkAdapter myAdapter = new produkAdapter(getContext(),produkList);
        rvProduk.setLayoutManager(new GridLayoutManager(getContext(),2));
        rvProduk.setAdapter(myAdapter);

        return v;
    }

}
