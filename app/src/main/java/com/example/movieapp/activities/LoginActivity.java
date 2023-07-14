package com.example.movieapp.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

//    private MaterialButton login_BTN_verify, login_BTN_generate;
//    private EditText login_EDT_phoneNum, login_EDT_otp;
//    private AppManager appManager;
//    private FirebaseAuth mAuth;
//    private String mVerificationId;

    private MaterialButton register_BTN;
    private EditText register_EDT_name;
    private EditText register_EDT_userName;
    private ArrayList<Chip> chipsCategories;
    private final int NUM_OF_CHIPS = 14;
    private ChipGroup chipGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

                openApp();
            }
        });
        // register_BTN.setEnabled(false);

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
        //   register_BTN.setEnabled(true);
        if (firebaseUser != null) {
            User user = new User()
                    .setId(firebaseUser.getUid())
                    .setPhone(firebaseUser.getPhoneNumber())
                    .setName(register_EDT_name.getText().toString())
                    .setUserName(register_EDT_userName.getText().toString());
            for (int i = 0; i < NUM_OF_CHIPS; i++) {
                if (chipsCategories.get(i).isChecked()) {
                    user.addCategory(chipsCategories.get(i).toString());
                }
            }
            MyRTFB.saveNewUser(user);
        } else {
            Log.d("faillll", "liri firebase failed!!!");
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
                    Log.e("pttt", "Sign-in error: ", response.getError());
                }
            });


    private void login() {
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(Arrays.asList(
                        new AuthUI.IdpConfig.PhoneBuilder().build()
                ))
                .build();

//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference movieRef = db.getReference("MOVIES");
//        ArrayList<Movie> allMovies = DBManager.allMovies();
//        for (Movie movie:allMovies){
//            movieRef.child(movie.getName()).setValue(movie);
//        }
        signInLauncher.launch(signInIntent);
    }

    private void openApp() {

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void checkIfUserInMyServer() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        MyRTFB.getUserData(firebaseUser.getUid(), new MyRTFB.CB_User() {
            @Override
            public void data(User user) {
                if (user == null) {
                    registerUser();
                } else {
                    Toast.makeText(LoginActivity.this, "Welcome back " + user.getName(), Toast.LENGTH_LONG).show();
                    openApp();
                }
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