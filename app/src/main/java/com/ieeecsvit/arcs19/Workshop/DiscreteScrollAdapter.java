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

    public class ScrollViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;

        public ScrollViewHolder(View itemView) {
            super(itemView);
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
        int id = mContext.getResources().getIdentifier(dataList.get(position).getIcon(), "drawable", mContext.getPackageName());
        if (dataList.get(position).getName().equals("Machine Learning")) {
            holder.icon.setScaleX(1.4f);
            holder.icon.setScaleY(1.4f);

        }
        Drawable drawable = mContext.getResources().getDrawable(id);
        holder.icon.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



}