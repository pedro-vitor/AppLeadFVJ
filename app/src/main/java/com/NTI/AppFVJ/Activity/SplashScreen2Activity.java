package com.NTI.AppFVJ.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.NTI.AppFVJ.R;

public class SplashScreen2Activity extends AppCompatActivity {
    private static int TIME_OUT = 3000;

    private ImageView imageView;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

        imageView = findViewById(R.id.imgLogo);
        imageView.setBackgroundResource(R.drawable.splash_animation);

        animationDrawable = (AnimationDrawable) imageView.getBackground();

    }

    protected void onResume() {
         super.onResume();

         animationDrawable.start();

         checkAnimation(50, animationDrawable);


    }


    private void checkAnimation(final int time, final AnimationDrawable animationDrawable){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if(animationDrawable.getCurrent() != animationDrawable.getFrame(animationDrawable.getNumberOfFrames() - 1))
                    checkAnimation(time, animationDrawable);
                else {
                    Intent intent = new Intent(SplashScreen2Activity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },time);
    }


}
/*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen2Activity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },TIME_OUT);*/