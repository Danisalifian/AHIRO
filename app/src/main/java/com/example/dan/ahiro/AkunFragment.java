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
public class AkunFragment extends Fragment {

    private Toolbar toolbar;

    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_akun, container, false);

        toolbar = (Toolbar)v.findViewById(R.id.tbAkun);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        toolbar.setTitle("Pengaturan Akun");
        activity.setSupportActionBar(toolbar);

        return v;
    }

}
