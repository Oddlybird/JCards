import java.util.ArrayList;

public class Misc {

	public static void main(String[] args) {
		// dicecheck();
		printdeck();
	}
	
	// Card.java
	public static void printdeck() {
		// How to summon a deck of standard playing cards
		ArrayList<Card> deck = Card.deckplayingcard(false);
		// ...and shuffle it: 
		deck = Card.shuffle(deck);
		// Print the name of every card in the deck
		for (int i=0; i<deck.size(); i++) {
			System.out.println(deck.get(i).name());
		}
	}
	
	// Dice.java
	public static void dicecheck() {
		Dice dice = new Dice();
		int[] num = dice.roll(3, 6);
		for (int arb : num) {System.out.print(arb + ", ");};		
		System.out.println("= " + dice.sum(num));
	}
	
}
