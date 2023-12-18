package com.ifstatic.banking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.ifstatic.banking.Adapters.NotificationAdapter;
import com.ifstatic.banking.Models.NotificationData;
import com.ifstatic.banking.Models.NotificationModel;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity {

    private ImageButton imgBtnBack;
    private RecyclerView recyclerNotifications;

    private NotificationAdapter notificationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        initializeView();

        notificationAdapter=new NotificationAdapter(this);
        recyclerNotifications.setAdapter(notificationAdapter);

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setNotifications();
    }

    private void setNotifications(){
        List<NotificationModel>notificationModels=new ArrayList<>();

        notificationModels.add(new NotificationModel("Today",null));
        notificationModels.add(new NotificationModel("",new NotificationData("You received a payment of $830.00 from Josie Amanda","",R.drawable.img_sample,"09:20AM",false)));
        notificationModels.add(new NotificationModel("",new NotificationData("John DeoY requested a payment of $200.00 from","",R.drawable.img_sample,"08:00AM",true)));
        notificationModels.add(new NotificationModel("Yesterday",null));
        notificationModels.add(new NotificationModel("",new NotificationData("You received a payment of $830.00 from Josie Amanda","",R.drawable.img_sample,"09:20AM",false)));
        notificationModels.add(new NotificationModel("This weekend",null));
        notificationModels.add(new NotificationModel("",new NotificationData("John DeoY requested a payment of $200.00 from","",R.drawable.img_sample,"08:00AM",true)));
        notificationModels.add(new NotificationModel("",new NotificationData("You received a payment of $830.00 from Josie Amanda","",R.drawable.img_sample,"09:20AM",false)));

        notificationAdapter.setNotifications(notificationModels);
    }

    private void initializeView(){
        imgBtnBack=findViewById(R.id.imgBtnBack);
        recyclerNotifications=findViewById(R.id.recyclerNotifications);
        recyclerNotifications.setLayoutManager(new LinearLayoutManager(this));
    }
}