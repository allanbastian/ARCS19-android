package com.ieeecsvit.arcs19.Sponsor;

import android.content.Context;
import android.content.Intent;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ieeecsvit.arcs19.DiscreteScrollClass;
import com.ieeecsvit.arcs19.GlideApp;
import com.ieeecsvit.arcs19.R;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.CustomViewHolder> {

    private ArrayList<DiscreteScrollClass> dataList;
    private Context context;
    public SponsorAdapter(Context context,ArrayList<DiscreteScrollClass> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private CardView sponsor;
        private ImageView image;
        private ProgressBar progressBar;
        //private TextView name;
        public CustomViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.sponsor_image_progressbar);
            sponsor = itemView.findViewById(R.id.sponsor_root_layout);
            //name = itemView.findViewById(R.id.sponsor_name);
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
    public void onBindViewHolder(@NonNull final SponsorAdapter.CustomViewHolder holder, final int i) {
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
        //holder.name.setText(dataList.get(i).getName());
        // Opening Sponsor's website via browser
        holder.sponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(dataList.get(i).getsDesc()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
