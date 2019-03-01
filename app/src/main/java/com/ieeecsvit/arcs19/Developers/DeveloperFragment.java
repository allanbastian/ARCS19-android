package com.ieeecsvit.arcs19.Developers;

import com.ieeecsvit.arcs19.R;
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
    ArrayList<DeveloperClass> developerList = new ArrayList<DeveloperClass>();
    StorageReference storageReference;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_developers, container, false);

        //initializing the firebase storage reference for the Developers directory
        storageReference = FirebaseStorage.getInstance().getReference().child("Developers");

        //Loading data for the Developer's list
        //DiscreteScrollClass(StorageReference developerImage, null,String developerName,String developerPost, null, String LinkedLink,  null,null, String GithubLink));
        developerList.add(new DeveloperClass(R.drawable.allan_bastian,"Allan Bastian", "Mentor","https://www.linkedin.com/in/allan-bastian-31b4aa13b","https://github.com/allanbastian"));
        developerList.add(new DeveloperClass(R.drawable.siddharth,"Siddharth Goradia", "Designer and Developer","https://www.linkedin.com/in/siddharth-goradia-831979155","https://github.com/Siddharth-14"));
        developerList.add(new DeveloperClass(R.drawable.gaurav_jha,"Gaurav Jha", "Developer","https://www.linkedin.com/in/gaurav-jha-90aa9b152","https://github.com/gauravjha70"));
        developerList.add(new DeveloperClass(R.drawable.koushik_saha,"Koushik Saha", "Developer","https://www.linkedin.com/in/koushik-saha-42317315b/","https://github.com/Kshik02"));
        developerList.add(new DeveloperClass(R.drawable.devarsh,"Devarsh Patel", "Developer","https://www.linkedin.com/in/devarsh-patel-399971146","https://github.com/dbpatel2000"));
        developerList.add(new DeveloperClass(R.drawable.anish_ganguly,"Anish Ganguly", "Developer","https://www.linkedin.com/in/anish-ganguly-5b4615142/","https://github.com/Ragingvenomicity"));


        DeveloperAdapter adapter = new DeveloperAdapter(developerList,getContext());
        RecyclerView recyclerView= rootView.findViewById(R.id.dev_recycler_view);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_ALWAYS);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
