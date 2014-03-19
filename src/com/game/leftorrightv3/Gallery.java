package com.game.leftorrightv3;

public class Gallery {

	private final int [] myGallery = new int[StartMenu.numberOfItems]; 
	
	public Gallery(){
		
	}
	
	public void updateGallery(int[] itemsFound){
		for(int i = 0; i < myGallery.length; i++){
			myGallery[i] = itemsFound[i];
		}
	}
	
}
