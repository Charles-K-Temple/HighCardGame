import java.io.IOException;
import java.util.*;

/*********************************************************************
 * 
 * @author:	 Charles Karstens
 * @title:	 Maxeta Submission
 * @general: Card Game: Main
 * @date:	 March 7, 2018
 * 
 * @IDE:	Eclipse Neon Build 4.6.0
 * 			20160613 - 1800
 *
 */

public class CharlesKarstens_CardGame {

	public static void main(String[] args) {
		
		System.out.println(" Welcome to the Maxeta Card Game...");
		Scanner reader = new Scanner(System.in); 
		String numberPlayers = null;
		
		// get number of players from input
		boolean numberCheck = false;
		do {
			System.out.print("Please Enter the number of players [2 through 4]: ");
			numberPlayers = reader.nextLine(); 
			numberCheck = checkNumber(numberPlayers);  // see CheckNumber class for integer validation below
		} while (!numberCheck);	

		int n = Integer.parseInt(numberPlayers);
		Player[] players = new Player[n];	// create Player array of players for game
		Deck deck = new Deck();				// instantiate a deck object of cards
		deck.shuffleDeck();					// shuffle the Deck of Card objects
		
		for ( int i = 0; i < n; i++ ){
			players[i] = new Player();
			
			String inputName = "";
			boolean checkName = false;
			do{						// get players names
				System.out.print("Enter name of Player " + (i+1) + " >> ");
				inputName = reader.nextLine();
				checkName = checkInput(inputName, players);  // see EmptyInput class for input/name validation below
				
			} while (!checkName);	// continue if validation doesn't check out
			players[i].setName(inputName);		
		}
		
		int winner = -1;	// game winner index
		int c = 0;			// card counter
		do {				// game loop that plays until someone wins
			for (int i = 0; i < n; i++){
				
				String nextPlayer = String.format("\n  Player %d : %12s  Press [ENTER] to draw a card   ->>", i+1, players[i].getName());
				System.out.print(nextPlayer);	

				hitEnterToProceed(); // comment out this line and game will play itself to completion

				players[i].setHand(deck.deal(c));	// deal card to player
				nextPlayer = String.format("  Player %d (%s) draws %25s", i+1, players[i].getName(), players[i].getHand().ToString());
				System.out.println(nextPlayer);
				
				players[i].isPenalty();		// checks for and handles a penalty card
				c++;						// increase card counter by 1 for each card dealt
			}
						
			int win = Player.winRound(players);	// check the players array for a round winner			
			if (win >= 0){						// returning the index of the winner in the array
				players[win].setScore(2);		// and adds 2 points to that player's score
				String winRound = String.format("\n **  Player  %d ->>  %s <<- WINS this Round!  **", win+1, players[win].getName());
				System.out.println(winRound);
			}
			
			for (Player player : players ){		// print out the players hands for the round
				String thisRound = String.format("%15s:\t %25s Score:  %2d",player.getName(), player.getHand().ToString(), player.getScore());
				System.out.println(thisRound);
			}		
			
			winner = Player.winGame(players);	// check the players array for a game winner		
			if (winner >= 0){					// returning the index of the winner in the players array
				String gameWinner = String.format(" ***  Player %d ->> %s <<-  is the WINNER!  ***", winner+1, players[winner].getName());
				System.out.println(gameWinner);
				System.exit(0);
			}
				
			System.out.println();
			
			int cardsLeft = deck.getLength() - c;	// using the card counter check against the number of cards
			if (cardsLeft < n){						// left in the deck vs the number of players
				deck.shuffleDeck();					// tells when to reshuffle the deck and  
				c = 0;								// start over at the top 
				System.out.println("  -- Reshuffling the deck --");
			}				
			System.out.println(" ...  Next Round  ...");
				
		} while (winner < 0);	// end of game loop 
		
		// once finished
		reader.close();
		System.exit(0);
	}
	
	// validation for an integer value from 2 thru 4 for number of players
	  public static boolean checkNumber(String str){
		  boolean proceed = false;
		    try {
		      Integer.parseInt(str);
		      proceed = true;
		    }
		      catch (Exception e) {
		        System.err.print(" << Sorry, that is not a number or integer value >>\n");
		      proceed = false;
		    }
		    if (proceed == true){
		    	int x = Integer.parseInt(str);
		    	if ((x < 2) || (x > 4)){
			        System.err.print(" << Sorry, that number is not between 2 and 4 >>\n");
			        proceed = false;
		    	}
		    }
		    return proceed;
	}
	  
	  // check that name input is not empty or blank
	  public static boolean checkInput(String s, Player[] players) {

		  if (s == null || s.trim().isEmpty()){
			  System.err.print("  Name can not be blank, please try again ->>\n");
			  return false;
		  }
		  for (Player player : players){		
			  if (player != null){						// disregard null players
				  if (player.uniqueName(s) == false){	// check each player name against input string
					  return false;						// returns false if name is not unique
				  }
			  }
		  }		  
		  return true;
		}

	  public static void hitEnterToProceed(){
		  Scanner reader = new Scanner(System.in); 
		  reader.nextLine();
		  return;
	  }
}
