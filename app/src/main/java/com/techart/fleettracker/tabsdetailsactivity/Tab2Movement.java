package com.techart.fleettracker.tabsdetailsactivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.techart.fleettracker.R;
import com.techart.fleettracker.models.Fleet;
import com.techart.fleettracker.utils.FireBaseUtils;
import com.techart.fleettracker.utils.TimeUtils;
import com.techart.fleettracker.viewholders.StoryViewHolder;


public class Tab2Movement extends Fragment {
    private RecyclerView rvSearchResults;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragement_list, container, false);

        rvSearchResults = rootView.findViewById(R.id.rv_track);
        rvSearchResults.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        rvSearchResults.setLayoutManager(linearLayoutManager);
        firebaseStorySearch();
        return rootView;

    }

    private void firebaseStorySearch() {
        FirebaseRecyclerAdapter<Fleet,StoryViewHolder> fireBaseRecyclerAdapter = new FirebaseRecyclerAdapter<Fleet, StoryViewHolder>(
                Fleet.class,R.layout.listing_track,StoryViewHolder.class, FireBaseUtils.mDatabaseFleet)
        {
            @Override
            protected void populateViewHolder(StoryViewHolder viewHolder, final Fleet model, int position) {
                final String post_key = getRef(position).getKey();
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
                    /*Boolean t = TimeUtils.currentTime() - model.getLastOnline() < TimeUtils.MILLISECONDS_DAY; //&& res;
                    viewHolder.setVisibility(t);*/
                }

                viewHolder.tvDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog("Upon clicking this button, additional information about a particular vehicle will be shown");
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
