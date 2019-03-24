package com.ieeecsvit.arcs19.Workshop;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.ieeecsvit.arcs19.DiscreteScrollClass;
import com.ieeecsvit.arcs19.GlideApp;
import com.ieeecsvit.arcs19.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class DiscreteScrollAdapter extends RecyclerView.Adapter<DiscreteScrollAdapter.ScrollViewHolder>{

    Context mContext;
    List<DiscreteScrollClass> dataList;

    public DiscreteScrollAdapter(Context mContext, List<DiscreteScrollClass> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    /*ArrayList<DiscreteScrollClass> dataList;

    public DiscreteScrollAdapter()
    {
        dataList = new ArrayList<DiscreteScrollClass>();
        dataList.add(new DiscreteScrollClass(R.drawable.artificial_intelligence, "Artificial Intelligence", "This is for AI #AIROXXX", "Technology Tower,VIT", "speak on AI" , 1000, R.drawable.artificial_intelligence, "Mr. AI"));
        dataList.add(new DiscreteScrollClass(R.drawable.machine, "Machine Learning", "This is for ML #MLROXXX", "Technology Tower,VIT", "ARCS2k19",  1001, R.drawable.machine,"Mr. ML"));
        dataList.add(new DiscreteScrollClass(R.drawable.artificial_intelligence, "Artificial Intelligence", "This is for AI #AIROXXX", "Technology Tower,VIT", "speak on AI" , 1000, R.drawable.artificial_intelligence,"Mr. AI"));
        dataList.add(new DiscreteScrollClass(R.drawable.machine, "Machine Learning", "This is for ML #MLROXXX", "Technology Tower,VIT", "ARCS2k19",  1001, R.drawable.machine, "Mr. ML"));

    }*/

    /*public ArrayList<DiscreteScrollClass> setList(){
        return dataList;
    }*/

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

        //setting the image from firebase into the discrete scroll adapter
        //GlideApp.with(mContext).load(dataList.get(position).getImage()).into(holder.icon);
        int id = mContext.getResources().getIdentifier(dataList.get(position).getIcon(), "drawable", mContext.getPackageName());
        if (dataList.get(position).getName().equals("Machine Learning")) {
            holder.icon.setScaleX(1.4f);
            holder.icon.setScaleY(1.4f);

        }
        Drawable drawable = mContext.getResources().getDrawable(id);
        holder.icon.setImageDrawable(drawable);

        //holder.icon.setImageResource(dataList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



}