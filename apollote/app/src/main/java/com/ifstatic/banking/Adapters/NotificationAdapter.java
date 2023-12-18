package com.ifstatic.banking.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.ifstatic.banking.Models.NotificationModel;
import com.ifstatic.banking.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mCxt;
    private List<NotificationModel>notificationModels;

    public NotificationAdapter(Context mCxt) {
        this.mCxt = mCxt;
    }

    public void setNotifications(List<NotificationModel>notificationModels){
        this.notificationModels=notificationModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==0){
           View view= LayoutInflater.from(mCxt).inflate(R.layout.view_notification_date,parent,false);
           return new NotificationDateViewHolder(view);
        }else{
            View view=LayoutInflater.from(mCxt).inflate(R.layout.view_notification,parent,false);
            return new NotificationViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NotificationModel notificationModel=notificationModels.get(position);

        if (getItemViewType(position)==0){
            NotificationDateViewHolder notificationDateViewHolder=(NotificationDateViewHolder) holder;
            notificationDateViewHolder.txtDate.setText(notificationModel.getDate());
        }else {
            NotificationViewHolder notificationViewHolder=(NotificationViewHolder) holder;
            notificationViewHolder.txtTitle.setText(notificationModel.getNotification().getTitle());
            notificationViewHolder.circleUser.setImageDrawable(mCxt.getDrawable(notificationModel.getNotification().getProfile()));
            notificationViewHolder.txtTime.setText(notificationModel.getNotification().getTime());
            if (notificationModel.getNotification().isPayRequest()){
                notificationViewHolder.btnPay.setVisibility(View.VISIBLE);
            }else {
                notificationViewHolder.btnPay.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return notificationModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (notificationModels.get(position).getNotification()==null){
            return 0;
        }else {
            return 1;
        }
    }

    public class NotificationDateViewHolder extends RecyclerView.ViewHolder{
        private TextView txtDate;
        public NotificationDateViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate=itemView.findViewById(R.id.txtDate);
        }
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView circleUser;
        private TextView txtTitle,txtTime;
        private MaterialButton btnPay;
        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            circleUser=itemView.findViewById(R.id.circleUser);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtTime=itemView.findViewById(R.id.txtTime);
            btnPay=itemView.findViewById(R.id.btnPay);
        }
    }
}
