package com.example.dan.ahiro;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class KeranjangFragment extends Fragment {

    private Toolbar toolbar;

    public KeranjangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_keranjang, container, false);

        toolbar = (Toolbar)v.findViewById(R.id.tbKeranjang);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        toolbar.setTitle("Keranjang");
        activity.setSupportActionBar(toolbar);

        return v;
    }

}
