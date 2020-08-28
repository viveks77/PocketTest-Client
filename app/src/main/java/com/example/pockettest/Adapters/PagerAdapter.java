package com.example.pockettest.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pockettest.Fragments.OngoingTFragment;
import com.example.pockettest.Fragments.UpcomingTFragment;

public class PagerAdapter extends FragmentPagerAdapter {
     private int noOfTabs;
    public PagerAdapter(FragmentManager fragmentManager , int noOfTabs)
    {
        super(fragmentManager);
        this.noOfTabs=noOfTabs;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new UpcomingTFragment();

            case 1:
                return new OngoingTFragment();

            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return noOfTabs;
    }
}
