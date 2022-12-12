import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.game.Blackjack;
import blackjack.game.Card;
import blackjack.game.Card.Color;
import blackjack.game.GameResult;
import blackjack.game.OutOfCardsException;

public class BlackjackTest {

	private Blackjack game; 
	
	@BeforeEach
	void setUp() {
		game = new Blackjack();		
	}
	
	@Test
	void testEvaluatePlayerWin() {
		
		game.getHumanPlayer().addCard(new Card(Color.Diamonds, 10));
		game.getHumanPlayer().addCard(new Card(Color.Diamonds, 5));
		
		game.getDealer().addCard(new Card(Color.Diamonds, 5));
		game.getDealer().addCard(new Card(Color.Diamonds, 3));
		
		assertEquals(GameResult.PlayerWins, game.evaluateCards());	
	}
	
	@Test
	void testEvaluateDealerWin() {
		
		game.getHumanPlayer().addCard(new Card(Color.Diamonds, 5));
		game.getHumanPlayer().addCard(new Card(Color.Diamonds, 3));
		
		game.getDealer().addCard(new Card(Color.Diamonds, 10));
		game.getDealer().addCard(new Card(Color.Diamonds, 5));
		
		assertEquals(GameResult.DealerWins, game.evaluateCards());	
	}
	
	@Test
	void testEvaluateDraw() {
		
		game.getHumanPlayer().addCard(new Card(Color.Diamonds, 10));
		game.getHumanPlayer().addCard(new Card(Color.Hearts, 1));
		
		game.getDealer().addCard(new Card(Color.Hearts, 10));
		game.getDealer().addCard(new Card(Color.Diamonds, 1));
		
		assertEquals(GameResult.Draw, game.evaluateCards());	
	}
	
	@Test
	void testDrawRandomCards() {

		Card c1 = game.drawCard(game.getHumanPlayer());
		Card c2 = game.drawCard(game.getHumanPlayer());
		game.drawCard(game.getDealer());

		assertEquals(2, game.getHumanPlayer().getCards().size());
		assertEquals(1, game.getDealer().getCards().size());
		
		assertTrue(c1 != c2);
	}
	
	@Test
	void testDrawCardNoMoreCards() {
		for (int i = 0; i < 52; i++) {
			game.getHumanPlayer().addCard(new Card(i));
		}
		
		assertThrows(OutOfCardsException.class, () -> game.drawCard(game.getHumanPlayer()));
	}
}
