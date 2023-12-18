package com.ifstatic.banking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class ReceiveDirectStatus extends AppCompatActivity {

    private RelativeLayout relSearching,relStatus;
    private LottieAnimationView lottie_animation_searching,lottie_animation;
    private TextView txtCancel,txtStatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_direct_status);
        initializeView();

        lottie_animation.setAnimation(R.raw.success);

        lottie_animation_searching.setAnimation(R.raw.searching);
        lottie_animation_searching.playAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                relSearching.setVisibility(View.GONE);
                lottie_animation_searching.cancelAnimation();
                relStatus.setVisibility(View.VISIBLE);
                lottie_animation.playAnimation();
            }
        },5000);



        lottie_animation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onBackPressed();
                    }
                },1000);
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {

            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initializeView(){
        relSearching=findViewById(R.id.relSearching);
        relStatus=findViewById(R.id.relStatus);
        lottie_animation=findViewById(R.id.lottie_animation);
        lottie_animation_searching=findViewById(R.id.lottie_animation_searching);
        txtCancel=findViewById(R.id.txtCancel);
        txtStatus=findViewById(R.id.txtStatus);
    }
}