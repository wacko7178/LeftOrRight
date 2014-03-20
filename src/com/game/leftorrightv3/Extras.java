package com.game.leftorrightv3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class Extras extends Activity {

	private int[] galleryItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_extras);
		galleryItems = savedInstanceState.getIntArray("galleryItems");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.extras, menu);
		return true;
	}

	public void logbookClicked(View view){
		
	}
	
	public void galleryClicked(View view){
		setContentView(R.layout.gallery_layout);
		fillGalleryText();
	}
	
	public void fillGalleryText(){
		String text = "";
		
		for (int i = 0; i < galleryItems.length; i++){
			if(galleryItems[i] == 0){
				text += "?\n";
			}
			else{
				text += StartMenu.items[i] + " x " + galleryItems[i];
			}
		}
	}
	
	
}
