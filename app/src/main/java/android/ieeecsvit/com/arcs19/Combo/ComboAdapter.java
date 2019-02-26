package android.ieeecsvit.com.arcs19.Combo;

import android.content.Context;
import android.content.Intent;
import android.ieeecsvit.com.arcs19.DiscreteScrollClass;
import android.ieeecsvit.com.arcs19.R;
import android.ieeecsvit.com.arcs19.RegisterWebView;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
        private TextView name, shortDes, price, viewMore;
        public CustomViewHolder(View itemView) {
            super(itemView);
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
    public void onBindViewHolder(@NonNull final ComboAdapter.CustomViewHolder holder, final int i) {
        Glide.with(holder.itemView.getContext()).load(dataList.get(i).getImage()).into(holder.image);
        holder.name.setText(dataList.get(i).getName());
        holder.price.setText(dataList.get(i).getPrice());
        holder.shortDes.setText(dataList.get(i).getsDesc());

        holder.viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, RegisterWebView.class));
            }
        });
        // Opening Sponsor's website via browser
        /*holder.sponsor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(dataList.get(i).getsDesc()));
                context.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
