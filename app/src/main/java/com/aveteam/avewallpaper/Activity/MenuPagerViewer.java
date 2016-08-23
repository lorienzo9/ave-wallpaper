package com.aveteam.avewallpaper.Activity;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.aveteam.avewallpaper.Model.Image;
import com.aveteam.avewallpaper.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

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
    private class DownloadWallpaperAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Image image = images.get(selectedPosition);
            String urlbit = image.getLarge();
            try {
                InputStream inputStream = new URL(urlbit).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                download(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }

            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(MenuPagerViewer.this,"Salvato",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        public void download(Bitmap bitmap){
            String root = Environment.getExternalStorageDirectory().toString();
            File Dir = new File(root+"/AveWalls");
            Dir.mkdirs();
            Random generator = new Random();
            int i = 10000;
            i = generator.nextInt(i);
            String name = "Image-"+i+".jpg";
            File file = new File(Dir, name);
            if (file.exists())file.delete();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    /**private class SetLockWallpaperAsyncTask extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            Image image = images.get(selectedPosition);
            String URL = image.getLarge();
            setLockWallpaper(URL);
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

        private void setLockWallpaper(String url) {
            try {
                WallpaperManager wpm = WallpaperManager.getInstance(MenuPagerViewer.this);
                InputStream ins = new URL(url).openStream();
                wpm.setStream(ins, null, true, WallpaperManager.FLAG_LOCK);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }**/



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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_settings){
            new DownloadWallpaperAsyncTask().execute(images.get(selectedPosition).getLarge());
        }
        return super.onOptionsItemSelected(item);
    }
}

