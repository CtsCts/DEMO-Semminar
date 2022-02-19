package com.example.demoseminar.classtesttab;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.demoseminar.fragmenttab.Tab_1_Home;
import com.example.demoseminar.fragmenttab.Tab_2_Home;
import com.example.demoseminar.fragmenttab.Tab_3_Home;

public class ViewPagerClassTestAdapter extends FragmentStateAdapter {


    public ViewPagerClassTestAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

            case 0:
                return new Tab_1_Class_Test();

            case 1:
                return new Tab_2_Class_Test();

            default:
                return new Tab_1_Home();

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

