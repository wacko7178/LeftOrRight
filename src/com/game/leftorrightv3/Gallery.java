package com.game.leftorrightv3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

public class Gallery {

	private final int [] myGallery = new int[StartMenu.numberOfItems]; 
	private Context context;
	private AssetManager assetManager;
	private String externalStoragePath;
	private String galleryFile = ".galleryFile";
	
	public Gallery(Context context){
		this.context = context;
		this.assetManager = context.getAssets();
		this.externalStoragePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
	}

	public void readFile(){
		BufferedReader in = null;
		try{
			in = new BufferedReader(new InputStreamReader(new FileInputStream(externalStoragePath + galleryFile)));
			for(int i = 0; i < StartMenu.numberOfItems; i++){
				myGallery[i] = Integer.parseInt(in.readLine());
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
	
	public void updateGallery(int[] itemsFound){
		for(int i = 0; i < myGallery.length; i++){
			myGallery[i] = itemsFound[i];
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
			
		}finally{
			try{
				if(out != null)
					out.close();
			}catch(IOException e){
				
			}
		}
	}
}
