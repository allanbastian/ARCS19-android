package android.gaurav.com.arcs19;

import android.content.Intent;
import android.gaurav.com.arcs19.Convoke.ConvokeFragment;
import android.gaurav.com.arcs19.Developers.DeveloperFragment;
import android.gaurav.com.arcs19.Forum.ForumActivity;
import android.gaurav.com.arcs19.Hackathon.HackathonFragment;
import android.gaurav.com.arcs19.Schedule.SchedulePageFragment;
import android.gaurav.com.arcs19.Sponsor.SponsorFragment;
import android.gaurav.com.arcs19.Workshop.WorkShopFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class BottomNavigationFragment extends BottomSheetDialogFragment {

    NavigationView navigationView;
    Fragment fragment;
    FragmentTransaction transaction;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.bottom_navigation_fragment,container,false);

        navigationView = rootView.findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int menuId = menuItem.getItemId();

                switch(menuId)
                {
                    case R.id.workshops : fragment = new WorkShopFragment();
                        transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, fragment).commit();
                                        break;

                    case R.id.hackathon : fragment = new HackathonFragment();
                        transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, fragment).commit();
                                        break;

                    case R.id.convoke : fragment = new ConvokeFragment();
                        transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, fragment).commit();
                                        break;

                    case R.id.schedule: fragment = new SchedulePageFragment();
                        transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, fragment).commit();
                                        break;

                    case R.id.developers: fragment = new DeveloperFragment();
                        transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, fragment).commit();
                                        break;

                    case R.id.sponsors: fragment = new SponsorFragment();
                        transaction = getActivity().getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, fragment).commit();
                        break;

                    case R.id.forum: intent = new Intent(getActivity(), ForumActivity.class);
                                        startActivity(intent);
                                        break;




                }

                getDialog().cancel();
                return true;
            }
        });


        return rootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);

    }
}
