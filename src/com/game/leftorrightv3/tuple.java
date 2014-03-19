package com.game.leftorrightv3;

public class tuple {
	int itemIndex; 
	int choiceIndex;
	
	public tuple(int itemIndex, int choiceIndex){
		this.itemIndex = itemIndex;
		this.choiceIndex = choiceIndex;
	}
	
	public int getItemIndex(){
		return itemIndex; 
	}
	
	public int getChoiceIndex(){
		return choiceIndex;
	}
}
