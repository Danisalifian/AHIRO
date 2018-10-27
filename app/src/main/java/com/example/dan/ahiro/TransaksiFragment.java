package com.example.dan.ahiro;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dan.ahiro.adapter.ViewPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class TransaksiFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar toolbar;


    public TransaksiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transaksi, container, false);

        toolbar = (Toolbar)v.findViewById(R.id.tbTransaksi);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        toolbar.setTitle("Transaksi");
        activity.setSupportActionBar(toolbar);

        tabLayout = (TabLayout)v.findViewById(R.id.tabLayout);
        viewPager = (ViewPager)v.findViewById(R.id.vPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        //Menambahkan fragment
        adapter.AddFragment(new TabBelumLunas(),"Belum Lunas");
        adapter.AddFragment(new TabLunas(),"Lunas");
        //setup adapter
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return v;
    }



}
