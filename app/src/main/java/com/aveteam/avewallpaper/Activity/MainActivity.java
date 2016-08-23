package com.aveteam.avewallpaper.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.util.VersionInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.aveteam.avewallpaper.Adapter.ImageAdapter;
import com.aveteam.avewallpaper.Adapter.ViewPagerAdapter;
import com.aveteam.avewallpaper.Model.Image;
import com.aveteam.avewallpaper.R;
import com.aveteam.avewallpaper.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;
    CharSequence Titles[]={"Community","Home", "Top"};
    int Numboftabs =3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Community"));
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Top"));
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //set the Adapter for the ViewPager
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);
        viewPager.setAdapter(viewPagerAdapter);
        setSupportActionBar(toolbar);
        viewPager.setCurrentItem(1);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                              @Override
                                              public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                              }

                                              @Override
                                              public void onPageSelected(int position) {
                                                  switch (position) {
                                                      case 0:

                                                          break;
                                                      case 1:

                                                          break;
                                                      case 2:
                                                          break;
                                                  }
                                              }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if  (id == R.id.nav_gallery) {
            Galleria();
        } else if (id == R.id.nav_share) {
            share();
        } else if (id == R.id.nav_send) {
            contactUs();
        }else if (id == R.id.info){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void share(){
        Intent i = new Intent();
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, "Prova la mia app");
        i.setType("text/plain");
        startActivity(Intent.createChooser(i, "Condividi la tua esperienza con"));
    }
    public void contactUs(){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/3/116409389496417361538"));
        startActivity(i);
    }
    public void Galleria(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        final int ACTIVITY_IMAGE = 1000;
        startActivityForResult(i, ACTIVITY_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1000: if (resultCode==RESULT_OK){
                Uri image = data.getData();


                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(image,filePath, null, null, null);
                cursor.moveToFirst();
                int Index = cursor.getColumnIndex(filePath[0]);
                String file = cursor.getString(Index);
                cursor.close();

                Bitmap imageSelected = BitmapFactory.decodeFile(file);

                Intent intentshare = new Intent();
                intentshare.setType("image/jpeg");
                intentshare.setAction(Intent.ACTION_SEND);
                intentshare.getPackage();
                intentshare.putExtra(Intent.EXTRA_TEXT, "Condiviso da " + " " + Build.MODEL);
                intentshare.putExtra(Intent.EXTRA_SUBJECT, "Immagine");
                intentshare.putExtra(Intent.EXTRA_EMAIL, new String[]{"aveteamdevelopers@gmail.com"});
                intentshare.putExtra(Intent.EXTRA_STREAM, image);
                startActivity(Intent.createChooser(intentshare, "Invia Email con..."));

            }
        }
    }


}

