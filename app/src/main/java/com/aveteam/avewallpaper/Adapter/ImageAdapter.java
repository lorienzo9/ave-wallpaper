package com.aveteam.avewallpaper.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Lorenzo on 02/08/2016.
 */
public class ImageAdapter extends BaseAdapter  {

    private Context mcontext;
    private List<com.aveteam.avewallpaper.Model.Image> images;

    @Override
    public int getCount() {
        return this.images.size();
    }

    public ImageAdapter(Context context,List<com.aveteam.avewallpaper.Model.Image> image){
        this.mcontext = context;
        this.images = image;
    }

    @Override
    public Object getItem(int i) {
        return this.images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        com.aveteam.avewallpaper.Model.Image image = images.get(i);

        ImageView imageView;
        if (view==null){
            imageView = new ImageView(mcontext);
            imageView.setLayoutParams(new GridView.LayoutParams(600, 600));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1,1,1,0);
        }else{
            imageView = (ImageView)view;
        }
        // imageView.setImageResource(Thumbs[i]);
        Picasso.with(mcontext).load(image.getSmall()).resize(200, 200).into(imageView);

        return imageView;
    }
   /* public static Integer[] Thumbs={
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher

    };*/
}
