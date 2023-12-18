package com.ifstatic.banking;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.ifstatic.banking.Adapters.BadgeAdapter;

import java.util.ArrayList;
import java.util.List;

public class Profile extends Fragment {

    private ImageButton imgBtnEditProfile;
    private RecyclerView recyclerBadges;
    private RelativeLayout relProfileSetting,relPrivacy,relNotification;

    private BadgeAdapter badgeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        initializeView(view);

        badgeAdapter=new BadgeAdapter(requireContext());
        recyclerBadges.setAdapter(badgeAdapter);

        imgBtnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireContext(),ProfileSetting.class);
                startActivity(intent);
            }
        });

        relProfileSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBtnEditProfile.callOnClick();
            }
        });

        relPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireContext(),ChangePassword.class);
                startActivity(intent);
            }
        });

        relNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireContext(),NotificationSetting.class);
                startActivity(intent);
            }
        });

        setBadges();

        return view;
    }

    private void setBadges(){
        List<Integer>badges=new ArrayList<>();
        badges.add(R.drawable.badge1);
        badges.add(R.drawable.badge2);
        badges.add(R.drawable.badge3);
        badges.add(R.drawable.badge4);

        badgeAdapter.setBadges(badges);
    }

    private void initializeView(View view){
        recyclerBadges=view.findViewById(R.id.recyclerBadges);
        recyclerBadges.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false));
        imgBtnEditProfile=view.findViewById(R.id.imgBtnEditProfile);
        relProfileSetting=view.findViewById(R.id.relProfileSetting);
        relPrivacy=view.findViewById(R.id.relPrivacy);
        relNotification=view.findViewById(R.id.relNotification);
    }
}