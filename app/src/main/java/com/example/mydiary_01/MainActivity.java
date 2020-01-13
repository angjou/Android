package com.example.mydiary_01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.mydiary_01.Profile.MyDBHandlerProfile;
import com.example.mydiary_01.Profile.ProfileEditFragment;
import com.example.mydiary_01.Profile.ProfileFragment;
import com.example.mydiary_01.Story.StoryFragment;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String COLUMN_NAME = "UserName";
    private static final String COLUMN_ID = "UserID";
    private static final String COLUMN_SURNAME = "UserSurname";
    private static final String COLUMN_ADDRESS = "UserAddress";
    private static final String COLUMN_EMAIL = "UserEmail";
    private static final String COLUMN_OIB  = "UserOooib";
    private static final String COLUMN_PASSPORT  = "UserPassport";
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDBHandlerProfile dbHelper = new MyDBHandlerProfile(this);


        /*ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, "bla");
        values.put(COLUMN_ID, "AUTO_INCREMENT");
        values.put(COLUMN_SURNAME,"A");
        values.put(COLUMN_ADDRESS,"B");
        values.put(COLUMN_EMAIL,"C");
        values.put(COLUMN_OIB,"D");
        values.put(COLUMN_PASSPORT,"E");

        dbHelper.getWritableDatabase().insert("User", null, values);
*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.draw_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_profile);
        }

        String query= "Select "+COLUMN_NAME + " FROM " + "User";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //String name = cursor.getString(0);

        //Log.d("Debug",name);
        cursor.close();
        db.close();
        //save in Edit profile


        //open edit view

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
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StoryFragment()).commit();
                /*Intent myIntent = new Intent(MainActivity.this, StoryActivity.class);

                MainActivity.this.startActivity(myIntent);*/
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

}