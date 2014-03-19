package com.game.leftorrightv3;
	public class choices{
		String string1;
		String string2;
		String response1;
		String response2;
		int itemResp1;
		int itemResp2;
		int itemUsed1;
		int itemUsed2;
		boolean alive1;
		boolean alive2;
		
		public choices(String left, String right){
			string1 = left;
			string2 = right;			
		}
		
		public void setResponse1(String res1, int itemResp, int stillAlive, int itemUsed){
			response1 = res1;
			itemUsed1 = itemUsed;
			this.itemResp1 = itemResp;
			if(stillAlive == 0){
				alive1 = true;
			} else{
				alive1 = false;
			}
		}
		
		public void setResponse2(String res2, int itemResp, int stillAlive, int itemUsed){
			response2 = res2;
			itemUsed2 = itemUsed;
			this.itemResp2 = itemResp;
			if(stillAlive == 0){
				alive2 = true;
			} else{
				alive2 = false;
			}
		}
		
		public String toString(){
			return string1 + " " + string2 + "." ;
		}
	}