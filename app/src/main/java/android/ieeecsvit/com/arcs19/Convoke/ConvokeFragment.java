package android.ieeecsvit.com.arcs19.Convoke;

import android.content.SharedPreferences;
import android.ieeecsvit.com.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ConvokeFragment extends Fragment {

    //convokeList is used to store the list of convoke speakers
    ArrayList<ConvokeClass> convokeList = new ArrayList<ConvokeClass>();

    //Cache
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_convoke, container, false);

        //Cache
        sp = this.getActivity().getSharedPreferences("key",0);

        sp.getBoolean("cacheAvailable",false);

        editor = sp.edit();
        editor.putBoolean("cache",false);




        //List of Convoke speakers- Caching .

        convokeList.add(new ConvokeClass("Developer 1", "Country 1", "https://www.github.com","https://www.facebook.com",R.drawable.artificial_intelligence));
        convokeList.add(new ConvokeClass("Developer 2", "Country 2", "https://www.github.com","https://www.facebook.com",R.drawable.machine));
        convokeList.add(new ConvokeClass("Developer 3", "Country 3", "https://www.github.com","https://www.facebook.com",R.drawable.artificial_intelligence));
        convokeList.add(new ConvokeClass("Developer 4", "Country 4", "https://www.github.com","https://www.facebook.com",R.drawable.machine));
        convokeList.add(new ConvokeClass("Developer 5", "Country 5", "https://www.github.com","https://www.facebook.com",R.drawable.artificial_intelligence));


        //For caching, after FireBase integration
        /*


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
