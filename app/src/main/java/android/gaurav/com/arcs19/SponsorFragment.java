package android.gaurav.com.arcs19;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class SponsorFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener,View.OnClickListener{

    ArrayList<DiscreteScrollClass> sponsorList = new ArrayList<DiscreteScrollClass>();
    //TextView currentSponsorName;
    //TextView currentSponsorType;
    ImageView previousSponsorButton;
    ImageView nextSponsorButton;

    DiscreteScrollView itemPicker;
    InfiniteScrollAdapter infiniteAdapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sponsors, container, false);

        //The Sponsors' database.
        sponsorList.add(new DiscreteScrollClass(R.drawable.machine,"Sponsor 1","https://www.google.com"));
        sponsorList.add(new DiscreteScrollClass(R.drawable.artificial_intelligence,"Sponsor 2","https://www.google.com"));
        sponsorList.add(new DiscreteScrollClass(R.drawable.machine,"Sponsor 3","https://www.google.com"));
        sponsorList.add(new DiscreteScrollClass(R.drawable.artificial_intelligence,"Sponsor 4","https://www.google.com"));

        previousSponsorButton = rootView.findViewById(R.id.previous_sponsor_button);
        nextSponsorButton = rootView.findViewById(R.id.next_sponsor_button);

        itemPicker = rootView.findViewById(R.id.item_picker);
        itemPicker.setOrientation(DSVOrientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);

        infiniteAdapter = InfiniteScrollAdapter.wrap(new SponsorAdapter(getContext(),sponsorList));

        itemPicker.setAdapter(infiniteAdapter);
        itemPicker.setItemTransitionTimeMillis(150);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder().setMinScale(0.8f).build());


        rootView.findViewById(R.id.previous_sponsor_button).setOnClickListener(this);
        rootView.findViewById(R.id.next_sponsor_button).setOnClickListener(this);

        itemPicker.setSlideOnFling(true);
        itemPicker.setSlideOnFlingThreshold(2500);

        return rootView;
    }



    private void onItemChanged(DiscreteScrollClass sponsor) {
        //currentSponsorName.setText(sponsor.getName());
        //currentSponsorType.setText(sponsor.getData());

    }

    private void smoothScrollToNextPosition( DiscreteScrollView scrollView, int pos) {
        InfiniteScrollAdapter adapter = (InfiniteScrollAdapter) scrollView.getAdapter();
        int destination;
        if  (pos< sponsorList.size()-1){
             destination = pos + 1;
        }else
        {
            destination = 0;
        }
        if (adapter != null) {
            destination = adapter.getClosestPosition(destination);
        }
        scrollView.smoothScrollToPosition(destination);
    }

    private void smoothScrollToPreviousPosition( DiscreteScrollView scrollView, int pos) {
        InfiniteScrollAdapter adapter = (InfiniteScrollAdapter) scrollView.getAdapter();
        int destination = pos - 1;
        if (adapter != null) {
            destination = adapter.getClosestPosition(destination);
        }
        scrollView.smoothScrollToPosition(destination);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.previous_sponsor_button:
                int realPosition = infiniteAdapter.getRealPosition(itemPicker.getCurrentItem());
                smoothScrollToPreviousPosition(itemPicker,realPosition);
                break;
            case R.id.next_sponsor_button:
                realPosition = infiniteAdapter.getRealPosition(itemPicker.getCurrentItem());
                smoothScrollToNextPosition(itemPicker,realPosition);
                break;

        }

    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int position) {
        int positionInDataSet = infiniteAdapter.getRealPosition(position);
        onItemChanged(sponsorList.get(positionInDataSet));
    }
}
