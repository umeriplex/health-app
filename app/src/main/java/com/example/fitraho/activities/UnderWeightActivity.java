package com.example.fitraho.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.fitraho.R;
import com.example.fitraho.databinding.ActivityUnderWeightBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UnderWeightActivity extends AppCompatActivity {

    ActivityUnderWeightBinding binding;
    private AdView mAdView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUnderWeightBinding.inflate(getLayoutInflater());
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

        slider();

        binding.btnEng.setOnClickListener(view -> {
            setLocale("");
            recreate();
        });

        binding.btnUrdu.setOnClickListener(view -> {
            setLocale("ur");
            recreate();
        });



    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;

        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());


    }


    private void slider() {
        binding.carousel.registerLifecycle(getLifecycle());
        List<CarouselItem> list = new ArrayList<>();
        list.add(new CarouselItem(R.drawable.uwex));
        list.add(new CarouselItem(R.drawable.push));
        list.add(new CarouselItem(R.drawable.squats));
        list.add(new CarouselItem(R.drawable.lunges));
        list.add(new CarouselItem(R.drawable.headp));
        binding.carousel.setData(list);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setLocale("");
        recreate();
    }


}