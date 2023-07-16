package com.example.movieapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.R;
import com.example.movieapp.adapters.Adapter_Categories_List;
import com.example.movieapp.adapters.Adapter_Group_List;
import com.example.movieapp.databinding.FragmentProfileBinding;
import com.example.movieapp.init.AppManager;
import com.example.movieapp.models.User;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private MaterialTextView profile_TXT_userName, profile_TXT_phone, profile_TXT_name;
    private MaterialButton profile_BTN_logout;
    private RecyclerView profile_LST_categories;
    private ArrayList<String> categories = new ArrayList<>();
    private Adapter_Categories_List adapter = new Adapter_Categories_List(categories);


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
        getCategoriesFromDB(view);
        findViews(view);
        updateList(view, categories);
        initViews();
        return view;

    }

    private void updateList(View view, ArrayList<String> categories) {
    }

    private void getCategoriesFromDB(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("USERS")
                .child(AppManager.getInstance().getLoggedIn().getId()).child("categories");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap : snapshot.getChildren()){
                    categories.add(snap.getValue().toString());
                    Log.d("categories1", categories.toString());
                    Log.d("categories2", snap.getValue().toString());
                }
                initList(view);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void initList(View view) {
        profile_LST_categories.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        profile_LST_categories.setHasFixedSize(true);
        profile_LST_categories.setAdapter(adapter);

    }

    private void findViews(View view) {
        profile_TXT_name =view.findViewById(R.id.profile_TXT_name);
        profile_TXT_userName =view.findViewById(R.id.profile_TXT_userName);
        profile_TXT_phone =view.findViewById(R.id.profile_TXT_phone);
        profile_BTN_logout = view.findViewById(R.id.profile_BTN_logout);
        profile_LST_categories = view.findViewById(R.id.profile_LST_categories);

        User user = AppManager.getInstance().getLoggedIn();
        profile_TXT_name.setText(user.getName());
        profile_TXT_phone.setText(user.getPhone());
        profile_TXT_userName.setText(user.getUserName());


    }

    private void initViews() {
        profile_BTN_logout.setOnClickListener(v->{
            AppManager.getInstance().setLoggedIn(null);
            FirebaseAuth mAuth=FirebaseAuth.getInstance();
            mAuth.signOut();
            getActivity().finish();
        });

    }
}