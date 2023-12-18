package com.ifstatic.banking.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ifstatic.banking.Models.UserModel;
import com.ifstatic.banking.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserCardAdapter extends RecyclerView.Adapter<UserCardAdapter.UserCardViewHolder> {

    private Context mCxt;
    private UserCardClickEvents userCardClickEvents;
    private List<UserModel>userModels=new ArrayList<>();


    public UserCardAdapter(Context mCxt, UserCardClickEvents userCardClickEvents) {
        this.mCxt = mCxt;
        this.userCardClickEvents = userCardClickEvents;
    }

    public void setUsers(List<UserModel>userModels){
        this.userModels=userModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mCxt).inflate(R.layout.view_card_user,parent,false);
        return new UserCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCardViewHolder holder, int position) {
        UserModel userModel=userModels.get(position);
        if (userModel!=null){
            Glide.with(mCxt.getApplicationContext()).load(userModel.getProfile()).into(holder.circleUserProfile);
            holder.txtUserName.setText(userModel.getName());
            holder.txtUserEmail.setText(userModel.getEmail());
        }
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public class UserCardViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView circleUserProfile;
        private TextView txtUserName;
        private TextView txtUserEmail;
        public UserCardViewHolder(@NonNull View itemView) {
            super(itemView);
            circleUserProfile=itemView.findViewById(R.id.circleUserProfile);
            txtUserName=itemView.findViewById(R.id.txtUserName);
            txtUserEmail=itemView.findViewById(R.id.txtUserEmail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition()!=-1&&getAdapterPosition()<userModels.size()){
                        userCardClickEvents.onUserClicked(userModels.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface UserCardClickEvents{
        void onUserClicked(UserModel userModel);
    }
}
