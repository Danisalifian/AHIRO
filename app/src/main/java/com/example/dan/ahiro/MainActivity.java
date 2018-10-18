package com.example.dan.ahiro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    private BottomBar NavBottom;
    private FrameLayout mMainFrame;

    private BerandaFragment berandaFragment;
    private KeranjangFragment keranjangFragment;
    private TransaksiFragment transaksiFragment;
    private AkunFragment akunFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavBottom = (BottomBar)findViewById(R.id.NavBottom);
        mMainFrame = (FrameLayout)findViewById(R.id.main_frame);

        berandaFragment = new BerandaFragment();
        keranjangFragment = new KeranjangFragment();
        transaksiFragment = new TransaksiFragment();
        akunFragment = new AkunFragment();

        NavBottom.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                if (tabId == R.id.nav_beranda){
                    setFragment(berandaFragment);
                } else if (tabId == R.id.nav_keranjang){
                    setFragment(keranjangFragment);
                } else if(tabId == R.id.nav_transaksi){
                    setFragment(transaksiFragment);
                } else if (tabId == R.id.nav_akun){
                    setFragment(akunFragment);
                }
            }
        });
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
