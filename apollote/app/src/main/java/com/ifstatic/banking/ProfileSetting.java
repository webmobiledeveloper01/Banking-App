package com.ifstatic.banking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.button.MaterialButton;

public class ProfileSetting extends AppCompatActivity {

    private ImageButton imgBtnBack;
    private MaterialButton btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);
        initializeView();

        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void initializeView(){
        imgBtnBack=findViewById(R.id.imgBtnBack);
        btnSave=findViewById(R.id.btnSave);
    }
}