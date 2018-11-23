package com.example.dan.ahiro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import com.example.dan.ahiro.adapter.belumlunasAdapter;
import com.example.dan.ahiro.model.BelumLunas;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabLunas extends Fragment {

    List<BelumLunas> LunasList;
    private Toolbar toolbar;
    String[] list;

    private static final String TAG = "Tab Lunas";

    public TabLunas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_tab_lunas, container, false);
        setHasOptionsMenu(true);



        list = new String[]{"Produk 1", "Produk 2", "Aproduk 1", "Aproduk 2"};

        LunasList = new ArrayList<>();
        LunasList.add(new BelumLunas("Produk1","100000","20","vit","21"));
        LunasList.add(new BelumLunas("Produk2","100000","20","vit","21"));
        LunasList.add(new BelumLunas("Produk3","100000","20","vit","21"));
        LunasList.add(new BelumLunas("Produk4","100000","20","vit","21"));
        LunasList.add(new BelumLunas("Produk5","100000","20","vit","21"));
        LunasList.add(new BelumLunas("Produk6","100000","20","vit","21"));
        LunasList.add(new BelumLunas("Produk7","100000","20","vit","21"));

        RecyclerView rvlunas = (RecyclerView)v.findViewById(R.id.rvlunas);
        belumlunasAdapter myAdapter = new belumlunasAdapter(getContext(),LunasList);
        rvlunas.setLayoutManager(new GridLayoutManager(getContext(),1));
        rvlunas.setAdapter(myAdapter);

        return v;
    }

}
