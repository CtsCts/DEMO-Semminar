package com.example.demoseminar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.demoseminar.object.User;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InFormationActivity extends AppCompatActivity {


//    MainActivity mainActivity;
//    private TextInputLayout inputSchoolYear,inputClass;
    private AutoCompleteTextView autoCompleteSchoolYear,autoCompleteClass;
    private EditText edtAge,edtAddress,edtName,edtPhoneNumber;
    //private RadioButton rdMale,rdFemale;
    private RadioGroup rdGender;
    private Button btnFinish;
    String [] schoolYear = {"K2016","K2017","K2018","K2019","K2020"};
    String [] classPresent = {"DTV1","DTV2","CNTT1","CNTT2","CNSH1","CNSH2"};
    ArrayAdapter<String> adapter1;
    ArrayAdapter<String> adapter2;


    private String nameShoolYear,nameClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_formation);

        initui();
        adapter1 = new ArrayAdapter<String>(InFormationActivity.this,R.layout.drop_menu,schoolYear);
        autoCompleteSchoolYear.setAdapter(adapter1);
        adapter2 = new ArrayAdapter<String>(InFormationActivity.this,R.layout.drop_menu,classPresent);
        autoCompleteClass.setAdapter(adapter2);
        autoCompleteSchoolYear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                nameShoolYear = item;
                Toast.makeText(InFormationActivity.this,"ban chon"+ item ,Toast.LENGTH_LONG).show();

            }
        });
        autoCompleteClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                nameClass = item;
                Toast.makeText(InFormationActivity.this,"ban chon"+ item ,Toast.LENGTH_LONG).show();

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
                String selectedtext = (String) radioButton.getText();

                FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference userRef = database.getReference("list_user");
                String email = muser.getEmail();
                User user = new User(name,age,selectedtext,email,nameClass,nameShoolYear,address,number);

                setDatabaseToRealTimeDatabase(user);
                Intent intent =new Intent(InFormationActivity.this,MainActivity.class);
                startActivity(intent);
                finishAffinity();

                Toast.makeText(InFormationActivity.this,"success",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setDatabaseToRealTimeDatabase(User user) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userRef = database.getReference("list_user");

        FirebaseUser userID = FirebaseAuth.getInstance().getCurrentUser();
        String UID = userID.getUid();

        String pathOject = String.valueOf(UID);
        userRef.child(pathOject).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(InFormationActivity.this,"success",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initui() {
        autoCompleteClass = findViewById(R.id.auto_complete_class);
        autoCompleteSchoolYear= findViewById(R.id.auto_complete_school_year);
        edtAddress = findViewById(R.id.edt_address_information);
        edtAge = findViewById(R.id.edt_age_information);
        edtName = findViewById(R.id.edt_name_information);
//        rdFemale = findViewById(R.id.radio_female);
//        rdMale = findViewById(R.id.radio_male);
        rdGender = findViewById(R.id.radio_group);
        btnFinish = findViewById(R.id.btn_finish_information);
        edtPhoneNumber = findViewById(R.id.edt_phone_number_information);
    }
}