package com.example.demoseminar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.demoseminar.MainActivity;
import com.example.demoseminar.R;
import com.example.demoseminar.fragmenttab.ViewPagerHomeAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private TabLayout mTabLayoutHome;
    private ViewPager2 mPager2Home;
    View mView;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container,@NonNull  Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.layout_fragment_home,container,false);
        mTabLayoutHome = mView.findViewById(R.id.tab_home);

        mPager2Home = mView.findViewById(R.id.home_viewpager);
        ViewPagerHomeAdapter adapter = new ViewPagerHomeAdapter(getActivity());//bắt hoạt động
        mPager2Home.setAdapter(adapter);
        //tao tablayout
        new TabLayoutMediator(mTabLayoutHome, mPager2Home, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0 :
                        tab.setText("Memo");
                        break;
                    case 1 :
                        tab.setText("List Student");
                        break;
                    case 2 :
                        tab.setText("List Teacher");
                        break;
                }

            }
        }).attach();
//
//        ViewPagerHomeAdapter pagerHomeAdapter = new ViewPagerHomeAdapter(getChildFragmentManager());
//        mPager2Home.setAdapter(pagerHomeAdapter);
//        mTabLayoutHome.setupWithViewPager(mPager2Home,true);
        return mView;
    }
}
