package com.techart.fleettracker.tabsdetailsactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techart.fleettracker.PostKey;
import com.techart.fleettracker.R;
import com.techart.fleettracker.TimeLineAdapter;
import com.techart.fleettracker.models.OrderStatus;
import com.techart.fleettracker.models.Orientation;
import com.techart.fleettracker.models.TimeLineModel;

import java.util.ArrayList;
import java.util.List;


public class Tab2Movement extends Fragment {

    private RecyclerView mRecyclerView;
    private TimeLineAdapter mTimeLineAdapter;
    private List<TimeLineModel> mDataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragement_movement, container, false);
        mRecyclerView =  rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        initView();
        return rootView;
    }

    private void initView() {
        getTruck();
        mTimeLineAdapter = new TimeLineAdapter(mDataList, Orientation.VERTICAL, false);
        mRecyclerView.setAdapter(mTimeLineAdapter);
    }

    private void getTruck() {
        switch (PostKey.postNumber)
        {
            case "abz 1278":
                abz_1278();
                break;
            case "cd 5748 y":
                cd_5748_y();
                break;
            case "bab 1867":
                bab_1867();
                break;
            case "z 6748 yc":
                z_6748_yc();
                break;
        }
    }

    private void abz_1278(){
        mDataList.add(new TimeLineModel("Courier checked in at Kitwe toll gate", "2018-02-10 12:30", OrderStatus.ACTIVE));
        mDataList.add(new TimeLineModel("Courier checked in at Chililabombwe border office", "2018-01-10 09:00", OrderStatus.COMPLETED));
    }

    private void cd_5748_y(){
        mDataList.add(new TimeLineModel("Courier checked in at Serenje toll of gate", "2018-02-1 19:00", OrderStatus.ACTIVE));
        mDataList.add(new TimeLineModel("Courier checked in at Mpika toll gate", "2018-02-10 16:30", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Courier checked in at Kasama toll gate", "2018-02-10 13:30", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Courier checked in at Nakonde border office", "2018-02-10 08:00", OrderStatus.COMPLETED));
    }

    private void bab_1867(){
        mDataList.add(new TimeLineModel("Courier checked in at Chinsali toll of gate", "2018-04-1 17:00", OrderStatus.ACTIVE));
        mDataList.add(new TimeLineModel("Courier checked in at Nakonde border office", "2018-04-10 14:00", OrderStatus.COMPLETED));
    }

    private void z_6748_yc(){
        mDataList.add(new TimeLineModel("Courier checked in at Rufunsa toll gate", "2018-02-10 13:30", OrderStatus.ACTIVE));
        mDataList.add(new TimeLineModel("Courier checked in at Mwami  border office", "2018-02-10 06:00", OrderStatus.COMPLETED));
    }




}
