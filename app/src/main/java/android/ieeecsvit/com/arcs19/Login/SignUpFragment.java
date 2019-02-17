package android.ieeecsvit.com.arcs19.Login;

import android.content.Context;
import android.ieeecsvit.com.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.regex.Pattern;

public class SignUpFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Button signUp;
    EditText emailID, password, confirmPassword, phoneNumber, name, university, regNumber, roomNumber, membershipNumber, section;
    TextView alertMsg;
    CheckBox member, vitian;
    RadioGroup gender;
    Spinner tSize;
    LinearLayout membershipDetails, vitianDetails;

    ArrayList<String> sizeArray;

    String currentSize = "SIZE";

    String API_KEY = "6Lf96n8UAAAAAIPzsRSuQtoOldpI14e76_a3oz9g";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.signup_fragment,container,false);

        signUp = rootView.findViewById(R.id.sign_up_button);
        phoneNumber = rootView.findViewById(R.id.phone_number);
        name = rootView.findViewById(R.id.name);
        emailID = rootView.findViewById(R.id.email_id);
        password = rootView.findViewById(R.id.password);
        confirmPassword = rootView.findViewById(R.id.confirm_password);
        alertMsg = rootView.findViewById(R.id.alert_msg);
        university = rootView.findViewById(R.id.university);
        regNumber = rootView.findViewById(R.id.reg_number);
        membershipNumber = rootView.findViewById(R.id.membership_number);
        roomNumber = rootView.findViewById(R.id.room_number);
        section = rootView.findViewById(R.id.section);
        member = rootView.findViewById(R.id.member);
        vitian = rootView.findViewById(R.id.vitian);
        gender = rootView.findViewById(R.id.gender);
        tSize = rootView.findViewById(R.id.t_shirt_size);
        membershipDetails = rootView.findViewById(R.id.membership_details);
        vitianDetails = rootView.findViewById(R.id.vitian_details);

        //Setting up the size list for the spinner
        sizeArray = new ArrayList<String>();
        sizeArray.add("SIZE");
        sizeArray.add("XS");
        sizeArray.add("S");
        sizeArray.add("M");
        sizeArray.add("L");
        sizeArray.add("XL");
        sizeArray.add("XXL");
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_item,sizeArray);        //Size adapter
        tSize.setAdapter(sizeAdapter);


        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(member.isChecked())
                {
                    membershipDetails.setVisibility(View.VISIBLE);          //Showing up the details fields
                }
                else
                {
                    membershipDetails.setVisibility(View.GONE);             //Hiding the membership details
                }
            }
        });

        vitian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vitian.isChecked())
                {
                    vitianDetails.setVisibility(View.VISIBLE);              //Showing up the VIT details fields
                    university.setText("VIT Vellore");                      //Setting the University to VIT
                    university.setEnabled(false);                           //Disabling the university field
                }
                else
                {
                    vitianDetails.setVisibility(View.GONE);                 //Hiding the VIT details fields
                    university.getText().clear();                           //Reset the university field
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Closes the on screen keyboard
                InputMethodManager imm = (InputMethodManager) getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                //Starting the signUp process
                signUpProcess();
            }
        });

        return rootView;
    }


    private void signUpProcess()
    {
        String email = emailID.getText().toString();                    //Email string
        String pass = password.getText().toString();                    //Password String
        String cpass = confirmPassword.getText().toString();            //Confirm Password String
        String username = name.getText().toString();                    //Name of the user
        String mobNo = phoneNumber.getText().toString();                //Phone number
        String uni = university.getText().toString();                   //University name
        String regNo= regNumber.getText().toString().toUpperCase();     //VIT Registration Number
        String ieeeNo = membershipNumber.getText().toString();          //IEEE Membership Number
        String roomNo = roomNumber.getText().toString();                //Hostel room number
        String ieeeSec = section.getText().toString();                  //IEEE Section

        //Checking if the fields are empty
        if (email.isEmpty()) {
            emailID.setError("Email is required");
            emailID.requestFocus();
            return;
        }

        if (pass.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if (username.isEmpty()) {
            name.setError("Name is required");
            name.requestFocus();
            return;
        }

        if (mobNo.isEmpty()) {
            phoneNumber.setError("Phone Number is required");
            phoneNumber.requestFocus();
            return;
        }

        if (uni.isEmpty()) {
            university.setError("University name is required");
            university.requestFocus();
            return;
        }

        if(currentSize.equals("SIZE"))
        {
            Toast.makeText(getActivity(),"SELECT SIZE",Toast.LENGTH_SHORT).show();
            tSize.requestFocus();
            return;
        }

        if(gender.getCheckedRadioButtonId() == -1)
        {
            Toast.makeText(getActivity(),"SELECT GENDER",Toast.LENGTH_SHORT).show();
            gender.requestFocus();
            return;
        }


        //Email Validation
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailID.setError("Please enter a valid email");
            emailID.requestFocus();
            return;
        }

        //Password length validation
        if (pass.length() < 8) {
            password.setError("Minimum length of password should be 8");
            password.requestFocus();
            return;
        }

        //Phone number length validation
        if (mobNo.length() < 10) {
            phoneNumber.setError("Enter a valid phone number");
            phoneNumber.requestFocus();
            return;
        }

        //Comparing the two passwords entered
        if(!pass.equals(cpass))
        {
            confirmPassword.setError("Both passwords should be same");
            confirmPassword.requestFocus();
            return;
        }

        if(member.isChecked())
        {
            //Checking if the fields are empty
            if (ieeeNo.isEmpty()) {
                membershipNumber.setError("Membership Number is required");
                membershipNumber.requestFocus();
                return;
            }

            if (ieeeSec.isEmpty()) {
                section.setError("IEEE Section is required");
                section.requestFocus();
                return;
            }
        }

        if(vitian.isChecked())
        {
            //Checking if the fields are empty
            if (regNo.isEmpty()) {
                regNumber.setError("VIT registration number r is required");
                regNumber.requestFocus();
                return;
            }

            if (roomNo.isEmpty()) {
                roomNumber.setError("Hostel Room Number is required");
                roomNumber.requestFocus();
                return;
            }

            //validating registration number
            if(!Pattern.compile("[0-9][0-9][A-Z][A-Z][A-Z][0-9][0-9][0-9][0-9]").matcher(regNo).matches())
            {
                regNumber.setError("Enter a valid VIT registration number");
                regNumber.requestFocus();
                return;
            }
        }

        //API Calling


    }

    private void recaptcha()
    {
        SafetyNet.getClient(getActivity()).verifyWithRecaptcha(API_KEY)
                .addOnSuccessListener((Executor) this,
                        new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                            @Override
                            public void onSuccess(SafetyNetApi.RecaptchaTokenResponse response) {
                                // Indicates communication with reCAPTCHA service was
                                // successful.
                                String userResponseToken = response.getTokenResult();
                                if (!userResponseToken.isEmpty()) {
                                    // Validate the user response token using the
                                    // reCAPTCHA siteverify API.
                                }
                            }
                        })
                .addOnFailureListener((Executor) this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof ApiException) {
                            // An error occurred when communicating with the
                            // reCAPTCHA service. Refer to the status code to
                            // handle the error appropriately.
                            ApiException apiException = (ApiException) e;
                            int statusCode = apiException.getStatusCode();
                            Log.e("Error: " , CommonStatusCodes
                                    .getStatusCodeString(statusCode));
                        } else {
                            // A different, unknown type of error occurred.
                            Log.e("Error", e.getMessage());
                        }
                    }
                });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentSize = sizeArray.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
