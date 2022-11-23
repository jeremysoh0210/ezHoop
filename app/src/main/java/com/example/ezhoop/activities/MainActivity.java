package com.example.ezhoop.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.ezhoop.App;
import com.example.ezhoop.R;
import com.example.ezhoop.fragments.HomeFragment;
import com.example.ezhoop.fragments.ProfileFragment;
import com.example.ezhoop.fragments.StatisticsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    private Context context;
    private FirebaseAuth firebaseAuth;
    public FirebaseUser currentUser;

    private BottomNavigationView navBottom;
    private Fragment fragmentHome;
    private Fragment fragmentStatistics;
    private Fragment fragmentProfile;
    private final FragmentManager fm = getSupportFragmentManager();
    private Fragment active = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = ((App) getApplicationContext()).firebaseAuth;
        context = this;

        setupViews();
    }

    private void setupViews() {
        navBottom = findViewById(R.id.nav_bottom);
        navBottom.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                fm.beginTransaction().hide(active).show(fragmentHome).commit();
                active = fragmentHome;
                return true;

            case R.id.nav_statistics:
                fm.beginTransaction().hide(active).show(fragmentStatistics).commit();
                active = fragmentStatistics;
                return true;

            case R.id.nav_profile:
                fm.beginTransaction().hide(active).show(fragmentProfile).commit();
                active = fragmentProfile;
                return true;
        }
        return false;
    }

    private void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_top_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.btn_logout) {
            firebaseAuth.signOut();
            navigateToLogin();
        }
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void onStart() {
        super.onStart();

        currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            navigateToLogin();
        } else {
            if (active == null) {
                fragmentHome = new HomeFragment();
                fragmentStatistics = new StatisticsFragment();
                fragmentProfile = new ProfileFragment();

                fm.beginTransaction().add(R.id.fragment_container, fragmentProfile, "3").hide(fragmentProfile).commit();
                fm.beginTransaction().add(R.id.fragment_container, fragmentStatistics, "2").hide(fragmentStatistics).commit();
                fm.beginTransaction().add(R.id.fragment_container, fragmentHome, "1").commit();

                active = fragmentHome;
            }
        }
    }
}