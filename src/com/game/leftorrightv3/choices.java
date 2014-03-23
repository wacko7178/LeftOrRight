package com.game.leftorrightv3;
	public class choices{
		static final String space = "[ ]+";
		String logEntry1;
		String logEntry2;
		String choice1;
		String choice2;
		String choiceResult1;
		String choiceResult2;
		String logResponse1;
		String logResponse2;
		int itemResp1;
		int itemResp2;
		int itemUsed1;
		int itemUsed2;
		int itemIndex1;
		int itemIndex2;
		boolean alive1;
		boolean alive2;
		
		public choices(String left, String logLeft, String right, String logRight){
			choice1 = left;
			choice2 = right;
			logEntry1 = logLeft;
			logEntry2 = logRight;
		}
		
	
		
		public void setResponse1(String res1, String choiceResult, String info){
			logResponse1 = res1;
			choiceResult1 = choiceResult;
			String[] ints = info.split(space);
			itemUsed1 = Integer.parseInt(ints[2]);
			this.itemResp1 = Integer.parseInt(ints[0]);
			int stillAlive = Integer.parseInt(ints[1]);
			itemIndex1 = Integer.parseInt(ints[3]);
			if(stillAlive == 0){
				alive1 = true;
			} else{
				alive1 = false;
			}
		}
		
		
		public void setResponse2(String res2, String choiceResult, String info){
			logResponse2 = res2;
			choiceResult2 = choiceResult;
			String[] ints = info.split(space);
			itemUsed2 = Integer.parseInt(ints[2]);
			this.itemResp2 = Integer.parseInt(ints[0]);
			int stillAlive = Integer.parseInt(ints[1]);
			itemIndex2 = Integer.parseInt(ints[3]);
			if(stillAlive == 0){
				alive2 = true;
			} else{
				alive2 = false;
			}
		}
		
		public String toString(){
			return choice1 + " " + choice2 + "." ;
		}
	}