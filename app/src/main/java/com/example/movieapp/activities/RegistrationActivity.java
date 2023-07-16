package com.example.movieapp.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapp.init.AppManager;
import com.example.movieapp.init.MyRTFB;
import com.example.movieapp.R;
import com.example.movieapp.models.User;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Collections;

public class RegistrationActivity extends AppCompatActivity {

    private MaterialButton register_BTN;
    private EditText register_EDT_name;
    private EditText register_EDT_userName;
    private ArrayList<Chip> chipsCategories;
    private final int NUM_OF_CHIPS = 14;
    private ChipGroup chipGroup;
    private boolean userRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        findViews();
        initViews();


        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            login();
        } else {
            checkIfUserInMyServer();
        }

    }

    private void initViews() {
        register_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
                openApp();
            }
        });

    }

    private void findViews() {
        register_EDT_name = findViewById(R.id.register_EDT_name);
        register_EDT_userName = findViewById(R.id.register_EDT_userName);
        register_BTN = findViewById(R.id.register_BTN);
        chipGroup = findViewById(R.id.chipGroup);
        chipsCategories = new ArrayList<Chip>();
        for (int i = 1; i <= NUM_OF_CHIPS; i++) {
            int chipID = getResources().getIdentifier("chip" + i, "id", getPackageName());
            Chip currentChip = findViewById(chipID);
            chipsCategories.add(currentChip);
        }
    }

    private void registerUser() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            User user = new User()
                    .setId(firebaseUser.getUid())
                    .setPhone(firebaseUser.getPhoneNumber())
                    .setName(register_EDT_name.getText().toString())
                    .setUserName(register_EDT_userName.getText().toString());
            for (int i = 0; i < NUM_OF_CHIPS; i++) {
                if (chipsCategories.get(i).isChecked()) {
                    user.addCategory(chipsCategories.get(i).getText().toString());
                }
            }
            AppManager.getInstance().setLoggedIn(user);
            MyRTFB.saveNewUser(user);
        } else {
            Log.d("faillll", " firebase failed!!!");
        }
    }

    private ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(),
            (result) -> {
                IdpResponse response = result.getIdpResponse();

                if (result.getResultCode() == RESULT_OK) {
                    checkIfUserInMyServer();
                } else {
                    // Sign in failed
                    if (response == null) {
                        // User pressed back button
                        showSnackbar("Sign in cancelled");
                        return;
                    }

                    if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                        showSnackbar("No internet connection");
                        return;
                    }

                    showSnackbar("Unknown error");
                }
            });


    private void login() {
        final Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setIsSmartLockEnabled(false)
                .setAlwaysShowSignInMethodScreen(true)
                .setAvailableProviders(Collections.singletonList(
                        new AuthUI.IdpConfig.PhoneBuilder().build()
                ))
                .setLogo(R.mipmap.ic_launcher)
                .build();


        signInLauncher.launch(signInIntent);
    }

    private void openApp() {
//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference movieRef = db.getReference("MOVIES");
//        ArrayList<Movie> allMovies = DBManager.allMovies();
//        for (Movie movie:allMovies){
//            movieRef.child(movie.getName()).setValue(movie);
//        }
            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            finish();

    }


    private void checkIfUserInMyServer() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;
        MyRTFB.getUserData(firebaseUser.getUid(), user -> {
            if (user == null) {
                Log.d("checkuser - null", "hi");
                //  registerUser();
            } else if (user.getName() == null || user.getUserName() == null || user.getName().isEmpty() || user.getUserName().isEmpty()) {
                //  registerUser();
                Log.d("checkuser - 2", "hi");


            } else {
                Log.d("checkuser - else", "hi");

                Toast.makeText(RegistrationActivity.this, "Welcome back " + user.getName(), Toast.LENGTH_LONG).show();
                AppManager.getInstance().setLoggedIn(user);
                openApp();
            }
        });
    }


    private void showSnackbar(String message) {
        Snackbar
                .make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setAction("OK", null)
                .show();
    }
}