package com.peoplentech.bangladeshpolice;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import at.markushi.ui.CircleButton;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton btnDmp, btnRmp, btnKmp, btnBmp, btnCmp, btnSmp, btnDr, btnRajr, btnRanr, btnSyr, btnKhr, btnMyr, btnBar, btnChir, btnRar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //Circle button onclick
        btnDmp = (ImageButton) findViewById(R.id.Dmp);
        btnDmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(HomeActivity.this,"DMP Button Click.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this,DmpActivity.class);
                startActivity(intent);
            }
        });

        //Circle button onclick
        btnRmp = (ImageButton) findViewById(R.id.Rmp);
        btnRmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"RMP Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        //Circle button onclick
        btnKmp = (ImageButton) findViewById(R.id.Kmp);
        btnKmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"KPM Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        //Circle button onclick
        btnBmp = (ImageButton) findViewById(R.id.Bmp);
        btnBmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"BPM Button Click.", Toast.LENGTH_SHORT).show();
            }
        });
        //Circle button onclick
        btnCmp = (ImageButton) findViewById(R.id.Cmp);
        btnCmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"CPM Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        //Circle button onclick
        btnSmp = (ImageButton) findViewById(R.id.Smp);
        btnSmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"SMP Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        //Circle button onclick
        btnDr = (ImageButton) findViewById(R.id.dr);
        btnDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"Dhaka range Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        //Circle button onclick
        btnRajr = (ImageButton) findViewById(R.id.Rajr);
        btnRajr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"Rajshahi range Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        //Circle button onclick
        btnRanr = (ImageButton) findViewById(R.id.Rpr);
        btnRanr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        //Circle button onclick
        btnSyr = (ImageButton) findViewById(R.id.Sr);
        btnSyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        //Circle button onclick
        btnKhr = (ImageButton) findViewById(R.id.Kr);
        btnKhr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        //Circle button onclick
        btnMyr = (ImageButton) findViewById(R.id.Mr);
        btnMyr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        //Circle button onclick
        btnBar = (ImageButton) findViewById(R.id.Br);
        btnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        //Circle button onclick
        btnChir = (ImageButton) findViewById(R.id.Ctr);
        btnChir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"Button Click.", Toast.LENGTH_SHORT).show();
            }
        });
        //Circle button onclick
        btnRar = (ImageButton) findViewById(R.id.Rwr);
        btnRar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"Button Click.", Toast.LENGTH_SHORT).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);

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
            Intent intent =  new Intent(HomeActivity.this,OfflineActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
