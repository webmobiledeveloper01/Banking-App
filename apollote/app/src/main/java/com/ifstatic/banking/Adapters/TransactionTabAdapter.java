package com.ifstatic.banking.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TransactionTabAdapter extends FragmentPagerAdapter {

    List<Fragment>fragments=new ArrayList<>();
    ArrayList<String>arrayList=new ArrayList<>();

    public void addFragment(Fragment fragment,String title){
        fragments.add(fragment);
        arrayList.add(title);
    }

    public TransactionTabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }
}
