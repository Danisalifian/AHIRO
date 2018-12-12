package com.example.dan.ahiro;


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

import com.example.dan.ahiro.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;



/**
 * A simple {@link Fragment} subclass.
 */
public class AkunFragment extends Fragment {

    private Toolbar toolbar;
    private TextView tvName, tvGender, tvPhone, tvEmail;
    private MaterialEditText metAddress, metUPassword;
    private String uid;
    private Button btnLogout;

    private static final String TAG = "AkunFragment";

    public AkunFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_akun, container, false);

        tvName = v.findViewById(R.id.tvName);
        tvEmail = v.findViewById(R.id.tvEmail);
        tvGender = v.findViewById(R.id.tvGender);
        tvPhone = v.findViewById(R.id.tvPhone);
        metAddress = v.findViewById(R.id.metAddress);
        metUPassword = v.findViewById(R.id.metUPassword);
        btnLogout = v.findViewById(R.id.btnLogout);

        //get userId
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Query Select * from Users where id="uid"
        Query query = FirebaseDatabase.getInstance().getReference("Users").orderByChild("uid")
                .equalTo(uid);
        query.addValueEventListener(valueEventListener);

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

        return v;
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
                tvName.setText(user.getName());
                tvGender.setText(user.getGender());
                tvPhone.setText(user.getPhone());
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
