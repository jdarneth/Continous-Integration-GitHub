package blackjack.game;

public class OutOfCardsException extends RuntimeException {

	public OutOfCardsException(){
		super("No more cards left in the deck.");
	}
	
}
