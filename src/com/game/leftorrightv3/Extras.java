package com.game.leftorrightv3;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Extras extends Activity {

	private int[] galleryItems;
	private ArrayList<String> logItems;
	private int[] logValues;
	static final String space = "[ ]+";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    //Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		
		galleryItems = this.getIntent().getIntArrayExtra("galleryItems");
		logItems = this.getIntent().getStringArrayListExtra("logItems");
		logValues = this.getIntent().getIntArrayExtra("logValues");
		setContentView(R.layout.activity_extras);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.extras, menu);
		return true;
	}
	
	@Override
	public void onBackPressed(){
		try{
			Button test = (Button)findViewById(R.id.viewLogbook);
			test.setText("Logbook");
			super.onBackPressed();
		}
		catch(Exception e){
			//
			setContentView(R.layout.activity_extras);
		}
		
	}

	public void logbookClicked(View view){
		Intent i = new Intent(this, GalleryList.class);
		i.putExtra("galOrLog", 1);
		i.putExtra("logItems", logItems);
		i.putExtra("logValues", logValues);
		startActivity(i);
		
	}
	
	public void fillLogText(){
//		String text = "";
//		
//		for (int i = 0; i < logItems.size(); i++){
//			String newLog = logItems.get(i);
//			String[] logArr = newLog.split("\\.");
//			if(logValues[i] == 0){
//				text += "???????\n";
//			}
//			else{
//				text += logArr[0] + " + " + logArr[1] + " = ";
//				if(logArr[2].equals("true")){
//					text += "Alive! 8D";
//				}
//				else{
//					text += "Dead...X(";
//				}
//				text += " x " + logValues[i] + "\n";
//			}
//			
//		}
//		
//		TextView textView = (TextView)findViewById(R.id.itemsText);
//		textView.setText(text);
	}
	
	public void galleryClicked(View view){
//		setContentView(R.layout.gallery_layout);
//		fillGalleryText();
		Intent i = new Intent(this, GalleryList.class);
		i.putExtra("galOrLog", 0);
		i.putExtra("itemsFound", galleryItems);
		startActivity(i);
	}
	
//	public void fillGalleryText(){
//		String text = "";
//		
//		for (int i = 0; i < galleryItems.length; i++){
//			if(galleryItems[i] == 0){
//				text += "?\n";
//			}
//			else{
//				text += StartMenu.items[i] + " x " + galleryItems[i] + "\n";
//			}
//		}
//		
//		TextView textView = (TextView)findViewById(R.id.itemsText);
//		textView.setText(text);
//	}
	
	
}
