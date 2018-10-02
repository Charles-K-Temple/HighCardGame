import java.util.Arrays;
import java.util.Collections;

/*********************************************************************
 * 
 * @author:	 Charles Karstens
 * @title:	 High Card Game
 * @general: Card Game: Deck class
 * @date:	 March 7, 2018
 *
 * @IDE:	Eclipse Neon Build 4.6.0
 * 			20160613 - 1800
 *
 */

public class Deck {
	
	private final int LENGTH = 56;
	private Card[] cards = new Card[LENGTH];
		
	public int getLength () {
	    return LENGTH;
	}
	
	public Deck(){	//  creates the Deck of Card objects

		final int SUITS = 4;
		final int MINVAL = 2;
		final int MAXVAL = 14;
		
		for (int suit = 0; suit < SUITS; suit++){
			for (int value = MINVAL; value <= MAXVAL; value++){
				
				int n = ((suit * ((MAXVAL - MINVAL) +1)) + (value - MINVAL));
				this.cards[n] = new Card(value, suit);
			}
		}		
		// add Penalty cards to the end
		int j = (SUITS * ((MAXVAL - MINVAL) +1));
		for (int i = j; i < LENGTH; i++){
			
			this.cards[i] = new Card (-1, -1);
		}	
	}
	
	public void shuffleDeck(){
		// shuffle the Deck
		Collections.shuffle(Arrays.asList(this.cards));
	}
	
	public Card deal(int i){
		return this.cards[i];
	}
}
