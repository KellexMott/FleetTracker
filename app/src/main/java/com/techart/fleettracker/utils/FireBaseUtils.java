package com.techart.fleettracker.utils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Has constants for Fire base variable names
 * Created by Kelvin on 11/09/2017.
 */

public final class FireBaseUtils {

   public static DatabaseReference mDatabaseFleet = FirebaseDatabase.getInstance().getReference().child(Constants.FLEET_KEY);
   public static DatabaseReference mDatabaseComment = FirebaseDatabase.getInstance().getReference().child(Constants.COMMENTS_KEY);
   public static DatabaseReference mDatabaseViews = FirebaseDatabase.getInstance().getReference().child(Constants.VIEWS_KEY);
   public static DatabaseReference mDatabaseUsers = FirebaseDatabase.getInstance().getReference().child(Constants.USERS);
   public static DatabaseReference mDatabaseLibrary = FirebaseDatabase.getInstance().getReference().child(Constants.LIBRARY);
  /* public static FirebaseAuth mAuth  = FirebaseAuth.getInstance();
   private static FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();*/



    private FireBaseUtils() {

    }
/*
    public static void subscribeToNewPostNotification(){
        mSubscriptions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (FireBaseUtils.mAuth.getCurrentUser() != null && !dataSnapshot.hasChild(getUiD()))  {
                    mSubscriptions.child(getUiD()).setValue(Constants.NEW_POST_SUBSCRIPTION);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void isComplete(String status, DatabaseReference databaseReference) {
        if (status.equals("Complete")) {
            databaseReference.keepSynced(false);
        } else {
            databaseReference.keepSynced(true);
        }
    }

    public static String getAuthor() {
        return user != null ? user.getDisplayName() : "User"; //(a > b) ? a : b
    }

    @NonNull
    public static String getUiD() {
        return mAuth.getCurrentUser().getUid();
    }

    public static void updateStatus(String status, String post_key) {
        mDatabaseFleet.child(post_key).child(Constants.STORY_STATUS).setValue(status);
    }

    public static void updateCategory(String category, String post_key) {
        mDatabaseFleet.child(post_key).child(Constants.STORY_CATEGORY).setValue(category);
    }

    public static void addStoryLike(Fleet model, String post_key) {
        Map<String,Object> values = new HashMap<>();
        values.put(Constants.AUTHOR_URL,getUiD());
        values.put(Constants.USER, FireBaseUtils.getAuthor());
        values.put(Constants.POEM_KEY, post_key);
        values.put(Constants.TIME_CREATED, ServerValue.TIMESTAMP);
        mDatabaseLike.child(post_key).child(getUiD()).setValue(values);
    }*/
}
