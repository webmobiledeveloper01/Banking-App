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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context mCxt;
    private UserClickEvents userClickEvents;
    private List<UserModel>userModels=new ArrayList<>();

    public UserAdapter(Context mCxt, UserClickEvents userClickEvents) {
        this.mCxt = mCxt;
        this.userClickEvents = userClickEvents;
    }

    public void setUsers(List<UserModel>userModels){
        this.userModels=userModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mCxt).inflate(R.layout.view_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserModel userModel=userModels.get(position);
        if (userModel!=null){
            if (userModel.getId()==0){
                holder.txtUserName.setText("Add new contacts");
                Glide.with(mCxt.getApplicationContext()).load(mCxt.getResources().getDrawable(R.drawable.plus)).into(holder.circleUserProfile);
            }else {
                holder.txtUserName.setText(userModel.getName());
                Glide.with(mCxt.getApplicationContext()).load(userModel.getProfile()).into(holder.circleUserProfile);
            }

        }
    }

    @Override
    public int getItemCount() {
        return userModels.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView circleUserProfile;
        private TextView txtUserName;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            circleUserProfile=itemView.findViewById(R.id.circleUserProfile);
            txtUserName=itemView.findViewById(R.id.txtUserName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition()!=-1&&getAdapterPosition()<userModels.size()){
                        userClickEvents.onUserClicked(userModels.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface UserClickEvents{
        void onUserClicked(UserModel userModel);
    }
}
