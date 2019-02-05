package android.gaurav.com.arcs19;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DiscreteScrollAdapter extends RecyclerView.Adapter<DiscreteScrollAdapter.ScrollViewHolder>{


    ArrayList<DiscreteScrollClass> dataList;

    public DiscreteScrollAdapter()
    {
        dataList = new ArrayList<DiscreteScrollClass>();
        dataList.add(new DiscreteScrollClass(R.drawable.artificial_intelligence, "Artificial Intelligence", "This is for AI #AIROXXX", "Technology Tower,VIT", "speak on AI" , 1000, R.drawable.artificial_intelligence, "Mr. AI"));
        dataList.add(new DiscreteScrollClass(R.drawable.machine, "Machine Learning", "This is for ML #MLROXXX", "Technology Tower,VIT", "ARCS2k19",  1001, R.drawable.machine,"Mr. ML"));
        dataList.add(new DiscreteScrollClass(R.drawable.artificial_intelligence, "Artificial Intelligence", "This is for AI #AIROXXX", "Technology Tower,VIT", "speak on AI" , 1000, R.drawable.artificial_intelligence,"Mr. AI"));
        dataList.add(new DiscreteScrollClass(R.drawable.machine, "Machine Learning", "This is for ML #MLROXXX", "Technology Tower,VIT", "ARCS2k19",  1001, R.drawable.machine, "Mr. ML"));

    }

    public ArrayList<DiscreteScrollClass> setList(){
        return dataList;
    }

    public class ScrollViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        /*TextView title;
        TextView wDesc;
        TextView price;
        TextView authDesc;
        ImageView authImg;
        TextView loc;*/

        public ScrollViewHolder(View itemView) {
            super(itemView);
            /*loc = itemView.findViewById(R.id.workshop_location);
            authImg = itemView.findViewById(R.id.workshop_speaker_img);
            authDesc = itemView.findViewById(R.id.workshop_speaker_description);
            title = itemView.findViewById(R.id.workshop_name);
            price = itemView.findViewById(R.id.workshop_price);
            wDesc = itemView.findViewById(R.id.workshop_description);*/
            icon = itemView.findViewById(R.id.imageIcon);
        }
    }

    @NonNull
    @Override
    public ScrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.discrete_scroll_adapter,parent,false);
        return new ScrollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScrollViewHolder holder, int position) {

        holder.icon.setImageResource(dataList.get(position).getIcon());
        /*holder.title.setText(dataList.get(position).getName());
        holder.price.setText(dataList.get(position).getPrice());
        holder.wDesc.setText(dataList.get(position).getDes());
        holder.authImg.setImageResource(dataList.get(position).getAuthImg());
        holder.authDesc.setText(dataList.get(position).getAuthDesc());
        holder.loc.setText(dataList.get(position).getLocation());*/
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



}