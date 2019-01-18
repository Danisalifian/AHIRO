package com.example.dan.ahiro;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dan.ahiro.Model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {

    private Toolbar toolbar;
    private TextView tvEmail;
    private MaterialEditText metAddress, metUPassword, metName, metGender, metPhone;
    private String uid;
    private Button btnLogout, btnalmPerbarui;
    DatabaseReference agenDB;

    private static final String TAG = "AkunFragment";

    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_akun, container, false);

        metName = v.findViewById(R.id.metName);
        tvEmail = v.findViewById(R.id.tvEmail);
        metGender = v.findViewById(R.id.metGender);
        metPhone = v.findViewById(R.id.metPhone);
        metAddress = v.findViewById(R.id.metAddress);
        metUPassword = v.findViewById(R.id.metUPassword);
        btnLogout = v.findViewById(R.id.btnLogout);
        btnalmPerbarui = v.findViewById(R.id.almPerbarui);

        //get userId
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Query Select * from Users where id="uid"
        Query query = FirebaseDatabase.getInstance().getReference().child("Users").orderByChild("uid")
                .equalTo(uid);
        query.addValueEventListener(valueEventListener);

        agenDB = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        toolbar = (Toolbar)v.findViewById(R.id.tbAkun);
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        toolbar.setTitle("Pengaturan Akun");
        activity.setSupportActionBar(toolbar);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), LoginActivity.class));

            }
        });

        btnalmPerbarui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Pemberitahuan");
                alert.setMessage("Apakah anda yakin ingin menyimpan pembaruan ?");
                alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        perbaruiAgen();
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

        return v;
    }

    private void perbaruiAgen() {
        String Uaddress = metAddress.getText().toString().trim();
        String Uname = metName.getText().toString().trim();
        String Uphone = metPhone.getText().toString().trim();

        Map updateMap = new HashMap();
        updateMap.put("address", Uaddress);
        updateMap.put("name", Uname);
        updateMap.put("phone", Uphone);

        agenDB.updateChildren(updateMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(getContext(), "Data telah diperbarui", Toast.LENGTH_SHORT).show();
            }
        });
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot ds : dataSnapshot.getChildren()){
                User user = new User();
                user.setName(ds.getValue(User.class).getName());
                user.setGender(ds.getValue(User.class).getGender());
                user.setAddress(ds.getValue(User.class).getAddress());
                user.setArea(ds.getValue(User.class).getArea());
                user.setEmail(ds.getValue(User.class).getEmail());
                user.setPhone(ds.getValue(User.class).getPhone());
                user.setPassword(ds.getValue(User.class).getPassword());

//                Log.d(TAG,"name:" + user.getName());
                metName.setText(user.getName());
                metGender.setText(user.getGender());
                metPhone.setText(user.getPhone());
                tvEmail.setText(user.getEmail());
                metAddress.setText(user.getAddress());
                metUPassword.setText(user.getPassword());
            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
        }
    };

}
