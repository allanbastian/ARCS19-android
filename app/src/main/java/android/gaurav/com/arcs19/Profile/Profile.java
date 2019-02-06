package android.gaurav.com.arcs19.Profile;

import android.gaurav.com.arcs19.R;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;


public class Profile extends AppCompatActivity {
    DiscreteScrollView scrollView;
    ArrayList<ProfileScrollClass> items;
    ProfileScrollAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageButton imageButton = findViewById(R.id.qr_code_button);
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
