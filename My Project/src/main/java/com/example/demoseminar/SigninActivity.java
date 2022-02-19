package com.example.demoseminar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    private TextView layoutsignup;
    private EditText mEditTextEmail,mEditTextPassword;
    private Button mButtonSignin;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initui();
        //set su kien khi nhan vao vung singup
        initlistener();
    }

    private void initui(){
        dialog=new ProgressDialog(this);
        layoutsignup        = findViewById(R.id.layout_signup);
        mButtonSignin       = findViewById(R.id.btn_signin);
        mEditTextEmail      = findViewById(R.id.edt_email_sigin);
        mEditTextPassword   = findViewById(R.id.edt_password_signin);
    }

    private void initlistener(){
        layoutsignup.setOnClickListener(v -> {
            Intent intent =new Intent(SigninActivity.this,SignUpActivity.class);
            startActivity(intent);
        });

        mButtonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickSignIn();
            }
        });
    }

    private void onClickSignIn() {
        String email =mEditTextEmail.getText().toString().trim();
        String password = mEditTextPassword.getText().toString().trim();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        dialog.show();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                dialog.dismiss();
                if(task.isSuccessful()){//kiem tra dang nhap thanh cong

                    //chuyen den man hinh main
                    Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                    startActivity(intent);
                    finishAffinity();//dong tat ca ca actyvity dang nhap
                }else {

                    Toast.makeText(SigninActivity.this,"Authentication Faild",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}