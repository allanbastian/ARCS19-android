package android.ieeecsvit.com.arcs19.Convoke;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.ieeecsvit.com.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ConvokeFragment extends Fragment {

    FirebaseDatabase firebaseDatabase;
    private DatabaseReference ref;

    String speakerName, speakerDetails, speakerImage, speakerTopic;
    Bitmap bitmap;
    int image;
    View rootView;
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

        //Initialise Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference().child("Convoke");         //Convoke node
        ref.keepSynced(true);                                       //Synced with database


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    speakerName = snapshot.getKey().toString();
                    speakerDetails = snapshot.child("details").getValue().toString();
                    speakerImage = snapshot.child("photo").getValue().toString();
                    speakerTopic = snapshot.child("topic").getValue().toString();
                    convokeList.add(new ConvokeClass(speakerName, speakerTopic, "https://www.github.com", "https://www.facebook.com",speakerImage));

                }

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

        ConvokeAdapter adapter = new ConvokeAdapter(getContext(),convokeList);
        RecyclerView recyclerView= rootView.findViewById(R.id.developer_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
