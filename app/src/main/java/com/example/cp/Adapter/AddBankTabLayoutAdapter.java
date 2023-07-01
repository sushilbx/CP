package com.example.cp.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cp.AddBankAccountFragment;
import com.example.cp.Fragment.AddUpiFragment;

public class AddBankTabLayoutAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public AddBankTabLayoutAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AddBankAccountFragment mobileFragment = new AddBankAccountFragment();
                return mobileFragment;

            case 1:
                AddUpiFragment photoFragment = new AddUpiFragment();
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