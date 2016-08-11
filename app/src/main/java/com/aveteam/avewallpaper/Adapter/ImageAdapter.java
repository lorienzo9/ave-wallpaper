package com.aveteam.avewallpaper.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.aveteam.avewallpaper.R;

/**
 * Created by Lorenzo on 02/08/2016.
 */
public class ImageAdapter extends BaseAdapter  {

    private Context mcontext;

    @Override
    public int getCount() {
        return Thumbs.length;
    }
    public ImageAdapter(Context context){
        mcontext = context;
    }

    @Override
    public Object getItem(int i) {
        return Thumbs[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view==null){
            imageView = new ImageView(mcontext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8,8,8,8);
        }else{
            imageView = (ImageView)view;
        }
        imageView.setImageResource(Thumbs[i]);
        return imageView;
    }
    public static Integer[] Thumbs={
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher

    };
}
