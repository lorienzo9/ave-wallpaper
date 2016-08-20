package com.aveteam.avewallpaper.Activity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aveteam.avewallpaper.Model.Image;
import com.aveteam.avewallpaper.R;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Lorenzo on 04/08/2016.
 */

public class MenuPagerViewer extends AppCompatActivity {
    FloatingActionButton fab;
    private String TAG = MenuPagerViewer.class.getSimpleName();
    private int selectedPosition = 0;
    private ArrayList<Image> images;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        images = (ArrayList<Image>) getIntent().getExtras().getSerializable("images");
        selectedPosition = getIntent().getExtras().getInt("id");
        Log.e(TAG, "position: " + selectedPosition);
        Log.e(TAG, "images size: " + images.size());
        final Image image = images.get(selectedPosition);

        //per ricevere la posizione del tocco
       /* Intent i = getIntent();
        final int position = i.getExtras().getInt("id");

        ImageAdapter imageAdapter = new ImageAdapter(this);
*/
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Picasso.with(this).load(image.getLarge()).into(imageView);

        // imageView.setImageResource(ImageAdapter.Thumbs[position]);

        fab = (FloatingActionButton)findViewById(R.id.fab_setwall);
        fab.setImageResource(R.mipmap.fab_blu);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // setWall();
                Image image = images.get(selectedPosition);
                new SetWallpaperAsyncTask().execute(image.getLarge());
            }
        });

    }

    private class SetWallpaperAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Image image = images.get(selectedPosition);
            String URL = image.getLarge();
            setWallpaper(URL);
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MenuPagerViewer.this,"Impostato",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        private void setWallpaper(String url) {
            try {
                WallpaperManager wpm = WallpaperManager.getInstance(MenuPagerViewer.this);
                InputStream ins = new URL(url).openStream();
                wpm.setStream(ins);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


   /* public void setWall(){
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

    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

