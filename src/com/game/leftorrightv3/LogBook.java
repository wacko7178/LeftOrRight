package com.game.leftorrightv3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import android.os.Environment;
import android.util.Log;

public class LogBook {


	private String externalStoragePath;
	private String logbookFile = ".logbookFile";
	private ArrayList<String> myLogs;
	private int[] sceneChoiceNum;
	private final int numberOfSceneChoices = 95; //Through cop scene
	public LogBook(ArrayList<String> logEntries){
		this.externalStoragePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
		myLogs = new ArrayList<String>();
		myLogs.addAll(logEntries);
		sceneChoiceNum = new int[numberOfSceneChoices];
	}
	
	public void readFile(){
		BufferedReader in = null;
		try{
			in = new BufferedReader(new InputStreamReader(new FileInputStream(externalStoragePath + logbookFile)));
			
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
		BufferedWriter out = null;
		try{
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(externalStoragePath + logbookFile)));
			
		}catch(IOException e){
			
		}finally{
			try{
				if(out != null)
					out.close();
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
