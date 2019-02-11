package android.gaurav.com.arcs19.Hackathon;

import android.app.Dialog;
import android.gaurav.com.arcs19.Developers.DeveloperAdapter;
import android.gaurav.com.arcs19.DiscreteScrollClass;
import android.gaurav.com.arcs19.R;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HackathonFragment extends Fragment {

    View v;
    private RecyclerView mRecyclerView,membersRecyclerView;
    private List<HackathonClass> lstquestion;
    //private ArrayList<DiscreteScrollClass> members;
    private EditText getName;
    private TextView teamName,questionAbout;
    private LinearLayout displayTeam,teamNameLayout;
    private Button uploadButton;
    private Dialog myDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.hackathon_fragment,container,false);

        lstquestion = new ArrayList<>();
        //members=new ArrayList<>();
        lstquestion.add(new HackathonClass("Business","Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut", R.drawable.machine));
        lstquestion.add(new HackathonClass("Space","Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut",R.drawable.artificial_intelligence));
        lstquestion.add(new HackathonClass("Education","Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut",R.drawable.artificial_intelligence));
        lstquestion.add(new HackathonClass("Health","Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut",R.drawable.machine));

        /*members.add(new DiscreteScrollClass(0,"Memeber 1", "AppDev"));
        members.add(new DiscreteScrollClass(0,"Member 2", "WebDev"));
        members.add(new DiscreteScrollClass(0,"Member 3", "Pitching"));*/

        questionAbout = v.findViewById(R.id.question_about);

        getName = v.findViewById(R.id.et_team_name);
        teamName = v.findViewById(R.id.hackathon_team_name_tv);

        uploadButton = v.findViewById(R.id.link_upload_button);

        displayTeam = v.findViewById(R.id.display_team_name_layout);
        teamNameLayout = v.findViewById(R.id.team_name_layout);

        ImageButton enterButton = v.findViewById(R.id.team_name_button);
        displayTeam.setVisibility(View.GONE);

        /*questionAbout.setVisibility(View.GONE);
        myDialog = new Dialog(v.getContext());
        myDialog.setContentView(R.layout.dialog_hackathon);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;*/



        /*uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.show();
            }
        });*/

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = getName.getText().toString();
                if(!name.isEmpty()){
                    teamName.setText(name);
                    displayTeam.setVisibility(View.VISIBLE);
                    teamNameLayout.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "Field can not be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mRecyclerView = (RecyclerView) v.findViewById(R.id.hackathon_recyclerview);
        HackathonRecyclerAdapter recyclerAdapter = new HackathonRecyclerAdapter(getContext(),lstquestion);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        mRecyclerView.setAdapter(recyclerAdapter);

        /*DeveloperAdapter memberAdapter = new DeveloperAdapter(members);
        membersRecyclerView = v.findViewById(R.id.hackathon_team_list);
        membersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        membersRecyclerView.setAdapter(memberAdapter);*/
        return v;
    }
}
