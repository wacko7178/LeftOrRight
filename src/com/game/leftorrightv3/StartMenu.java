package com.game.leftorrightv3;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class StartMenu extends Activity {

	public static final int numberOfItems = 16;
	public static final String[] items = {"Key", "Baby bottle", "Dog", "T-Rex", "Liquid ice", "Silver bat", "Shrinkage", "Sushi roll",
		"Buffalo sauce", "Doughnut", "Ferret", "Do Not Remove tag", "Alien ray gun", "Fire dino", "Water Dino", "Tennis ball"};
	public Gallery gallery;
	public LogBook logBook;
	private GameState staticGame;
	
	//private AdView mAdView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    //Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		staticGame = new GameState(this);
		
		gallery = new Gallery(this);
		gallery.readFile();
		
		logBook = new LogBook(this, staticGame.buildLogbook());
		logBook.readFile();
		

		
//		 LinearLayout layout = new LinearLayout(this);
//         layout.setOrientation(LinearLayout.VERTICAL);
//
//         // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
//         mAdView = new AdView(this);
//         mAdView.setAdSize(AdSize.SMART_BANNER);
//         mAdView.setAdUnitId("myAdUnitId");
//
//         // Create an ad request.
//         AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
//
//         // Optionally populate the ad request builder.
//         adRequestBuilder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
//
//         // Add the AdView to the view hierarchy.
//         layout.addView(mAdView);
//
//         // Start loading the ad.
//         mAdView.loadAd(adRequestBuilder.build());

 //        setContentView(layout);
		
		
		setContentView(R.layout.activity_start_menu);
		
	    AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start_menu, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == 0){
			//lose
		} else if(resultCode == 1){
			//win
		}
		if(data != null){
			int [] itemsFound = data.getExtras().getIntArray("itemsFound");
			ArrayList<String> logsFound = data.getExtras().getStringArrayList("logsFound");
			gallery.updateGallery(itemsFound);
			logBook.updateMyLogs(logsFound);
			gallery.writeFile();
			logBook.writeFile();
		}
	}
	
	public void startClicked(View view){
		//StartGame
		Intent startGame = new Intent(this, NewGame.class);
		startActivityForResult(startGame, 0);
	}
	
	public void settingsClicked(View view){
		//StartSettings
		//NOT SETTINGS
		gallery.writeFile();
		logBook.writeFile();
	}
	
	public void extrasClicked(View view){
		//StartExtras
		Intent viewExtras = new Intent(this, Extras.class);
		viewExtras.putExtra("galleryItems", gallery.getMyGallery());
		viewExtras.putExtra("logItems", logBook.getMyLogs());
		viewExtras.putExtra("logValues", logBook.getSceneChoiceNum());
		startActivity(viewExtras);
	}
	
}
