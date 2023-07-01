package com.example.cp.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cp.Fragment.LeveFragment;
import com.example.cp.Fragment.LevelFragment;

import java.util.logging.Level;

public class InviteTablayoutAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public InviteTablayoutAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                LevelFragment mobileFragment = new LevelFragment();
                return mobileFragment;

            case 1:
                LeveFragment photoFragment = new LeveFragment();
                return photoFragment;

            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
