package com.example.demoseminar.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demoseminar.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListClassFragment extends Fragment {


    ListView listView;
    ArrayList data;

    ProgressDialog dialog;
    String key;
    ArrayAdapter subject;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.layout_fragment_list_class,container,false);

        initui();

        getlistUserFromDatabase();

        return view;
    }

    private void initui() {
        dialog = new ProgressDialog(getActivity());
       listView = view.findViewById(R.id.list_view_list_class);

       //get value schoolyear tu infor user
       FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();
        String ID = muser.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef2 = database.getReference("list_user").child(ID).child("shoolyear");

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                key = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getlistUserFromDatabase(){

//DELAY     thoi gian load du lieu ngan loi exception
        CountDownTimer count = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                dialog.setMessage("Loading...,please wait...");
                dialog.show();
            }

            @Override
            public void onFinish() {
                dialog.dismiss();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef1 = database.getReference("list_subject").child(key);

                myRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        data = new ArrayList();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                            data.add(dataSnapshot.getValue().toString());
//                            Toast.makeText(getActivity(),dataSnapshot.getValue().toString(), Toast.LENGTH_LONG).show();

                            subject = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,data);
                            listView.setAdapter(subject);
                            subject.notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                        Toast.makeText(getActivity(),"Get list user Faile",Toast.LENGTH_LONG).show();
                    }
                });
            }
        }.start();

    }
}
