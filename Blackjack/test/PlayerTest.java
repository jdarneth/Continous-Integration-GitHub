import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import blackjack.game.Card;
import blackjack.game.Card.Color;
import blackjack.player.Dealer;
import blackjack.player.HumanPlayer;
import blackjack.player.Player;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

	private Player human;
	private Player dealer; 
	
	@BeforeEach
	void setUp() {
		human = new HumanPlayer();
		dealer = new Dealer(); 
	}
	
	@Test
	void testNoCards () {
		assertEquals(0, human.getValue());
		assertFalse(human.hasBlackJack());
		assertEquals(0, dealer.getValue());
		assertFalse(dealer.hasBlackJack());
	}

	@Test
	void testHumanAceAs1() {
		testAceAs1(human);
	}

	@Test
	void testDealerAceAs1() {
		testAceAs1(dealer);
	}
	
	void testAceAs1(Player pl) {
		pl.addCard(new Card(Color.Diamonds, 10));
		pl.addCard(new Card(Color.Diamonds, 5));
		pl.addCard(new Card(Color.Diamonds, 1));
		
		assertEquals(16, pl.getValue());
	}
	

	@Test
	void testHumanAceAs11() {
		testAceAs11(human);
	}
	
	@Test
	void testDealerAceAs11() {
		testAceAs11(dealer);
	}
	
	void testAceAs11(Player pl) {
		pl.addCard(new Card(Color.Diamonds, 5));
		pl.addCard(new Card(Color.Diamonds, 3));
		pl.addCard(new Card(Color.Diamonds, 1));
		
		assertEquals(19, pl.getValue());
	}
	
	@Test
	void testHumanMultiAce() {
		testMultiAce(human);
	}
	
	@Test
	void testDealerMultiAce() {
		testMultiAce(dealer);
	}	
	
	void testMultiAce(Player pl) {
		pl.addCard(new Card(Color.Hearts, 1));
		pl.addCard(new Card(Color.Diamonds, 1));
		
		assertEquals(12, pl.getValue());
	}
	
	@Test
	void testHumanNoBlackJack() {
		testNoBlackJack(human);
	}
	
	@Test
	void testDealerNoBlackJack() {
		testNoBlackJack(dealer);
	}
	
	void testNoBlackJack(Player pl) {
		pl.addCard(new Card(Color.Diamonds, 5));
		pl.addCard(new Card(Color.Diamonds, 3));
		pl.addCard(new Card(Color.Diamonds, 1));
		
		assertFalse(pl.hasBlackJack());
	}
	
	@Test
	void testHumanBlackJack() {
		testBlackJack(human);
	}
	
	@Test
	void testDealerBlackJack() {
		testBlackJack(dealer);
	}
	
	void testBlackJack(Player pl) {
		pl.addCard(new Card(Color.Diamonds, 10));
		pl.addCard(new Card(Color.Diamonds, 1));
		
		assertTrue(pl.hasBlackJack());
	}
	
	@Test
	void testHumanValue21AndNoBlackJack() {
		testValue21AndNoBlackJack(human);
	}

	@Test
	void testDealerValue21AndNoBlackJack() {
		testValue21AndNoBlackJack(dealer);
	}
	
	void testValue21AndNoBlackJack(Player pl) {
		pl.addCard(new Card(Color.Hearts, 7));
		pl.addCard(new Card(Color.Diamonds, 7));
		pl.addCard(new Card(Color.Spades, 7));

		assertFalse(pl.hasBlackJack());
	}
}