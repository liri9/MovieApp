package com.example.movieapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapp.DBManager;
import com.example.movieapp.R;
import com.example.movieapp.adapters.Adapter_MovieCard;
import com.example.movieapp.init.AppManager;
import com.example.movieapp.init.MyRTFB;
import com.example.movieapp.models.Movie;
import com.example.movieapp.models.User;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ActiveSessionActivity extends AppCompatActivity {

    private RecyclerView movie_card_view;
    private MaterialTextView active_TXT_groupName;
    private ArrayList<Movie> movieList = new ArrayList<Movie>();
    private Adapter_MovieCard adapter = new Adapter_MovieCard(movieList);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_session);
        findViews();
        setMovieListFromDB();
        initViews();
        updateList(movieList);
    }

    private void updateList(ArrayList<Movie> movieList) {
        adapter.updateList(movieList);
    }

    private void initList() {
        movie_card_view.setLayoutManager(new LinearLayoutManager(this));
        movie_card_view.setHasFixedSize(true);
        movie_card_view.setAdapter(adapter);
        Log.d("movies init", movieList.toString());

    }

    private void setMovieListFromDB() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference moviesRef = database.getReference("MOVIES");
        moviesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    Movie movie = child.getValue(Movie.class);
                    movieList.add(movie);
                }
                Log.d("all movies", movieList.toString());

                Collections.shuffle(movieList);
                initList();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void initViews() {

    }

    private void findViews() {
        active_TXT_groupName = findViewById(R.id.active_TXT_groupName);
        movie_card_view = findViewById(R.id.movie_card_view);
    }


    private void showSnackbar(String message) {
        Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction("OK", null)
                .show();
    }
}