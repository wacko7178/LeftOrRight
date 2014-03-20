package com.game.leftorrightv3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class StartMenu extends Activity {

	public static final int numberOfItems = 16;
	public static final String[] items = {"Key", "Baby bottle", "Dog", "T-Rex", "Liquid ice", "Silver bat", "Shrinkage", "Sushi roll",
		"Buffalo sauce", "Doughnut", "Ferret", "Do Not Remove tag", "Alien ray gun", "Fire dino", "Water Dino", "Tennis ball"};
	public Gallery gallery;
	public LogBook logBook;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		gallery = new Gallery(this);
		gallery.readFile();
		
		logBook = new LogBook();
		logBook.readFile();
		
		setContentView(R.layout.activity_start_menu);
		
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
		int [] itemsFound = data.getExtras().getIntArray("itemsFound");
		gallery.updateGallery(itemsFound);
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
	}
	
	public void extrasClicked(View view){
		//StartExtras
		Intent viewExtras = new Intent(this, Extras.class);
		viewExtras.putExtra("galleryItems", gallery.getMyGallery());
		startActivity(viewExtras);
	}
	
}
