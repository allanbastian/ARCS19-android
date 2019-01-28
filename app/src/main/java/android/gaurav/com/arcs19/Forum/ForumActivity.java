package android.gaurav.com.arcs19.Forum;

import android.content.SharedPreferences;
import android.gaurav.com.arcs19.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForumActivity extends AppCompatActivity {

    RecyclerView queryRecyler;
    EditText msgContainer;
    ImageButton sendButton;
    ForumAdapter forumAdapter;
    String USERNAME, EMAIL;
    List<ForumClass> queries;         //The list contains all the messages
    SharedPreferences sp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        queryRecyler = findViewById(R.id.query_recycler);
        msgContainer = findViewById(R.id.msg_container);
        sendButton = findViewById(R.id.send_button);

        //Initialising the recycler view
        queryRecyler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);                                          //Stacking the new messages from bottom
        queryRecyler.setLayoutManager(linearLayoutManager);                                 //Setting the layout linear


        //Initialise username
        sp = getSharedPreferences("key",0);
        USERNAME = sp.getString("username","");     //Getting Username
        EMAIL = sp.getString("email","");           //Getting email


        queries = new ArrayList<ForumClass>();


        //Sample code
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        String timestamp = simpleDateFormat.format(new Date());
        queries.add(new ForumClass("Admin","How can I help","Admin",timestamp));
        queries.add(new ForumClass("Gaurav","Can you suggest me a haunting place?","User",timestamp));
        queries.add(new ForumClass("Admin","Sure","Admin",timestamp));
        queries.add(new ForumClass("Gaurav","I am sharing my location.....","User",timestamp));
        //Sample Code Ends

        //Setting the list adapter
        forumAdapter = new ForumAdapter(this, queries);
        queryRecyler.setAdapter(forumAdapter);


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!msgContainer.getText().toString().isEmpty())
                {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
                    String timestamp = simpleDateFormat.format(new Date());

                    /*Add to the firebase database using Forum Class*/

                    //Sample
                    queries.add(new ForumClass(USERNAME,msgContainer.getText().toString(),"User",timestamp));
                    forumAdapter = new ForumAdapter(getApplicationContext(), queries);
                    queryRecyler.setAdapter(forumAdapter);

                    msgContainer.getText().clear();
                }
            }
        });

    }
}
