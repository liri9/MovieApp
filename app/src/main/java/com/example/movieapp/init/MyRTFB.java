package com.example.movieapp.init;

import com.example.movieapp.models.Group;
import com.example.movieapp.models.Movie;
import com.example.movieapp.models.Session;
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



    public interface CB_MoviesArray {
        void data(ArrayList<Movie> movie);
    }

    public interface CB_GroupsArray {
        void data(ArrayList<Group> groups);
    }


    public interface CB_User {
        void data(User user);
    }

    public interface CB_Group {
        void data(Group group);
    }

    public static void setGameLikePerUser(String userId, String gameId, boolean value) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS");
        ref.child(userId).child("likes").child(gameId).setValue(value);
    }

    //    public ArrayList<Group> getGroupsPerUser(User user) {
//        //todo
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference usersRef = database.getReference("USERS").child(user.getId()).child("GROUPS");
//        ArrayList<Group> groups = new ArrayList<>();
//        usersRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    categories.add(dataSnapshot.getValue().toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return groups;
//    }
    public ArrayList<String> getLikedCategoriesPerUser(User user) {
        //todo
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("USERS").child(user.getId()).child("categories");
        ArrayList<String> categories = new ArrayList<>();
        usersRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    categories.add(dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return categories;
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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("GROUPS");
        ref.child(group.getId()).child("userIds").setValue(user.getId());
    }

    public static void saveNewGroupToUser(User user, Group group) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS");
//        ref.child(user.getId()).child("GROUPS").child();
        ref.child(user.getId()).child("GROUPS").setValue(group.getId(), user.getGroups());
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
                            boolean check= user.getUserName().trim().equals(userName.trim());
//                            String lettersBeforeSpace = userName.substring(0, userName.indexOf(" "));
                            if (check) {
                                Log.d("success???", user.userAsHashmap().toString());

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

    public static void setGroupsForUser(ArrayList<String> groups, User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("USERS")
                .child(user.getId()).child("GROUPS");
        for (String groupID : groups) {
            ref.child(groupID).setValue(groupID);
        }
    }
    public static void getGroupData(String id, CB_Group cb_group) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("GROUPS");
        ref.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    Group group = snapshot.getValue(Group.class);
                    cb_group.data(group);
                } catch (Exception ex) {
                    cb_group.data(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                cb_group.data(null);
            }
        });
    }

    public static void getUserGroups(String userId, CB_GroupsArray cb_groupsArray) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("USERS").child(userId);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<Group> groups = new ArrayList<>();

                    // Get the groups data from the "groups" node of the user
                    DataSnapshot groupsSnapshot = dataSnapshot.child("groups");
                    for (DataSnapshot groupSnapshot : groupsSnapshot.getChildren()) {
                        String groupId = groupSnapshot.getValue(String.class);
                        if (groupId != null) {
                            DatabaseReference groupRef = database.getReference("GROUPS").child(groupId);
                            groupRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        Group group = dataSnapshot.getValue(Group.class);
                                        if (group != null) {
                                            groups.add(group);
                                        }
                                    }

                                    // Check if all groups have been retrieved
                                    if (groups.size() == groupsSnapshot.getChildrenCount()) {
                                        cb_groupsArray.data(groups);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    // Handle the error
                                    cb_groupsArray.data(null);
                                }
                            });
                        }
                    }
                } else {
                    cb_groupsArray.data(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
                cb_groupsArray.data(null);
            }
        });
    }

    public static void updateGameLike(String id, boolean value) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference gamesRef = database.getReference("GAMES");
        gamesRef.child(id).child("liked").setValue(value);
    }


    public static void saveNewSession(Group group, Session currentSess) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference groupRef = database.getReference("GROUPS").child(group.getId()).child("SESSIONS");

    }
}
