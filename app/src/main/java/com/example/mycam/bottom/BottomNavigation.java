package com.example.mycam.bottom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.mycam.R;
import com.example.mycam.bottom.Tools.SharedPrefManager;
import com.example.mycam.bottom.ui.admin.AdminFragment;
import com.example.mycam.bottom.ui.admin.ListPemesanan;
import com.example.mycam.bottom.ui.kategori.KategoriFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class BottomNavigation extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);
        sharedPrefManager = new SharedPrefManager(this);
        loadFragment(new KategoriFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                if (sharedPrefManager.getSPSudahLogin()){
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_kategori:
                            fragment = new KategoriFragment();
                            break;
                        case R.id.navigation_admin:
                            fragment = new ListPemesanan();
                            break;
                    }
                }else{
                    switch (menuItem.getItemId()) {
                        case R.id.navigation_kategori:
                            fragment = new KategoriFragment();
                            break;
                        case R.id.navigation_admin:
                            fragment = new AdminFragment();
                            break;
                    }
                }

                return loadFragment(fragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.logout){
            sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, "");
            sharedPrefManager.saveSPString(SharedPrefManager.SP_NOHP, "");
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
            startActivity(new Intent(getApplicationContext(), BottomNavigation.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        }

        return true;
    }




}
