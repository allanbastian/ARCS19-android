package android.gaurav.com.arcs19.Login;

import android.content.Intent;
import android.gaurav.com.arcs19.MainActivity;
import android.gaurav.com.arcs19.R;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button signUpButton;
    ImageButton continueButton;
    EditText emailID, password;
    RelativeLayout fragmentContainer;
    ProgressBar progressBar;
    FragmentManager fragmentManager;
    SignUpFragment signUpFragment;
    Boolean signUpFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        continueButton = findViewById(R.id.continue_button);
        signUpButton = findViewById(R.id.sign_up_button);
        emailID = findViewById(R.id.email_id);
        password = findViewById(R.id.password);
        fragmentContainer = findViewById(R.id.fragment_container);

        //Flag variables for active fragment
        signUpFrag = false;

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


                    startActivity(new Intent(LoginActivity.this,MainActivity.class));

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
        if(signUpFrag)
        {
            //Removing SignUp fragment to Login Activity
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right);
            transaction.remove(signUpFragment).commit();
            fragmentContainer.setClickable(false);
            signUpFrag = false;
        }
        else
            super.onBackPressed();
    }
}