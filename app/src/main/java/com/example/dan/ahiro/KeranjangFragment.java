package com.example.dan.ahiro;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.ahiro.adapter.keranjangAdapter;
import com.example.dan.ahiro.model.Keranjang;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class KeranjangFragment extends Fragment {

    List<Keranjang> keranjangList;
    private Toolbar toolbar;
    String[] list ;


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

        toolbar = (Toolbar)v.findViewById(R.id.tbKeranjang);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        toolbar.setTitle("Keranjang");
        activity.setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        list = new String[]{"Produk 1", "Produk 2", "Aproduk 1", "Aproduk 2"};


        keranjangList = new ArrayList<>();
        keranjangList.add(new Keranjang("Keranjang1","10000","20"));
        keranjangList.add(new Keranjang("Keranjang2","10000","20"));
        keranjangList.add(new Keranjang("Keranjang3","10000","20"));
        keranjangList.add(new Keranjang("Keranjang4","10000","20"));
        keranjangList.add(new Keranjang("Keranjang5","10000","20"));
        keranjangList.add(new Keranjang("Keranjang6","10000","20"));
        keranjangList.add(new Keranjang("Keranjang7","10000","20"));
        keranjangList.add(new Keranjang("Keranjang8","10000","20"));

        RecyclerView rvKeranjang =(RecyclerView)v.findViewById(R.id.rvKeranjang);
        keranjangAdapter myAdapter =new keranjangAdapter(getContext(),keranjangList);
        rvKeranjang.setLayoutManager(new GridLayoutManager(getContext(),1));
        rvKeranjang.setAdapter(myAdapter);


        return v;
    }


}
