package android.ieeecsvit.com.arcs19.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.ieeecsvit.com.arcs19.MainActivity;
import android.ieeecsvit.com.arcs19.R;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
    Boolean signUpFrag, loginStatus, otpFrag, passwordFrag;
    SharedPreferences sp;
    String USERNAME = "USERNAME";

    LinearLayout signInField, signUpField;
    TextView arcsText, forgotPassword;

    OTPFragment otpFragment;


    //Splash screen Animation
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            signInField.setVisibility(View.VISIBLE);
            signUpField.setVisibility(View.VISIBLE);
            arcsText.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        continueButton = findViewById(R.id.continue_button);
        signUpButton = findViewById(R.id.sign_up_button);
        emailID = findViewById(R.id.email_id);
        password = findViewById(R.id.password);
        fragmentContainer = findViewById(R.id.fragment_container);
        forgotPassword = findViewById(R.id.forgot_password);
        signInField = findViewById(R.id.sign_in_field);
        signUpField = findViewById(R.id.sign_up_field);
        arcsText = findViewById(R.id.arcs_text);

        Handler handler = new Handler();
        handler.postDelayed(runnable,2000);             //Post delay animation for Splash Screen


        //Initialising SP
        sp = getSharedPreferences("key", 0);

        //Getting the login session status
        loginStatus = sp.getBoolean("loginStatus",false);

        //Login Status Check
        try {
            if (loginStatus) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
        catch(Exception e){}


        //Flag variables for active fragment
        signUpFrag = false;

        //Login Process
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


        //Sign Up Process
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

        //Forgot Password
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Starting OTP process
                fragmentManager = getSupportFragmentManager();
                fragmentContainer.setClickable(true);
                otpFragment = new OTPFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right);
                transaction.add(R.id.fragment_container,otpFragment).commit();
                otpFrag = true;
            }
        });

    }

    void login()
    {
        String email = emailID.getText().toString();
        String pass = password.getText().toString();

        if (email.isEmpty()) {
            emailID.setError("Email is required");
            emailID.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailID.setError("Please enter a valid email");
            emailID.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if (pass.length() < 8) {
            password.setError("Minimum length of password should be 8");
            password.requestFocus();
            return;
        }

        //Starting SignIn Process
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("loginStatus",true);                          //Login Session True
        editor.putString("email",emailID.getText().toString());         //Email
        editor.putString("password",password.getText().toString());     //Password
        editor.apply();

        //Initialising the username
        editor.putString("username",USERNAME);

        startActivity(new Intent(LoginActivity.this,MainActivity.class));
        finish();
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
        else if(otpFrag)
        {
            //Removing SignUp fragment to Login Activity
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right);
            transaction.remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container)).commit();
            fragmentContainer.setClickable(false);
            otpFrag = false;
        }
        else
            super.onBackPressed();
    }
}