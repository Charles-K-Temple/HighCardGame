
/*********************************************************************
 * 
 * @author:	 Charles Karstens
 * @title:	 Maxeta Submission
 * @general: Card Game: Card class
 * @date:	 March 7, 2018
 *
 * @IDE:	Eclipse Neon Build 4.6.0
 * 			20160613 - 1800
 *
 */

public class Card {
	
	private int FaceValue;
	private int Suit;
	
	public Card (int value, int suit){
		this.FaceValue = value;
		this.Suit = suit;
	}
	
	public Card (){
		
	}
	
	public void setFaceValue(int faceValue){
		this.FaceValue = faceValue;
	}
	
	public int getFaceValue(){
		return this.FaceValue;
	}
	
	public void setSuit(int suit){
		this.Suit = suit;
	}
	
	public int getSuit(){
		return this.Suit;
	}
	
	public String ToString() {	// formatting what cards look like printed on screen
		
		String cardString = null;
		if (this.FaceValue < 0){
			return "[ Penalty Card ]";
		}
		
		switch (this.FaceValue){
			
			case 2:
				cardString = "[ 2 ";
				break;
				
			case 3:
				cardString = "[ 3 ";
				break;
				
			case 4:
				cardString = "[ 4 ";
				break;
				
			case 5:
				cardString = "[ 5 ";
				break;
				
			case 6:
				cardString = "[ 6 ";
				break;
				
			case 7:
				cardString = "[ 7 ";
				break;
				
			case 8:
				cardString = "[ 8 ";
				break;
				
			case 9:
				cardString = "[ 9 ";
				break;
				
			case 10:
				cardString = "[ 10 ";
				break;
				
			case 11:
				cardString = "[ Jack ";
				break;
			
			case 12:
				cardString = "[ Queen ";
				break;
			
			case 13:
				cardString = "[ King ";
				break;
			
			case 14:
				cardString = "[ Ace ";
				break;			
		}
				
		switch (this.Suit){
			case 0:
				cardString += " of Clubs ]";
				break;
		
			case 1:
				cardString += " of Diamonds ]";
				break;
			
			case 2:
				cardString += " of Hearts ]";
				break;
			
			case 3:
				cardString += " of Spades ]";
				break;
			}
		
		return cardString;	
	}
}