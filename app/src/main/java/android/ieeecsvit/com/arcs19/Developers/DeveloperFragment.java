package android.ieeecsvit.com.arcs19.Developers;

import android.ieeecsvit.com.arcs19.DiscreteScrollClass;
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

public class DeveloperFragment extends Fragment {

    //convokeList is used to store the list of convoke speakers
    ArrayList<DiscreteScrollClass> developerList = new ArrayList<DiscreteScrollClass>();;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_developers, container, false);
        //List of Convoke speakers- Caching .

        developerList.add(new DiscreteScrollClass(0,"Allan Bastian", "Mentor"));
        developerList.add(new DiscreteScrollClass(0,"Siddharth Goradia", "Designer and Developer"));
        developerList.add(new DiscreteScrollClass(0,"Gaurav Jha", "Developer"));
        developerList.add(new DiscreteScrollClass(0,"Devarsh Patel", "Developer"));
        developerList.add(new DiscreteScrollClass(0,"Anish Ganguly", "Developer"));
        developerList.add(new DiscreteScrollClass(0,"Koushik Saha", "Developer"));


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

        DeveloperAdapter adapter = new DeveloperAdapter(developerList);
        RecyclerView recyclerView= rootView.findViewById(R.id.dev_recycler_view);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
