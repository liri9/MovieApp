package com.example.movieapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.movieapp.R;
import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.fragments.HomeFragment;
import com.example.movieapp.fragments.ProfileFragment;
import com.example.movieapp.fragments.SessionsFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(R.layout.activity_main);
        setContentView(binding.getRoot());
        replaceFragment(new ProfileFragment());
        initViews();
    }

    private void initViews() {
        binding.navView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.navigation_profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.navigation_sessions:
                    replaceFragment(new SessionsFragment());
                    break;
            }

            return true;
        });
    }
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragManager = getSupportFragmentManager();
        FragmentTransaction fragTransaction = fragManager.beginTransaction();
        fragTransaction.replace(R.id.nav_main, fragment);
        fragTransaction.commit();
    }
}