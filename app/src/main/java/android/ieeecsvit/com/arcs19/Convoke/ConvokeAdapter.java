package android.ieeecsvit.com.arcs19.Convoke;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ConvokeAdapter extends RecyclerView.Adapter<ConvokeAdapter.CustomViewHolder> {

    private ArrayList<ConvokeClass> dataList;
    private Context context;

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
        //private ImageView instagram;
        //private ImageView twitter;
        //private Button follow;


        public CustomViewHolder(Context context, View itemView) {

            super(itemView);
            convokeItem = itemView.findViewById(R.id.convoke_root_item);
            details = itemView.findViewById(R.id.convoke_details);
            topic = itemView.findViewById(R.id.convoke_topic);
            name = itemView.findViewById(R.id.convoke_name);
            image = itemView.findViewById(R.id.convoke_image);
            facebook = itemView.findViewById(R.id.developer_facebook);
            //instagram = itemView.findViewById(R.id.developer_instagram);
            //twitter = itemView.findViewById(R.id.developer_twitter);
            //follow = itemView.findViewById(R.id.convoke_follow_button);


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
        holder.details.setVisibility(View.GONE);
        holder.details.setText(dataList.get(i).getmConvokeDetails());
        holder.topic.setText(dataList.get(i).getmConvokeCountry());
        holder.name.setText(dataList.get(i).getmConvokeName());
        //holder.image.setImageResource(dataList.get(i).getmConvokeImage());
        //example .load(FirebaseStorage.getInstance().getReference().child("Convoke").child("speakerName.png")) works
        GlideApp.with(context).load(dataList.get(i).getmStorageReference()).into(holder.image);


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
        /*holder.follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(dataList.get(i).getmConvokeGithub()));
                context.startActivity(intent);
            }
        });*/
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
                if(holder.details.getVisibility() == View.GONE){
                    holder.details.setVisibility(View.VISIBLE);

                    final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                    holder.details.measure(widthSpec, heightSpec);

                    ValueAnimator mAnimator = slideAnimator(0, holder.details.getMeasuredHeight());
                    mAnimator.start();
                }
                else {
                    int finalHeight = holder.details.getHeight();

                    ValueAnimator mAnimator = slideAnimator(finalHeight, 0);

                    mAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            //Height=0, but it set visibility to GONE
                            holder.details.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                    mAnimator.start();
                }
            }
            private ValueAnimator slideAnimator(int start, int end) {

                ValueAnimator animator = ValueAnimator.ofInt(start, end);

                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        //Update Height
                        int value = (Integer) valueAnimator.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = holder.details.getLayoutParams();
                        layoutParams.height = value;
                        holder.details.setLayoutParams(layoutParams);
                    }
                });
                return animator;
            }
        });




    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }


}
