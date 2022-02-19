package com.example.demoseminar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.demoseminar.fragment.AboutFragment;
import com.example.demoseminar.fragment.ChatFragment;
import com.example.demoseminar.fragment.ClassTestFragment;
import com.example.demoseminar.fragment.HomeFragment;
import com.example.demoseminar.fragment.ListClassFragment;

public class ViewPagerMainAdapter extends FragmentStateAdapter
{
    public ViewPagerMainAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
               return new HomeFragment();

            case 1:
                return new ListClassFragment();

            case 2:
                return new ClassTestFragment();

            case 3:
                return new ChatFragment();

            case 4:
                return new AboutFragment();

            default:
                return new HomeFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
