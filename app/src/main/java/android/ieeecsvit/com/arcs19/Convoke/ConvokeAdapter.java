package android.ieeecsvit.com.arcs19.Convoke;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.ieeecsvit.com.arcs19.GlideApp;
import android.ieeecsvit.com.arcs19.R;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ConvokeAdapter extends RecyclerView.Adapter<ConvokeAdapter.CustomViewHolder> {

    private ArrayList<ConvokeClass> dataList;
    private Context context;
    Dialog myDialog;

    public ConvokeAdapter(Context context, ArrayList<ConvokeClass> dataList) {

        this.dataList = dataList;
        this.context = context;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private int originalHeight = 0;
        private boolean isViewExpanded = false;
        private CardView convokeItem;
        private ImageView image;
        private TextView name;
        private TextView details;
        private TextView topic;
        private ImageView facebook;


        public CustomViewHolder(Context context, View itemView) {

            super(itemView);
            convokeItem = itemView.findViewById(R.id.convoke_root_item);
            topic = itemView.findViewById(R.id.convoke_topic);
            name = itemView.findViewById(R.id.convoke_name);
            image = itemView.findViewById(R.id.convoke_image);
            facebook = itemView.findViewById(R.id.developer_facebook);


        }

        @Override
        public void onClick(View v) {

        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_convoke_view, parent, false);
        return new CustomViewHolder(parent.getContext(), view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final ConvokeAdapter.CustomViewHolder holder, final int i) {
        holder.topic.setText(dataList.get(i).getmConvokeCountry());
        holder.name.setText(dataList.get(i).getmConvokeName());

        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.dialog_about_speaker);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        //holder.image.setImageResource(dataList.get(i).getmConvokeImage());
        //example .load(FirebaseStorage.getInstance().getReference().child("Convoke").child("speakerName.png")) works
        GlideApp.with(context).load(dataList.get(i).getmStorageReference()).into(holder.image);

        //for FaceBook button onClick- open FaceBook Link
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
        //for CardView click- Show and hide details
        holder.convokeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = myDialog.findViewById(R.id.about_speaker_image);
                TextView textView = myDialog.findViewById(R.id.about_speaker_details);
                GlideApp.with(context).load(dataList.get(i).getmStorageReference()).into(imageView);
                textView.setText(dataList.get(i).getmConvokeDetails());
                myDialog.show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
