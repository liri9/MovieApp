package com.example.movieapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.databinding.FragmentHomeBinding;
import com.example.movieapp.init.MyRTFB;
import com.example.movieapp.models.Group;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.HashSet;

public class HomeFragment extends Fragment {

    private RecyclerView main_LST_groups;
    private EditText main_EDT_grpName, main_EDT_userName;
    private MaterialButton main_BTN_addGroup, main_BTN_addUser, main_BTN_exit;
    private CardView main_CRD_newGroup;
    private String groupName;
    private ArrayList<String> userNames = new ArrayList<>();

    public HomeFragment() {
    }

    private FragmentHomeBinding binding;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Inflate the layout for this fragment
        findViews(view);
        initViews();
        return view;
    }

    private void initViews() {

        main_BTN_addGroup.setOnClickListener(v -> {
            main_CRD_newGroup.setVisibility(View.VISIBLE);
        });


        main_BTN_exit.setOnClickListener(v->{
            main_CRD_newGroup.setVisibility(View.INVISIBLE);
            main_EDT_grpName.getText().clear();
            Group group = new Group()
                    .setName(groupName)
                    .setUsers(userNames);
            MyRTFB.saveNewGroup(group);
        });

        main_BTN_addUser.setOnClickListener((v->{
            //todo check if empty and if user exists or double username
           groupName= main_EDT_grpName.getText().toString();
           userNames.add( main_EDT_userName.getText().toString());
           main_EDT_userName.getText().clear();
        }));


    }

    private void findViews(View view) {
        main_BTN_addGroup = view.findViewById(R.id.main_BTN_addGroup);
        main_LST_groups = view.findViewById(R.id.main_LST_groups);
        main_BTN_addUser= view.findViewById(R.id. main_BTN_addUser);
        main_EDT_userName = view.findViewById(R.id.main_EDT_userName);
        main_EDT_grpName = view.findViewById(R.id.main_EDT_grpName);
        main_CRD_newGroup =view.findViewById(R.id.main_CRD_newGroup);
        main_BTN_exit =view.findViewById(R.id.main_BTN_exit);
    }
}