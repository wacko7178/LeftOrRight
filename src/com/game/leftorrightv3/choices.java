package com.game.leftorrightv3;
	public class choices{
		static final String space = "[ ]+";
		String string1;
		String string2;
		String response1;
		String response2;
		int itemResp1;
		int itemResp2;
		int itemUsed1;
		int itemUsed2;
		int itemIndex1;
		int itemIndex2;
		boolean alive1;
		boolean alive2;
		
		public choices(String left, String right){
			string1 = left;
			string2 = right;			
		}
		
		public void setResponse1(String res1, int itemResp, int stillAlive, int itemUsed, int itemIndex){
			response1 = res1;
			itemUsed1 = itemUsed;
			this.itemResp1 = itemResp;
			if(stillAlive == 0){
				alive1 = true;
			} else{
				alive1 = false;
			}
		}
		
		public void setResponse1(String res1, String info){
			response1 = res1;
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
		
		public void setResponse2(String res2, int itemResp, int stillAlive, int itemUsed, int itemIndex){
			response2 = res2;
			itemUsed2 = itemUsed;
			this.itemResp2 = itemResp;
			if(stillAlive == 0){
				alive2 = true;
			} else{
				alive2 = false;
			}
		}
		
		public void setResponse2(String res2, String info){
			response2 = res2;
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
			return string1 + " " + string2 + "." ;
		}
	}