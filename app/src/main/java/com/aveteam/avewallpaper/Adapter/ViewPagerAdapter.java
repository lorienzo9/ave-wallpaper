package com.aveteam.avewallpaper.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.aveteam.avewallpaper.Tabs.Community;
import com.aveteam.avewallpaper.Tabs.Home;
import com.aveteam.avewallpaper.Tabs.Top;

/**
 * Created by Lorenzo on 23/08/2016.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter  {
    int NumbOfTabs;
    CharSequence Titles[];

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;}

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    public Fragment getItem(int position) {

        switch (position) {
            case 1:
                Home tab1 = new Home();
                return tab1;
            case 0:
                Community tab2 = new Community();
                return tab2;
            case 2:
                Top tab3 = new Top();
                return tab3;
            default:
                return null;
        }
    }

}
