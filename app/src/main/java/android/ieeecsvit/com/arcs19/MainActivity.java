package android.ieeecsvit.com.arcs19;

import android.content.SharedPreferences;
import android.content.Intent;
import android.ieeecsvit.com.arcs19.Login.LoginActivity;
import android.ieeecsvit.com.arcs19.Login.UserClass;
import android.ieeecsvit.com.arcs19.Profile.Profile;
import android.ieeecsvit.com.arcs19.Schedule.SchedulePageFragment;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    SchedulePageFragment schedulePageFragment;
    BottomNavigationFragment bottomNavigationFragment = new BottomNavigationFragment();;
    BottomAppBar bottomAppBar;
    LinearLayout progressSection;
    FloatingActionButton arcsFloatingActionButton;
    SharedPreferences sp;
    String USERNAME = "", EMAIL="", token = "";

    Boolean updatedAvail = false;

    APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomAppBar = findViewById(R.id.bottom_app_bar);                               //Bottom navigation App bar
        arcsFloatingActionButton = findViewById(R.id.arcs_floating_action_button);      //Center Arcs Button
        progressSection = findViewById(R.id.progress_section);                          //Loading progress bar

        apiInterface = APIClient.getClient().create(APIInterface.class);


        //Initialising User
        sp = getSharedPreferences("key", 0);
        USERNAME = sp.getString("username","");                         //Getting Username
        EMAIL = sp.getString("email","");                               //Getting email
        token = sp.getString("jwtToken","");                            //JWT Token
        updatedAvail = sp.getBoolean("updateAvail",false);             //Is the profile downloaded

        if(!updatedAvail)
        {
            progressSection.setVisibility(View.VISIBLE);
            Call<UserClass> call = apiInterface.getProfile(token);
            call.enqueue(new Callback<UserClass>() {
                @Override
                public void onResponse(Call<UserClass> call, Response<UserClass> response) {
                    UserClass userClass = response.body();
                    progressSection.setVisibility(View.GONE);
                    SharedPreferences.Editor editor = sp.edit();
                    try {
                        if (!userClass.getName().isEmpty()) {
                            editor.putString("username", userClass.getName());
                            editor.putString("regNumber", userClass.getRegNumber());
                            editor.putString("phoneNumber", userClass.getPhoneNumber());
                            editor.putBoolean("updateAvail", true);

                            editor.commit();

                            //Home fragment here
                            schedulePageFragment = new SchedulePageFragment();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.add(R.id.fragment_container, schedulePageFragment).commit();
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Session Expired ! Login !",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        editor.remove("username").commit();
                        editor.remove("email").commit();
                        editor.remove("password").commit();
                        editor.remove("loginStatus").commit();
                        editor.remove("image_data").commit();
                        editor.remove("jwtToken").commit();
                        editor.remove("phoneNumber").commit();
                        editor.remove("updateAvail").commit();
                        editor.remove("loginStatus").commit();
                        editor.remove("teamName").commit();
                        intent.setAction(Intent.ACTION_MAIN);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<UserClass> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"An error has occurred",Toast.LENGTH_SHORT).show();
                    progressSection.setVisibility(View.GONE);
                }
            });

        }
        else
        {
            //Home fragment here
            schedulePageFragment = new SchedulePageFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container,schedulePageFragment).commit();

            Call<UserClass> call = apiInterface.getProfile(token);
            call.enqueue(new Callback<UserClass>() {
                @Override
                public void onResponse(Call<UserClass> call, Response<UserClass> response) {
                    UserClass userClass = response.body();
                    SharedPreferences.Editor editor = sp.edit();
                    try {
                        if (!userClass.getName().isEmpty()) {
                            editor.putString("username", userClass.getName());
                            editor.putString("regNumber", userClass.getRegNumber());
                            editor.putString("phoneNumber", userClass.getPhoneNumber());
                            editor.putBoolean("updateAvail", true);

                            editor.commit();
                        }
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(),"Session Expired ! Login !",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        editor.remove("username").commit();
                        editor.remove("email").commit();
                        editor.remove("password").commit();
                        editor.remove("loginStatus").commit();
                        editor.remove("image_data").commit();
                        editor.remove("jwtToken").commit();
                        editor.remove("phoneNumber").commit();
                        editor.remove("updateAvail").commit();
                        editor.remove("loginStatus").commit();
                        editor.remove("teamName").commit();
                        intent.setAction(Intent.ACTION_MAIN);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<UserClass> call, Throwable t) {

                }
            });


        }


        //profile activity
        arcsFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });

        bottomAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationFragment.show(getSupportFragmentManager(),bottomNavigationFragment.getTag());
            }
        });

    }


}
