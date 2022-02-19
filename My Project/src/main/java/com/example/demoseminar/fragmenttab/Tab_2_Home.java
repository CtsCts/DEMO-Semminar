package com.example.demoseminar.fragmenttab;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoseminar.R;
import com.example.demoseminar.object.ListUser;
import com.example.demoseminar.object.ListUserAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tab_2_Home extends Fragment {

    int  flag=0;
    private String nameclass;
    private View view;
    private ProgressDialog dialog;

    private RecyclerView recyclerView;
    private ListUserAdapter userAdapter;
    private List<ListUser> listUserArrayAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmet_tab_2_home,container,false);

        checkClass();
        dialog = new ProgressDialog(getActivity());

        recyclerView = view.findViewById(R.id.recycler_view_tab1);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);

        recyclerView.addItemDecoration(dividerItemDecoration);

        listUserArrayAdapter = new ArrayList<>();

        userAdapter = new ListUserAdapter(listUserArrayAdapter);

        recyclerView.setAdapter(userAdapter);
        checkUserAccAction();



        return view;
    }

    private void checkUserAccAction() {
        dialog.show();

        FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();
        String ID = muser.getUid().trim();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference check = database.getReference("list_teacher");
        check.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if(ID.equals(dataSnapshot.getValue().toString().trim())){
                      flag = 1;
                    }
                }
                if(flag==0){
                    getlistUserFromDatabase();

                }else{
                    getlistStudentRelateTeacher();
                }
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getlistStudentRelateTeacher() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("list_user");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //datasnapshot tra ve tong cac phan tu trong list_user
                //getchidren lay tung item trong list_user
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    //lay cac gia tri can thiet tu class listuser
                    ListUser user = dataSnapshot.getValue(ListUser.class);

                    //add du lieu get dc tu database vao litsuser da tao
                    //in tat ca danh sach

                    listUserArrayAdapter.add(user);

                }
                //lam moi gia tri data thay doi
                userAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getActivity(),"Get list user Faile",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void checkClass() {
        FirebaseUser muser = FirebaseAuth.getInstance().getCurrentUser();
        String ID = muser.getUid();

        FirebaseDatabase database2 = FirebaseDatabase.getInstance();
        DatabaseReference myRef2 = database2.getReference("list_user").child(ID).child("numclass");

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nameclass = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void getlistUserFromDatabase(){

        CountDownTimer count = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                checkClass();
                dialog.setMessage("Loading...,please wait...");
                dialog.show();
            }

            @Override
            public void onFinish() {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("list_user");


                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //datasnapshot tra ve tong cac phan tu trong list_user
                        //getchidren lay tung item trong list_user
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                            //lay cac gia tri can thiet tu class listuser
                            ListUser user = dataSnapshot.getValue(ListUser.class);
                            if(nameclass.equals(user.getNumclass())){

                                listUserArrayAdapter.add(user);
                            }
                            //add du lieu get dc tu database vao litsuser da tao
                            //in tat ca danh sach

                                //listUserArrayAdapter.add(user);

                        }
                        //lam moi gia tri data thay doi
                        userAdapter.notifyDataSetChanged();
                        dialog.dismiss();

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
