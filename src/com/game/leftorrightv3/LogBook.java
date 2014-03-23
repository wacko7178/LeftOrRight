package com.game.leftorrightv3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.content.Context;

public class LogBook {


	private String logbookFile = "logbookFile";
	private ArrayList<String> myLogs;
	private int[] sceneChoiceNum;
	private final int numberOfSceneChoices = 92; //Through cop scene
	private Context context;
	
	public LogBook(Context context, ArrayList<String> logEntries){
		this.context = context;
		myLogs = new ArrayList<String>();
		myLogs.addAll(logEntries);
		sceneChoiceNum = new int[numberOfSceneChoices];
	}
	
	public void readFile(){
		BufferedReader in = null;
		try{
			in = new BufferedReader(new FileReader(new File(context.getFilesDir() + File.separator + logbookFile)));
			for(int i = 0; i < numberOfSceneChoices; i++){
				//Log.d("File stuff: ", in.readLine());
				sceneChoiceNum[i] = Integer.parseInt(in.readLine());//Integer.parseInt(in.readLine());
			}
		}catch(IOException e){			
		}catch(NumberFormatException e){

		}finally{
			try{
				if(in != null){
					in.close();
				}
			}catch(IOException e){

			}
		}
		
	}
	
	public void writeFile(){
		BufferedWriter bufferedWriter = null;
		try{
			bufferedWriter = new BufferedWriter(new FileWriter(new File(context.getFilesDir() + File.separator + logbookFile)));
			for(int i = 0; i < numberOfSceneChoices; i++){
				bufferedWriter.write(String.valueOf(sceneChoiceNum[i])); 
				bufferedWriter.newLine();
			}
		}catch(IOException e){
			
		}finally{
			try{
				if(bufferedWriter != null)
					bufferedWriter.close();
			}catch(IOException e){
				
			}
		}
	}
	
	public void updateMyLogs(ArrayList<String> newLogs){
		for(int i = 0; i < newLogs.size(); i++){
			int indexOfLog = myLogs.indexOf(newLogs.get(i));
			sceneChoiceNum[indexOfLog]++;
		}
		
	}
	
	public ArrayList<String> getMyLogs(){
		return myLogs;
	}
	
	public int[] getSceneChoiceNum(){
		return sceneChoiceNum;
	}
}
