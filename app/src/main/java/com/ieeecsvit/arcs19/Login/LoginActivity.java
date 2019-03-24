package com.ieeecsvit.arcs19.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.ieeecsvit.arcs19.APIClient;
import com.ieeecsvit.arcs19.APIInterface;
import com.ieeecsvit.arcs19.MainActivity;
import com.ieeecsvit.arcs19.R;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button signUpButton;
    ImageButton continueButton;
    EditText emailID, password;
    RelativeLayout fragmentContainer;
    ProgressBar progressBar;
    FragmentManager fragmentManager;
    SignUpFragment signUpFragment;
    ImageView arcsLogo;
    Boolean signUpFrag, loginStatus, otpFrag, passwordFrag;
    SharedPreferences sp;
    String USERNAME = "USERNAME";

    LinearLayout signInField, signUpField;
    TextView arcsText, forgotPassword, signingIn;

    OTPFragment otpFragment;

    APIInterface apiInterface;

    float scaleDP;

    //Splash screen Animation
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int dps = (int) (100 * scaleDP + 0.5f);
            arcsLogo.requestLayout();
            arcsLogo.getLayoutParams().height = dps;
            arcsLogo.getLayoutParams().width = dps;
            arcsLogo.setScaleType(ImageView.ScaleType.FIT_XY);
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
        //forgotPassword = findViewById(R.id.forgot_password);
        signInField = findViewById(R.id.sign_in_field);
        signUpField = findViewById(R.id.sign_up_field);
        arcsText = findViewById(R.id.arcs_text);
        progressBar = findViewById(R.id.progress_bar);
        signingIn = findViewById(R.id.signing_in);
        arcsLogo = findViewById(R.id.arcs_logo);

        scaleDP = getApplicationContext().getResources().getDisplayMetrics().density;

        signUpFrag = loginStatus = otpFrag = false;

        Handler handler = new Handler();
        handler.postDelayed(runnable,2000);             //Post delay animation for Splash Screen

        apiInterface = APIClient.getClient().create(APIInterface.class);            //API client interface

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
                //Closing the keyboard
                try {
                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
                catch (Exception e){}
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
        /*forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),"This feature is currently unavailable",Toast.LENGTH_SHORT).show();

              *//*  //Starting OTP process
                fragmentManager = getSupportFragmentManager();
                fragmentContainer.setClickable(true);
                otpFragment = new OTPFragment();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right);
                transaction.add(R.id.fragment_container,otpFragment).commit();
                otpFrag = true;*//*
            }
        });*/


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


        Call<HashMap<String, Boolean>> call = apiInterface.loginUser(emailID.getText().toString(),password.getText().toString());
        progressBar.setVisibility(View.VISIBLE);
        signingIn.setVisibility(View.VISIBLE);
        continueButton.setEnabled(false);
        signUpButton.setEnabled(false);
        call.enqueue(new Callback<HashMap<String, Boolean>>() {
            @Override
            public void onResponse(Call<HashMap<String, Boolean>> call, Response<HashMap<String, Boolean>> response) {
                //On successful login
                HashMap<String,Boolean> hashMap = new HashMap<String, Boolean>();
                hashMap = response.body();

                if(hashMap.get("success")) {
                    SharedPreferences.Editor editor = sp.edit();
                    String jwtToken = response.headers().get("Token");
                    editor.putString("jwtToken",jwtToken);                           //JWT Token
                    editor.putBoolean("loginStatus", true);                          //Login Session set to true
                    editor.putString("email", emailID.getText().toString());         //Email
                    editor.putString("password", password.getText().toString());     //Password
                    editor.apply();

                    Log.e("JWTToken",jwtToken);

                    //Initialising the username
                    editor.putString("username", USERNAME);
                    progressBar.setVisibility(View.GONE);
                    signingIn.setVisibility(View.GONE);
                    finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }
                else
                {
                    progressBar.setVisibility(View.GONE);
                    signingIn.setVisibility(View.GONE);
                    continueButton.setEnabled(true);
                    signUpButton.setEnabled(true);
                    Toast.makeText(getApplicationContext(),"Username or Password may be wrong !",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<HashMap<String, Boolean>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"An error has occurred...!",Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                signingIn.setVisibility(View.GONE);
                continueButton.setEnabled(true);
                signUpButton.setEnabled(true);
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