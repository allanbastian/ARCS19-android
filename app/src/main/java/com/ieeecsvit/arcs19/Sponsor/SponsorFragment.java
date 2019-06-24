package com.ieeecsvit.arcs19.Sponsor;

import com.ieeecsvit.arcs19.DiscreteScrollClass;
import com.ieeecsvit.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class SponsorFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener,View.OnClickListener{

    ArrayList<DiscreteScrollClass> sponsorList = new ArrayList<DiscreteScrollClass>();
    ImageView previousSponsorButton;
    ImageView nextSponsorButton;

    ProgressBar sponsorsProgress;

    String name, url;

    DiscreteScrollView itemPicker;
    InfiniteScrollAdapter infiniteAdapter;

    StorageReference storageReference, icon;
    DatabaseReference sponsorRef;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sponsors, container, false);

        itemPicker = rootView.findViewById(R.id.sponsors_item_picker);
        sponsorsProgress = rootView.findViewById(R.id.sponsors_fragment_progressbar);
        itemPicker.setVisibility(View.GONE);
        previousSponsorButton = rootView.findViewById(R.id.previous_sponsor_button);
        nextSponsorButton = rootView.findViewById(R.id.next_sponsor_button);
        previousSponsorButton.setVisibility(View.GONE);
        nextSponsorButton.setVisibility(View.GONE);

        //Setting up firebase
        storageReference = FirebaseStorage.getInstance().getReference().child("Sponsors"); //Reference of firebase stoorage for the sponsors logos
        sponsorRef = FirebaseDatabase.getInstance().getReference().child("Sponsors");       //Database reference
        sponsorRef.keepSynced(true);

        //The Sponsors' database.
        sponsorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        name= snapshot.getKey().toString();                         //Sponsor's name
                        url= snapshot.child("url").getValue().toString();           //Sponsor's website
                        icon = storageReference.child(name+".png");                 //Sponsor's Logo
                        sponsorList.add(new DiscreteScrollClass(icon,name, url, null, null));

                }
                sponsorsProgress.setVisibility(View.GONE);
                itemPicker.setVisibility(View.VISIBLE);
                previousSponsorButton.setVisibility(View.VISIBLE);
                nextSponsorButton.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);

        infiniteAdapter = InfiniteScrollAdapter.wrap(new SponsorAdapter(getContext(),sponsorList));

        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransitionTimeMillis(150);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.75f).build());


        rootView.findViewById(R.id.previous_sponsor_button).setOnClickListener(this);
        rootView.findViewById(R.id.next_sponsor_button).setOnClickListener(this);

        itemPicker.setSlideOnFling(true);
        itemPicker.setSlideOnFlingThreshold(5000);

        return rootView;
    }



    private void onItemChanged(DiscreteScrollClass sponsor) {

    }

    private void smoothScrollToNextPosition( DiscreteScrollView scrollView, int pos) {
        InfiniteScrollAdapter adapter = (InfiniteScrollAdapter) scrollView.getAdapter();
        int destination;
        if  (pos< sponsorList.size()-1){
             destination = pos + 1;
        }else
        {
            destination = 0;
        }
        if (adapter != null) {
            destination = adapter.getClosestPosition(destination);
        }
        scrollView.smoothScrollToPosition(destination);
    }

    private void smoothScrollToPreviousPosition( DiscreteScrollView scrollView, int pos) {
        InfiniteScrollAdapter adapter = (InfiniteScrollAdapter) scrollView.getAdapter();
        int destination = pos - 1;
        if (adapter != null) {
            destination = adapter.getClosestPosition(destination);
        }
        scrollView.smoothScrollToPosition(destination);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.previous_sponsor_button:
                int realPosition = infiniteAdapter.getRealPosition(itemPicker.getCurrentItem());
                smoothScrollToPreviousPosition(itemPicker,realPosition);
                break;
            case R.id.next_sponsor_button:
                realPosition = infiniteAdapter.getRealPosition(itemPicker.getCurrentItem());
                smoothScrollToNextPosition(itemPicker,realPosition);
                break;

        }

    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        onItemChanged(sponsorList.get(positionInDataSet));
    }
}
