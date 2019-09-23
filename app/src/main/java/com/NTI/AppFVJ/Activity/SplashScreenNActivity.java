package com.NTI.AppFVJ.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.NTI.AppFVJ.R;

public class SplashScreenNActivity extends AppCompatActivity {
    private final int TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_n);
        StartAnimation();
    }

    private void StartAnimation(){
        Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        anim.reset();
        LinearLayout linearLayout = findViewById(R.id.ll_screen);
        if(linearLayout != null){
            linearLayout.clearAnimation();
            linearLayout.startAnimation(anim);
        }

        anim = AnimationUtils.loadAnimation(this,R.anim.translate_left);
        anim.reset();
        ImageView imageViewF = findViewById(R.id.iv_logoF);
        if(imageViewF != null){
            imageViewF.clearAnimation();
            imageViewF.startAnimation(anim);
        }

        anim = AnimationUtils.loadAnimation(this,R.anim.translate);
        anim.reset();
        ImageView imageViewV = findViewById(R.id.iv_logoV);
        if(imageViewV != null){
            imageViewV.clearAnimation();
            imageViewV.startAnimation(anim);
        }

        anim = AnimationUtils.loadAnimation(this,R.anim.translate_right);
        anim.reset();
        ImageView imageViewJ = findViewById(R.id.iv_logoJ);
        if(imageViewJ != null){
            imageViewJ.clearAnimation();
            imageViewJ.startAnimation(anim);
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenNActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },TIME_OUT);
    }
}
