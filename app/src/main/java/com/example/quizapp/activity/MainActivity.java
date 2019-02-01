package com.example.quizapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.fragment.ContestFragment;
import com.example.quizapp.fragment.SocialMediaFragment;
import com.example.quizapp.models.Response.dynamicContest.DynamicContestResponse;
import com.example.quizapp.utils.DynamicContestPage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private Fragment fragment = null;
    IConnectAPI iConnectAPI;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.main_tab_layout);
        TabLayout.Tab contest = tabLayout.newTab();
        contest.setText("Contest");
        contest.setIcon(R.drawable.contest);
        tabLayout.addTab(contest, true);

        iConnectAPI = AppController.dynamic_contest_retrofit.create(IConnectAPI.class);

        TabLayout.Tab socialMedia = tabLayout.newTab();
        socialMedia.setText("Social Media");
        socialMedia.setIcon(R.drawable.socialmedia);
        tabLayout.addTab(socialMedia);
        if (savedInstanceState == null) {
            fragment = new ContestFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.simpleFrameLayout, fragment).commit();
        }

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ContestFragment();
                        break;
                    case 1:
                        fragment = new SocialMediaFragment();
                        break;

                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.nav_contest) {
            fragment = new ContestFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.simpleFrameLayout, fragment).commit();
        }else if(id == R.id.nav_dynamic_contest){
            Toast.makeText(this, "Dynamic Contest", Toast.LENGTH_SHORT).show();
            checkDynamicContest();
        } else if (id == R.id.nav_social_media) {
            fragment = new SocialMediaFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.simpleFrameLayout, fragment).commit();

        } else if (id == R.id.nav_leader_board) {
            startActivity(new Intent(MainActivity.this, LeaderBoard.class));

        } else if (id == R.id.nav_log_out) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void checkDynamicContest() {
        Toast.makeText(this, "Dynamic Contest ()", Toast.LENGTH_SHORT).show();
        iConnectAPI.dynamicContestResponse().enqueue(new Callback<DynamicContestResponse>() {
            @Override
            public void onResponse(Call<DynamicContestResponse> call, Response<DynamicContestResponse> response) {
                Log.d("DYNAMIC_ACTIVE_CONTEST",response.body().toString());
                if(response.body().getStatus().equals("success"))
                {
                    Intent intent=new Intent(MainActivity.this, DynamicContestPage.class);
                    intent.putExtra("contestId",response.body().getResponse().getContestId());
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<DynamicContestResponse> call, Throwable t) {
                Log.d("DYNAMIC_ACTIVE_fail",t.getMessage());
            }
        });
    }
}
