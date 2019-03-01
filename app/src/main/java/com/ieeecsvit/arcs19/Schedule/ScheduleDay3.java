package com.ieeecsvit.arcs19.Schedule;

import com.ieeecsvit.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ScheduleDay3 extends Fragment {

    ListView listView;
    ArrayList<ScheduleItemClass> items;
    ScheduleItemAdapter adapter;

    ProgressBar progressBar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.schedule_page_fragment,container,false);

        listView = rootView.findViewById(R.id.item_list);
        progressBar = rootView.findViewById(R.id.progress_bar);

        //Firebase Initialisation
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("Timeline").child("3");
        ref.keepSynced(true);                                                   //Synced with firebase

        items = new ArrayList<ScheduleItemClass>();
        adapter = new ScheduleItemAdapter(getContext(),R.layout.schedule_items_adapter,items);      //initial Adapter
        listView.setAdapter(adapter);

        progressBar.setVisibility(View.VISIBLE);
        ref.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ScheduleItemClass object = new ScheduleItemClass();
                object = dataSnapshot.getValue(ScheduleItemClass.class);                                    //Holds the value of current node in database
                items.add(object);
                //Setting the new Adapter
                adapter = new ScheduleItemAdapter(getContext(),R.layout.schedule_items_adapter,items);
                listView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
        return rootView;
    }
}
