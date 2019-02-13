package android.ieeecsvit.com.arcs19.Login;

import android.ieeecsvit.com.arcs19.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ChangePasswordFragment extends Fragment {

    EditText newPassword, confirmPassword;
    Button changePasswordButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.change_password_fragment,container,false);

        changePasswordButton = rootView.findViewById(R.id.change_password_button);
        newPassword = rootView.findViewById(R.id.new_password);
        confirmPassword = rootView.findViewById(R.id.confirm_password);


        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });

        return rootView;
    }

    void changePassword()
    {
        String pass = newPassword.getText().toString();
        String cpass = confirmPassword.getText().toString();
        if (pass.isEmpty()) {
            newPassword.setError("Password is required");
            newPassword.requestFocus();
            return;
        }

        if (pass.length() < 8) {
            newPassword.setError("Minimum length of password should be 8");
            newPassword.requestFocus();
            return;
        }

        if (cpass.isEmpty()) {
            confirmPassword.setError("Confirm your password");
            confirmPassword.requestFocus();
            return;
        }

        if(!pass.equals(cpass))
        {
            confirmPassword.setError("Both passwords should be same");
            confirmPassword.requestFocus();
            return;
        }
    }


}
