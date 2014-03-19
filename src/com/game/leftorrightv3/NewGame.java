package com.game.leftorrightv3;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
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
	boolean alive;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_game);
		game = new GameState(this);
		left = (Button)findViewById(R.id.left);
		right = (Button)findViewById(R.id.right);
		gamePic = (Button)findViewById(R.id.gamePic);
		currentChoice = 0;
		currentNeeded = 0;
		itemsFound = new int[StartMenu.numberOfItems];
		alive = true;

		Scene startScene = game.pickScene(0);
		currentScene = startScene;
		choices c = startScene.choice.get(0);

		left.setText(c.string1);
		right.setText(c.string2);
		gamePic.setText(startScene.sceneDescription);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_game, menu);
		return true;
	}

	public void choiceMade(View view){
		String response = "";
		choices c = currentScene.choice.get(currentChoice);
		if(view.getId() == R.id.left){
			response = c.response1;
			alive = c.alive1;
			if(c.itemUsed1 == 0){
				game.items[currentScene.itemsNeeded.get(currentNeeded).itemIndex]--;
			}
			if(c.itemResp1 == 0){ //This does not work for the dinner table!...yet
				int toReceive = currentScene.itemsRecieved.get(0);
				int count = 0;
				if(currentScene.itemsRecieved.get(0) == 99){
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
				}
				else{
					game.items[toReceive]++;
					itemsFound[0]++;
				}
					
			}
			 
		} else{
			response = c.response2;
			alive = c.alive2;
			if(c.itemUsed2 == 0){
				game.items[currentScene.itemsNeeded.get(currentNeeded).itemIndex]--;
			}
			if(c.itemResp2 == 0){ //This does not work for the dinner table!...yet
				int toReceive = currentScene.itemsRecieved.get(0);
				int count = 0;
				if(currentScene.itemsRecieved.get(0) == 99){
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
				}
				else{
					game.items[toReceive]++;
					itemsFound[toReceive]++;
				}				
			}
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
			left.setText(c.string1);
			right.setText(c.string2);
			gamePic.setText(nextScene.sceneDescription);
			
		}
		else{
			setContentView(R.layout.vinning);
		}
	}

	public void endGame(View view){
		Intent i = new Intent();
		i.putExtra("itemsFound", itemsFound);
		if(alive){
			setResult(1, i);
		}
		else{
			setResult(0, i);
		}
		finish();
	}

}
