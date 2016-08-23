package com.aveteam.avewallpaper.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.aveteam.avewallpaper.Model.SquardeImage;
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

        SquardeImage v = (SquardeImage) view;
        if (v == null) {
            v = new SquardeImage(mcontext);
            v.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        // imageView.setImageResource(Thumbs[i]);
        Picasso.with(mcontext).load(image.getSmall()).centerCrop().resize(200, 200).into(v);

        return v;
    }
   /* public static Integer[] Thumbs={
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher

    };*/
}
