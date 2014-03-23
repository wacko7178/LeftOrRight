package com.game.leftorrightv3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;




public class GameState {
	ArrayList<Scene> scenarios;
	File scenarioList;
	File sI;
	Scanner sf;
	int[] items;

	public GameState(Context context){
		
		try {
			
//			AssetManager am = context.getAssets();
//			InputStream inputStream = am.open("assets/scenario_list.txt");
//			File file = createFileFromInputStream(inputStream);
//
//			final File createFileFromInputStream(InputStream inputStream);
//			
//			File f = new File(getCacheDir()+ " ");
			
			AssetManager am1 = context.getAssets();
			InputStream sL = am1.open("scenario_list");
			Scanner sf = new Scanner(new InputStreamReader(sL));
			
			scenarios =  new ArrayList<Scene>();
			//scenarioList = new File("assets/scenario_list.txt");
			//sI = new File("assets/scenario_items.txt");
			//sf = new Scanner(scenarioList);
			
			
			
			String currName = sf.nextLine();
			String currDescr = "";
			String currLog = "";
			String nextLog = "";
			while(sf.hasNext()){
				currLog = sf.nextLine();
				currDescr = sf.nextLine();
				nextLog = sf.nextLine();
				ArrayList<choices> choiceList = new ArrayList<choices>();
				while(!nextLog.startsWith("*") && sf.hasNext()){
					String currOption1 = sf.nextLine();
					String nextLog2 = sf.nextLine();
					String currOption2 = sf.nextLine();
					choices currChoice = new choices(currOption1, nextLog, currOption2, nextLog2);
					choiceList.add(currChoice);
					if(sf.hasNext())
						nextLog = sf.nextLine();
					//		System.out.println(currName + " " + currOption1 + " " + currOption2 + " " + next);

				}
				Scene currScene = new Scene(currName, currLog, currDescr, choiceList, context);
				scenarios.add(currScene);
				currName = nextLog;
			}
			
			
			InputStream cR = am1.open("choice_result");
			Scanner cRs = new Scanner(new InputStreamReader(cR));
			
			
			for(int i = 0; i < scenarios.size(); i++){
				String currScene = cRs.nextLine();
				for(int j = 0; j < scenarios.get(i).choice.size(); j++){
					try{
						choices choice = scenarios.get(i).choice.get(j);
						//choice.setResponse1(cRs.nextLine(), cRs.nextInt(), cRs.nextInt(), cRs.nextInt());
						//choice.setResponse2(cRs.nextLine(), cRs.nextInt(), cRs.nextInt(), cRs.nextInt());
						choice.setResponse1(cRs.nextLine(), cRs.nextLine(), cRs.nextLine());
						choice.setResponse2(cRs.nextLine(), cRs.nextLine(), cRs.nextLine());
					} catch(Exception e){
						Log.d("index i: ", i + " ");
						Log.d("index j: ", j + " ");
						e.printStackTrace();
					}
					
				}
			}
			
			items = new int[StartMenu.numberOfItems];
			Arrays.fill(items, 0);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Scene pickScene(){
		if(scenarios.size() > 0){
			Random r = new Random();
			int sceneChoice = r.nextInt(scenarios.size());
			Scene toReturn = scenarios.remove(sceneChoice);
			return toReturn;
		}
		else{
			return null;
		}
	}
	
	public Scene pickScene(int index){
		Scene toReturn = scenarios.get(index);
		scenarios.remove(index);
		return toReturn;
	}
	
	public ArrayList<String> buildLogbook(){
		
		ArrayList<String> toReturn = new ArrayList<String>();
		
		for(int i = 0;  i < scenarios.size(); i++){
			Scene currScene = scenarios.get(i);
			for(int j = 0; j < currScene.choice.size(); j++){
				choices currChoice = currScene.choice.get(j);
				String newLog1 = currScene.logName + "." + currChoice.logEntry1 + "." + currChoice.logResponse1;
				String newLog2 = currScene.logName + "." + currChoice.logEntry2 + "." + currChoice.logResponse2;
				if(!toReturn.contains(newLog1)){
					toReturn.add(newLog1);
				}
				if(!toReturn.contains(newLog2)){
					toReturn.add(newLog2);
				}
			}
		}
	
		return toReturn;
	}
	//	public static void main(String[] args){
	//		GameState gS = new GameState();
	//		for(int i = 0; i< gS.scenarios.size(); i++){
	//			Scene curr = gS.scenarios.get(i);
	//			System.out.println(curr.name);
	//			for(int j = 0; j<curr.choice.size(); j++){
	//				System.out.print(curr.choice.get(j)+ " ");
	//			}
	//			System.out.println();
	//			for(int k = 0; k<curr.itemsNeeded.size(); k++){
	//				System.out.print(curr.itemsNeeded.get(k).getItemIndex() + ", "  + curr.itemsNeeded.get(k).getChoiceIndex() + ". ");
	//			}
	//			System.out.println();
	//		}
	//	}
}
