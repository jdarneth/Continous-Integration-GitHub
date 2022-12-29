package blackjack.game;

public enum GameResult {
	
	PlayerWins, DealerWins, Draw;

	@Override
	public String toString() {
		if(this.name() == GameResult.DealerWins.name()) {
			return "Dealer wins!"; 
		}
		else if(this.name() == GameResult.PlayerWins.name()) {
			return "Player wins!"; 
		}
		return "Game draw!"; 
	}
	
}
