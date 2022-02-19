package com.example.demoseminar.subject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoseminar.R;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.subjectViewHolder>  {//
    // B4  extend doi tuong can truyen cycleview laf 1 adapter vs thuoc tinh can co o B3


    //  B1   tao danh sach doi tuong
    List<Subject> mSubjects;

    //  B2    khoi tao ham contructor
    public SubjectAdapter(List<Subject> mSubjects) {
        this.mSubjects = mSubjects;
    }

    @NonNull
    @Override
    public subjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //      B5      tao bien View gan vao layout da thiet ke de hien thi doi tuong
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.
                layout_list_subject,parent,false);

        return new subjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull subjectViewHolder holder, int position) {
        //      B6      lien ket vitri doi tuong
        Subject subject = mSubjects.get(position);
        if(subject == null){
            return;
        }


    }

    @Override
    public int getItemCount() {
        //      B7  tra ve so luong doi tuong trong mang
        return mSubjects.size();
    }

    //  B3 tao doi tuong anh xa voi tham chieu cua recyclevie
    public class subjectViewHolder extends  RecyclerView.ViewHolder {

        private TextView txtSubject;

        public subjectViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubject = itemView.findViewById(R.id.txt_subject);
        }
    };
}
