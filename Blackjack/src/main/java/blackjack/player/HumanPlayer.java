package blackjack.player;

import blackjack.game.Turn;
import inout.In;

public class HumanPlayer extends Player {

	private int chips = 10;

	public void betChip(int toBet) {
		chips -= toBet;
	}

	public void addChips(int toAdd) {
		chips += toAdd;
	}

	public int getChips() {
		return chips;
	}

	@Override
	public Turn makeTurn() {
		System.out.println("What do you want to do?");
		System.out.println("    -> Stay (s)");
		System.out.println("    -> Hit (h)");

		if (getCards().size() == 2) {
			System.out.println("    -> Double down (d)");
			System.out.print("Enter [s | h | d]: ");
		} else {
			System.out.print("Enter [s | h]: ");
		}

		char in = In.readChar();
		switch (in) {
			case ('s'):
				return Turn.Stay;
			case ('h'):
				return Turn.Hit;
			case ('d'):
				if (getCards().size() == 2) {
					return Turn.DoubleDown;
				}
			default:
				System.out.println("Invalid Answer!");
				makeTurn();
		}
		return null;
	}

}
