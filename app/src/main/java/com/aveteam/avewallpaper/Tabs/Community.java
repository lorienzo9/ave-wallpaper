package com.aveteam.avewallpaper.Tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aveteam.avewallpaper.R;

/**
 * Created by Lorenzo on 23/08/2016.
 */
public class Community extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.content_main,container,false);
        return v;
    }
}
