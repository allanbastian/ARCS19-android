package android.ieeecsvit.com.arcs19.Login;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OTPFragment extends Fragment {

    EditText digit1, digit2, digit3, digit4, emailID;
    Button continueButton;
    TextView alertMsg;
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
        alertMsg = rootView.findViewById(R.id.alert_msg);

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
                    alertMsg.setText("Enter the 4 digit OTP sent on your Email ID");
                    emailPassed = true;

                    InputMethodManager imm = (InputMethodManager) getActivity()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

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
        try {
            InputMethodManager imm = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }catch (Exception e){}

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
                    continueButton.requestFocus();
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
            String text = arg0.toString();
            switch(view.getId())
            {
                case R.id.digit1:
                    if(text.length()>1)
                        digit1.setText(String.valueOf(text.charAt(text.length()-1)));
                    break;
                case R.id.digit2:
                    if(text.length()>1)
                        digit2.setText(String.valueOf(text.charAt(text.length()-1)));
                    break;
                case R.id.digit3:
                    if(text.length()>1)
                        digit3.setText(String.valueOf(text.charAt(text.length()-1)));
                    break;
                case R.id.digit4:
                    if(text.length()>1)
                        digit4.setText(String.valueOf(text.charAt(text.length()-1)));
                    break;
            }
        }
    }

}
