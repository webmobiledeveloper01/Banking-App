package com.ifstatic.banking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.ifstatic.banking.Adapters.FirstSliderAdapter;
import com.ifstatic.banking.Models.FirstSliderModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private TextView txtSkip;
    private SliderView slider;
    private MaterialButton btnLogin,btnSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initializeView();

        txtSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Skip the login
                Intent intent=new Intent(FirstActivity.this,Login.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Login to app
                Intent intent=new Intent(FirstActivity.this,Login.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Signup to app
                Intent intent=new Intent(FirstActivity.this,Signup.class);
                startActivity(intent);
            }
        });

        setSlider();
    }

    private void setSlider(){
        FirstSliderAdapter firstSliderAdapter=new FirstSliderAdapter(this);

        List<FirstSliderModel>firstSliderModels=new ArrayList<>();

        FirstSliderModel firstSliderModel=new FirstSliderModel();
        firstSliderModel.setTitle("Welcome, Manage your expense");
        firstSliderModel.setDescription("easily manage your expenses by your mobile");

        firstSliderModels.add(firstSliderModel);
        firstSliderModels.add(firstSliderModel);
        firstSliderModels.add(firstSliderModel);
        firstSliderModels.add(firstSliderModel);

        firstSliderAdapter.renewItems(firstSliderModels);

        slider.setSliderAdapter(firstSliderAdapter);
        slider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        slider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        slider.startAutoCycle();
    }

    private void initializeView(){
        txtSkip=findViewById(R.id.txtSkip);
        slider=findViewById(R.id.slider);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignup=findViewById(R.id.btnSignup);
    }
}