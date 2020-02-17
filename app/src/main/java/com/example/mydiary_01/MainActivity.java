package com.example.mydiary_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.example.mydiary_01.Calendar.CalendarFragment;
import com.example.mydiary_01.Database.ProfileDataSource;
import com.example.mydiary_01.Profile.ProfileEditFragment;
import com.example.mydiary_01.Profile.ProfileFragment;
import com.example.mydiary_01.Story.StoryEditFragment;
import com.example.mydiary_01.Story.StoryFragment;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private Button mButton ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting main view
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //define views inside main view
        mButton = findViewById(R.id.mainBtn);
        drawer = findViewById(R.id.draw_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
//opening fragment
if(new ProfileDataSource(this).isDbEmpty() && savedInstanceState == null)
{
    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileEditFragment()).commit();
    navigationView.setCheckedItem(R.id.nav_profileedit);
}else if(savedInstanceState == null ){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }

  mButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

          getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StoryEditFragment()).commit();
      }
  });

    }
    //menubarfunctions
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_profile:

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new  ProfileFragment()).commit();
                break;
            case R.id.nav_profileedit:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileEditFragment()).commit();
                break;
            case R.id.nav_story:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StoryEditFragment()).commit();
                break;
            case R.id.nav_stories:
               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StoryFragment()).commit();

                break;
            case R.id.nav_calendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalendarFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    }