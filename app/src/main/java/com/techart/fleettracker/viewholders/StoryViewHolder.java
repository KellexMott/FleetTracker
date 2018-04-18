package com.techart.fleettracker.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.techart.fleettracker.R;


/**
 * Created by kelvin on 2/12/18.
 */

public final class StoryViewHolder extends RecyclerView.ViewHolder {
    public RelativeLayout rlUmbrella;
    public TextView tvPlateNumber;
    public TextView tvTransit;
    public TextView lastLocation;
    public TextView tvDetails;
    public TextView tvTime;
    public View mView;

    public StoryViewHolder(View itemView) {
        super(itemView);
        /*
        tvTime = itemView.findViewById(R.id.tv_time);p)*/
        rlUmbrella = itemView.findViewById(R.id.rv_umbrella);
        lastLocation = itemView.findViewById(R.id.tv_lastLocation);
        tvPlateNumber = itemView.findViewById(R.id.tv_fleet);
        tvTransit = itemView.findViewById(R.id.tv_transit);
        tvDetails = itemView.findViewById(R.id.tv_details);
        this.mView = itemView;
    }
}
