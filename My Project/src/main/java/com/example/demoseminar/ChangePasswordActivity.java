package com.example.demoseminar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {


    private EditText edtPasswordPresent,edtNewPassword,edtConfirmPassword;
    private Button btnUpDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initui();

        btnUpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateChangePassword();
            }
        });
    }

    private void updateChangePassword() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String newPassword = edtNewPassword.getText().toString().trim();
        String passConfirm = edtConfirmPassword.getText().toString().trim();
//        String presentPassword = edtPasswordPresent.getText().toString().trim();
       //String u = user.zza().getPersistenceKey();

        //dialog.show();

        if( newPassword.equals(passConfirm) ) {
            user.updatePassword(newPassword)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //dialog.dismiss();
                            if (task.isSuccessful()) {
                                Toast.makeText(ChangePasswordActivity.this,
                                        "User password updated.",Toast.LENGTH_LONG).show();

                            }else {
                                Toast.makeText(ChangePasswordActivity.this,
                                        "please check again", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }else {
            Toast.makeText(ChangePasswordActivity.this,
                    "please check again",Toast.LENGTH_LONG).show();
        }
    }

    private void initui() {
        edtPasswordPresent = findViewById(R.id.edt_present_password_cpw);
        edtNewPassword = findViewById(R.id.edt_new_password_1_cpw);
        edtConfirmPassword = findViewById(R.id.edt_new_password_2_cpw);
        btnUpDate = findViewById(R.id.btn_update_change_password);

    }
}