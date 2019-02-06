package android.gaurav.com.arcs19.Schedule;

import android.gaurav.com.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

public class SchedulePageFragment extends Fragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    SchedulePagerAdapter schedulePagerAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.schedule_page,container,false);

        tabLayout = rootView.findViewById(R.id.tab_bar);
        viewPager = rootView.findViewById(R.id.view_pager);

        schedulePagerAdapter = new SchedulePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(schedulePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return rootView;
    }
}
