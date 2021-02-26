package com.oneup.cosmin.xheart.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oneup.cosmin.xheart.R;
import com.oneup.cosmin.xheart.net.Connection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private static  Looper looper;
    private static Context context;

    private LinearLayout statusLight;
    private TextView statusText;
    private Toolbar toolbar;
    private static MenuItem bluetoothStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        statusLight = (LinearLayout) navigationView.getHeaderView(0);
        statusText = statusLight.findViewById(R.id.status_text);


        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.content_frame , new Main()).commit();

        looper = getMainLooper();
        context = getApplicationContext();

        Connection.getConnection().setAddress("B8:27:EB:EA:23:93");
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Fragment fragment = null;

        if(id == R.id.nav_history)
            fragment = new History();
        else if(id == R.id.nav_main)
            fragment = new Main();
        else if(id == R.id.nav_status_bt) {
            if(bluetoothStatus ==null) {
                bluetoothStatus = item;
                setBluetoothStatus("Disconnected");
            }
            Connection.getConnection().connect();
            return true;
        }else if(id==R.id.nav_report || id==R.id.nav_social)
            return true;


        FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.content_frame , fragment).commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return  true;

    }

    //TODO USE RESOURCES FOR STRINGS
    private void updateStatus(int value){
        int finalBackground;
        String finalText;

        if(value < 2){
            finalText = "Foarte bun";
            finalBackground = ContextCompat.getColor(getApplicationContext(), R.color.veryGood);
        }
        else if (value < 5){
            finalText = "Bun";
            finalBackground = ContextCompat.getColor(getApplicationContext(), R.color.good);
        }
        else if (value < 8){
            finalText = "Rău";
            finalBackground = ContextCompat.getColor(getApplicationContext(), R.color.bad);
        }
        else{
            finalText = "Foarte rău";
            finalBackground = ContextCompat.getColor(getApplicationContext(), R.color.ded);
        }
        statusText.setText(finalText);
        ColorDrawable finalDrawable = new ColorDrawable(finalBackground);
        statusLight.setBackground(finalDrawable);
        toolbar.setBackgroundColor(finalBackground);
        toolbar.setTitle(finalText);
    }

    public static Looper getLooper() {
        return looper;
    }
    public static Context getContext() { return context;}

    public static void setBluetoothStatus(final String str){
        if(bluetoothStatus != null)
            new Handler(getLooper()).post(new Runnable() {
                @Override
                public void run() {
                    bluetoothStatus.setTitle("Bluetooth: " + str);
                }
            });
    }
}
