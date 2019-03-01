package com.ieeecsvit.arcs19.Combo;

import com.ieeecsvit.arcs19.Combo.ComboAdapter;
import com.ieeecsvit.arcs19.DiscreteScrollClass;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ComboFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener{
    ArrayList<DiscreteScrollClass> comboList = new ArrayList<DiscreteScrollClass>();

    String name, price, shortDes , longDes;

    DiscreteScrollView itemPicker;
    InfiniteScrollAdapter infiniteAdapter;

    ProgressBar comboProgressBar;

    StorageReference storageReference, icon;
    DatabaseReference comboRef;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_combo, container, false);

        itemPicker = rootView.findViewById(R.id.combo_scrollview);
        itemPicker.setVisibility(View.GONE);
        comboProgressBar = rootView.findViewById(R.id.combo_progressbar);

        //Setting up firebase
        storageReference = FirebaseStorage.getInstance().getReference().child("Combos"); //Reference of firebase stoorage for the combo images
        comboRef = FirebaseDatabase.getInstance().getReference().child("Combos");       //Database reference
        comboRef.keepSynced(true);

        //The combo database.
        comboRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    name= snapshot.getKey().toString();                         //Combo's name
                    shortDes= snapshot.child("shortDescription").getValue().toString(); //Combos's short description
                    icon = storageReference.child(name+".png");                 //Combo's Logo
                    longDes= snapshot.child("longDescription").getValue().toString(); //Combos's long description
                    price = snapshot.child("price").getValue().toString();      //Combo's Long Des
                    comboList.add(new DiscreteScrollClass(icon,name, shortDes, price, longDes));

                }
                comboProgressBar.setVisibility(View.GONE);
                itemPicker.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        /*comboList.add(new DiscreteScrollClass(R.drawable.artificial_intelligence,"Combo 1","Convoke + ML + Cloud","Rs. 500/-",""));
        comboList.add(new DiscreteScrollClass(R.drawable.machine,"Combo 2","Convoke + Crypto + Cyber Security","Rs. 500/-",""));
        comboList.add(new DiscreteScrollClass(R.drawable.artificial_intelligence,"Combo 3","UI/UX + ML + Cloud","Rs. 500/-",""));
        comboList.add(new DiscreteScrollClass(R.drawable.machine,"Combo 4","UI/UX + Crypto + Cyber Security","Rs. 500/-",""));*/


        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);

        infiniteAdapter = InfiniteScrollAdapter.wrap(new ComboAdapter(getContext(), comboList));

        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransitionTimeMillis(250);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder().setMinScale(1.0f).build());


        itemPicker.setSlideOnFling(false);
        itemPicker.setSlideOnFlingThreshold(5000);

        return rootView;
    }



    private void onItemChanged(DiscreteScrollClass sponsor) {
        //currentSponsorName.setText(sponsor.getName());
        //currentSponsorType.setText(sponsor.getData());

    }

    /*private void smoothScrollToNextPosition( DiscreteScrollView scrollView, int pos) {
        InfiniteScrollAdapter adapter = (InfiniteScrollAdapter) scrollView.getAdapter();
        int destination;
        if  (pos< comboList.size()-1){
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

    }*/

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        onItemChanged(comboList.get(positionInDataSet));
    }
}
