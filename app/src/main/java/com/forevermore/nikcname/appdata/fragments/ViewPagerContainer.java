package com.forevermore.nikcname.appdata.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.forevermore.nikcname.appdata.R;
import com.forevermore.nikcname.appdata.adapters.ViewPagerAdapterDialog;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerContainer extends Fragment {

    private CallbackViewPagerContainer callbackViewPagerContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_pager, container, false);


        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ViewPager viewPager = view.findViewById(R.id.view_pager);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout_pager);
        tabLayout.addTab(tabLayout.newTab().setText("File"));
        tabLayout.addTab(tabLayout.newTab().setText("Directory"));
        tabLayout.setTabGravity(Gravity.FILL);

        ViewPagerAdapterDialog pagerAdapter = new ViewPagerAdapterDialog(
                getChildFragmentManager(), tabLayout.getTabCount()
        );

        pagerAdapter.setListener(new ViewPagerAdapterDialog.CallbackViewPagerAdapterDialog() {
            List<String> list = new ArrayList<>();
            @Override
            public void dataFile(String name, String text) {
                if (viewPager.getCurrentItem() == 0){
                    list.add(name);
                    list.add(text);
                    callbackViewPagerContainer.getList(list);
                }
            }

            @Override
            public void dataDirectory(String name) {
                if (viewPager.getCurrentItem() == 1){
                    list.add(name);
                    callbackViewPagerContainer.getList(list);
                }
            }
        });

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public interface CallbackViewPagerContainer{
        void getList(List<String> list);
    }

    public void setListener(CallbackViewPagerContainer callbackViewPagerContainer){
        this.callbackViewPagerContainer = callbackViewPagerContainer;
    }
}
