package com.example.ezhoop.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import android.widget.Button;

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
    private FirebaseAuth firebaseAuth;
    private Context context;

    private BottomNavigationView navBottom;
    private final Fragment fragmentHome = new HomeFragment();
    private final Fragment fragmentStatistics = new StatisticsFragment();
    private final Fragment fragmentProfile = new ProfileFragment();
    private final FragmentManager fm = getSupportFragmentManager();
    private Fragment active = fragmentHome;

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

        fm.beginTransaction().add(R.id.fragment_container, fragmentProfile, "3").hide(fragmentProfile).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragmentStatistics, "2").hide(fragmentStatistics).commit();
        fm.beginTransaction().add(R.id.fragment_container, fragmentHome, "1").commit();

//        Button btnStartTraining = findViewById(R.id.btn_start_training);
//        btnStartTraining.setOnClickListener(v -> {
//            Intent intent = new Intent(context, StartTrainingActivity.class);
//            startActivity(intent);
//        });
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
        inflater.inflate(R.menu.menu_options, menu);
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

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            navigateToLogin();
        }
    }
}