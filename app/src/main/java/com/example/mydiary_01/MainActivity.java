package com.example.mydiary_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydiary_01.Calendar.CalendarFragment;
import com.example.mydiary_01.Database.ProfileDataSource;
import com.example.mydiary_01.Database.StoryDataSource;
import com.example.mydiary_01.NodesClasses.Story;
import com.example.mydiary_01.Profile.ProfileEditFragment;
import com.example.mydiary_01.Profile.ProfileFragment;
import com.example.mydiary_01.Story.StoryEditFragment;
import com.example.mydiary_01.Story.StoryFragment;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    StoryDataSource db ;
    int i = 0;
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

    private void displayDialog() {

        db = new StoryDataSource(this.getBaseContext());
        Dialog d = new Dialog(this);
        d.setTitle("SQLITE DATA");
        d.setContentView(R.layout.storyview_fragment);

        //INITIALIZE VIEWS
        final TextView title = (TextView) d.findViewById(R.id.viewTitle);
        final TextView story = (TextView) d.findViewById(R.id.viewStory);
        final ImageView img = d.findViewById(R.id.imgView);
        Cursor cursor = db.initializeData();

        int id;


        if (cursor.getCount() == 0) {
            Toast.makeText(this.getBaseContext(), "Empty database", Toast.LENGTH_SHORT).show();
        } else {
            do {

                id = cursor.getInt(0);
                story.setText(cursor.getString(1));

                Bitmap bitmap = BitmapFactory.decodeByteArray(cursor.getBlob(2), 0, cursor.getBlob(2).length);
                img.setImageBitmap(bitmap);
                title.setText(cursor.getString(3));

            } while (cursor.moveToNext());


            //SHOW DIALOG
            d.show();


        }


    }}