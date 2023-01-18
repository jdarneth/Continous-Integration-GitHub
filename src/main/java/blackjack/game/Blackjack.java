package blackjack.game;

import java.io.InvalidObjectException;
import java.util.Random;

import blackjack.player.Dealer;
import blackjack.player.HumanPlayer;
import blackjack.player.Player;
import inout.In;
import inout.Out;

public class Blackjack {
	public static final int TOTAL_NUM_CARDS = 52;

	private HumanPlayer human;
	private Dealer dealer;	

	private int gameStake; 
	
	public Blackjack () {
		dealer = new Dealer(); 
		human = new HumanPlayer();
	}
	
	public Player getHumanPlayer() {
		return human;
	}

	public Player getDealer() {
		return dealer;
	}
	
	/**
	 * draw a random card that is still in the deck
	 * 
	 * @throws OutOfCardsException when no more cards are available
	 * 
	 * @return the selected card
	 */
	public Card drawCard(Player forPlayer) {
		if ((dealer.getCards().size() + human.getCards().size()) >= TOTAL_NUM_CARDS) {
			throw new OutOfCardsException();
		}

		Card card = new Card(new Random().nextInt(TOTAL_NUM_CARDS));
		while (dealer.getCards().contains(card) || human.getCards().contains(card)) {
			card = new Card(new Random().nextInt(TOTAL_NUM_CARDS));
		}

		forPlayer.addCard(card);

		printGameState();	
		return card;
	}
	
	/**
	 * play the game
	 */
	public void play() {
		System.out.println("--- new game started ---");
		
		char continuePlay = 'y';
		while(human.getChips() > 0 && Character.toLowerCase(continuePlay) == 'y') {	
			GameResult winner;

			gameStake = 1; 
			human.betChip(gameStake);
	
			// one card for dealer, two cards for human
			drawCard(dealer); 
			drawCard(human); 
			drawCard(human);
			
			
			if((winner = checkBlackJack()) != null) {
				System.out.println("-> BlackJack! <-");
			}
			else {
				// human
				winner = playHuman();
				
				// dealer
				if(winner == null) {			
					while(dealer.makeTurn() != Turn.Stay) {
						drawCard(dealer);
					}
					
					if(dealer.getValue() > 21) {
						winner = GameResult.PlayerWins;
					}
				}
				
				// find winner
				if(winner == null) { winner = evaluateCards(); }
				
				if(winner == GameResult.Draw) { human.addChips(gameStake); }
				else if (winner == GameResult.PlayerWins) { human.addChips(2 * gameStake); }
			}
			
			System.out.println(winner.toString());
			System.out.print("You have " + human.getChips() + " chips. Do you want to continue playing? (y/n): ");
			continuePlay = In.readChar(); 				
			resetCards();
		}
		
		System.out.println("--- bye! ---");
	}
	
	private GameResult playHuman () {
		GameResult winner = null;
		Turn currentTurn; 
		while (winner == null && (currentTurn = human.makeTurn()) != Turn.Stay) {		
			if(currentTurn == Turn.DoubleDown) {
				// double down
				drawCard(human);
				human.betChip(gameStake);
				gameStake *= 2;
			}
			else {
				// hit
				drawCard(human);
			}
			
			if(human.getValue() == 21) {
				// 'auto stay'
				break; 
			}
			else if(human.getValue() > 21) {
				winner = GameResult.DealerWins;
			}
			
			if(currentTurn == Turn.DoubleDown) { break; }
		}
		return winner;
	}
	
	private void resetCards() {
		dealer.getCards().clear();
		human.getCards().clear();
	}


	private GameResult checkBlackJack() {
		if(human.hasBlackJack()) {
			if(dealer.getCards().size() < 2) {
				drawCard(dealer);
			}
			
			if(dealer.hasBlackJack()) {
				human.addChips(gameStake);
				return GameResult.Draw;
			}
			else {
				human.addChips(3 * gameStake);
				return GameResult.PlayerWins;
			}
		}
		return null;
	}
	
	/**
	 * evaluate which player won this round
	 * 
	 * @return the result of this round
	 */
	public GameResult evaluateCards() {
		
		if(human.getValue() > dealer.getValue()) {
			return GameResult.PlayerWins; 
		}
		else if(human.getValue() < dealer.getValue()) {
			return GameResult.DealerWins; 
		}
		return GameResult.Draw;
	}
	
	/**
	 * print the cards of both players and their value
	 */
	public void printGameState() {

		Out.println("Dealer (" + dealer.getValue() + ")");
		for (Card c : dealer.getCards()) {
			Out.print(c + " ");
		}
		Out.println();

		Out.println("Player (" + human.getValue() + ")");
		for (Card c : human.getCards()) {
			Out.print(c + " ");
		}
		Out.println("\n");
	}

}
