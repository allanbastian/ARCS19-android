package android.ieeecsvit.com.arcs19.Schedule;

import android.content.Context;
import android.ieeecsvit.com.arcs19.R;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScheduleItemAdapter extends ArrayAdapter<ScheduleItemClass> {

    Context context;
    ArrayList<ScheduleItemClass> items;
    ScheduleItemClass scheduleItemClass;


    public ScheduleItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ScheduleItemClass> items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup itemView) {
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.schedule_items_adapter, null);
        }

        TextView agenda, timing;
        ImageView icon;

        agenda = convertView.findViewById(R.id.agenda);
        timing = convertView.findViewById(R.id.timing);
        icon = convertView.findViewById(R.id.agenda_icon);

        scheduleItemClass = getItem(position);

        if(scheduleItemClass!=null) {
            //Inflating item layout
            agenda.setText(scheduleItemClass.getAgenda());
            timing.setText(scheduleItemClass.getTiming());

            //Set Image
            int id = getContext().getResources().getIdentifier("drawable/"+scheduleItemClass.getIconName(), null, getContext().getPackageName());
            icon.setImageResource(id);

        }

        return convertView;
    }

}
