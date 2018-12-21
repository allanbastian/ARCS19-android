package android.gaurav.com.arcs19;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;


public class MainActivity extends AppCompatActivity {


    SpaceNavigationView spaceNavigationView;
    WorkShopFragment workShopFragment;
    BottomNavigationFragment bottomNavigationFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Home fragment here
        workShopFragment = new WorkShopFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,workShopFragment).commit();

        //Bottom Space Navigation
        spaceNavigationView = findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.addSpaceItem(new SpaceItem("Menu", R.drawable.ic_menu_white_24dp));
        spaceNavigationView.addSpaceItem(new SpaceItem("Sponsors", R.drawable.sponsors));

        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Toast.makeText(MainActivity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {

                switch(itemName)
                {
                    case "Menu" : bottomNavigationFragment = new BottomNavigationFragment();
                                  bottomNavigationFragment.show(getSupportFragmentManager(),bottomNavigationFragment.getTag());
                                  break;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                switch(itemName)
                {
                    case "Menu" : bottomNavigationFragment = new BottomNavigationFragment();
                        bottomNavigationFragment.show(getSupportFragmentManager(),bottomNavigationFragment.getTag());
                        break;
                }            }
        });


    }


}
