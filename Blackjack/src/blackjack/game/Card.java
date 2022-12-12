package blackjack.game;

public class Card {

	public enum Color {
		Clubs("\u2663"), Diamonds("\u2666"), Hearts("\u2665"), Spades("\u2660");

		private final String encoding;

		private Color(String encoding) {
			this.encoding = encoding;
		}

		public String getEncoding() {
			return encoding;
		}

	}

	private final Color color;
	private final int index;

	/**
	 * Create a card from its index in the deck (0 to 51)
	 * 
	 * @param deckIndex index of the card in the complete deck
	 */
	public Card(int deckIndex) {
		color = Color.values()[deckIndex / 13];

		// value from 1 to 13
		index = 1 + deckIndex % 13;
	}

	public Card(Color color, int value) {
		this.color = color;
		this.index = value;
	}

	/**
	 * @return index of the card, between 1 (Ace) and 13 (king)
	 */
	public int getIndex() {
		return index;
	}

	@Override
	public String toString() {
		switch (index) {
		case 1:
			return "A" + color.getEncoding();
		case 11:
			return "J" + color.getEncoding();
		case 12:
			return "Q" + color.getEncoding();
		case 13:
			return "K" + color.getEncoding();
		default:
			return index + color.getEncoding();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + index;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (color != other.color)
			return false;
		if (index != other.index)
			return false;
		return true;
	}

}
