package com.example.demoseminar;

import static com.example.demoseminar.MainActivity.MY_REQUEST_CODE;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyProfileActivity extends AppCompatActivity {


    private String name;
    private MainActivity mMainActivity;
    private Uri uri;
    private ProgressDialog dialog;
    private EditText edtFullName,edtEmail;
    private Button btnUpDateProfile;
    private CircleImageView circleImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        initui();

        setUserInformation();

        initListener();
    }

    private void initListener() {
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPesmission();
            }
        });
        btnUpDateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpDateProfile();

            }
        });
    }

    private void onClickUpDateProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            return;
        }

        String fullname = edtFullName.getText().toString().trim();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(fullname)
                .setPhotoUri(uri)
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MyProfileActivity.this,"update done",Toast.LENGTH_LONG).show();
                            setUserInformation();
                            //update du lieu trong ham main
                            mMainActivity.showUserInformation();

                        }
                    }
                });
    }
    private void onClickRequestPesmission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }

        if(MyProfileActivity.this.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallery();
        } else {

            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
            MyProfileActivity.this.requestPermissions(permission,MY_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }else{
                Toast.makeText(this,"vui long cho phep doc bo nho",Toast.LENGTH_LONG).show();
                onClickRequestPesmission();

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE && data != null && data.getData() != null){
            uri = data.getData();
            circleImageView.setImageURI(uri);
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        if(requestCode == MY_REQUEST_CODE && resultCode ==RESULT_OK &&data!= null){
//             uri = data.getData();
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(uri);
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                circleImageView.setImageBitmap(bitmap);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, MY_REQUEST_CODE);
//        intent.setAction(intent.ACTION_GET_CONTENT);
//        intent.createChooser(intent,"select picture");
        //startActivityForResult(intent,MY_REQUEST_CODE);
//
    }

    public void setUserInformation() {
        FirebaseUser  user = FirebaseAuth.getInstance().getCurrentUser();
        String key = user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_user");
        myRef.child(key).child("name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                name = task.getResult().getValue().toString().trim();
            }
        });
        if(user == null){
            return;
        }else {
            edtFullName.setText(name);
            edtEmail.setText(user.getEmail());
            circleImageView.setImageURI(uri);
//            Glide.with(MyProfileActivity.this).load(user.getPhotoUrl())
//                    .error(R.drawable.ic_launcher_background).into(circleImageView);
        }
    }

    private void initui() {
        edtEmail = findViewById(R.id.edt_update_email_my_profile);
        edtFullName = findViewById(R.id.edt_update_full_name_my_profile);
        btnUpDateProfile = findViewById(R.id.btn_update_my_profile);
        circleImageView = findViewById(R.id.image_update_my_proflie);
    }
}