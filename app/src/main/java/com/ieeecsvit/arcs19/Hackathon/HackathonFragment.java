package com.ieeecsvit.arcs19.Hackathon;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import com.ieeecsvit.arcs19.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class HackathonFragment extends Fragment {

    View v;
    RecyclerView mRecyclerView,membersRecyclerView;
    List<HackathonClass> lstquestion;
    //private ArrayList<DiscreteScrollClass> members;
    private EditText getName, getLink;
    private TextView teamName,questionAbout;
    private LinearLayout displayTeam,teamNameLayout;
    private Button docUploadButton,linkUploadButton;
    private Dialog linkDialog;
    private ImageButton enterButton, uploadEnter;
    private ProgressBar docUplaodProgress, questionUploadProgress;
    String domain, question, name, link;

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    String TeamName;

    StorageReference storageReference, filepath, icon;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference hackathonRef,fileUploadref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.hackathon_fragment,container,false);

        lstquestion = new ArrayList<>();

        //Initialise Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("Hackathon");
        hackathonRef = firebaseDatabase.getReference().child("Hackathon");   //Hackathon database reference
        fileUploadref = firebaseDatabase.getReference().child("FileUpload"); //FileUpload database reference
        hackathonRef.keepSynced(true);                                       //Synced with database
        fileUploadref.keepSynced(true);
        //Traversing the Hackathon branch for collecting the domains and questions, and storing in lstquestion



        //members=new ArrayList<>();

        /*members.add(new DiscreteScrollClass(0,"Memeber 1", "AppDev"));
        members.add(new DiscreteScrollClass(0,"Member 2", "WebDev"));
        members.add(new DiscreteScrollClass(0,"Member 3", "Pitching"));*/

        //View objects defined
        mRecyclerView = v.findViewById(R.id.hackathon_recyclerview);
        mRecyclerView.setVisibility(View.GONE);
        questionAbout = v.findViewById(R.id.question_about);

        getName = v.findViewById(R.id.et_team_name);
        teamName = v.findViewById(R.id.hackathon_team_name_tv);

        docUploadButton = v.findViewById(R.id.project_doc_upload);
        linkUploadButton = v.findViewById(R.id.link_upload_button);

        docUploadButton.setEnabled(false);
        linkUploadButton.setEnabled(false);

        displayTeam = v.findViewById(R.id.display_team_name_layout);
        teamNameLayout = v.findViewById(R.id.team_name_layout);

        enterButton = v.findViewById(R.id.team_name_button);
        displayTeam.setVisibility(View.GONE);

        docUplaodProgress= v.findViewById(R.id.doc_upload_progressbar);
        questionUploadProgress = v.findViewById(R.id.question_upload_progressbar);
        docUplaodProgress.setVisibility(View.GONE);

        sp = getActivity().getSharedPreferences("key", 0);



        hackathonRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(!snapshot.getKey().toString().equals("coupons")) {
                        domain= snapshot.getKey().toString(); //
                        question= snapshot.child("question").getValue().toString();
                        icon = storageReference.child(domain+".png");
                        lstquestion.add(new HackathonClass(domain,question, icon));
                    }
                }
                questionUploadProgress.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





        //Setting up dialog box for link upload
        linkDialog = new Dialog(v.getContext());
        linkDialog.setContentView(R.layout.dialog_hackathon_linkupload);
        linkDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        linkDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        uploadEnter = linkDialog.findViewById(R.id.gitlink_enter);
        getLink = linkDialog.findViewById(R.id.hackathon_gitlink_display);

        //Intent for choosing ppt, pptx, doc, docx or pdf for file upload;
        docUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                String[] mimeTypes = {"application/msword","application/vnd.openxmlformats-officedocument.wordprocessingml.document","application/vnd.ms-powerpoint","application/vnd.openxmlformats-officedocument.presentationml.presentation", "application/pdf"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                startActivityForResult(Intent.createChooser(intent,"Choose File"), 234);
            }
        });

        //Upload links work only after team name is set up

        if (sp.getString("teamName",null)==null) {
            enterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = getName.getText().toString();
                    editor = sp.edit();
                    editor.putString("teamName", name);
                    editor.apply();
                    if (!name.isEmpty()) {
                        teamName.setText(name);
                        displayTeam.setVisibility(View.VISIBLE);
                        teamNameLayout.setVisibility(View.GONE);
                        docUploadButton.setBackgroundResource(R.drawable.orange_button_curve);
                        linkUploadButton.setBackgroundResource(R.drawable.orange_button_curve);
                        docUploadButton.setEnabled(true);
                        linkUploadButton.setEnabled(true);

                    } else {
                        Toast.makeText(getContext(), "Field can not be empty", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }else{

            teamName.setText(sp.getString("teamName",null));
            displayTeam.setVisibility(View.VISIBLE);
            teamNameLayout.setVisibility(View.GONE);
            docUploadButton.setBackgroundResource(R.drawable.orange_button_curve);
            linkUploadButton.setBackgroundResource(R.drawable.orange_button_curve);
            docUploadButton.setEnabled(true);
            linkUploadButton.setEnabled(true);

        }


        linkUploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linkDialog.show();
            }
        });



        uploadEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                link = getLink.getText().toString();
                if(!link.isEmpty()) {
                    fileUploadref.child(name).child("GitHub").setValue(link);
                    Toast.makeText(getContext(), "GitHub link uploaded successfully!", Toast.LENGTH_SHORT).show();

                }
            }
        });





        HackathonRecyclerAdapter recyclerAdapter = new HackathonRecyclerAdapter(getContext(),lstquestion);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        mRecyclerView.setAdapter(recyclerAdapter);

        /*DeveloperAdapter memberAdapter = new DeveloperAdapter(members);
        membersRecyclerView = v.findViewById(R.id.hackathon_team_list);
        membersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        membersRecyclerView.setAdapter(memberAdapter);*/
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 234 && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();
            filepath = storageReference.child("Hackathon").child(name);
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    docUplaodProgress.setVisibility(View.GONE);
                    fileUploadref.child(name).child("ppt").setValue(filepath.getDownloadUrl().toString());
                    Toast.makeText(getContext(), "Project document uploaded successfully!", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @SuppressWarnings("VisibleForTests")
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    docUplaodProgress.setVisibility(View.VISIBLE);
                }
            });

        }
    }
}