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
	
	private String galleryFile = "galleryFile";
	private Context context;
		
	public Gallery(Context context){
		this.context = context;
	}
	
	public void readFile(){
		
		BufferedReader in = null;
		String read;
		StringBuilder builder = new StringBuilder("");
		try{
			in = new BufferedReader(new FileReader(new File(context.getFilesDir() + File.separator + galleryFile)));
			for(int i = 0; i < StartMenu.numberOfItems; i++){
				//Log.d("File stuff: ", in.readLine());
				myGallery[i] = Integer.parseInt(in.readLine());//Integer.parseInt(in.readLine());
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
			bufferedWriter = new BufferedWriter(new FileWriter(new File(context.getFilesDir() + File.separator + galleryFile)));
			for(int i = 0; i < StartMenu.numberOfItems; i++){
				bufferedWriter.write(String.valueOf(myGallery[i])); 
				bufferedWriter.newLine();
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
