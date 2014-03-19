package com.game.leftorrightv3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class StartMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	}
	
	public void startClicked(View view){
		//StartGame
		Intent startGame = new Intent(this, NewGame.class);
		startActivityForResult(startGame, 0);
	}
	
	public void settingsClicked(View view){
		//StartSettings
		
	}
	
	public void extrasClicked(View view){
		//StartExtras
	}
	
}