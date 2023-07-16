package com.example.movieapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.activities.ActiveSessionActivity;
import com.example.movieapp.adapters.Adapter_Session_List;
import com.example.movieapp.databinding.FragmentSessionsBinding;
import com.example.movieapp.init.AppManager;
import com.example.movieapp.models.Group;
import com.example.movieapp.models.Session;
import com.example.movieapp.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SessionsFragment extends Fragment implements Adapter_Session_List.OnItemClickListener {
    private ArrayList<Session> mySessions = new ArrayList<>();
    private RecyclerView sess_LST_groups;
    private int currentPosition;
    private boolean checked = false;

    public SessionsFragment() {
    }

    private FragmentSessionsBinding binding;
    private Adapter_Session_List adapter = new Adapter_Session_List(mySessions);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sessions, container, false);
        // Inflate the layout for this fragment
        findViews(view);
        setSessionListFromDB(view);
        updateList(view, mySessions);
        initViews();
        return view;
    }

    private void initViews() {
        adapter.setOnItemClickListener(this);
    }

    private void updateList(View view, ArrayList<Session> mySessions) {
        adapter.updateList(mySessions);
    }

    private void setSessionListFromDB(View view) {
//            if (AppManager.getInstance().getLoggedIn()==null) return;
        User user = AppManager.getInstance().getLoggedIn();
        ArrayList<Group> myGroups = user.getGroups();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference groupsRef = database.getReference("GROUPS");

        groupsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()) {
                    for (Group group : myGroups) {
                        if (snap.getKey().equals(group.getId())) {
                            DatabaseReference sessRef =
                                    groupsRef.child(group.getId()).child("SESSIONS");
                            sessRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    for (DataSnapshot snaps : snapshot.getChildren()) {
                                        if (snaps.hasChild("isDone")) {
                                            if (!snaps.child("isDone").getValue(Boolean.class)) {
                                                Session session = snaps.getValue(Session.class);
                                                session.setGroup(group);
                                                session.setSize((int) snap.child("userIDs").getChildrenCount());
                                                DataSnapshot likedSnapshot = snaps.child("LIKED");
                                                if (likedSnapshot.exists()) {
                                                    HashMap<String, Integer> likedMap = new HashMap<>();
                                                    for (DataSnapshot likedItemSnapshot : likedSnapshot.getChildren()) {
                                                        String movieName = likedItemSnapshot.getKey();
                                                        int numberOfLikes = likedItemSnapshot.getValue(Integer.class);
                                                        likedMap.put(movieName, numberOfLikes);
                                                    }
                                                    session.setLikedMovies(likedMap);
                                                }
                                                mySessions.add(session);
                                                initList(view);
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    private void findViews(View view) {
        sess_LST_groups = view.findViewById(R.id.sess_LST_groups);
    }

    private void initList(View view) {
        sess_LST_groups.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        sess_LST_groups.setHasFixedSize(true);
        sess_LST_groups.setAdapter(adapter);

    }

    public void onItemClick(int position) {
        Session clickedSession = mySessions.get(position);
        AppManager.getInstance().setCurrentSession(clickedSession);
        AppManager.getInstance().setCurentGroup(clickedSession.getGroup());
        startActivity(new Intent(getActivity(), ActiveSessionActivity.class));

    }
}