package android.gaurav.com.arcs19;

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

public class SignInFragment extends Fragment {

    Button logIn;
    EditText emailID, password;
    TextView alertMsg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.sign_in_fragment,container,false);

        logIn = rootView.findViewById(R.id.log_in_button);
        emailID = rootView.findViewById(R.id.email_id);
        password = rootView.findViewById(R.id.password);
        alertMsg = rootView.findViewById(R.id.alert_msg);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }
}
