package com.techart.fleettracker.tabsmainactivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.techart.fleettracker.Detail;
import com.techart.fleettracker.PostKey;
import com.techart.fleettracker.R;
import com.techart.fleettracker.models.Fleet;
import com.techart.fleettracker.utils.FireBaseUtils;
import com.techart.fleettracker.utils.TimeUtils;
import com.techart.fleettracker.viewholders.StoryViewHolder;

import static android.content.Context.MODE_PRIVATE;


public class Tab1List extends Fragment {
    private RecyclerView rvSearchResults;

    private SharedPreferences mPref;
    private String postKey;
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragement_list, container, false);

        rvSearchResults = rootView.findViewById(R.id.rv_track);

       // mPref = PreferenceManager.getDefaultSharedPreferences(getActivity());

        rvSearchResults.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        rvSearchResults.setLayoutManager(linearLayoutManager);
        firebaseStorySearch();
        return rootView;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPref = context.getSharedPreferences(String.format("%s",getString(R.string.app_name)), MODE_PRIVATE);
    }

    private void firebaseStorySearch() {
        FirebaseRecyclerAdapter<Fleet,StoryViewHolder> fireBaseRecyclerAdapter = new FirebaseRecyclerAdapter<Fleet, StoryViewHolder>(
                Fleet.class,R.layout.listing_track,StoryViewHolder.class, FireBaseUtils.mDatabaseFleet)
        {
            @Override
            protected void populateViewHolder(StoryViewHolder viewHolder, final Fleet model, int position) {
                final String post_key = getRef(position).getKey();
                final int pos = position;
                viewHolder.tvPlateNumber.setText(getString(R.string.fleet,model.getPlateNumber(),model.getDriver()));
                viewHolder.tvTransit.setText(getString(R.string.transit,model.getFrom(),model.getTo()));
                viewHolder.lastLocation.setText(getString(R.string.lastLocation,model.getLastLocation()));

                if (model.getTimeCreated() != null) {
                    String time = TimeUtils.timeElapsed(model.getTimeCreated());
                    viewHolder.tvTime.setText(time);
                }

                if (model.getAmountPaid() != null) {
                    if (model.getAmountPaid() <= 10) {
                        viewHolder.rlUmbrella.setBackground(getResources().getDrawable(R.drawable.cardview_blue));
                        viewHolder.tvPlateNumber.setTextColor(getResources().getColor(R.color.colorPrimary));
                    } else if (model.getAmountPaid() > 10 && model.getAmountPaid() <= 15){
                        viewHolder.rlUmbrella.setBackground(getResources().getDrawable(R.drawable.cardview_yellow));
                    } else{
                        viewHolder.rlUmbrella.setBackground(getResources().getDrawable(R.drawable.cardview_red));
                        viewHolder.tvPlateNumber.setTextColor(getResources().getColor(R.color.colorRed));
                    }
                }

                viewHolder.tvDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PostKey.postKey = post_key;
                        PostKey.postNumber = model.getPlateNumber().toLowerCase().trim();
                        Intent details = new Intent(getContext(), Detail.class);
                        startActivity(details);
                    }
                });

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });


            }
        };
        rvSearchResults.setAdapter(fireBaseRecyclerAdapter);
        fireBaseRecyclerAdapter.notifyDataSetChanged();
    }

    private void showDialog(String msg){
        DialogInterface.OnClickListener dialogClickListener =
        new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int button) {
                if (button == DialogInterface.BUTTON_POSITIVE) {
                    dialog.dismiss();
                }
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Attention..!")
        .setMessage(msg)
        .setPositiveButton("DISMISS", dialogClickListener)
        .show();
    }
}
