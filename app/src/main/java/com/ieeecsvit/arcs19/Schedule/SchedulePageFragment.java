package com.ieeecsvit.arcs19.Schedule;

import com.ieeecsvit.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gigamole.navigationtabstrip.NavigationTabStrip;

public class SchedulePageFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    SchedulePagerAdapter schedulePagerAdapter;
    NavigationTabStrip navigationTabStrip;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.schedule_page,container,false);

        viewPager = rootView.findViewById(R.id.view_pager);
        navigationTabStrip = rootView.findViewById(R.id.navigation_tab);

        schedulePagerAdapter = new SchedulePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(schedulePagerAdapter);

        navigationTabStrip.setTitles("Day 1", "Day 2", "Day 3", "Day 4");
        navigationTabStrip.setViewPager(viewPager,0);



        return rootView;
    }
}
