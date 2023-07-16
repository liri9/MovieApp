package com.example.movieapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.activities.ActiveSessionActivity;
import com.example.movieapp.activities.MainActivity;
import com.example.movieapp.activities.RegistrationActivity;
import com.example.movieapp.adapters.Adapter_Group_List;
import com.example.movieapp.databinding.FragmentHomeBinding;
import com.example.movieapp.init.AppManager;
import com.example.movieapp.init.MyRTFB;
import com.example.movieapp.models.Group;
import com.example.movieapp.models.Session;
import com.example.movieapp.models.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements Adapter_Group_List.OnItemClickListener {

    private RecyclerView main_LST_groups;
    private EditText main_EDT_grpName, main_EDT_userName;
    private MaterialButton main_BTN_addGroup, main_BTN_addUser,
            main_BTN_exit, mainGroup_BTN_exit, mainGroup_BTN_exitGroup, mainGroup_BTN_newSession;
    private MaterialCardView main_CRD_newGroup, main_CRD_Group;
    private String groupName;
    private MaterialTextView mainGroup_LBL_grpName;

    private Fragment_List fragment_list;
    private int currentPosition;
    //users_LST

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
        adapter.updateList(myGroups);

    }

    private void setGroupListFromDB(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("USERS")
                .child(user.getId()).
                child("GROUPS");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    String groupId = snap.getValue(String.class);
                    DatabaseReference groupRef = database.getReference("GROUPS").child(groupId);
                    groupRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Group group = snapshot.getValue(Group.class);
                            group.setUserIDs((ArrayList<String>)snapshot.child("userIDs").getValue());
                            myGroups.add(group);
                            user.addGroup(group);
                            initList(view);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle onCancelled
                        }

                    });

                }

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

        adapter.setOnItemClickListener(this);


        main_BTN_exit.setOnClickListener(v -> {
            main_CRD_newGroup.setVisibility(View.INVISIBLE);
            main_EDT_grpName.getText().clear();
            //todo checking if empty
            Group group = new Group("")
                    .setName(groupName)
                    .setUsersToDB(userNames);
            group.updateGroupInFB();
        });

        main_BTN_addUser.setOnClickListener((v -> {
            //todo check if empty and if user exists or double username
            groupName = main_EDT_grpName.getText().toString();
            userNames.add(main_EDT_userName.getText().toString());
            main_EDT_userName.getText().clear();
        }));


    }

    public void onItemClick(int position) {
        main_CRD_Group.setVisibility(View.VISIBLE);
        Group clickedGroup = myGroups.get(position);
        mainGroup_LBL_grpName.setText(clickedGroup.getName());
        currentPosition = position;
        initCardViews();

    }

    private void initCardViews() {
        fragment_list = new Fragment_List();
        fragment_list.setCallback_list(callback_list);
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .add(R.id.users_LST, fragment_list)
                .commit();

        mainGroup_BTN_exit.setOnClickListener(v -> {
            main_CRD_Group.setVisibility(View.INVISIBLE);
        });
        mainGroup_BTN_exitGroup.setOnClickListener(v -> {
            main_CRD_Group.setVisibility(View.INVISIBLE);
            MyRTFB.removeUserFromGroup(user,myGroups.get(currentPosition));
            user.removeFromGroup(myGroups.get(currentPosition));
            myGroups.get(currentPosition).removeUser(user);
        });
        mainGroup_BTN_newSession.setOnClickListener(v -> {
            Session currentSess = new Session(myGroups.get(currentPosition));
            AppManager.getInstance().setCurentGroup(myGroups.get(currentPosition));
            AppManager.getInstance().setCurrentSession(currentSess);
            MyRTFB.saveNewSession (myGroups.get(currentPosition),currentSess);
            startActivity(new Intent(getActivity(), ActiveSessionActivity.class));
        });
    }

    private void findViews(View view) {
        main_BTN_addGroup = view.findViewById(R.id.main_BTN_addGroup);
        main_LST_groups = view.findViewById(R.id.main_LST_groups);
        main_BTN_addUser = view.findViewById(R.id.main_BTN_addUser);
        main_EDT_userName = view.findViewById(R.id.main_EDT_userName);
        main_EDT_grpName = view.findViewById(R.id.main_EDT_grpName);
        main_CRD_newGroup = view.findViewById(R.id.main_CRD_newGroup);
        main_BTN_exit = view.findViewById(R.id.main_BTN_exit);
        main_CRD_Group = view.findViewById(R.id.main_CRD_Group);
        mainGroup_BTN_exit = view.findViewById(R.id.mainGroup_BTN_exit);
        mainGroup_LBL_grpName = view.findViewById(R.id.mainGroup_LBL_grpName);
        mainGroup_BTN_exitGroup = view.findViewById(R.id.mainGroup_BTN_exitGroup);
        mainGroup_BTN_newSession = view.findViewById(R.id.mainGroup_BTN_newSession);
    }

    public interface Callback_List {
        ArrayList<User> getGroupUsers();
    }

    Callback_List callback_list = new Callback_List() {
        @Override
        public ArrayList<User> getGroupUsers() {
            Log.d("allUsers", myGroups.get(currentPosition).groupAsHashmap().toString());
            return myGroups.get(currentPosition).getUsers();
        }
    };
}