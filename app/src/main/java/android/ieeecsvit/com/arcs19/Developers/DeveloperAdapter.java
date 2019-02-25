package android.ieeecsvit.com.arcs19.Developers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.ieeecsvit.com.arcs19.DiscreteScrollClass;
import android.ieeecsvit.com.arcs19.GlideApp;
import android.ieeecsvit.com.arcs19.R;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.CustomViewHolder> {

    private ArrayList<DiscreteScrollClass> dataList;
    private Context mContext;

    public DeveloperAdapter(ArrayList<DiscreteScrollClass> dataList, Context mContext){

        this.dataList = dataList;
        this.mContext = mContext;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        private TextView name,post;
        private ImageView image,linkedin,github;


        public CustomViewHolder(Context context, View itemView) {

            super(itemView);
            post = itemView.findViewById(R.id.developer_post);
            name = itemView.findViewById(R.id.developer_name);
            image = itemView.findViewById(R.id.developer_image);
            linkedin = itemView.findViewById(R.id.developers_linkedin);
            github = itemView.findViewById(R.id.developers_github);



        }
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.developers_card, parent, false);
        return new CustomViewHolder(parent.getContext(),view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final DeveloperAdapter.CustomViewHolder holder, final int i) {
        holder.name.setText(dataList.get(i).getName());
        holder.post.setText(dataList.get(i).getDes());
        holder.github.setClickable(true);
        holder.linkedin.setClickable(true);
        //GlideApp.with(mContext).load(dataList.get(i).getImage()).into(holder.image);
        int id = mContext.getResources().getIdentifier(dataList.get(i).getIcon(), "drawable", mContext.getPackageName());
        Drawable drawable = mContext.getResources().getDrawable(id);
        holder.image.setImageDrawable(drawable);
        holder.github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(dataList.get(i).getSname()));
                mContext.startActivity(intent);
            }
        });

        holder.linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(dataList.get(i).getsDesc()));
                mContext.startActivity(intent);
            }
        });
    };




    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
