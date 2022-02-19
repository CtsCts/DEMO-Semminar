package com.example.demoseminar.fragmenttab;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerHomeAdapter extends FragmentStateAdapter {


    public ViewPagerHomeAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 0:
                return new Tab_1_Home();

            case 1:
                return new Tab_2_Home();

            case 2:
                return new Tab_3_Home();


            default:
                return new Tab_1_Home();

        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

