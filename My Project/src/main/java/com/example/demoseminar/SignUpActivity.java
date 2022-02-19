package com.example.demoseminar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {


    private EditText edtmail,edtpassword;
    private Button btnsignup,btnteachersignup;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //anh xa va khoi tao
        initui();


        initlistener();

    }

    private void initlistener() {
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicksignup();
            }
        });
        btnteachersignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclickTeacherSignUp();
            }
        });
    }

    private void onclickTeacherSignUp() {
        String stremail =edtmail.getText().toString().trim();
        String strpassword = edtpassword.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(stremail, strpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(SignUpActivity.this,InformationTeacher.class);
                            startActivity(intent);
                            finishAffinity();
//                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    private void onclicksignup() {
        String stremail =edtmail.getText().toString().trim();
        String strpassword = edtpassword.getText().toString().trim();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(stremail, strpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent intent = new Intent(SignUpActivity.this,InFormationActivity.class);
                            startActivity(intent);
                            finishAffinity();
//                            FirebaseUser user = mAuth.getCurrentUser();

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void initui() {
        progressDialog = new ProgressDialog(this);
        edtmail     = findViewById(R.id.email_auth_sign_up);
        edtpassword = findViewById(R.id.password_auth_sign_up);
        btnsignup = findViewById(R.id.btn_signup);
        btnteachersignup = findViewById(R.id.btn_signup_teacher);

    }
}