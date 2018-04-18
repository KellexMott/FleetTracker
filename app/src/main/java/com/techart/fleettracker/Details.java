package com.techart.fleettracker;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.techart.fleettracker.tabsdetailsactivity.Tab1Credentails;
import com.techart.fleettracker.tabsdetailsactivity.Tab2Movement;
import com.techart.fleettracker.tabsdetailsactivity.Tab3Follow;

import static android.widget.Toast.LENGTH_LONG;

public class Details extends AppCompatActivity {
    private TextView tvList;
    private TextView tvMap;

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvList = findViewById(R.id.tv_list);
        tvMap = findViewById(R.id.tv_map);
        vp= findViewById(R.id.container);
        this.addPages();
        //TABLAYOUT
        tvList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(0);
                toggleListTab();
            }
        });

        tvMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(1);
                toggleMapTab();
            }
        });

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    toggleListTab();
                } else {
                    toggleMapTab();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        vp.setCurrentItem(0);
        toggleListTab();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        vp.setCurrentItem(0);
        toggleListTab();
    }

    private void addPages() {
        MyPageAdapter pagerAdapter=new MyPageAdapter(this.getSupportFragmentManager());
        pagerAdapter.addFragment(new Tab1Credentails());
        pagerAdapter.addFragment(new Tab2Movement());
        pagerAdapter.addFragment(new Tab3Follow());
        //SET ADAPTER TO VP
        vp.setAdapter(pagerAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            showDialog("Upon clicking this button, a view with a provision to search for a particulate " +
                    "vehicle using its plate number will be shown");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggleListTab(){
        tvList.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvList.setBackground(getResources().getDrawable(R.drawable.lefttab_active));

        tvMap.setTextColor(getResources().getColor(R.color.colorWhite));
        tvMap.setBackground(getResources().getDrawable(R.drawable.righttab_inactive));
    }

    private void toggleMapTab(){
        tvList.setTextColor(getResources().getColor(R.color.colorWhite));
        tvList.setBackground(getResources().getDrawable(R.drawable.lefttab_inactive));

        tvMap.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvMap.setBackground(getResources().getDrawable(R.drawable.righttab_active));
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attention..!")
        .setMessage(msg)
                .setPositiveButton("DISMISS", dialogClickListener)
                .show();
    }
    private void haveNetworkConnection() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo netWorkInfo = cm.getActiveNetworkInfo();
            if (netWorkInfo != null && netWorkInfo.getState() == NetworkInfo.State.CONNECTED) {
                Toast.makeText(Details.this,"Connected", LENGTH_LONG).show();
            } else {
                Toast.makeText(Details.this,"No internet Connection", LENGTH_LONG).show();
            }
        }
    }

}
