package com.example.mycam;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.mycam.ui.Kategori.KategoriFragment;
import com.example.mycam.ui.admin.AdminFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_kategori, R.id.navigation_admin)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(this);
        openFragment(new KategoriFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_kategori:
                openFragment(new KategoriFragment());
                return true;
            case R.id.navigation_admin:
                openFragment(new AdminFragment());
                return true;

        }
        return false;
    }

    private void openFragment(Fragment fragment) {
        openFragment(fragment, false);
    }

    private void openFragment(Fragment fragment, boolean addToBackstack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (addToBackstack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
