package com.example.movieapp.init;

import com.example.movieapp.models.Group;
import com.example.movieapp.models.User;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyRTFB {

    public interface CB_GamesArray {
//        void data(ArrayList<Game> games);
    }

    public interface CB_User {
        void data(User user);
    }

    public static void setGameLikePerUser(String userId, String gameId, boolean value) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS");
        ref.child(userId).child("likes").child(gameId).setValue(value);
    }

    public static void saveNewUser(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS");
        ref.child(user.getId()).setValue(user.userAsHashmap());
    }

    public static void saveNewGroup(Group group) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("GROUPS");
         ref.child(group.getId()).setValue(group.groupAsHashmap());
    }

    public static void saveNewUserToGroup(User user, Group group) {
        //todo
    }

    public static void getUserByUserName(String userName, CB_User cb_user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS");
        ref.orderByKey().addListenerForSingleValueEvent
                (new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                            User user = userSnapshot.getValue(User.class);
                            if (user.getUserName().equals(userName)) {
                                cb_user.data(user);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Handle the error
                        System.out.println("Error: " + databaseError.getMessage());
                    }
                });
    }

    public static void getUserData(String id, CB_User cb_user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS");
        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    User user = snapshot.getValue(User.class);
                    cb_user.data(user);
                } catch (Exception ex) {
                    cb_user.data(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                cb_user.data(null);
            }
        });
    }


    public static void updateGameLike(String id, boolean value) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gamesRef = database.getReference("GAMES");
        gamesRef.child(id).child("liked").setValue(value);
    }
}
