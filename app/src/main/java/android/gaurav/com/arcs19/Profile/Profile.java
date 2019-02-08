package android.gaurav.com.arcs19.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.gaurav.com.arcs19.Login.LoginActivity;
import android.gaurav.com.arcs19.MainActivity;
import android.gaurav.com.arcs19.R;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class Profile extends AppCompatActivity {
    DiscreteScrollView scrollView;
    ArrayList<ProfileScrollClass> items;
    ProfileScrollAdapter adapter;
    ImageButton qrButton, backButton;
    FloatingActionButton signOutButton;

    String USERNAME = "";
    String EMAIL = "";
    SharedPreferences sp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        qrButton = findViewById(R.id.qr_code_button);
        backButton = findViewById(R.id.back_button);
        signOutButton = findViewById(R.id.sign_out_button);


        sp = getSharedPreferences("key",0);

        USERNAME = sp.getString("email","");


        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Logout();

            }
        });

        scrollView = findViewById(R.id.profileScroll);
         //profile scroll adapter
        adapter = new ProfileScrollAdapter();

        items = new ArrayList<ProfileScrollClass>();
        items = adapter.setList();

        scrollView.setOffscreenItems(4);
        scrollView.setOverScrollEnabled(true);
        scrollView.setAdapter(adapter);
        scrollView.setSlideOnFling(true);
        scrollView.setItemTransitionTimeMillis(150);
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        onItemChanged(items.get(0));

    }

    private void onItemChanged(ProfileScrollClass profileScrollClass) {
    }

    private void Logout()
    {
        SharedPreferences.Editor editor= sp.edit();
        editor.remove("username").commit();
        editor.remove("email").commit();
        editor.remove("password").commit();
        editor.remove("loginStatus").commit();
        startActivity(new Intent(Profile.this, LoginActivity.class));
    }

}
