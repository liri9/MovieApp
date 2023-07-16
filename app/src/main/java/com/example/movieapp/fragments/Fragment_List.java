package com.example.movieapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.movieapp.R;
import com.example.movieapp.adapters.Adapter_Users_List;
import com.example.movieapp.models.User;

import java.util.ArrayList;
import java.util.List;

public class Fragment_List extends Fragment {
    private ListView fragmentList_LIST_Users;
    private HomeFragment.Callback_List callback_list;
    private ArrayList<User> allUsers;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        findViews(view);
        initViews();
        return view;
    }

    public void setCallback_list(HomeFragment.Callback_List callback_list) {
        this.callback_list = callback_list;
    }

    private void initViews() {

        allUsers  = callback_list.getGroupUsers();

        if (allUsers != null) {
            Adapter_Users_List adapter = new Adapter_Users_List(getActivity(),
                    android.R.layout.simple_list_item_1, allUsers);
            fragmentList_LIST_Users.setAdapter(adapter);

        }
        fragmentList_LIST_Users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = (User) adapterView.getItemAtPosition(i);
            }
        });
        allUsers = callback_list.getGroupUsers();
        if (allUsers != null) {
            Adapter_Users_List adapter = new Adapter_Users_List(getActivity(),
                    android.R.layout.simple_list_item_1, allUsers);
            fragmentList_LIST_Users.setAdapter(adapter);
        }
//        List<User> updatedUsers = callback_list.getGroupUsers();
//        if (updatedUsers != null) {
//            Adapter_Users_List adapter = new Adapter_Users_List(getActivity(),
//                    android.R.layout.simple_list_item_1, allUsers);
//
//            adapter.updateData(updatedUsers);
//        }
    }


    private void findViews(View view) {
        fragmentList_LIST_Users = view.findViewById(R.id.fragmentList_LIST_Users);
    }



}