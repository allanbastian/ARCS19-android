package com.ieeecsvit.arcs19.Convoke;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
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
import com.ieeecsvit.arcs19.R;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class ConvokeFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener{

    FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;
    StorageReference storageReference, speakerImage;

    String speakerName, speakerDetails, speakerFacebook, speakerTopic;
    Bitmap bitmap;
    int image;
    View rootView;
    DiscreteScrollView convokeRecycler;
    InfiniteScrollAdapter infiniteAdapter;
    ProgressBar convokeProgress;
    //convokeList is used to store the list of convoke speakers
    ArrayList<ConvokeClass> convokeList = new ArrayList<ConvokeClass>();
    ImageView previousConvokeButton, nextConvokeButton;

    //Cache
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_convoke, container, false);
        //ConvokeAdapter adapter = new ConvokeAdapter(getContext(),convokeList);
        convokeRecycler = rootView.findViewById(R.id.convoke_recycler_view);
        convokeRecycler.setVisibility(View.GONE);
        convokeProgress=rootView.findViewById(R.id.convoke_fragment_progressbar);
        previousConvokeButton = rootView.findViewById(R.id.previous_convoke_button);
        nextConvokeButton = rootView.findViewById(R.id.next_convoke_button);
        previousConvokeButton.setVisibility(View.GONE);
        nextConvokeButton.setVisibility(View.GONE);

        //Initialise Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference().child("Convoke");         //Convoke node
        ref.keepSynced(true);                                       //Synced with database
        storageReference = FirebaseStorage.getInstance().getReference().child("Convoke");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    speakerName = snapshot.getKey().toString();
                    speakerDetails = snapshot.child("details").getValue().toString();
                    //all images of the speakers should be in same file format.
                    speakerImage = storageReference.child(speakerName+".png");
                    speakerFacebook = snapshot.child("facebook_link").getValue().toString();
                    speakerTopic = snapshot.child("topic").getValue().toString();
                    convokeList.add(new ConvokeClass(speakerName, speakerTopic,speakerDetails, speakerFacebook,speakerImage));

                }
                convokeProgress.setVisibility(View.GONE);
                convokeRecycler.setVisibility(View.VISIBLE);
                previousConvokeButton.setVisibility(View.VISIBLE);
                nextConvokeButton.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        rootView.findViewById(R.id.previous_convoke_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int realPosition = infiniteAdapter.getRealPosition(convokeRecycler.getCurrentItem());
                smoothScrollToPreviousPosition(convokeRecycler,realPosition);
            }
        });
        rootView.findViewById(R.id.next_convoke_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int realPosition = infiniteAdapter.getRealPosition(convokeRecycler.getCurrentItem());
                smoothScrollToNextPosition(convokeRecycler,realPosition);
            }
        });

        convokeRecycler.setOrientation(DSVOrientation.HORIZONTAL);
        convokeRecycler.addOnItemChangedListener(this);

        infiniteAdapter = InfiniteScrollAdapter.wrap(new ConvokeAdapter(getContext(), convokeList));

        convokeRecycler.setAdapter(infiniteAdapter);
        convokeRecycler.setItemTransitionTimeMillis(250);
        convokeRecycler.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.8f).build());


        convokeRecycler.setSlideOnFling(false);
        convokeRecycler.setSlideOnFlingThreshold(5000);

        return rootView;
    }

    private void smoothScrollToNextPosition( DiscreteScrollView scrollView, int pos) {
        InfiniteScrollAdapter adapter = (InfiniteScrollAdapter) scrollView.getAdapter();
        int destination;
        if  (pos< convokeList.size()-1){
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
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        onItemChanged(convokeList.get(positionInDataSet));
    }

    private void onItemChanged(ConvokeClass sponsor) {

    }
}
