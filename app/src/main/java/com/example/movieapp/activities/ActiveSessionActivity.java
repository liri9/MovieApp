package com.example.movieapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

public class ActiveSessionActivity extends AppCompatActivity implements Adapter_MovieCard.OnActionListener{

    private RecyclerView movie_card_view;
    private MaterialTextView active_TXT_groupName;
    private TextView match_crd_lbl;
    private MaterialCardView match_crd;
    private MaterialButton match_BTN_yes, match_BTN_no;
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
                // Log.d("all movies", movieList.toString());

                Collections.shuffle(movieList);
                initList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

    private void initViews() {
        active_TXT_groupName.setText(AppManager.getInstance().getCurentGroup().getName());
        adapter.setOnActionListener(this);

        match_BTN_yes.setOnClickListener(v->{
            match_crd.setVisibility(View.INVISIBLE);
        });

        match_BTN_no.setOnClickListener(v->{
            AppManager.getInstance().getCurrentSession().finish();
            finish();
        });
    }

    private void findViews() {
        active_TXT_groupName = findViewById(R.id.active_TXT_groupName);
        movie_card_view = findViewById(R.id.movie_card_view);
        match_crd_lbl = findViewById(R.id.match_crd_lbl);
        match_crd = findViewById(R.id.match_crd);
        match_BTN_yes = findViewById(R.id.match_BTN_yes);
        match_BTN_no = findViewById(R.id.match_BTN_no);
    }


    private void showSnackbar(String message) {
        Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction("OK", null)
                .show();
    }

    @Override
    public void onMatch(Movie item) {
        match_crd.setVisibility(View.VISIBLE);
        match_crd_lbl.setText(item.getName());
    }
}