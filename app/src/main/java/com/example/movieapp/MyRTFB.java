package com.example.movieapp;

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
        ref.child(user.getId()).setValue(user);
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

    private static void getGamesFromServer(CB_GamesArray cb_gamesArray) {
        if (cb_gamesArray == null) {
            return;
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gamesRef = database.getReference("GAMES");

        gamesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<Game> games = new ArrayList<>();
//                for (DataSnapshot child : snapshot.getChildren()) {
//                    Game game = child.getValue(Game.class);
//                    games.add(game);
//                }
//                cb_gamesArray.data(games);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("pttt", "onCancelled(" + error.getMessage() + ")");
//                cb_gamesArray.data(null);
            }
        });
    }

    public static void getGamesWithLikes(String userId, CB_GamesArray cb_gamesArray) {
        getUserData(userId, new CB_User() {
            @Override
            public void data(User user) {
                if (user == null) {
                    return;
                }

//                getGamesFromServer(new CB_GamesArray() {
//                    @Override
//                    public void data(ArrayList<Game> games) {
//                        if (games == null) {
//                            cb_gamesArray.data(null);
//                            return;
//                        }
//                        for (Game game : games) {
//                            boolean isLikedByCurrentUser = user.getLikes().getOrDefault(game.getTitle(), false);
//                            game.setLiked(isLikedByCurrentUser);
//                        }
//                        cb_gamesArray.data(games);
//                    }
//                });
            }
        });
    }

    public static void updateGameLike(String id, boolean value) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gamesRef = database.getReference("GAMES");
        gamesRef.child(id).child("liked").setValue(value);
    }
}
