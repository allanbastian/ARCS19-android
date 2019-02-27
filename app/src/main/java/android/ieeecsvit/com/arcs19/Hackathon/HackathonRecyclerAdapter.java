package android.ieeecsvit.com.arcs19.Hackathon;

import android.app.Dialog;
import android.content.Context;
import android.ieeecsvit.com.arcs19.GlideApp;
import android.ieeecsvit.com.arcs19.R;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

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

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.dialog_hackathon);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        vHolder.item_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialog_name_tv = (TextView) myDialog.findViewById(R.id.question_dialog);
                TextView dialog_question_tv = myDialog.findViewById(R.id.question_about);
                dialog_question_tv.setText(mData.get(vHolder.getAdapterPosition()).getDescribe());
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getQuestion());

                //Toast.makeText(mContext,"Test Click"+String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                myDialog.show();
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tv_Question.setText(mData.get(i).getQuestion());
        GlideApp.with(mContext).load(mData.get(i).getLogo()).into(myViewHolder.tv_logo);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout item_question;
        private TextView tv_Question;
        //private TextView tv_Describe;
        private ImageView tv_logo;
        //private LinearLayout projectLink;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //projectLink = itemView.findViewById(R.id.hackathon_project_link);
            item_question = (LinearLayout) itemView.findViewById(R.id.question_item);
            tv_Question = (TextView) itemView.findViewById(R.id.question);
            //tv_Describe = (TextView) itemView.findViewById(R.id.describe);
            tv_logo = itemView.findViewById(R.id.hackathon_question_logo);
        }
    }

}
