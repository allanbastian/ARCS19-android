package com.ieeecsvit.arcs19.Hackathon;

import android.app.Dialog;
import android.content.Context;
import com.ieeecsvit.arcs19.GlideApp;
import com.ieeecsvit.arcs19.R;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class HackathonRecyclerAdapter extends RecyclerView.Adapter<HackathonRecyclerAdapter.MyViewHolder>{

    Context mContext;
    List<HackathonClass> mData;
    Dialog myDialog;

    public HackathonRecyclerAdapter(Context mContext, List<HackathonClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_question,viewGroup,false);
        final MyViewHolder vHolder = new MyViewHolder(v);


        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_Question.setText(mData.get(i).getQuestion());
        GlideApp.with(mContext).load(mData.get(i).getLogo()).into(myViewHolder.tv_logo);



        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_hackathon);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        myViewHolder.item_question.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                TextView dialog_name_tv =  myDialog.findViewById(R.id.track_name);
                TextView tv_question_about = myDialog.findViewById(R.id.question_about);
                dialog_name_tv.setText(mData.get(i).getDescribe());
                tv_question_about.setText(mData.get(i).getAbout());
                tv_question_about.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
                myDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private CardView item_question;
        private TextView tv_Question;
        private ImageView tv_logo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_question =  itemView.findViewById(R.id.question_item);
            tv_Question = (TextView) itemView.findViewById(R.id.question);
            tv_logo = itemView.findViewById(R.id.hackathon_question_logo);
        }
    }

}
