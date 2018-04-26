package com.techart.fleettracker;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.techart.fleettracker.tabsdetailsactivity.Tab1Credentails;
import com.techart.fleettracker.tabsdetailsactivity.Tab2Movement;

public class Detail extends AppCompatActivity {
    private TextView tvCredentials;
    private TextView tvMovement;
    private TextView tvFollow;

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvCredentials = findViewById(R.id.tv_detail);
        tvMovement = findViewById(R.id.tv_movement);
       // tvFollow = findViewById(R.id.tv_track);
        vp= findViewById(R.id.container);
        this.addPages();
        tab1Credentails();
        //TABLAYOUT
        tvCredentials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(0);
                tab1Credentails();
            }
        });

        tvMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(1);
                tab2Movement();
            }
        });
        /*
        tvFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(2);
                tab3Follow();
            }
        });*/

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    tab1Credentails();
                }else {
                    tab2Movement();
                } /*else {
                    tab3Follow();
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addPages() {
        MyPageAdapter pagerAdapter=new MyPageAdapter(this.getSupportFragmentManager());
        pagerAdapter.addFragment(new Tab1Credentails());
        pagerAdapter.addFragment(new Tab2Movement());
      //  pagerAdapter.addFragment(new Tab3Follow());
        //SET ADAPTER TO VP
        vp.setAdapter(pagerAdapter);
    }

    private void tab1Credentails(){
        tvCredentials.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvCredentials.setBackground(getResources().getDrawable(R.drawable.oval_activetv));

        tvMovement.setTextColor(getResources().getColor(R.color.colorWhite));
        tvMovement.setBackground(getResources().getDrawable(R.drawable.oval_inactivetv));

       // tvFollow.setTextColor(getResources().getColor(R.color.colorWhite));
       // tvFollow.setBackground(getResources().getDrawable(R.drawable.oval_inactivetv));
    }

    private void tab2Movement(){
        tvMovement.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvMovement.setBackground(getResources().getDrawable(R.drawable.oval_activetv));

        //tvFollow.setTextColor(getResources().getColor(R.color.colorWhite));
        //tvFollow.setBackground(getResources().getDrawable(R.drawable.oval_inactivetv));

        tvCredentials.setTextColor(getResources().getColor(R.color.colorWhite));
        tvCredentials.setBackground(getResources().getDrawable(R.drawable.oval_inactivetv));
    }
    /*
    private void tab3Follow(){
        tvFollow.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvFollow.setBackground(getResources().getDrawable(R.drawable.oval_activetv));

        tvCredentials.setTextColor(getResources().getColor(R.color.colorWhite));
        tvCredentials.setBackground(getResources().getDrawable(R.drawable.oval_inactivetv));

        tvMovement.setTextColor(getResources().getColor(R.color.colorWhite));
        tvMovement.setBackground(getResources().getDrawable(R.drawable.oval_inactivetv));
    }*/

}
