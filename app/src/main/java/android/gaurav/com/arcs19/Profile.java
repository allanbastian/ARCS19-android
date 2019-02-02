package android.gaurav.com.arcs19;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.support.v7.widget.Toolbar;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;


public class Profile extends AppCompatActivity {
    View view;
    DiscreteScrollView scrollView;
    ArrayList<ProfileScrollClass> items;
    ProfileScrollAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        ImageButton imageButton = findViewById(R.id.qr_image);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

}
