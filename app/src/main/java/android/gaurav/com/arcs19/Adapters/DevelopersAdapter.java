package android.gaurav.com.arcs19.Adapters;

import android.content.Context;
import android.gaurav.com.arcs19.Developers_data;
import android.gaurav.com.arcs19.R;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DevelopersAdapter extends RecyclerView.Adapter<DevelopersAdapter.MyViewHolder> {

    private Context mContext;
    private List<Developers_data> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, post;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            post = (TextView) view.findViewById(R.id.developer_post);
        }
    }


    public DevelopersAdapter(Context mContext, List<Developers_data> albumList) {
        this.mContext = mContext;
        this.albumList = albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developers_card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Developers_data album = albumList.get(position);
        holder.title.setText(album.getName());
        holder.post.setText(album.getPost());
    }

    @Override
    public int getItemCount() {
        return albumList.size();
    }
}
