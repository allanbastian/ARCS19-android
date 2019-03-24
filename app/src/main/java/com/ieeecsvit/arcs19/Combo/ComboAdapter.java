package com.ieeecsvit.arcs19.Combo;

import android.content.Context;
import android.content.Intent;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ieeecsvit.arcs19.DiscreteScrollClass;

import com.ieeecsvit.arcs19.GlideApp;
import com.ieeecsvit.arcs19.R;
import com.ieeecsvit.arcs19.RegisterWebView;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ComboAdapter extends RecyclerView.Adapter<ComboAdapter.CustomViewHolder>{

    private ArrayList<DiscreteScrollClass> dataList;
    private Context context;
    public ComboAdapter(Context context,ArrayList<DiscreteScrollClass> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private ProgressBar progressBar;
        private TextView name, shortDes, price, viewMore;
        public CustomViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.combo_image_progressbar);
            name = itemView.findViewById(R.id.combo_id);
            shortDes = itemView.findViewById(R.id.combo_info_brief);
            image = itemView.findViewById(R.id.combo_image);
            price = itemView.findViewById(R.id.combo_price);
            viewMore = itemView.findViewById(R.id.combo_view_more);

        }
    }

    @NonNull
    @Override
    public ComboAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.combo_card, parent, false);
        return new ComboAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ComboAdapter.CustomViewHolder holder, int i) {
        //Glide.with(holder.itemView.getContext()).load(dataList.get(i).getImage()).into(holder.image);

        GlideApp.with(holder.itemView.getContext()).load(dataList.get(i).getImage()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                holder.image.setVisibility(View.VISIBLE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                holder.image.setVisibility(View.VISIBLE);
                return false;
            }
        }).into(holder.image);

        final String eventName = dataList.get(i).getName();
        holder.name.setText(eventName);
        holder.price.setText(dataList.get(i).getPrice());
        holder.shortDes.setText(dataList.get(i).getsDesc());

        holder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegisterWebView.class);
                intent.putExtra("eventName",eventName);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
