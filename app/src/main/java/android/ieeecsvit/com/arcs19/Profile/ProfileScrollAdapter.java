package android.ieeecsvit.com.arcs19.Profile;

import android.ieeecsvit.com.arcs19.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;


public class ProfileScrollAdapter extends RecyclerView.Adapter<ProfileScrollAdapter.ScrollViewHolder>{

    ArrayList<ProfileScrollClass> items = new ArrayList<ProfileScrollClass>();


    public ProfileScrollAdapter(){
        items.add(new ProfileScrollClass(R.drawable.machine,"Machine Learning"));
        items.add(new ProfileScrollClass(R.drawable.artificial_intelligence,"Artificial intelligence"));
        items.add(new ProfileScrollClass(R.drawable.machine,"Machine Learning"));
        items.add(new ProfileScrollClass(R.drawable.artificial_intelligence,"Artificial intelligence"));

    }
    public ArrayList<ProfileScrollClass> setList(){
        return items;
    }
    @Override
    public ProfileScrollAdapter.ScrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.profile_scroll_adapter,parent,false);
        return new ProfileScrollAdapter.ScrollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileScrollAdapter.ScrollViewHolder scrollViewHolder, int i) {
        scrollViewHolder.icon.setImageResource(items.get(i).getIcon());

    }


    public class ScrollViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        public ScrollViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.profile_imageIcon);
        }
    }
    @Override
    public int getItemCount() {
        return items.size();
    }


}
