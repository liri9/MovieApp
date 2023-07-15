package com.example.movieapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.movieapp.R;
import com.example.movieapp.databinding.FragmentSessionsBinding;

public class SessionsFragment extends Fragment {

    public SessionsFragment(){}
    private FragmentSessionsBinding binding;

    public static SessionsFragment newInstance(String param1, String param2) {
        SessionsFragment fragment = new SessionsFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sessions, container, false);
    }
}