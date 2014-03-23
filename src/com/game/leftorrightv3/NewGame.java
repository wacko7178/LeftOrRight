package com.game.leftorrightv3;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class NewGame extends Activity {

	GameState game;
	Button left;
	Button right;
	Button gamePic;
	Scene currentScene;
	int currentChoice;
	int currentNeeded;
	int[] itemsFound;
	ArrayList<String> logsFound;
	boolean alive;
	boolean exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Remove title bar
	    this.requestWindowFeature(Window.FEATURE_NO_TITLE);

	    //Remove notification bar
	    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_new_game);
		game = new GameState(this);
		left = (Button)findViewById(R.id.left);
		right = (Button)findViewById(R.id.right);
		gamePic = (Button)findViewById(R.id.gamePic);
		currentChoice = 0;
		currentNeeded = 0;
		itemsFound = new int[StartMenu.numberOfItems];
		logsFound = new ArrayList<String>();
		alive = true;
		exit = false;

		Scene startScene = game.pickScene(0);
		currentScene = startScene;
		choices c = startScene.choice.get(0);

		left.setText(c.choice1);
		right.setText(c.choice2);
		gamePic.setText(startScene.sceneDescription);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_game, menu);
		return true;
	}
	
	@Override
	public void onBackPressed(){
		new AlertDialog.Builder(this)
	    .setTitle("Exit game")
	    .setMessage("Are you sure you want to exit the game?\nAll progress will be lost!")
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
	        	exit = true;
	        	endGame(null);
	        }
	     })
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	     .show();
	}

	public void choiceMade(View view){
		String response = "";
		choices c = currentScene.choice.get(currentChoice);
		int toReceive = 0;
		if(view.getId() == R.id.left){
			response = c.choiceResult1;
			alive = c.alive1;
			if(c.itemUsed1 == 0){
				game.items[currentScene.itemsNeeded.get(currentNeeded).itemIndex]--;
			}
			if(c.itemResp1 == 0){ //This does not work for the dinner table!...yet
				if(c.itemIndex1 == currentScene.itemsRecieved.size()){
					Random r = new Random();
					int rand = r.nextInt(100)%c.itemIndex1;
					toReceive = currentScene.itemsRecieved.get(rand);
				}
				else{
					toReceive = currentScene.itemsRecieved.get(c.itemIndex1);
				}
				int count = 0;
				if(toReceive == 99){
					Random r = new Random();
					
					toReceive = r.nextInt(StartMenu.numberOfItems);
					
					while(toReceive == 13 || toReceive == 14 || game.items[toReceive] == 1 || count < 16){
						toReceive = r.nextInt(StartMenu.numberOfItems);
						count++;
					}
				}
				if(count == 16){
					game.items[0]++;
					itemsFound[0]++;
					toReceive = 0;
				}
				else{
					game.items[toReceive]++;
					itemsFound[toReceive]++;
				}
				response += "\nYou got a " + StartMenu.items[toReceive] + "!!";
			}
			
			
			
			String newLog = currentScene.logName + "." + c.logEntry1 + "." + c.logResponse1;
			logsFound.add(newLog);
		} else{
			response = c.choiceResult2;
			alive = c.alive2;
			if(c.itemUsed2 == 0){
				game.items[currentScene.itemsNeeded.get(currentNeeded).itemIndex]--;
			}
			if(c.itemResp2 == 0){ //This does not work for the dinner table!...yet
				if(c.itemIndex2 == currentScene.itemsRecieved.size()){
					Random r = new Random();
					int rand = r.nextInt(100)%c.itemIndex2;
					toReceive = currentScene.itemsRecieved.get(rand);
				}
				else{
					toReceive = currentScene.itemsRecieved.get(c.itemIndex2);
				}
				
				int count = 0;
				if(toReceive == 99){
					Random r = new Random();
					
					toReceive = r.nextInt(StartMenu.numberOfItems);
					
					while(toReceive == 13 || toReceive == 14 || game.items[toReceive] == 1 || count < 16){
						toReceive = r.nextInt(StartMenu.numberOfItems);
						count++;
					}
				}
				if(count == 16){
					game.items[0]++;
					itemsFound[0]++;
					toReceive = 0;
				}
				else{
					game.items[toReceive]++;
					itemsFound[toReceive]++;
				}	
				response += "\nYou got a " + StartMenu.items[toReceive] + "!!";
			}
			
			
			String newLog = currentScene.logName + "." + c.logEntry2 + "." + c.logResponse2;
			logsFound.add(newLog);
		} 
		if(alive){
			setContentView(R.layout.after_choice);
			Button whatHappened = (Button)findViewById(R.id.whatHappened);
			whatHappened.setText(response);
		} else{
			setContentView(R.layout.game_over);
		}
	}
	
	public void getNextScene(View view){
		Scene nextScene = game.pickScene();
		if(nextScene != null){
			
			choices c = nextScene.choice.get(0);
			currentChoice = 0;
			currentNeeded = 0;
			for(int i = 0; i < nextScene.itemsNeeded.size(); i++){
				tuple currTuple = nextScene.itemsNeeded.get(i);
				if(currTuple.itemIndex >= 0){
					if(game.items[currTuple.itemIndex] >= 1){
						c = nextScene.choice.get(currTuple.choiceIndex);
						currentChoice = currTuple.choiceIndex;
						currentNeeded = i;
						break;
					}
				}
				else{
					c = nextScene.choice.get(0);
				}
				
			}
			
			currentScene = nextScene;
			setContentView(R.layout.activity_new_game);
			left = (Button)findViewById(R.id.left);
			right = (Button)findViewById(R.id.right);
			gamePic = (Button)findViewById(R.id.gamePic);
			left.setText(c.choice1);
			right.setText(c.choice2);
			gamePic.setText(nextScene.sceneDescription);
			
		}
		else{
			setContentView(R.layout.vinning);
		}
	}

	public void endGame(View view){
		Intent i = new Intent();
		i.putExtra("itemsFound", itemsFound);
		i.putExtra("logsFound", logsFound);
		if(exit){
			setResult(2, i);
		}
		else if(alive){
			setResult(1, i);
		}
		else{
			setResult(0, i);
		}
		finish();
	}

}
