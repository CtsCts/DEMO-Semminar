package com.example.demoseminar.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demoseminar.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class ChatFragment extends Fragment {

    private static final String AUTH_KEY = "key=YOUR-SERVER-KEY";
    private TextView mTextView;
    private String token;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.layout_fragment_chat,container,false);




        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    token = task.getException().getMessage();
                    Log.w("FCM TOKEN", task.getException());
                }else {
                    token = task.getResult();
                    Log.i("FCM TOKEN", token);
                }

            }
        });
        return view;
    }

}
