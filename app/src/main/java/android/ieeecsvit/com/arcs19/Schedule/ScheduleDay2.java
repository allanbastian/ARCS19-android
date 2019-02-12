package android.ieeecsvit.com.arcs19.Schedule;

import android.ieeecsvit.com.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class ScheduleDay2 extends Fragment {

    ListView listView;
    ArrayList<ScheduleItemClass> items;
    ScheduleItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.schedule_page_fragment,container,false);

        listView = rootView.findViewById(R.id.item_list);

        items = new ArrayList<ScheduleItemClass>();
        adapter = new ScheduleItemAdapter(getContext(),R.layout.schedule_items_adapter,items);
        listView.setAdapter(adapter);

        //Sample Code
        items.add(new ScheduleItemClass("Breakfast","7:00 AM - 9:30 AM","breakfast_icon"));
        items.add(new ScheduleItemClass("Breakfast","7:00 AM - 9:30 AM","breakfast_icon"));
        items.add(new ScheduleItemClass("Breakfast","7:00 AM - 9:30 AM","breakfast_icon"));
        items.add(new ScheduleItemClass("Breakfast","7:00 AM - 9:30 AM","breakfast_icon"));
        items.add(new ScheduleItemClass("Breakfast","7:00 AM - 9:30 AM","breakfast_icon"));
        items.add(new ScheduleItemClass("Breakfast","7:00 AM - 9:30 AM","breakfast_icon"));
        items.add(new ScheduleItemClass("Breakfast","7:00 AM - 9:30 AM","breakfast_icon"));
        items.add(new ScheduleItemClass("Breakfast","7:00 AM - 9:30 AM","breakfast_icon"));
        items.add(new ScheduleItemClass("Breakfast","7:00 AM - 9:30 AM","breakfast_icon"));
        items.add(new ScheduleItemClass("Breakfast","7:00 AM - 9:30 AM","breakfast_icon"));

        return rootView;
    }
}
