package android.gaurav.com.arcs19.Login;

import android.gaurav.com.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpFragment extends Fragment {

    Button signUp;
    EditText emailID, password, phoneNumber, name;
    TextView alertMsg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.signup_fragment,container,false);

        signUp = rootView.findViewById(R.id.sign_up_button);
        phoneNumber = rootView.findViewById(R.id.phone_number);
        name = rootView.findViewById(R.id.name);
        emailID = rootView.findViewById(R.id.email_id);
        password = rootView.findViewById(R.id.password);
        alertMsg = rootView.findViewById(R.id.alert_msg);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }
}
