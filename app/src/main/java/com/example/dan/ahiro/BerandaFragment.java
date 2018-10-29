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

import com.example.dan.ahiro.model.Produk;
import com.example.dan.ahiro.adapter.produkAdapter;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment{

    List<Produk> produkList;
    private Toolbar toolbar;
    String[] list;
    MaterialSearchView searchView;

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

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("Beranda");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));

        list = new String[]{"Produk 1", "Produk 2", "Aproduk 1", "Aproduk 2"};

        searchView = (MaterialSearchView)v.findViewById(R.id.search_view);
        searchView.closeSearch();
        searchView.setSuggestions(list);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        produkList = new ArrayList<>();
        produkList.add(new Produk("Produk 1", "deskripsi 1","10","20000","20"));
        produkList.add(new Produk("Produk 2", "deskripsi 2","10","20000","20"));
        produkList.add(new Produk("Produk 3", "deskripsi 3","10","20000","20"));
        produkList.add(new Produk("Produk 4", "deskripsi 4","10","20000","20"));
        produkList.add(new Produk("Produk 5", "deskripsi 5","10","20000","20"));
        produkList.add(new Produk("Produk 6", "deskripsi 6","10","20000","20"));
        produkList.add(new Produk("Produk 7", "deskripsi 7","10","20000","20"));
        produkList.add(new Produk("Produk 8", "deskripsi 8","10","20000","20"));

        RecyclerView rvProduk = (RecyclerView)v.findViewById(R.id.rvProduk);
        produkAdapter myAdapter = new produkAdapter(getContext(),produkList);
        rvProduk.setLayoutManager(new GridLayoutManager(getContext(),2));
        rvProduk.setAdapter(myAdapter);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        searchView = (MaterialSearchView)getActivity().findViewById(R.id.search_view);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
//        super.onCreateOptionsMenu(menu, inflater);
    }
}
