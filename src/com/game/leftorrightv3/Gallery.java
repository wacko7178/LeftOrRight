package com.game.leftorrightv3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

import android.os.Environment;
import android.util.Log;

public class Gallery {

	private final int [] myGallery = new int[StartMenu.numberOfItems]; 
	
	private String externalStoragePath;
	private String galleryFile = ".galleryFile";
	
	public Gallery(){
		
		Arrays.fill(myGallery, 1);
		this.externalStoragePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
		writeFile();
		readFile();
	}

	public void readFile(){
		
		BufferedReader in = null;
		try{
			Log.d("Here", "I made it!");
			in = new BufferedReader(new InputStreamReader(new FileInputStream(externalStoragePath + galleryFile)));
			Log.d("Doubtful", "yea");
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
		BufferedWriter out = null;
		try{
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(externalStoragePath + galleryFile)));
			for(int i = 0; i < StartMenu.numberOfItems; i++){
				out.write(Integer.toString(myGallery[i]));
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(out != null)
					out.close();
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
