package android.gaurav.com.arcs19;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class WorkShopFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener, View.OnClickListener{

    DiscreteScrollView scrollView;
    ArrayList<DiscreteScrollClass> workshopList;
    DiscreteScrollAdapter adapter;
    ImageView authImg;
    TextView header, description, price, location, authName, authDesc;
    Button bookNow;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.workshop_fragment,container,false);

        description = rootview.findViewById(R.id.workshop_description);
        header = rootview.findViewById(R.id.workshop_name);
        scrollView = rootview.findViewById(R.id.discreteScroll);
        price = rootview.findViewById(R.id.workshop_price);
        location = rootview.findViewById(R.id.workshop_location);
        bookNow = rootview.findViewById(R.id.book_button);
        authDesc = rootview.findViewById(R.id.workshop_speaker_description);
        authImg= rootview.findViewById(R.id.workshop_speaker_img);
        authName = rootview.findViewById(R.id.workshop_speaker_name);

        rootview.findViewById(R.id.workshop_previous_button).setOnClickListener(this);
        rootview.findViewById(R.id.workshop_next_button).setOnClickListener(this);

        //Discrete Scroll View
        adapter = new DiscreteScrollAdapter();

        workshopList = new ArrayList<DiscreteScrollClass>();
        workshopList = adapter.setList();

        scrollView.setOffscreenItems(4);
        scrollView.setOverScrollEnabled(true);
        scrollView.setAdapter(adapter);
        scrollView.addOnItemChangedListener(this);
        scrollView.setSlideOnFling(true);
        scrollView.setItemTransitionTimeMillis(150);
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        onItemChanged(workshopList.get(0));


        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookNow.setBackgroundResource(R.drawable.orange_button_clicked_curve);
            }
        });



        return rootview;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.workshop_previous_button:
                int realpos= scrollView.getCurrentItem();
                if(realpos>0) {
                    realpos--;
                    scrollView.scrollToPosition(realpos);
                    scrollView.smoothScrollToPosition(realpos);
                    onItemChanged(workshopList.get(realpos));
                }
                break;
            case R.id.workshop_next_button:
                realpos=scrollView.getCurrentItem();
                if(realpos<workshopList.size()-1) {
                    realpos++;
                    scrollView.scrollToPosition(realpos);
                    scrollView.smoothScrollToPosition(realpos);
                    onItemChanged(workshopList.get(realpos));
                }
                break;
        }
    }

    @Override
    public void onCurrentItemChanged(@NonNull RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        onItemChanged(workshopList.get(adapterPosition));
    }

    public void onItemChanged(DiscreteScrollClass obj)
    {
        header.setText(obj.getName());
        description.setText(obj.getDes());
        price.setText(String.valueOf(obj.getPrice()));
        location.setText(obj.getLocation());
        authDesc.setText(obj.getAuthDesc());
        authImg.setImageResource(obj.getAuthImg());
        authName.setText(obj.getAuthName());
    }
}