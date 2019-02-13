package android.ieeecsvit.com.arcs19.Login;

import android.content.SharedPreferences;
import android.ieeecsvit.com.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class OTPFragment extends Fragment {

    EditText digit1, digit2, digit3, digit4, emailID;
    Button continueButton;
    String otp;
    LinearLayout otpField;

    Boolean emailPassed = false;

    ChangePasswordFragment changePasswordFragment;

    SharedPreferences sp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.otp_fragment,container,false);

        continueButton = rootView.findViewById(R.id.continue_button);
        digit1 = rootView.findViewById(R.id.digit1);
        digit2 = rootView.findViewById(R.id.digit2);
        digit3 = rootView.findViewById(R.id.digit3);
        digit4 = rootView.findViewById(R.id.digit4);
        otpField = rootView.findViewById(R.id.otp_field);
        emailID = rootView.findViewById(R.id.email_id);

        digit1.addTextChangedListener(new GenericTextWatcher(digit1));
        digit2.addTextChangedListener(new GenericTextWatcher(digit2));
        digit3.addTextChangedListener(new GenericTextWatcher(digit3));
        digit4.addTextChangedListener(new GenericTextWatcher(digit4));


        sp = this.getActivity().getSharedPreferences("key",0);


        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emailPassed)
                {
                    String email = emailID.getText().toString();

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

                    //Initialising the username
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("username",email);
                    emailID.setVisibility(View.GONE);
                    otpField.setVisibility(View.VISIBLE);

                }
                else
                {
                    authenticateOTP();
                }
            }
        });

        return rootView;
    }

    void authenticateOTP()
    {
        if (digit1.getText().toString().isEmpty()) {
            digit1.setError("Enter a valid OTP");
            digit1.requestFocus();
            return;
        }

        if (digit2.getText().toString().isEmpty()) {
            digit2.setError("Enter a valid OTP");
            digit2.requestFocus();
            return;
        }

        if (digit3.getText().toString().isEmpty()) {
            digit3.setError("Enter a valid OTP");
            digit3.requestFocus();
            return;
        }

        if (digit4.getText().toString().isEmpty()) {
            digit4.setError("Enter a valid OTP");
            digit4.requestFocus();
            return;
        }
        //Required OTP
        otp = digit1.getText().toString() + digit2.getText().toString() + digit3.getText().toString() + digit4.getText().toString();

        changePasswordFragment = new ChangePasswordFragment();
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_from_right);
        fragmentTransaction.add(R.id.fragment_container,changePasswordFragment).commit();

    }

    public class GenericTextWatcher implements TextWatcher
    {
        private View view;
        private GenericTextWatcher(View view)
        {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch(view.getId())
            {
                case R.id.digit1:
                    if(text.length()==1)
                        digit2.requestFocus();
                    break;
                case R.id.digit2:
                    if(text.length()==1)
                        digit3.requestFocus();
                    break;
                case R.id.digit3:
                    if(text.length()==1)
                        digit4.requestFocus();
                    break;
                case R.id.digit4:
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }

}
