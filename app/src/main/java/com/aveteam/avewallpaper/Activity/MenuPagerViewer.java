package com.aveteam.avewallpaper.Activity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aveteam.avewallpaper.Adapter.ImageAdapter;
import com.aveteam.avewallpaper.R;

import java.io.IOException;

/**
 * Created by Lorenzo on 04/08/2016.
 */
public class MenuPagerViewer extends AppCompatActivity {
    FloatingActionButton fab;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        //per ricevere la posizione del tocco
        Intent i = getIntent();
        final int position = i.getExtras().getInt("id");

        ImageAdapter imageAdapter = new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(ImageAdapter.Thumbs[position]);

        fab = (FloatingActionButton)findViewById(R.id.fab_setwall);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setWall();
            }
        });

    }

    public void setWall(){
        Intent intent = getIntent();
        final int pos = intent.getExtras().getInt("id");
        WallpaperManager wp = WallpaperManager.getInstance(getApplicationContext());
        try{
            wp.setResource(ImageAdapter.Thumbs[pos]);
            Toast.makeText(MenuPagerViewer.this, "Wallpaper setted", Toast.LENGTH_SHORT).show();
        }catch(IOException e){
            e.printStackTrace();
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 2000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
