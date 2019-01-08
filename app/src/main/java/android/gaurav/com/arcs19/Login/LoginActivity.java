package android.gaurav.com.arcs19.Login;

import android.gaurav.com.arcs19.R;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button continueButton, signUpButton;
    TextView alertMsg;
    EditText emailID;
    RelativeLayout fragmentContainer;
    ProgressBar progressBar;
    FragmentManager fragmentManager;
    SignInFragment signInFragment;
    SignUpFragment signUpFragment;
    Boolean signInFrag, signUpFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        continueButton = findViewById(R.id.continue_button);
        signUpButton = findViewById(R.id.sign_up_button);
        alertMsg = findViewById(R.id.alert_msg);
        emailID = findViewById(R.id.email_id);
        fragmentContainer = findViewById(R.id.fragment_container);
        progressBar = findViewById(R.id.progress_bar);

        //Flag variables for active fragment
        signInFrag = signUpFrag = false;

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(emailID.getText().toString().equals(null))
                {
                    //If email field ia empty
                    Toast.makeText(getApplicationContext(),"Enter emailID to continue",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Starting SignIn Process
                    progressBar.setVisibility(View.VISIBLE);
                    fragmentManager = getSupportFragmentManager();
                    fragmentContainer.setClickable(true);
                    signInFragment = new SignInFragment();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_right);
                    transaction.add(R.id.fragment_container, signInFragment).commit();
                    signInFrag = true;
                }
            }
        });


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Starting SingUp Process
                fragmentManager = getSupportFragmentManager();
                fragmentContainer.setClickable(true);
                signUpFragment = new SignUpFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right);
                transaction.add(R.id.fragment_container,signUpFragment).commit();
                signUpFrag = true;
            }
        });

    }


    @Override
    public void onBackPressed() {
        if(signInFrag)
        {
            //Removing SignIn fragment to activity
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right);
            transaction.remove(signInFragment).commit();
            progressBar.setVisibility(View.GONE);
            fragmentContainer.setClickable(false);
            signInFrag = false;
        }
        else if(signUpFrag)
        {
            //Removing SignUp fragment to Login Activity
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right);
            transaction.remove(signUpFragment).commit();
            progressBar.setVisibility(View.GONE);
            fragmentContainer.setClickable(false);
            signUpFrag = false;
        }
        else
            super.onBackPressed();
    }
}