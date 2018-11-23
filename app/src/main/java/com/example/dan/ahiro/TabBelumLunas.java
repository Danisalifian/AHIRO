package com.example.dan.ahiro;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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
public class TabBelumLunas extends Fragment {


    List<BelumLunas> belumLunasList;
    private Toolbar toolbar;
    String[] list;

    //private static final String TAG = "Tab Belum Lunas";

    public TabBelumLunas() {
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
        View v = inflater.inflate(R.layout.fragment_tab_belum_lunas,container,false);
        setHasOptionsMenu(true);



        list = new String[]{"Produk 1", "Produk 2", "Aproduk 1", "Aproduk 2"};

        belumLunasList = new ArrayList<>();
        belumLunasList.add(new BelumLunas("Produk1","20000","100","Vit","10"));
        belumLunasList.add(new BelumLunas("Produk2","20000","100","Vit","10"));
        belumLunasList.add(new BelumLunas("Produk3","20000","100","Vit","10"));
        belumLunasList.add(new BelumLunas("Produk4","20000","100","Vit","10"));
        belumLunasList.add(new BelumLunas("Produk5","20000","100","Vit","10"));
        belumLunasList.add(new BelumLunas("Produk6","20000","100","Vit","10"));

        RecyclerView rvBlunas = (RecyclerView)v.findViewById(R.id.rvBlunas);
        belumlunasAdapter myAdapter = new belumlunasAdapter(getContext(),belumLunasList);
        rvBlunas.setLayoutManager(new GridLayoutManager(getContext(),1));
        rvBlunas.setAdapter(myAdapter);

        return v;
    }

}
