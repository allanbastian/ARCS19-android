package android.gaurav.com.arcs19;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context mContext;
    List<Hackathon> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Hackathon> mData) {
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

        vHolder.item_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView dialog_name_tv = (TextView) myDialog.findViewById(R.id.question_dialog);
                dialog_name_tv.setText(mData.get(vHolder.getAdapterPosition()).getQuestion());
                Toast.makeText(mContext,"Test Click"+String.valueOf(vHolder.getAdapterPosition()),Toast.LENGTH_SHORT).show();
                myDialog.show();
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tv_Question.setText(mData.get(i).getQuestion());
        myViewHolder.tv_Describe.setText(mData.get(i).getDescribe());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout item_question;
        private TextView tv_Question;
        private TextView tv_Describe;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_question = (LinearLayout) itemView.findViewById(R.id.question_item);
            tv_Question = (TextView) itemView.findViewById(R.id.question);
            tv_Describe = (TextView) itemView.findViewById(R.id.describe);
        }
    }

}
