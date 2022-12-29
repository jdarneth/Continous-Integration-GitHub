package blackjack.player;

import blackjack.game.Turn;

public class Dealer extends Player {

	@Override
	public Turn makeTurn() {
		if(getValue() < 17) {
			return Turn.Hit;
		}
		return Turn.Stay; 
	}
}
