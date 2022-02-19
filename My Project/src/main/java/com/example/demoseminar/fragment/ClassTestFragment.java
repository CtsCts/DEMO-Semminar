package com.example.demoseminar.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.demoseminar.R;
import com.example.demoseminar.classtesttab.ViewPagerClassTestAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ClassTestFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager2;
    View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_fragment_class_test,container,false);

        mViewPager2 = mView.findViewById(R.id.view_pager_class_test);

        ViewPagerClassTestAdapter adapter = new ViewPagerClassTestAdapter(getActivity());
        mViewPager2.setAdapter(adapter);

        mTabLayout = mView.findViewById(R.id.tab_class_test);
        new TabLayoutMediator(mTabLayout, mViewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText(R.string.tab_1_class_test);
                        break;
                    case 1:
                        tab.setText(R.string.tab_2_class_test);
                        break;
                }

            }
        }).attach();


        return mView;
    }
}
