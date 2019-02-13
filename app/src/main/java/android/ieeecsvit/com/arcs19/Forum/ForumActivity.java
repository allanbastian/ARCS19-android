package android.ieeecsvit.com.arcs19.Forum;

import android.content.SharedPreferences;
import android.ieeecsvit.com.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ForumActivity extends AppCompatActivity {

    RecyclerView queryRecyler;
    EditText msgContainer;
    ImageButton sendButton;
    ProgressBar progressBar;
    ForumAdapter forumAdapter;
    String USERNAME, EMAIL;
    List<ForumClass> queries;         //The list contains all the messages
    SharedPreferences sp;

    //Firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;

    //Key of the new message
    int key = 0;

    //Connection status
    boolean connected = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        queryRecyler = findViewById(R.id.query_recycler);
        msgContainer = findViewById(R.id.msg_container);
        sendButton = findViewById(R.id.send_button);
        progressBar = findViewById(R.id.progress_bar);

        //Initialise Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("Forum");         //Forum node
        ref.keepSynced(true);                                       //Synced with database

        getMessage();

        //Initialising the recycler view
        queryRecyler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);                                          //Stacking the new messages from bottom
        queryRecyler.setLayoutManager(linearLayoutManager);                                 //Setting the layout linear


        //Initialise username
        sp = getSharedPreferences("key",0);
        USERNAME = sp.getString("username","");     //Getting Username
        EMAIL = sp.getString("email","");           //Getting email


        //Setting the list adapter
        queries = new ArrayList<ForumClass>();
        forumAdapter = new ForumAdapter(this, queries);
        queryRecyler.setAdapter(forumAdapter);

        //Checking the connectivity firebase
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                connected = snapshot.getValue(Boolean.class);
                if (!connected) {
                    Toast.makeText(getApplicationContext(),"Connection Lost !",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Connection established !",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!msgContainer.getText().toString().isEmpty())
                {
                    sendMessage();
                }
            }
        });

    }

    //Get the list of messages
    private void getMessage()
    {
        progressBar.setVisibility(View.VISIBLE);
        ref.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ForumClass object = dataSnapshot.getValue(ForumClass.class);
                key = Integer.parseInt(dataSnapshot.getKey()) + 1;
                queries.add(object);
                forumAdapter = new ForumAdapter(getApplicationContext(), queries);
                queryRecyler.setAdapter(forumAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Send the typed message
    private void sendMessage()
    {
        //Message is sent if the app is connected to firebase
        if(connected) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM hh:mmaa");
            String timestamp = simpleDateFormat.format(new Date());

            /*Add to the firebase database using Forum Class*/
            ForumClass object = new ForumClass(USERNAME, EMAIL, msgContainer.getText().toString(), "user", timestamp);
            msgContainer.getText().clear();
            ref.child(key + "").setValue(object);
        }
        else
        {
            //It app is not connected to firebase
            Toast.makeText(getApplicationContext(),"Connection Lost !",Toast.LENGTH_LONG).show();
        }

    }



}
