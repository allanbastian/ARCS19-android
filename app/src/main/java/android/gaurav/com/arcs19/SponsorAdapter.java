package android.gaurav.com.arcs19;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.CustomViewHolder> {

    private ArrayList<DiscreteScrollClass> dataList;

    public SponsorAdapter(ArrayList<DiscreteScrollClass> dataList){

        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView name;
        public CustomViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sponsor_name);
            image = itemView.findViewById(R.id.sponsor_image);

        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.sponsor_item_view, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SponsorAdapter.CustomViewHolder holder, int i) {
        Glide.with(holder.itemView.getContext()).load(dataList.get(i).getIcon()).into(holder.image);
        holder.name.setText(dataList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
