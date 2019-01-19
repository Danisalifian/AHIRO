package com.example.dan.ahiro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import dmax.dialog.SpotsDialog;

public class LoginActivity extends Activity {

//    private DatabaseReference dbReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button btnLogin;
    private MaterialEditText metEmail, metPassword;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        };

        btnLogin = findViewById(R.id.btnLogin);
        metEmail = findViewById(R.id.metEmail);
        metPassword = findViewById(R.id.metPassword);
        dialog = new SpotsDialog.Builder().setContext(LoginActivity.this).build();
        dialog.setTitle("Mengautentikasi...");

        //Init Firebase
//        dbReference = FirebaseDatabase.getInstance().getReference("Users");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                userLogin();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void userLogin(){

        String email = metEmail.getText().toString();
        String password = metPassword.getText().toString();

        if (TextUtils.isEmpty(email.trim())) {
            Snackbar.make(btnLogin, "Silahkan isi email", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password.trim())) {
            Snackbar.make(btnLogin, "Silahkan isi kata sandi", Snackbar.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()){
                        dialog.dismiss();
                        Snackbar.make(btnLogin,"gagal autentikasi", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
