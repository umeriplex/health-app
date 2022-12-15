package com.example.fitraho.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fitraho.R;
import com.example.fitraho.databinding.ActivityPlashBinding;

public class SplashActivity extends AppCompatActivity {

    TextView logo, teg, made;
    ImageView ayah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plash);

        //Hide status bar and make full window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        logo = findViewById(R.id.textView3);
        teg = findViewById(R.id.textView4);
        made = findViewById(R.id.textView5);

        logo.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        teg.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        made.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));

        delay();



    }

    void delay(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 4000);
    }
}