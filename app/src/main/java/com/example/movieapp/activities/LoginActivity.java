package com.example.movieapp.activities;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.movieapp.AppManager;
import com.example.movieapp.DBManager;
import com.example.movieapp.MyRTFB;
import com.example.movieapp.R;
import com.example.movieapp.models.Movie;
import com.example.movieapp.models.User;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {

//    private MaterialButton login_BTN_verify, login_BTN_generate;
//    private EditText login_EDT_phoneNum, login_EDT_otp;
//    private AppManager appManager;
//    private FirebaseAuth mAuth;
//    private String mVerificationId;

    private MaterialButton register_BTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register_BTN = findViewById(R.id.register_BTN);
        register_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApp();
            }
        });
        register_BTN.setEnabled(false);



        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser == null) {
            login();
        } else {
            checkIfUserInMyServer();
        }

    }

    private void registerUser() {
        EditText editTextText = findViewById(R.id.register_EDT_name);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        User user = new User()
                .setId(firebaseUser.getUid())
                .setPhone(firebaseUser.getPhoneNumber())
                .setName(editTextText.getText().toString());

        MyRTFB.saveNewUser(user);

        register_BTN.setEnabled(true);
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