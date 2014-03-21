package com.game.leftorrightv3;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import android.content.Context;
import android.content.res.AssetManager;


public class Scene {
	String name;
	String sceneDescription;
	ArrayList<choices> choice;
	ArrayList<tuple> itemsNeeded;
	ArrayList<Integer> itemsRecieved;
	
	public Scene(String sceneName, String sceneDescription, ArrayList<choices> choices, Context context) throws IOException{
		
		choice = new ArrayList<choices>();
		itemsNeeded = new ArrayList<tuple>();
		itemsRecieved = new  ArrayList<Integer>();
		
		name = sceneName;
		this.sceneDescription = sceneDescription;
		this.choice = choices;
		
		AssetManager am1 = context.getAssets();
		InputStream sI = am1.open("scenario_items");
		Scanner sF = new Scanner(new InputStreamReader(sI));
		
		while(sF.hasNext()){
			String currName = sF.next();
			if(currName.startsWith(name)){
				itemsRecieved.add(Integer.parseInt(sF.next()));					
				String currItem = "";
				
				while(!currItem.equals("^")){
					currItem = sF.next();
					if(!currItem.equals("^")){
						itemsRecieved.add(Integer.parseInt(currItem));
					}
				}

				currItem = sF.next();
				while(!currItem.startsWith("*") && sF.hasNext()){
					itemsNeeded.add(new tuple(Integer.parseInt(currItem), Integer.parseInt(sF.next())));
					if(sF.hasNext()){
						currItem = sF.next();
					}
				}

			}

		}
		sF.close();
		
	}
	
	public ArrayList<tuple> getItemsNeeded(){
		return itemsNeeded;
	}
	
//	public static void main(String[] args){
//		choices choice1 = new choices("x", "y");
//		choices choice2 = new choices("a", "b");
//		ArrayList<choices> cs = new ArrayList<choices>();
//		cs.add(choice1);
//		cs.add(choice2);
//		File sI = new File("scenario_items");
//		Scene bC = new Scene("*battingCages", cs, sI);
//		for(int i = 0; i<bC.itemsNeeded.size(); i++){
//			System.out.println(bC.itemsNeeded.get(i));
//		}
//		
//	}
}
