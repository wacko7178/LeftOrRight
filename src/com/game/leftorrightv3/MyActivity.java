package com.game.leftorrightv3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;



public class MyActivity extends Activity {
     private AdView mAdView;

     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         LinearLayout layout = new LinearLayout(this);
         layout.setOrientation(LinearLayout.VERTICAL);

         // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
         mAdView = new AdView(this);
         mAdView.setAdSize(AdSize.SMART_BANNER);
         mAdView.setAdUnitId("myAdUnitId");

         // Create an ad request.
         AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

         // Optionally populate the ad request builder.
         adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);

         // Add the AdView to the view hierarchy.
         layout.addView(mAdView);

         // Start loading the ad.
         mAdView.loadAd(adRequestBuilder.build());

         setContentView(layout);
     }

     @Override
     public void onResume() {
         super.onResume();

         // Resume the AdView.
         mAdView.resume();
     }

     @Override
     public void onPause() {
         // Pause the AdView.
         mAdView.pause();

         super.onPause();
     }

     @Override
     public void onDestroy() {
         // Destroy the AdView.
         mAdView.destroy();

         super.onDestroy();
     }
 }