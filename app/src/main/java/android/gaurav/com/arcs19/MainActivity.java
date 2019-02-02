package android.gaurav.com.arcs19;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    WorkShopFragment workShopFragment;
    BottomNavigationFragment bottomNavigationFragment;
    BottomAppBar bottomAppBar;
    FloatingActionButton arcsFloatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bottom_app_bar);
        arcsFloatingActionButton = findViewById(R.id.arcs_floating_action_button);

        //Home fragment here
        workShopFragment = new WorkShopFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,workShopFragment).commit();

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationFragment = new BottomNavigationFragment();
                bottomNavigationFragment.show(getSupportFragmentManager(),bottomNavigationFragment.getTag());
            }
        });

        //profile activity
        arcsFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Profile.class);
                startActivity(intent);
            }
        });


    }


}
