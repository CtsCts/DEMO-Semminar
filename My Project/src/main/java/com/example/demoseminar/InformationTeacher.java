package com.example.demoseminar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demoseminar.object.Teacher;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InformationTeacher extends AppCompatActivity {


    private AutoCompleteTextView autoCompleteMajor;
    private EditText edtAge,edtAddress,edtName,edtPhoneNumber;
    private RadioGroup rdGender;
    private Button btnFinish;
    String [] schoolYear = {"Information technology","Math","Physics","Social Science ","Practices"};

    ArrayAdapter<String> adapter1;



    private String nameMajor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_teacher);

        initui();
        adapter1 = new ArrayAdapter<String>(InformationTeacher.this,R.layout.drop_menu,schoolYear);
        autoCompleteMajor.setAdapter(adapter1);
        autoCompleteMajor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                nameMajor = item;
                Toast.makeText(InformationTeacher.this,"ban chon"+ item ,Toast.LENGTH_LONG).show();

            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edtName.getText().toString().trim();
                String age = edtAge.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                int number = Integer.parseInt(edtPhoneNumber.getText().toString().trim());

                int radioButtonID = rdGender.getCheckedRadioButtonId();
                RadioButton radioButton =  rdGender.findViewById(radioButtonID);
                String gender = (String) radioButton.getText();

                FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference userRef = database.getReference("list_teacher");
                String email = muser.getEmail();
//                User user = new User(name,age,selectedtext,email,nameClass,nameShoolYear,address,number);

                Teacher teacher = new Teacher(name,age,gender,email,nameMajor,address,number);
                setDatabaseToRealTimeDatabase(teacher);
                Intent intent =new Intent(InformationTeacher.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();

                Toast.makeText(InformationTeacher.this,"success",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setDatabaseToRealTimeDatabase(Teacher teacher) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("list_teacher");

        FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();
        String UID = userID.getUid();

        String pathOject = String.valueOf(UID);
        userRef.child(pathOject).setValue(teacher, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(InformationTeacher.this,"success",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initui() {
        autoCompleteMajor = findViewById(R.id.auto_complete_major);

        edtAddress = findViewById(R.id.edt_address_information_teacher);
        edtAge = findViewById(R.id.edt_age_information_teacher);
        edtName = findViewById(R.id.edt_name_information_teacher);
        rdGender = findViewById(R.id.radio_group_teacher);
        btnFinish = findViewById(R.id.btn_finish_information_teacher);
        edtPhoneNumber = findViewById(R.id.edt_phone_number_information_teacher);
    }
}