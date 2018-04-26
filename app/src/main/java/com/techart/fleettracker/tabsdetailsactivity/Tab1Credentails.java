package com.techart.fleettracker.tabsdetailsactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.techart.fleettracker.PostKey;
import com.techart.fleettracker.R;
import com.techart.fleettracker.models.Fleet;
import com.techart.fleettracker.utils.FireBaseUtils;
import com.techart.fleettracker.utils.TimeUtils;


public class Tab1Credentails extends Fragment {
    private TextView tvDriver;
    private TextView tvPlate;
    private TextView tvEntry;
    private TextView tvDestination;
    private TextView tvLastLocation;
    private SharedPreferences mPref;
    private String postKey;
    private SharedPreferences.Editor editor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_credentials, container, false);
        tvDriver = rootView.findViewById(R.id.tv_driver);
        tvPlate = rootView.findViewById(R.id.tv_plate);
        tvEntry = rootView.findViewById(R.id.tv_entry);
        tvDestination = rootView.findViewById(R.id.tv_destination);
        tvLastLocation = rootView.findViewById(R.id.tv_lastLocation);

        loadData();
        return rootView;
    }

    private void loadData(){
        if (!PostKey.postKey.equals(null)){
            FireBaseUtils.mDatabaseFleet.child(PostKey.postKey).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Fleet fleet = dataSnapshot.getValue(Fleet.class);
                    if (fleet != null) {
                        tvDriver.setText(fleet.getDriver());
                        tvPlate.setText(fleet.getPlateNumber());
                        tvEntry.setText(fleet.getFrom());
                        tvDestination.setText(fleet.getTo());
                        tvLastLocation.setText(fleet.getLastLocation());
                        String time = TimeUtils.timeElapsed(fleet.getLastOnLine());
                        tvLastLocation.setText(getString(R.string.last_location,fleet.getLastLocation(),time));

                    } else {

                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        } else {
            Toast.makeText(getContext(),"No Url found",Toast.LENGTH_LONG).show();
        }

    }

}
