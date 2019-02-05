package android.gaurav.com.arcs19.Schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class SchedulePagerAdapter extends FragmentPagerAdapter {

    int numberOfTabs = 4;

    public SchedulePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //Switching to current fragment
        switch (position)
        {
            case 0: return new ScheduleDay1();
            case 1: return new ScheduleDay2();
            case 2: return new ScheduleDay3();
            case 3: return new ScheduleDay4();
            default:return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //Setting the tab name
        switch(position){
            case 0: return "Day 1";
            case 1: return "Day 2";
            case 2: return "Day 3";
            case 3: return "Day 4";
            default: return " ";
        }
    }


    @Override
    public int getCount() {
        //Total number of tabs
        return numberOfTabs;
    }
}
