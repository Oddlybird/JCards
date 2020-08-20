
public class Misc {

	public static void main(String[] args) {
		// dicecheck();
		// printdeck();
		pokerhand();
	}

	
	// Card.java
	public static void pokerhand() {
		// Cause 1 deck of playing cards
		Card cards = new Card("", "");
		Card[] deck = cards.deckplayingcard(false);
		deck = cards.shuffle(deck);
		// A hand of five cards 
		Card[] hand = new Card[5];
		hand = cards.draw(deck, 5);
		hand = cards.sortvalue(hand);
		// print the hand, then the deck
		String handlist = hand[0].name() + ", " + hand[1].name() + ", " + hand[2].name() + ", " + hand[3].name() + ", " + hand[4].name(); 
		String valulist = hand[0].valuenum() + ", " + hand[1].valuenum() + ", " + hand[2].valuenum() + ", " + hand[3].valuenum() + ", " + hand[4].valuenum(); 
	    System.out.println(handlist);
	    System.out.println(valulist);
	    System.out.println(cards.pokerhand(hand));
	    System.out.println(" - ");
		// To print the entire deck in order
		// for (Card arb : deck) {System.out.println(arb.name());};		
		// note to self, you still need to shift all blank cards to the bottom o the stack
	}

	// Card.java
	public static void printdeck() {
		// How to initialize Cards as an object
		Card cards = new Card("", "");	
		// How to summon a deck of standard playing cards
		Card[] deck = cards.deckplayingcard(false);
		// ...and shuffle it: 
		deck = cards.shuffle(deck);
		// Print the name of every card in the deck
		for (Card arb : deck) {System.out.println(arb.name());};		
	}
	
	// Dice.java
	public static void dicecheck() {
		Dice dice = new Dice();
		int[] num = dice.roll(3, 6);
		for (int arb : num) {System.out.print(arb + ", ");};		
		System.out.println("= " + dice.sum(num));
	}
	
}
