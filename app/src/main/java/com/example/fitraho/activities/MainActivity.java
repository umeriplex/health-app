package com.example.fitraho.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.fitraho.R;

import com.example.fitraho.databinding.ActivityMainBinding;
import com.example.fitraho.databinding.BmiDetailsBinding;
import com.example.fitraho.databinding.ContactMeDialogeBinding;
import com.example.fitraho.databinding.DialogeLayoutBinding;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener;
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    ActivityMainBinding binding;
    private AdView mAdView;

    String[] w = {"kg", "pound"};
    String[] h = {"cm", "m", "ft", "inch"};
    int indexW, indexH;
    double reqWeight;

    private final String fbID = "https://www.facebook.com/umeriftikhar2526";
    private final String instaID = "https://www.instagram.com/_ummmer/?hl=en";
    private final String webID = "https://umer2526.github.io/MyPortfolio/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

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


        initializeSpinner();
        changeActivity();
        slider();



        binding.spW.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                indexW = binding.spW.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.spH.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                indexH = binding.spH.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.checkBTN.setOnClickListener(e -> {
            calculation();
        });

        binding.bmi.setOnClickListener(e -> {
            showBMIDetails();
        });

        binding.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });



    }

    private void calculation() {
        try {

            if (TextUtils.isEmpty(binding.etW.getText().toString())) {
                binding.etW.setError("put your weight");
            } else if (TextUtils.isEmpty(binding.etH.getText().toString())) {
                binding.etH.setError("put your height");
            } else {
                if (indexW == 0 & indexH == 0) {
                    double weight = Double.parseDouble(binding.etW.getText().toString());
                    double height = Double.parseDouble(binding.etH.getText().toString());
                    double res = (weight / height / height) * 10000;
                    //binding.bmi.setText("Your BMI is : " + new DecimalFormat("##.#").format(res));
                    if (res > 24.9) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 > 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res < 18.5) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 18.6) {
                            ++w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res > 30) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else {
                        res(res, 0);
                    }
                }

                if (indexW == 1 & indexH == 0) {
                    double weight = Double.parseDouble(binding.etW.getText().toString()) / 2.205;
                    double height = Double.parseDouble(binding.etH.getText().toString());
                    double res = (weight / height / height) * 10000;
                    //binding.bmi.setText("Your BMI is : " + new DecimalFormat("##.#").format(res));
                    if (res > 24.9) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 > 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res < 18.5) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 18.6) {
                            ++w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res > 30) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else {
                        res(res, 0);
                    }
                }

                if (indexW == 0 & indexH == 1) {
                    double weight = Double.parseDouble(binding.etW.getText().toString());
                    double height = Double.parseDouble(binding.etH.getText().toString()) * 100;
                    double res = (weight / height / height) * 10000;
                    //binding.bmi.setText("Your BMI is : " + new DecimalFormat("##.#").format(res));
                    if (res > 24.9) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 > 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res < 18.5) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 18.6) {
                            ++w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res > 30) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else {
                        res(res, 0);
                    }
                }

                if (indexW == 0 & indexH == 2) {
                    double weight = Double.parseDouble(binding.etW.getText().toString());
                    double height = Double.parseDouble(binding.etH.getText().toString()) * 30.48;
                    double res = (weight / height / height) * 10000;
                    // binding.bmi.setText("Your BMI is : " + new DecimalFormat("##.#").format(res));
                    if (res > 24.9) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 > 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res < 18.5) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 18.6) {
                            ++w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res > 30) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else {
                        res(res, 0);
                    }
                }

                if (indexW == 0 & indexH == 3) {
                    double weight = Double.parseDouble(binding.etW.getText().toString());
                    double height = Double.parseDouble(binding.etH.getText().toString()) * 2.54;
                    double res = (weight / height / height) * 10000;
                    // binding.bmi.setText("Your BMI is : " + new DecimalFormat("##.#").format(res));
                    if (res > 24.9) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 > 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res < 18.5) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 18.6) {
                            ++w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res > 30) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else {
                        res(res, 0);
                    }
                }

                if (indexW == 1 & indexH == 1) {
                    double weight = Double.parseDouble(binding.etW.getText().toString()) / 2.205;
                    double height = Double.parseDouble(binding.etH.getText().toString()) * 100;
                    double res = (weight / height / height) * 10000;
                    // binding.bmi.setText("Your BMI is : " + new DecimalFormat("##.#").format(res));
                    if (res > 24.9) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 > 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res < 18.5) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 18.6) {
                            ++w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res > 30) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else {
                        res(res, 0);
                    }
                }

                if (indexW == 1 & indexH == 2) {
                    double weight = Double.parseDouble(binding.etW.getText().toString()) / 2.205;
                    double height = Double.parseDouble(binding.etH.getText().toString()) * 30.48;
                    double res = (weight / height / height) * 10000;
                    // binding.bmi.setText("Your BMI is : " + new DecimalFormat("##.#").format(res));
                    if (res > 24.9) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 > 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res < 18.5) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 18.6) {
                            ++w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res > 30) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else {
                        res(res, 0);
                    }
                }

                if (indexW == 1 & indexH == 3) {
                    double weight = Double.parseDouble(binding.etW.getText().toString()) / 2.205;
                    double height = Double.parseDouble(binding.etH.getText().toString()) * 2.54;
                    double res = (weight / height / height) * 10000;
                    // binding.bmi.setText("Your BMI is : " + new DecimalFormat("##.#").format(res));
                    if (res > 24.9) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 > 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res < 18.5) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 18.6) {
                            ++w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else if (res > 30) {
                        double w1 = weight;
                        double r1 = res;
                        while (r1 < 24.5) {
                            --w1;
                            double r = (w1 / height / height) * 10000;
                            r1 = r;
                        }
                        //Toast.makeText(this, "Weight " + w1, Toast.LENGTH_LONG).show();
                        res(res, w1);
                    } else {
                        res(res, 0);
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Fields are empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void res(double res, double w1) {
        if (res < 18.5)
            underW(res, w1);
        else if (res >= 18 && res <= 24.9)
            showFit(res);
        else if (res > 24.9 && res <= 29.9)
            showAvg(res, w1);
        else if (res > 29.9 && res <= 34.9)
            showObse(res, w1);
        else if (res > 29.9)
            showExtreamObse(res);
    }

    private void initializeSpinner() {
        ArrayAdapter<String> weight_adapter = new ArrayAdapter<>(this, R.layout.sp_item, w);
        ArrayAdapter<String> height_adapter = new ArrayAdapter<>(this, R.layout.sp_item, h);

        binding.spW.setAdapter(weight_adapter);
        binding.spH.setAdapter(height_adapter);
    }

    private void showFit(double res) {
        DialogeLayoutBinding bindingFit = DialogeLayoutBinding.inflate(getLayoutInflater());
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(bindingFit.getRoot())
                .create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        dialog.show();
        DecimalFormat df = new DecimalFormat("####0.00");
        bindingFit.res.setText("YOUR BMI IS " + df.format(res));
        bindingFit.okFit.setOnClickListener(e -> {
            dialog.dismiss();
        });


    }

    private void showAvg(double res, double w1) {
        DialogeLayoutBinding bindingFit = DialogeLayoutBinding.inflate(getLayoutInflater());
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(bindingFit.getRoot())
                .create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        dialog.show();
        DecimalFormat df = new DecimalFormat("####0.00");
        bindingFit.res.setText("YOUR BMI IS " + df.format(res));
        bindingFit.img.setImageResource(R.drawable.avg);
        bindingFit.dest.setText("YOU ARE OVER WEIGHTED");
        bindingFit.res.setTextColor(Color.parseColor("#F3B50B"));
        bindingFit.okFit.setText("see diet");
        bindingFit.diffLay.setVisibility(View.VISIBLE);
        bindingFit.diff.setText(new DecimalFormat("##.#").format(w1));
        bindingFit.okFit.setOnClickListener(e -> {
            dialog.dismiss();
            startActivity(new Intent(MainActivity.this, OverWeightActivity.class));
        });


    }

    private void showObse(double res, double w1) {
        DialogeLayoutBinding bindingFit = DialogeLayoutBinding.inflate(getLayoutInflater());
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(bindingFit.getRoot())
                .create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        dialog.show();
        DecimalFormat df = new DecimalFormat("####0.00");
        bindingFit.res.setText("YOUR BMI IS " + df.format(res));
        bindingFit.dest.setText("YOU ARE VERY OVER WEIGHTED");
        bindingFit.img.setImageResource(R.drawable.obse);
        bindingFit.res.setTextColor(Color.parseColor("#FB7A38"));
        bindingFit.okFit.setText("see diet");
        bindingFit.diffLay.setVisibility(View.VISIBLE);
        bindingFit.diff.setText(new DecimalFormat("##.#").format(w1));
        bindingFit.okFit.setOnClickListener(e -> {
            dialog.dismiss();
            startActivity(new Intent(MainActivity.this, ObeseActivity.class));
        });


    }

    private void showExtreamObse(double res) {
        DialogeLayoutBinding bindingFit = DialogeLayoutBinding.inflate(getLayoutInflater());
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(bindingFit.getRoot())
                .create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        dialog.show();
        DecimalFormat df = new DecimalFormat("####0.00");
        bindingFit.res.setText("YOUR BMI IS " + df.format(res));
        bindingFit.dest.setText("Extremely over WEIGHTED, you need to join gym and personal training");
        bindingFit.img.setImageResource(R.drawable.scared);
        bindingFit.res.setTextColor(Color.parseColor("#FF1111"));
        bindingFit.okFit.setText("ok");
        bindingFit.okFit.setOnClickListener(e -> {
            dialog.dismiss();
        });


    }

    private void underW(double res, double w1) {
        DialogeLayoutBinding bindingFit = DialogeLayoutBinding.inflate(getLayoutInflater());
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(bindingFit.getRoot())
                .create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        dialog.show();
        DecimalFormat df = new DecimalFormat("####0.00");
        bindingFit.res.setText("YOUR BMI IS " + df.format(res));
        bindingFit.dest.setText("YOU ARE UNDER WEIGHTED");
        bindingFit.img.setImageResource(R.drawable.salad);
        bindingFit.res.setTextColor(Color.parseColor("#1D96DB"));
        bindingFit.diffLay.setVisibility(View.VISIBLE);
        bindingFit.diff.setText(new DecimalFormat("##.#").format(w1));
        bindingFit.okFit.setText("see diet");
        bindingFit.okFit.setOnClickListener(e -> {
            dialog.dismiss();
            startActivity(new Intent(MainActivity.this, UnderWeightActivity.class));
        });


    }

    private void showBMIDetails() {
        BmiDetailsBinding detailsBinding = BmiDetailsBinding.inflate(getLayoutInflater());
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(detailsBinding.getRoot())
                .create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        dialog.show();
        dialog.setCancelable(true);
    }

    private void changeActivity() {
        binding.uwLay.setOnClickListener(e -> {
            startActivity(new Intent(MainActivity.this, UnderWeightActivity.class));
        });
        binding.owLay.setOnClickListener(e -> {
            startActivity(new Intent(MainActivity.this, OverWeightActivity.class));
        });
        binding.oLay.setOnClickListener(e -> {
            startActivity(new Intent(MainActivity.this, ObeseActivity.class));
        });
        binding.calLayout.setOnClickListener(e -> {
            startActivity(new Intent(MainActivity.this, CalorieActivity.class));
        });
    }

    private void slider() {
        binding.belly.registerLifecycle(getLifecycle());
        List<CarouselItem> list = new ArrayList<>();
        list.add(new CarouselItem(R.drawable.belly));
        list.add(new CarouselItem(R.drawable.chin));
        list.add(new CarouselItem(R.drawable.shloudr));

        binding.belly.setCarouselListener(new CarouselListener() {
            @Override
            public ViewBinding onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public void onBindViewHolder(ViewBinding viewBinding, CarouselItem carouselItem, int i) {

            }

            @Override
            public void onClick(int i, CarouselItem carouselItem) {
                if (i == 0)
                    startActivity(new Intent(MainActivity.this,BellyActivity.class));
                if (i == 1)
                    startActivity(new Intent(MainActivity.this,ChinActivity.class));
                if (i == 2)
                    startActivity(new Intent(MainActivity.this,ShoulderActivity.class));

            }

            @Override
            public void onLongClick(int i, CarouselItem carouselItem) {
                if (i == 0)
                    Toast.makeText(MainActivity.this, "Best Diet Plan for Belly Fat!", Toast.LENGTH_LONG).show();
                else if (i == 1)
                    Toast.makeText(MainActivity.this, "Best Exercise for lose Double Chin & Face Fat!", Toast.LENGTH_LONG).show();
            }
        });

        binding.belly.setData(list);

    }

    private void showMenu(View view){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);//View will be an anchor for PopupMenu
        popupMenu.inflate(R.menu.menu);
        Menu menu = popupMenu.getMenu();
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.contact){
            ContactMeDialogeBinding bindingC = ContactMeDialogeBinding.inflate(getLayoutInflater());
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setView(bindingC.getRoot())
                    .create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
            dialog.show();
            dialog.setCancelable(true);

            bindingC.gmail.setOnClickListener(e->{
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"umeriftikhar2526@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Subject");
                intent.putExtra(Intent.EXTRA_TEXT,"Body of the content here...");
                intent.putExtra(Intent.EXTRA_CC,"mailcc@gmail.com");
                intent.setType("text/html");
                intent.setPackage("com.google.android.gm");
                startActivity(Intent.createChooser(intent, "Send mail"));
            });

            bindingC.wahtsapp.setOnClickListener(e->{
                try {
                    String headerReceiver = "";// Replace with your message.
                    String bodyMessageFormal = "";// Replace with your message.
                    String whatsappContain = headerReceiver + bodyMessageFormal;
                    String trimToNumner = "+923402093883"; //10 digit number
                    Intent intent = new Intent ( Intent.ACTION_VIEW );
                    intent.setData ( Uri.parse ( "https://wa.me/" + trimToNumner + "/?text=" + "" ) );
                    startActivity ( intent );
                } catch (Exception ee) {
                    ee.printStackTrace ();
                }
            });

            bindingC.facebook.setOnClickListener(e->{
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fbID));
                startActivity(browserIntent);
            });

            bindingC.insta.setOnClickListener(e->{
                Intent likeIng = new Intent(Intent.ACTION_VIEW, Uri.parse(instaID));

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException eee) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(instaID)));
                }
            });

            bindingC.web.setOnClickListener(e->{
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webID));
                startActivity(browserIntent);
            });

        }
        return true;
    }
}