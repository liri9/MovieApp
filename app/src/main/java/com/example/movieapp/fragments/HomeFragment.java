package com.example.movieapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.adapters.Adapter_Group_List;
import com.example.movieapp.adapters.Adapter_MovieCard;
import com.example.movieapp.databinding.FragmentHomeBinding;
import com.example.movieapp.init.AppManager;
import com.example.movieapp.models.Group;
import com.example.movieapp.models.Movie;
import com.example.movieapp.models.User;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class HomeFragment extends Fragment {

    private RecyclerView main_LST_groups;
    private EditText main_EDT_grpName, main_EDT_userName;
    private MaterialButton main_BTN_addGroup, main_BTN_addUser, main_BTN_exit;
    private CardView main_CRD_newGroup;
    private String groupName;
    private ArrayList<Group> myGroups = new ArrayList<>();
    private ArrayList<String> userNames = new ArrayList<>();
    private User user = AppManager.getInstance().getLoggedIn();
    private Adapter_Group_List adapter = new Adapter_Group_List(myGroups);


    public HomeFragment() {
    }

    private FragmentHomeBinding binding;

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
        setGroupListFromDB(view);
        updateList(view, myGroups);
        initViews();
        return view;
    }

    private void updateList(View view, ArrayList<Group> myGroups) {
    }

    private void setGroupListFromDB(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("USERS").child(user.getId()).child("GROUPS");
        Log.d("myref", usersRef.toString());
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    String groupId = snap.getValue(String.class);
                    DatabaseReference groupRef = database.getReference("GROUPS").child(groupId);
                    Log.d("groupref", groupRef.toString());
                    groupRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Group group = snapshot.getValue(Group.class);
                            myGroups.add(group);
                            Log.d("mygroups1", myGroups.toString());
                            initList(view);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle onCancelled
                        }

                    });
                    Log.d("mygroups2", myGroups.toString());

                }
                Log.d("mygroups3", myGroups.toString());

//                initList(view);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void initList(View view) {
        main_LST_groups.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        main_LST_groups.setHasFixedSize(true);
        main_LST_groups.setAdapter(adapter);

    }

    private void initViews() {

        main_BTN_addGroup.setOnClickListener(v -> {
            main_CRD_newGroup.setVisibility(View.VISIBLE);
        });


        main_BTN_exit.setOnClickListener(v -> {
            main_CRD_newGroup.setVisibility(View.INVISIBLE);
            main_EDT_grpName.getText().clear();
            Group group = new Group("")
                    .setName(groupName)
                    .setUsersToDB(userNames);
            Log.d("usernames",userNames.get(0));
            group.updateGroupInFB();
        });

        main_BTN_addUser.setOnClickListener((v -> {
            //todo check if empty and if user exists or double username
            groupName = main_EDT_grpName.getText().toString();
            userNames.add(main_EDT_userName.getText().toString());
            main_EDT_userName.getText().clear();
        }));


    }

    private void findViews(View view) {
        main_BTN_addGroup = view.findViewById(R.id.main_BTN_addGroup);
        main_LST_groups = view.findViewById(R.id.main_LST_groups);
        main_BTN_addUser = view.findViewById(R.id.main_BTN_addUser);
        main_EDT_userName = view.findViewById(R.id.main_EDT_userName);
        main_EDT_grpName = view.findViewById(R.id.main_EDT_grpName);
        main_CRD_newGroup = view.findViewById(R.id.main_CRD_newGroup);
        main_BTN_exit = view.findViewById(R.id.main_BTN_exit);
    }
}