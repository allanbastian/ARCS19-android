package android.gaurav.com.arcs19.Developers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.gaurav.com.arcs19.DiscreteScrollClass;
import android.gaurav.com.arcs19.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.CustomViewHolder> {

    private ArrayList<DiscreteScrollClass> dataList;

    public DeveloperAdapter(ArrayList<DiscreteScrollClass> dataList){

        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView post;


        public CustomViewHolder(Context context, View itemView) {

            super(itemView);
            post = itemView.findViewById(R.id.developer_post);
            name = itemView.findViewById(R.id.developer_name);



        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.developers_card, parent, false);
        return new CustomViewHolder(parent.getContext(),view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final DeveloperAdapter.CustomViewHolder holder, final int i) {
        holder.name.setText(dataList.get(i).getName());
        holder.post.setText(dataList.get(i).getDes());
        };




    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
