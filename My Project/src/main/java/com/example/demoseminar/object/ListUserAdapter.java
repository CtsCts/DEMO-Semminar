package com.example.demoseminar.object;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoseminar.R;

import java.util.List;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.UserViewHolder> {

    private List<ListUser> mListUsers;

    public ListUserAdapter(List<ListUser> mListUsers) {
        this.mListUsers = mListUsers;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .layout_list_user,parent,false);

        return  new UserViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        ListUser listUser = mListUsers.get(position);
        if(listUser == null){
            return;
        }

        holder.tvName.setText(listUser.getName());
        holder.tvAge.setText(listUser.getAge());
        holder.tvClass.setText(listUser.getNumclass());
        holder.tvEmail.setText(listUser.getEmail());
    }

    @Override
    public int getItemCount() {
        if(mListUsers != null) {
            return mListUsers.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tvAge;
        private TextView tvClass;
        private TextView tvEmail;
        private ImageView imAvatar;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            imAvatar = itemView.findViewById(R.id.image_avatar_user);
            tvName  = itemView.findViewById(R.id.txt_name_avatar_user);
            tvAge   = itemView.findViewById(R.id.txt_born_avatar_user);
            tvClass = itemView.findViewById(R.id.txt_class_avatar_user);
            tvEmail = itemView.findViewById(R.id.txt_email_user);
        }
    }


}
