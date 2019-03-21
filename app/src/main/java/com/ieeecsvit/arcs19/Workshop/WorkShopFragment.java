package com.ieeecsvit.arcs19.Workshop;

import android.app.Dialog;
import android.content.Intent;
import com.ieeecsvit.arcs19.DiscreteScrollClass;
import com.ieeecsvit.arcs19.GlideApp;
import com.ieeecsvit.arcs19.R;
import com.ieeecsvit.arcs19.RegisterWebView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class WorkShopFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener, View.OnClickListener{

    DiscreteScrollView scrollView;
    ArrayList<DiscreteScrollClass> workshopList;
    DiscreteScrollAdapter adapter;
    ImageView authImg;
    TextView header, description, price, location, authName, authDesc, date;
    Button bookNow;
    AppBarLayout appbar;
    NestedScrollView nestedScrollView;
    ProgressBar workshopProgress;


    LinearLayout speakerLayout;

    // for name of the event, description of the event, Location of the event, price of the event, speaker for the event and deatails of the speaker, respectively
    String eventName, eventDate, eventDescription, eventLocation, eventPrice, speaker, speakerDetails, eventImage;
    String currentEvent="";

    StorageReference storageReference, speakerImage;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference eventRef;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.workshop_fragment,container,false);

        //Initialise Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("Events"); //Reference to the Workshop folder of firebase storage
        eventRef = firebaseDatabase.getReference().child("Events");   //Events database reference
        eventRef.keepSynced(true);

        //initialisation of the view objects
        date = rootview.findViewById(R.id.workshop_date);
        description = rootview.findViewById(R.id.workshop_description); //Description of the workshop
        header = rootview.findViewById(R.id.workshop_name); //Name of the workshop
        scrollView = rootview.findViewById(R.id.discreteScroll);
        price = rootview.findViewById(R.id.workshop_price); //Price of the workshop
        location = rootview.findViewById(R.id.workshop_location); // Location of the workshop
        bookNow = rootview.findViewById(R.id.book_button); // Book Button
        authDesc = rootview.findViewById(R.id.workshop_speaker_description); //Description of the speaker
        authImg= rootview.findViewById(R.id.workshop_speaker_img); //Image of the speaker
        authName = rootview.findViewById(R.id.workshop_speaker_name); //Name of the speaker
        workshopProgress = rootview.findViewById(R.id.workshop_fragment_progressbar);
        appbar = rootview.findViewById(R.id.workshop_app_bar);
        nestedScrollView = rootview.findViewById(R.id.workshop_nested_scroll);
        appbar.setVisibility(View.GONE);
        nestedScrollView.setVisibility(View.GONE);
        speakerLayout = rootview.findViewById(R.id.workshop_speaker_layout);

        rootview.findViewById(R.id.workshop_previous_button).setOnClickListener(this);
        rootview.findViewById(R.id.workshop_next_button).setOnClickListener(this);

        workshopList = new ArrayList<DiscreteScrollClass>();


        eventRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Getting each object and storing in variable, and storing them in ArrayList
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        //
                        eventName= snapshot.getKey().toString(); //Event Name
                        eventDate = snapshot.child("date").getValue().toString(); // Event Date
                        eventImage = snapshot.child("image").getValue().toString(); //Event Logo
                        eventDescription= snapshot.child("description").getValue().toString(); // Event Description
                        eventLocation=snapshot.child("location").getValue().toString(); // Event location
                        eventPrice=snapshot.child("price").getValue().toString(); //Event price
                        speaker=snapshot.child("speaker").getValue().toString(); // Event Speaker Name
                        speakerImage = storageReference.child(speaker+".png"); //Image of the speaker
                        speakerDetails=snapshot.child("speakerDetails").getValue().toString(); //Event Speaker Details
                   workshopList.add(new DiscreteScrollClass(eventImage, eventDate,eventName, eventDescription, eventLocation, speakerDetails,  eventPrice,speakerImage, speaker));
                }
                workshopProgress.setVisibility(View.GONE);
                appbar.setVisibility(View.VISIBLE);
                nestedScrollView.setVisibility(View.VISIBLE);
                onItemChanged(workshopList.get(0));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Discrete Scroll View
        adapter = new DiscreteScrollAdapter(rootview.getContext(), workshopList);


        //Setting up the adapter
        scrollView.setOffscreenItems(4);
        scrollView.setOverScrollEnabled(true);
        scrollView.setAdapter(adapter);
        scrollView.addOnItemChangedListener(this);
        scrollView.setSlideOnFling(true);
        scrollView.setItemTransitionTimeMillis(150);
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());


        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (header.getText().toString())
                {
                    case "Blockchain": currentEvent = "Blockchain and Cryptocurrency Workshop";
                                       break;
                    case "Cloud Computing":  currentEvent = "Cloud Computing Workshop";
                                             break;
                    case "Cyber Security": currentEvent = "Cyber-Security Workshop";
                                           break;
                    case "Machine Learning": currentEvent = "Machine Learning Workshop";
                                             break;
                    case "UI UX": currentEvent = "UI/UX Workshop";
                                  break;
                }
                Intent i = new Intent(getActivity(), RegisterWebView.class);
                i.putExtra("eventName", currentEvent);
                startActivityForResult(i,300);
            }
        });



        return rootview;
    }

    //OnCLick for the next button and previous button
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.workshop_previous_button:
                int realpos= scrollView.getCurrentItem();
                if(realpos>0) {
                    realpos--;
                    scrollView.scrollToPosition(realpos);
                    scrollView.smoothScrollToPosition(realpos);
                    onItemChanged(workshopList.get(realpos));
                }
                break;
            case R.id.workshop_next_button:
                realpos=scrollView.getCurrentItem();
                if(realpos<workshopList.size()-1) {
                    realpos++;
                    scrollView.scrollToPosition(realpos);
                    scrollView.smoothScrollToPosition(realpos);
                    onItemChanged(workshopList.get(realpos));
                }
                break;
        }
    }

    @Override
    public void onCurrentItemChanged(@NonNull RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        onItemChanged(workshopList.get(adapterPosition));
    }

    //Changing the data as the button is clicked
    public void onItemChanged(DiscreteScrollClass obj)
    {
        speakerLayout.setVisibility(View.GONE);
        date.setText(obj.getDate());
        header.setText(obj.getName());
        description.setText(obj.getDes());
        price.setText(String.valueOf(obj.getPrice()));
        location.setText(obj.getLocation());
        Log.e("Speaker",speaker);
        if(!speaker.equals("ARCS")) {

            speakerLayout.setVisibility(View.VISIBLE);
            authDesc.setText(obj.getAuthDesc());
            GlideApp.with(getContext()).load(obj.getsImage())
                    .fitCenter()
                    .into(authImg);
            authName.setText(obj.getAuthName());
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 300 && resultCode == RESULT_OK)
        {
            if(data!=null)
            {
                String result = data.getParcelableExtra("payment");
                if(result == "success") {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Payment Successful")
                            .setMessage("        ")
                            .setIcon(R.drawable.correct_tick_green)
                            .show();
                }
                else {
                    Toast.makeText(getContext(),"Failure",Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(getContext())
                            .setTitle("Payment Failed")
                            .setMessage("        ")
                            .setIcon(R.drawable.wrong_red_cross)
                            .show();
                }

            }
            new AlertDialog.Builder(getContext())
                    .setTitle("Payment Failed")
                    .setMessage("         ")
                    .setIcon(R.drawable.wrong_red_cross)
                    .show();
        }
    }
}