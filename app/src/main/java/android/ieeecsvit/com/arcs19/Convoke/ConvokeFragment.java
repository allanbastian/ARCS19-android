package android.ieeecsvit.com.arcs19.Convoke;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.ieeecsvit.com.arcs19.Combo.ComboAdapter;
import android.ieeecsvit.com.arcs19.DiscreteScrollClass;
import android.ieeecsvit.com.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.FirebaseError;
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

import java.io.ByteArrayOutputStream;
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

    //Cache
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_convoke, container, false);

        //Cache
        /*sp = this.getActivity().getSharedPreferences("key",0);

        sp.getBoolean("cacheAvailable",false);



        editor = sp.edit();
        editor.putBoolean("cache",false);*/
        ConvokeAdapter adapter = new ConvokeAdapter(getContext(),convokeList);
        convokeRecycler = rootView.findViewById(R.id.convoke_recycler_view);
        convokeRecycler.setVisibility(View.GONE);
        convokeProgress=rootView.findViewById(R.id.convoke_fragment_progressbar);

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

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*
        convokeList.add(new ConvokeClass("Developer 1", "Country 1", "https://www.github.com", "https://www.facebook.com", R.drawable.artificial_intelligence));
        convokeList.add(new ConvokeClass("Developer 2", "Country 2", "https://www.github.com","https://www.facebook.com",R.drawable.machine));
        convokeList.add(new ConvokeClass("Developer 3", "Country 3", "https://www.github.com","https://www.facebook.com",R.drawable.artificial_intelligence));
        convokeList.add(new ConvokeClass("Developer 4", "Country 4", "https://www.github.com","https://www.facebook.com",R.drawable.machine));
        convokeList.add(new ConvokeClass("Developer 5", "Country 5", "https://www.github.com","https://www.facebook.com",R.drawable.artificial_intelligence));

        //For caching, after FireBase integration



        int noOfSpeakers;
        for(int i=0;i<noOfSpeakers;++i)
        {
            convokeList.add(new ConvokeList(database.getChild...);

        }

         SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());;
         SharedPreferences.Editor editor = sharedPreferences.edit();
         Editor prefsEditor = appSharedPrefs.edit();
         Gson gson = new Gson();
         String json = gson.toJson(convokeList);
         prefsEditor.putString("ConvokeListData", json);
         prefsEditor.commit();

         */

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

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        onItemChanged(convokeList.get(positionInDataSet));
    }

    private void onItemChanged(ConvokeClass sponsor) {
        //currentSponsorName.setText(sponsor.getName());
        //currentSponsorType.setText(sponsor.getData());

    }
}
