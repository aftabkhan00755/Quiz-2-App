package com.example.quiz2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.quiz2.Interface.mobileAdInterface;
import com.example.quiz2.MobileAdGeneric.MobileInterstitialAd;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;

public class score1 extends AppCompatActivity implements mobileAdInterface {
    TextView result,marks;
    RatingBar rb;
    ImageView home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score1);

        marks = (TextView)findViewById(R.id.score);
        result = (TextView)findViewById(R.id.result);
        rb = (RatingBar) findViewById(R.id.ratingBar);
        home = (ImageView) findViewById(R.id.home);

        // Globalvar gv = new Globalvar();
        // String s = gv.getScore();

        String score = getIntent().getExtras().getString("myscore");
        marks.setText(score);
        Float sc = Float.valueOf(score);

        ObjectAnimator anim = ObjectAnimator.ofFloat(rb,"rating",sc);
        anim.setDuration(2000);
        anim.start();
        rb.setRating(sc);

        switch (score)
        {
            case "1" : rb.setRating(1.0f);
                result.setText("Poor");
                break;

            case "2" : rb.setRating(2.0f);
                result.setText("Fair");
                break;

            case "3" : rb.setRating(3.0f);
                result.setText("Good");
                break;

            case "4" : rb.setRating(4.0f);
                result.setText("Very Good");
                break;

            case "5" : rb.setRating(5.0f);
                result.setText("Excellent");
                break;

        }


        mobileAdInterface mobileAdInterface = this;
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobileInterstitialAd mobileInterstitialAd = new MobileInterstitialAd(score1.this,mobileAdInterface,score1.this);
                if (mobileInterstitialAd.loadAd() != null) {
                    Intent myintent = new Intent(score1.this, MainActivity.class);
                    startActivity(myintent);
                    finish();
                }
            }
        });




    }


/*    private PopupWindow pw;
    private void initiatePopupWindow() {
        try {
            //We need to get the instance of the LayoutInflater, use the context of this activity
            LayoutInflater inflater = (LayoutInflater) com.example.quiz2.score1.this
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            View layout = inflater.inflate(R.layout.popup,
                    (ViewGroup) findViewById(R.id.popup_element));
            // create a 300px width and 470px height PopupWindow
            pw = new PopupWindow(layout, 1400, 800, true);
            // display the popup in the center
            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);

            Button cancelButton = (Button) layout.findViewById(R.id.end_data_send_button);
            cancelButton.setOnClickListener(cancel_button_click_listener);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button_click_listener = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };*/


    @Override
    public void onBackPressed() {
        /*  super.onBackPressed();*/

        MobileInterstitialAd mobileInterstitialAd = new MobileInterstitialAd(score1.this,this,score1.this);
        if (mobileInterstitialAd.loadAd() != null) {
            Intent myintent = new Intent(score1.this, MainActivity.class);
            startActivity(myintent);
            finish();
        }

    }

    @Override
    public void onAdLoaded(InterstitialAd ad, Activity activity) {
        ad.show(activity);
        ad.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                Intent myintent = new Intent(score1.this, MainActivity.class);
                startActivity(myintent);
                finish();

            }

            @Override
            public void onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent();


            }

            @Override
            public void onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent();
                Intent myintent = new Intent(score1.this, MainActivity.class);
                startActivity(myintent);
                finish();

            }
        });

    }

    @Override
    public void onAdFailedToLoad(String error, Activity activity) {

        startActivity(new Intent(activity,MainActivity.class));
        finish();
    }
}