package android.ieeecsvit.com.arcs19.Developers;

import android.ieeecsvit.com.arcs19.Developers.DeveloperAdapter;
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

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class DeveloperFragment extends Fragment {

    //developerList is used to store the list of developers for the ARCS 2019 Androdi app.
    ArrayList<DiscreteScrollClass> developerList = new ArrayList<DiscreteScrollClass>();
    StorageReference storageReference;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_developers, container, false);

        //initializing the firebase storage reference for the Developers directory
        storageReference = FirebaseStorage.getInstance().getReference().child("Developers");

        //Loading data for the Developer's list
        //DiscreteScrollClass(StorageReference developerImage, null,String developerName,String developerPost, null, String LinkedLink,  null,null, String GithubLink));
        developerList.add(new DiscreteScrollClass("allan_bastian",null,"Allan Bastian", "Mentor",null,"https://www.linkedin.com/in/allan-bastian-31b4aa13b",null,null,"https://github.com/allanbastian"));
        developerList.add(new DiscreteScrollClass("siddharth",null,"Siddharth Goradia", "Designer and Developer",null,"https://www.linkedin.com/in/siddharth-goradia-831979155",null,null,"https://github.com/Siddharth-14"));
        developerList.add(new DiscreteScrollClass("gaurav_jha",null,"Gaurav Jha", "Developer",null,"https://www.linkedin.com/in/gaurav-jha-90aa9b152",null,null,"https://github.com/gauravjha70"));
        developerList.add(new DiscreteScrollClass("koushik_saha",null,"Koushik Saha", "Developer",null,"https://www.linkedin.com/in/koushik-saha-42317315b/",null,null,"https://github.com/Kshik02"));
        developerList.add(new DiscreteScrollClass("devarsh",null,"Devarsh Patel", "Developer",null,"https://www.linkedin.com/in/devarsh-patel-399971146",null,null,"https://github.com/dbpatel2000"));
        developerList.add(new DiscreteScrollClass("anish_ganguly",null,"Anish Ganguly", "Developer",null,"https://www.linkedin.com/in/anish-ganguly-5b4615142/",null,null,"https://github.com/Ragingvenomicity"));


        DeveloperAdapter adapter = new DeveloperAdapter(developerList,getContext());
        RecyclerView recyclerView= rootView.findViewById(R.id.dev_recycler_view);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
