package com.game.leftorrightv3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import android.os.Environment;
import android.util.Log;

public class Gallery {

	private final int [] myGallery = new int[StartMenu.numberOfItems]; 
	
	private String externalStoragePath;
	private String galleryFile = "galleryFile";
	private Context context;
		
	public Gallery(Context context){
		this.context = context;
		Arrays.fill(myGallery, 1);
		this.externalStoragePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
		writeFile();
		readFile();
	}
	
	public void readFile(){
		
		BufferedReader in = null;
		String read;
		StringBuilder builder = new StringBuilder("");
		try{
			in = new BufferedReader(new FileReader(new File(context.getFilesDir() + File.separator + galleryFile)));
			for(int i = 0; i < StartMenu.numberOfItems; i++){
				myGallery[i] = 0;//Integer.parseInt(in.readLine());
				Log.d("Am I here", "For realz");
			}
		}catch(IOException e){	
			e.printStackTrace();
		}catch(NumberFormatException e){

		}finally{
			try{
				if(in != null){
					in.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public void writeFile(){
		BufferedWriter bufferedWriter = null;
		try{
			bufferedWriter = new BufferedWriter(new FileWriter(new File(context.getFilesDir() + File.separator+galleryFile)));
			for(int i = 0; i < StartMenu.numberOfItems; i++){
				bufferedWriter.write(myGallery[i]); // .write(Integer.toString(myGallery[i]));
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(bufferedWriter != null)
					bufferedWriter.close();
			}catch(IOException e){
				
			}
		}
	}
	
	public void updateGallery(int[] itemsFound){
		for(int i = 0; i < myGallery.length; i++){
			myGallery[i] += itemsFound[i];
		}
	}
	
	public int[] getMyGallery(){
		return myGallery;
	}
	
}
