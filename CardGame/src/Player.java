
/*********************************************************************
 * 
 * @author:	 Charles Karstens
 * @title:	 Maxeta Submission
 * @general: Card Game: Player class
 * @date:	 March 7, 2018
 *
 * @IDE:	Eclipse Neon Build 4.6.0
 * 			20160613 - 1800
 *
 */

public class Player {
	
	private String Name = "";
	private int Score = 0;
	private Card Hand = new Card();
	
	public Player (){
		
	}
	
	public void setName (String name) {
	    this.Name = name;
	}
	
	public String getName () {
	    return Name;
	}
	
	// add to a player score
	public void setScore (int points) {
		this.Score = Score += points;
		if (this.Score < 0){	// score can not be lower than zero
			this.Score = 0;
		}
	}	
	public int getScore(){
		return Score;
	}
	
	public void setHand (Card hand) {
	    this.Hand = hand;
	}
		
	public Card getHand (){
		return Hand;
	}
	
	// check and adjust for penalty card
	public void isPenalty(){
		if (this.Hand.getFaceValue() == -1){
			this.setScore(-1);
		}
	}
	
	public boolean uniqueName(String name){
		// checks a String value against the player Name for a match
		if ( new String(this.getName()).equals(name)){
				System.err.print(" Not a unique name, please enter a different name ->> ");
				return false;
			}
		return true;
	}
	
	public static int winRound(Player[] players){
		// index placeholder for highest face value holder
		int temp = 0;		
		for (int i = 0; i < players.length; i++){
			
			if (players[i].Hand.getFaceValue() > players[temp].Hand.getFaceValue()){
				temp = i;
			}
			
			// if 2 players hold the same face value card, check against the suit value
			if (players[i].Hand.getFaceValue() == players[temp].Hand.getFaceValue()){
				if (players[i].Hand.getSuit() > players[temp].Hand.getSuit()){
					temp = i;
				}
			}
		}
		// if all comparisons yield a -1, that means all players have penalty cards
		if (players[temp].Hand.getFaceValue() == -1){
			System.out.println("  ... There are no winners this round ...");
			return -1;  // returning a value below 0 results in the calling loop getting bypassed
		}
		// otherwise, return the index of the hi-score player
		return temp;
	}
	
	public static int winGame(Player[] players){

		int firstPlace = 0;
		int secondPlace = 1;

		for (int i = 1; i < players.length; i++){
		// determine players with highest and second highest scores
			if (players[secondPlace].getScore() <= players[i].getScore()){
				secondPlace = i;
			}
			if (players[firstPlace].getScore() <= players[i].getScore()){
				secondPlace = firstPlace;
				firstPlace = i;
			}	
		}
		// to check for at least a 2 point difference between those scores	
		int x = players[firstPlace].getScore();
		int y = players[secondPlace].getScore() + 1;
		if (x <= y){
			return -1;
			}
		
		// the winner must have at lest 21 points
		if (players[firstPlace].Score < 21){
			return -1;		// returning a value below 0 results in the calling loop getting bypassed
		}
		// returns the index of the winner
		return firstPlace;
	}
	   
}
