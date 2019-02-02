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
import android.widget.TextView;

import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;

public class WorkShopFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener, View.OnClickListener{

    DiscreteScrollView scrollView;
    ArrayList<DiscreteScrollClass> items;
    DiscreteScrollAdapter adapter;
    TextView header, description, price, location;
    Button bookNow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.workshop_fragment,container,false);

        description = rootview.findViewById(R.id.description);
        header = rootview.findViewById(R.id.header);
        scrollView = rootview.findViewById(R.id.discreteScroll);
        price = rootview.findViewById(R.id.price);
        location = rootview.findViewById(R.id.location);
        bookNow = rootview.findViewById(R.id.book_button);

        //Discrete Scroll View
        adapter = new DiscreteScrollAdapter();

        items = new ArrayList<DiscreteScrollClass>();
        items = adapter.setList();

        scrollView.setOffscreenItems(4);
        scrollView.setOverScrollEnabled(true);
        scrollView.setAdapter(adapter);
        scrollView.addOnItemChangedListener(this);
        scrollView.setSlideOnFling(true);
        scrollView.setItemTransitionTimeMillis(150);
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());
        onItemChanged(items.get(0));


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

    }

    @Override
    public void onCurrentItemChanged(@NonNull RecyclerView.ViewHolder viewHolder, int adapterPosition) {
        onItemChanged(items.get(adapterPosition));
    }

    public void onItemChanged(DiscreteScrollClass obj)
    {
        header.setText(obj.getName());
        description.setText(obj.getDes());
    }
}
