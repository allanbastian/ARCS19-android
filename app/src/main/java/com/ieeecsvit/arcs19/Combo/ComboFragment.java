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

    }



    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        onItemChanged(comboList.get(positionInDataSet));
    }
}
