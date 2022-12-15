package com.example.fitraho.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.fitraho.R;
import com.example.fitraho.databinding.ActivityChinBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

import java.util.ArrayList;
import java.util.List;

public class ChinActivity extends AppCompatActivity {

    ActivityChinBinding binding;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
                super.onAdClicked();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError);
                mAdView.loadAd(adRequest);
            }

            @Override
            public void onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                super.onAdOpened();
            }
        });

        changeStatusBarColor("#676767");


        binding.recView.setLayoutManager(new LinearLayoutManager(this));
        binding.recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        List<GifModel> list = new ArrayList<>();
        list.add(new GifModel(R.drawable.cone, "Straight jaw jut: Tilt head back and look toward ceiling.\n" +
                "Push your lower jaw forward to feel a stretch under the chin.\n" +
                "Hold for a count of 10.\n" +
                "Relax jaw and return head to a neutral position."));
        list.add(new GifModel(R.drawable.ctwo, "Ball exercise: Place a 9- to 10-inch ball under chin.\n" +
                "Press chin down against the ball.\n" +
                "Repeat several times daily."));
        list.add(new GifModel(R.drawable.cthree, "Pucker up: With head tilted back, look at the ceiling.\n" +
                "Pucker your lips as if you’re kissing the ceiling to stretch the area beneath your chin.\n" +
                "Stop puckering and bring your head back to its normal position."));
        list.add(new GifModel(R.drawable.cfour, "Tongue stretch: Looking straight ahead, stick your tongue out as far as you can.\n" +
                "Lift your tongue upward and toward your nose.\n" +
                "Hold for 10 seconds and release."));
        list.add(new GifModel(R.drawable.cfive, "Neck stretch: Tilt your head back and look at the ceiling.\n" +
                "Press your tongue against the roof of your mouth.\n" +
                "Hold for 5 to 10 seconds and release."));
        list.add(new GifModel(R.drawable.csix, "Bottom jaw jut: Tilt your head back and look at the ceiling.\n" +
                "Turn your head to the right.\n" +
                "Slide your bottom jaw forward.\n" +
                "Hold for 5 to 10 seconds and release.\n" +
                "Repeat with head turned to the left."));
        GifAdapter adapter = new GifAdapter(this, list);

        binding.recView.setAdapter(adapter);
    }

    private void changeStatusBarColor(String color) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }
}