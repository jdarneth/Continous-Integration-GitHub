package blackjack.player;

import java.util.ArrayList;
import java.util.List;

import blackjack.game.Card;
import blackjack.game.Turn;

public abstract class Player {	
	private final List<Card> cards = new ArrayList<>();
	
	public List<Card> getCards() {
		return cards;
	}
	
	public void addCard(Card card)  {
		cards.add(card);
	}
	
	public int getValue() {
		int valAce1 = cards.stream()
			.map(c -> (c.getIndex() > 10 ? 10 : c.getIndex()))
			.reduce(0, (sum, x) -> sum + x);
		
		boolean containsAce = cards.stream()
				.anyMatch(c -> c.getIndex() == 1); 
		
		//valAce1 is calculated with 1 for ace -> calculate any ace with 11 (therefore +10) 
		if(containsAce && valAce1+10 <= 21) {
			return valAce1+10; 
		}
		return valAce1; 
		
	}

	public boolean hasBlackJack() {
		return cards.size() == 2 && getValue() == 21;
	}

	public abstract Turn makeTurn();
}
