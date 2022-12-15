package com.example.fitraho.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fitraho.R;
import com.example.fitraho.databinding.ActivityObeseBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ObeseActivity extends AppCompatActivity {

    ActivityObeseBinding binding;
    int sel;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityObeseBinding.inflate(getLayoutInflater());
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



        changeStatusBarColor("#916C44");

        binding.btnEng.setOnClickListener(view -> {
            setLocale("");
            recreate();
        });

        binding.btnUrdu.setOnClickListener(view -> {
            setLocale("ur");
            recreate();
        });

        slider();
    }

    private void changeStatusBarColor(String color){
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }

    private void slider() {
        binding.carousel.registerLifecycle(getLifecycle());
        List<CarouselItem> list = new ArrayList<>();
        list.add(new CarouselItem(R.drawable.obex));
        list.add(new CarouselItem(R.drawable.one));
        list.add(new CarouselItem(R.drawable.rest));
        list.add(new CarouselItem(R.drawable.two));
        list.add(new CarouselItem(R.drawable.rest));
        list.add(new CarouselItem(R.drawable.three));
        list.add(new CarouselItem(R.drawable.rest));
        list.add(new CarouselItem(R.drawable.four));
        list.add(new CarouselItem(R.drawable.rest));
        list.add(new CarouselItem(R.drawable.five));
        list.add(new CarouselItem(R.drawable.rest));
        list.add(new CarouselItem(R.drawable.six));
        list.add(new CarouselItem(R.drawable.rest));
        list.add(new CarouselItem(R.drawable.seven));
        binding.carousel.setData(list);

    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;

        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setLocale("");
        recreate();
    }
}