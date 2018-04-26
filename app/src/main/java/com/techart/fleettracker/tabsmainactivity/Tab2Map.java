package com.techart.fleettracker.tabsmainactivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.techart.fleettracker.Detail;
import com.techart.fleettracker.PostKey;
import com.techart.fleettracker.R;
import com.techart.fleettracker.models.Fleet;
import com.techart.fleettracker.utils.FireBaseUtils;


public class Tab2Map extends Fragment{

    MapView mMapView;
    double userLat = 15.492559;
    double userLon;
    Integer trackCount;
    private GoogleMap googleMap;
    private int contents;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately
        /*
        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
        populateMap();

        return rootView;
    }


    private void populateMap() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading chapters");
        progressDialog.setCancelable(true);
        progressDialog.show();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                final LatLngBounds.Builder builder = new LatLngBounds.Builder();


                // For showing a move to my location button
                //googleMap.setMyLocationEnabled(true);
                FireBaseUtils.mDatabaseFleet.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        trackCount = ((int) dataSnapshot.getChildrenCount());
                        for (DataSnapshot chapterSnapShot: dataSnapshot.getChildren())
                        {
                            final String key = chapterSnapShot.getKey();
                            final Fleet fleet = chapterSnapShot.getValue(Fleet.class);
                            double lat = fleet.getV();
                            double log = fleet.getV2();
                            LatLng position = new LatLng(lat, log);

                            MarkerOptions markerOptions = new MarkerOptions().position(position).title(fleet.getPlateNumber());
                            if (fleet.getAmountPaid() != null) {
                                if (fleet.getAmountPaid() <= 10) {
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                                } else if (fleet.getAmountPaid() > 10 && fleet.getAmountPaid() <= 15){
                                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                                }
                            }
                            googleMap.addMarker(markerOptions);
                            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                @Override
                                public boolean onMarkerClick(Marker marker) {
                                    PostKey.postKey = key;
                                    PostKey.postNumber = fleet.getPlateNumber().toLowerCase().trim();
                                    Intent details = new Intent(getContext(), Detail.class);
                                    startActivity(details);
                                    return true;
                                }
                            });
                            builder.include(markerOptions.getPosition());
                            contents++;
                        }
                        if (contents == trackCount) {
                            progressDialog.dismiss();
                            LatLngBounds bounds = builder.build();
                            int width = getResources().getDisplayMetrics().widthPixels;
                            int height = getResources().getDisplayMetrics().widthPixels;
                            int padding = (int)(width*0.10);
                            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds,width,height,padding);
                            googleMap.moveCamera(cameraUpdate);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
