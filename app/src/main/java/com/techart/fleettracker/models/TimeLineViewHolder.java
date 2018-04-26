package com.techart.fleettracker.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;
import com.techart.fleettracker.R;

public class TimeLineViewHolder extends RecyclerView.ViewHolder {

    public TextView mDate;
    public TextView mMessage;
    public TimelineView mTimelineView;
    public View mView;


    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);

        mTimelineView = itemView.findViewById(R.id.time_marker);
        mDate = itemView.findViewById(R.id.text_timeline_date);
        mMessage = itemView.findViewById(R.id.text_timeline_title);
        this.mView = itemView;

        mTimelineView.initLine(viewType);
    }
}