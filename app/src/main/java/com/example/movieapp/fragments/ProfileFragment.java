package com.example.movieapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.movieapp.R;
import com.example.movieapp.databinding.FragmentProfileBinding;
import com.example.movieapp.init.AppManager;
import com.example.movieapp.models.User;
import com.google.android.material.textview.MaterialTextView;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private MaterialTextView profile_TXT_userName, profile_TXT_phone, profile_TXT_name;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        findViews(view);
        initViews();
        return view;

    }

    private void findViews(View view) {
        profile_TXT_name =view.findViewById(R.id.profile_TXT_name);
        profile_TXT_userName =view.findViewById(R.id.profile_TXT_userName);
        profile_TXT_phone =view.findViewById(R.id.profile_TXT_phone);

        User user = AppManager.getInstance().getLoggedIn();
        profile_TXT_name.setText(user.getName());
        profile_TXT_phone.setText(user.getPhone());
        profile_TXT_userName.setText(user.getUserName());

    }

    private void initViews() {

    }
}