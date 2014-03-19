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

public class LogBook {


	private String externalStoragePath;
	private String logbookFile = ".logbookFile";
	
	public LogBook(){
		this.externalStoragePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
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
}
