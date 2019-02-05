package android.gaurav.com.arcs19;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ConvokeAdapter extends RecyclerView.Adapter<ConvokeAdapter.CustomViewHolder> {

    private ArrayList<ConvokeClass> dataList;
    private Context context;

    public ConvokeAdapter(Context context, ArrayList<ConvokeClass> dataList){

        this.dataList = dataList;
        this.context = context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private CardView developer;
        private ImageView image;
        private TextView name;
        private TextView country;
        private CheckBox bookmark;
        private ImageView facebook;
        //private ImageView instagram;
       //private ImageView twitter;
        private Button follow;


        public CustomViewHolder(Context context, View itemView) {

            super(itemView);
            developer = itemView.findViewById(R.id.developer_details);
            country = itemView.findViewById(R.id.developer_country);
            name = itemView.findViewById(R.id.convoke_name);
            image = itemView.findViewById(R.id.convoke_image);
            bookmark = itemView.findViewById(R.id.developer_bookmark);
            facebook = itemView.findViewById(R.id.developer_facebook);
            //instagram = itemView.findViewById(R.id.developer_instagram);
            //twitter = itemView.findViewById(R.id.developer_twitter);
            follow = itemView.findViewById(R.id.convoke_follow_button);


        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_convoke_view, parent, false);
        return new CustomViewHolder(parent.getContext(),view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ConvokeAdapter.CustomViewHolder holder, final int i) {
        holder.country.setText(dataList.get(i).getmConvokeCountry());
        holder.name.setText(dataList.get(i).getmConvokeName());
        holder.image.setImageResource(dataList.get(i).getmConvokeImage());
        holder.bookmark.setOnCheckedChangeListener(null);
        holder.bookmark.setChecked(dataList.get(i).getmConvokeBookmark());



        holder.bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataList.get(holder.getAdapterPosition()).setmConvokeBookmark(isChecked);
            }
        });


        /*if (holder.bookmark.isChecked()){
            holder.developer.setCardBackgroundColor(R.color.orange);
            dataList.get(i).setmDeveloperBookmark(true);
            notifyDataSetChanged();
        }else {
            holder.developer.setCardBackgroundColor(R.color.purple);
            dataList.get(i).setmDeveloperBookmark(false);
            notifyDataSetChanged();
        }*/
        //for FOLLOW button onClick
        holder.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(dataList.get(i).getmConvokeGithub()));
                context.startActivity(intent);
            }
        });
        /*holder.twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(dataList.get(i).getmDeveloperTwitter()));
                context.startActivity(intent);
            }
        });
        holder.instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(dataList.get(i).getmDeveloperInstagram()));
                context.startActivity(intent);
            }
        });*/


        //for FaceBook button onClick
        holder.facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(dataList.get(i).getmConvokeFacebook()));
                context.startActivity(intent);
            }
        });




    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
