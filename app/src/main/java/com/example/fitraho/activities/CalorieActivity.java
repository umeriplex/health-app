package com.example.fitraho.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fitraho.R;
import com.example.fitraho.databinding.ActivityCalorieBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalorieActivity extends AppCompatActivity {

    ActivityCalorieBinding binding;
    int ganderFlag = 1;
    double val = 1.2;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCalorieBinding.inflate(getLayoutInflater());
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

        checkGander();

        binding.btnCal.setOnClickListener(e -> {

            try {
                int weight = Integer.parseInt(binding.weight.getText().toString());
                int height = Integer.parseInt(binding.height.getText().toString());
                int age = Integer.parseInt(binding.age.getText().toString());

                if (TextUtils.isEmpty(binding.weight.getText().toString())){
                    binding.weight.setError("Empty!");
                    return;
                }

                else if (weight > 150){
                    binding.weight.setError("can`t be greater then 150");
                    return;
                }

                else if (TextUtils.isEmpty(binding.height.getText().toString())){
                    binding.weight.setError("Empty!");
                    return;
                }

                else if (height > 300){
                    binding.height.setError("can`t be greater then 250");
                    return;
                }

                else if (TextUtils.isEmpty(binding.age.getText().toString())){
                    binding.weight.setError("Empty!");
                    return;
                }

                else if (age > 100){
                    binding.age.setError("enter realistic age");
                    return;
                }

                else{
                    calculationBMR(weight, height, age);
                }

            }catch (Exception err){
                Toast.makeText(this, "Fields are empty", Toast.LENGTH_LONG).show();
            }

        });


    }

    private void checkGander() {
        binding.male.setOnClickListener(e -> {
            ganderFlag = 1;
        });
        binding.female.setOnClickListener(e -> {
            ganderFlag = 0;
        });

    }

    private void calculationBMR(double weight, double height, double age) {
        if (ganderFlag == 0) {
            DecimalFormat df = new DecimalFormat("0.00");
            double femaleBMR = (10 * weight) + (6.25 * height) - (5 * age) - (161);
            double a = Double.parseDouble(df.format(femaleBMR));
            calculateFemaleAMR(a);
        }
        if (ganderFlag == 1) {
            DecimalFormat df = new DecimalFormat("0.00");
            double femaleBMR = (10 * weight) + (6.25 * height) - (5 * age) + (5);
            double a = Double.parseDouble(df.format(femaleBMR));
            calculateFemaleAMR(a);
        }
    }

    private void checkBtn() {
        binding.radioGroupActivity.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                i = binding.radioGroupActivity.getCheckedRadioButtonId();
                if (i == R.id.s) {
                    val = 1.2;
                }
                if (i == R.id.l) {
                    val = 1.375;
                }
                if (i == R.id.m) {
                    val = 1.55;
                }
                if (i == R.id.v) {
                    val = 1.9;
                }

            }
        });
    }

    private void calculateFemaleAMR(double femaleBMR) {

        checkBtn();
        if (binding.radioGroupActivity.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please select your activity status", Toast.LENGTH_SHORT).show();
        } else {
            if (val == 1.2) {
                double femaleAMRl = femaleBMR * 1.2;
                binding.resLay.setVisibility(View.VISIBLE);
                int val = (int) femaleAMRl;
                binding.calRes.setText(String.valueOf(val));
            } else if (val == 1.375) {
                double femaleAMRl = femaleBMR * 1.375;
                binding.resLay.setVisibility(View.VISIBLE);
                int val = (int) femaleAMRl;
                binding.calRes.setText(String.valueOf(val));
            } else if (val == 1.55) {
                double femaleAMRl = femaleBMR * 1.55;
                binding.resLay.setVisibility(View.VISIBLE);
                int val = (int) femaleAMRl;
                binding.calRes.setText(String.valueOf(val));
            } else if (val == 1.9) {
                double femaleAMRl = femaleBMR * 1.9;
                binding.resLay.setVisibility(View.VISIBLE);
                int val = (int) femaleAMRl;
                binding.calRes.setText(String.valueOf(val));
            }
        }

    }


}
