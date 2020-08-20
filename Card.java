import java.util.ArrayList;

// Should check a global value to see if aces are high or low. Currently: always high
/* Usage Example
 *--------------------------------------------------------------
 *		// How to summon a deck of ...
 *		ArrayList<Card> deck = Card.deckplayingcard(false);
 *		ArrayList<Card> deck = Card.decktarotcard();
 *		ArrayList<Card> deck = Card.deckmahjong();
 *		// ...and shuffle it: 
 *		deck = Card.shuffle(deck);
 *		// Print the name of every card in the deck
 *		for (int i=0; i<deck.size(); i++) {
 *			System.out.println(deck.get(i).name());
 *--------------------------------------------------------------
 */

public class Card {
	String suit = "";
	String value = "";
// Constructor
	public Card(String insuit, String invalue) {
		suit = insuit;
		value = invalue;		
		// * = is a face card
		// -------------------
		// Playing Cards
		// suits: Spades, Clubs, Diamonds, Hearts
		// value: 2, 3, 4, 5, 6, 7, 8, 9, 10, Ace*, Jack*, Queen*, King*
		// suit: Red (Joker), Black (Joker)
		// -------------------
		// Tarot Cards
		// suits : Swords, Batons, Coins, Cups
		// value : 2, 3, 4, 5, 6, 7, 8, 9, 10, Ace*, Page*, Knight*, Queen*, King*
		// suits:  Major Arcana,
		// value : The Magician(1), The High Priestess(2), The Empress(3), The Emperor(4), The Hierophant(5),
		// 		   The Lovers(6), The Chariot(7), Strength(8), The Hermit(9), Wheel of Fortune(10),
		// 		   Justice(11), The Hanged Man(12), Death(13), Temperance(14), The Devil(15),
		// 		   The Tower(16), The Star(17), The Moon(18), The Sun(19), Judgement(20),
		// 		   The World(21), The Fool(0 or 22)	
		// -------------------
		// Mah Jong Tiles
		// "suited tiles" / numerics
		// Suits: Dots, Bamboo, Characters (1, 2, 3, 4, 5, 6, 7, 8, 9)
		// honor tiles:
		// Suits: Wind (East1, South2, West3, North4), Dragon (Red, Green, White)
		// bonus/flower tiles
		// Suits: Four Seasons (Spring1, Summer2, Autumn3, Winter4), Four Gentlemen (Plum1, Orchid2, Chrysanthemum3, Bamboo4)
		// full deck has four copies of every dot, bamboo, and character tile
		//				four copies of every wind
		//				four copies of every dragon
		// 				one copy of the seasons, one copy of the gentlemen
		// LATER: Expand this to include flower mahjong BS. List of data at end of file.
	}

	// 
	// Functions that return the (trait) of a single card, based on its suit, value, and derived characteristics.
	// 
	
	public String color() {
		// Default
		String result = "";
		// Tarot cards are weird
		if (suit=="Swords") {result="White";};
		if (suit=="Batons") {result="Red";};
		if (suit=="Coins") {result="Blue";};
		if (suit=="Cups") {result="Green";};
		if (suit=="Major Arcana") {result = "Gold";};
		// Standard cards
		if (suit=="Spades") {result="Black";};
		if (suit=="Clubs") {result="Black";};
		if (suit=="Black") {result="Black";};
		if (suit=="Red") {result="Red";};
		if (suit=="Diamonds") {result="Red";};
		if (suit=="Hearts") {result="Red";};
		return result;
	}

	public String element() {
		String result = "";
		boolean has = false;
		if (suit=="Winds") {has=true;};
		if (isflowertile()) {has=true;};
		if ((suit =="Swords")||(suit =="Batons")||(suit =="Coins")||(suit =="Cups")) {has=true;}; 

		if (has) {
			// Mah Jong
			if (value=="1") {result="Wood";};
			if (value=="2") {result="Fire";};
			if (value=="3") {result="Metal";};
			if (value=="4") {result="Water";};
			// Tarot suit
			if (suit=="Swords") {result="Air";};
			if (suit=="Batons") {result="Fire";};
			if (suit=="Coins") {result="Water";};
			if (suit=="Cups") {result="Earth";};
			// Tarot ranks
			String suitelement = result;
			if (value=="Page")   {result="Earth of " + suitelement;};
			if (value=="Knight") {result="Air of " + suitelement;};
			if (value=="Queen")  {result="Water of " + suitelement;};
			if (value=="King")   {result="Fire of " + suitelement;};
		};
		return result;
	}
	
	public String wind() {
		String result = "";
		boolean haswind = false;
		if (suit=="Winds") {haswind=true;};
		if (isflowertile()) {haswind=true;};
		if ((suit =="Swords")||(suit =="Batons")||(suit =="Coins")||(suit =="Cups")) {haswind=true;}; 

		if (haswind) {
			// Mah Jong
			if (value=="1") {result="East";};
			if (value=="2") {result="South";};
			if (value=="3") {result="West";};
			if (value=="4") {result="North";};
			// Tarot
			if (suit=="Swords") {result="East";};
			if (suit=="Batons") {result="South";};
			if (suit=="Coins") {result="West";};
			if (suit=="Cups") {result="North";};
		};
		return result;
	}

	public String name() {
		String result = suit + " " + value;
		// Numerics and facecards follow a pattern
		if ((isnumeric())||(isfacecard())) {result = value + " of " + suit;};
		// Jokers are different, 
		if (value=="Joker") {result = suit + " " + value;};		
		// So are Major Arcana
		if (suit=="Major Arcana") {result = value;};
		// winds and dragons
		if (suit=="Winds") {result = value + " Wind";};		
		if (suit=="Dragons") {result = value + " Dragon";};		
		// flower tiles
		if (suit=="Seasons") {result = value;};		
		if (suit=="Gentlemen") {result = value;};		
		if (suit=="Professions") {result = value;};		
		if (suit=="Arts") {result = "Art of " + value;};		
		if ((suit=="Emperors")||(suit=="Empresses")) {result = value + " " + suit;};
		// blank cards
		if ((suit=="")&&(value=="")) {result="blank card";};
		return result;
	}
	
	public int valuenum() {
		int result = 0;
		if (value == "2") {result=2;};
		if (value == "3") {result=3;};
		if (value == "4") {result=4;};
		if (value == "5") {result=5;};
		if (value == "6") {result=6;};
		if (value == "7") {result=7;};
		if (value == "8") {result=8;};
		if (value == "9") {result=9;};
		if (value == "10") {result=10;};
		// Face Cards
		if ((suit =="Spades")||(suit =="Clubs")||(suit =="Diamonds")||(suit =="Hearts")) {
			if (value == "1") {result=1;};
			if (value == "Jack") {result=11;};
			if (value == "Queen") {result=12;};
			if (value == "King") {result=13;};
			if (value == "Ace") {result=14;};
		};
		if ((suit =="Swords")||(suit =="Batons")||(suit =="Coins")||(suit =="Cups")) {
			if (value == "1") {result=1;};
			if (value == "Page") {result=11;};
			if (value == "Knight") {result=12;};
			if (value == "Queen") {result=13;};
			if (value == "King") {result=14;};
			if (value == "Ace") {result=15;};
		};
		if (suit =="Major Arcana") {
			if (value == "The Fool") {result=0;};
			if (value == "The Magician") {result=1;};
			if (value == "The High Priestess") {result=2;};
			if (value == "The Empress") {result=3;};
			if (value == "The Emperor") {result=4;};
			if (value == "The Hierophant") {result=5;};
			if (value == "The Lovers") {result=6;};
			if (value == "The Chariot") {result=7;};
			if (value == "Strength") {result=8;};
			if (value == "The Hermit") {result=9;};
			if (value == "Wheel of Fortune") {result=10;};
			if (value == "Justice") {result=11;};
			if (value == "The Hanged Man") {result=12;};
			if (value == "Death") {result=13;};
			if (value == "Temperance") {result=14;};
			if (value == "The Devil") {result=15;};
			if (value == "The Tower") {result=16;};
			if (value == "The Star") {result=17;};
			if (value == "The Moon") {result=18;};
			if (value == "The Sun") {result=19;};
			if (value == "Judgement") {result=20;};
			if (value == "The World") {result=21;};
			if (value == "The Fool") {result=0;};
		};		
		if (suit=="Wind") {
			if (value=="East")  {result=1;};
			if (value=="South") {result=2;};
			if (value=="West")  {result=3;};
			if (value=="North") {result=4;};
		};//winds
		if (isflowertile()) {
			// Seasons
			if (value=="Spring") {result=1;};
			if (value=="Summer") {result=2;};
			if (value=="Autumn") {result=3;};
			if (value=="Winter") {result=4;};
			// Gentlemen
			if (value=="Plum") {result=1;};
			if (value=="Orchid") {result=2;};
			if (value=="Chrysanthemum") {result=3;};
			if (value=="Bamboo") {result=4;};
			// Arts
			if (value=="Guqin") {result=1;};
			if (value=="Go") {result=2;};
			if (value=="Calligraphy") {result=3;};
			if (value=="Painting") {result=4;};
			// Professions
			if (value=="Fisher") {result=1;};
			if (value=="Woodcutter") {result=2;};
			if (value=="Farmer") {result=3;};
			if (value=="Scholar") {result=4;};
			// Emperors, Empresses
			if (value=="First") {result=1;};
			if (value=="Second") {result=2;};
			if (value=="Third") {result=3;};
			if (value=="Fourth") {result=4;};
		};
		return result;
	}

	public boolean isnumeric() {
		boolean result = false;
		if ((suit == "Dots")||(suit == "Bamboo")||(suit == "Characters")) {result=true;};
		if ((suit == "Swords")||(suit == "Batons")||(suit == "Coins")||(suit == "Cups")) {result=true;};
		if ((suit == "Spades")||(suit == "Clubs")||(suit == "Diamonds")||(suit == "Hearts")) {result=true;};
		if (isfacecard() == true) {result=false;};
		if (value=="Joker") {result=true;};
		return result;
	}

	public boolean isfacecard() {
		boolean result = false;
		if (value == "Ace") {result=true;};
		if (value == "Jack") {result=true;};
		if (value == "Queen") {result=true;};
		if (value == "King") {result=true;};
		if (value == "Page") {result=true;};
		if (value == "Knight") {result=true;};
		if (value=="Joker") {result=true;};
		return result;
	}

	public boolean ishonortile() {
		boolean result = false;
		if (suit == "Winds") {result=true;};
		if (suit == "Dragons") {result=true;};
		return result;
	}

	public boolean isflowertile() {
		boolean result = false;
		if (suit == "Seasons") {result=true;};
		if (suit == "Gentlemen") {result=true;};
		if (suit == "Arts") {result=true;};
		if (suit == "Professions") {result=true;};
		if (suit == "Emperors") {result=true;};
		if (suit == "Empresses") {result=true;};
		return result;
	}

	// Functions that compare one card to another card
	
	public boolean samesuit(Card incard) {
		boolean result = false;
		if (suit == incard.suit) {result = true;};
		if ((value=="Joker")||(incard.value=="Joker")) {result=true;};
		return result;
	}

	public boolean sameval(Card incard) {
		boolean result = false;
		if (valuenum() == incard.valuenum()) {result = true;};
		// Major arcana are not sameval unless there is an exact text match.
		// ie; The Magician is not an ace, The High Priestess is not a two of clubs or swords, ...
		if (((suit == "Major Arcana")||(incard.suit == "Major Arcana"))&&(value!=incard.value)) {result=false;};
		if ((value=="Joker")||incard.value=="Joker") {result=true;};
		return result;
	}

	public boolean samewind(Card incard) {
		boolean result = false;
		if (wind()==incard.wind()) {result = true;};
		return result;
	}

	public boolean nextto(Card incard) {
		boolean result = false;
		// Cards are next to each other if their values are adjacent but not equal
		if ((valuenum() == incard.valuenum()+1)||(valuenum() == incard.valuenum()-1)) {result = true;};
		// ..AND, King-ace-two wraps around.
		if ((value == "Ace")&&(incard.value == "King")) {result=true;};
		if ((value == "Ace")&&(incard.value == "2")) {result=true;};
		if ((value == "2")&&(incard.value == "Ace")) {result=true;};
		if ((value == "King")&&(incard.value == "Ace")) {result=true;};
		// ...and the World, Fool, and Magician wraps around.
		if ((value == "Fool")&&(incard.value == "The Magician")) {result=true;};
		if ((value == "Fool")&&(incard.value == "The World")) {result=true;};
		if ((value == "The Magician")&&(incard.value == "The Fool")) {result=true;};
		if ((value == "The World")&&(incard.value == "The Fool")) {result=true;};		
		// and gdi jokers
		if (value=="Joker") {result=true;};
		if (incard.value=="Joker") {result=true;};		
		return result;
	}

	// 
	// Specific deck presets provided below
	// Playing cards, tarot cards, mahjong sets.
	// More custom decks can be added easily enough.
	// 
	
	public static ArrayList<Card> deckplayingcard(boolean jokers) {
		ArrayList<Card> pile = new ArrayList<Card>();
		pile.add(new Card("Spades", "Ace"));
		pile.add(new Card("Spades", "2"));
		pile.add(new Card("Spades", "3"));
		pile.add(new Card("Spades", "4"));
		pile.add(new Card("Spades", "5"));
		pile.add(new Card("Spades", "6"));
		pile.add(new Card("Spades", "7"));
		pile.add(new Card("Spades", "8"));
		pile.add(new Card("Spades", "9"));
		pile.add(new Card("Spades", "10"));
		pile.add(new Card("Spades", "Jack"));
		pile.add(new Card("Spades", "Queen"));
		pile.add(new Card("Spades", "King"));
		pile.add(new Card("Clubs", "Ace"));
		pile.add(new Card("Clubs", "2"));
		pile.add(new Card("Clubs", "3"));
		pile.add(new Card("Clubs", "4"));
		pile.add(new Card("Clubs", "5"));
		pile.add(new Card("Clubs", "6"));
		pile.add(new Card("Clubs", "7"));
		pile.add(new Card("Clubs", "8"));
		pile.add(new Card("Clubs", "9"));
		pile.add(new Card("Clubs", "10"));
		pile.add(new Card("Clubs", "Jack"));
		pile.add(new Card("Clubs", "Queen"));
		pile.add(new Card("Clubs", "King"));
		pile.add(new Card("Diamonds", "Ace"));
		pile.add(new Card("Diamonds", "2"));
		pile.add(new Card("Diamonds", "3"));
		pile.add(new Card("Diamonds", "4"));
		pile.add(new Card("Diamonds", "5"));
		pile.add(new Card("Diamonds", "6"));
		pile.add(new Card("Diamonds", "7"));
		pile.add(new Card("Diamonds", "8"));
		pile.add(new Card("Diamonds", "9"));
		pile.add(new Card("Diamonds", "10"));
		pile.add(new Card("Diamonds", "Jack"));
		pile.add(new Card("Diamonds", "Queen"));
		pile.add(new Card("Diamonds", "King"));
		pile.add(new Card("Hearts", "Ace"));
		pile.add(new Card("Hearts", "2"));
		pile.add(new Card("Hearts", "3"));
		pile.add(new Card("Hearts", "4"));
		pile.add(new Card("Hearts", "5"));
		pile.add(new Card("Hearts", "6"));
		pile.add(new Card("Hearts", "7"));
		pile.add(new Card("Hearts", "8"));
		pile.add(new Card("Hearts", "9"));
		pile.add(new Card("Hearts", "10"));
		pile.add(new Card("Hearts", "Jack"));
		pile.add(new Card("Hearts", "Queen"));
		pile.add(new Card("Hearts", "King"));
		if (jokers==true) {pile.add(new Card("Black", "Joker"));}; 
		if (jokers==true) {pile.add(new Card("Red", "Joker"));}; 
		return pile;
	}

	public static ArrayList<Card> decktarotcard() {
		ArrayList<Card> pile = new ArrayList<Card>();
		pile.add(new Card("Major Arcana", "The Fool"));
		pile.add(new Card("Major Arcana", "The Magician"));
		pile.add(new Card("Major Arcana", "The High Priestess"));
		pile.add(new Card("Major Arcana", "The Empress"));
		pile.add(new Card("Major Arcana", "The Emperor"));
		pile.add(new Card("Major Arcana", "The Hierophant"));
		pile.add(new Card("Major Arcana", "The Lovers"));
		pile.add(new Card("Major Arcana", "The Chariot"));
		pile.add(new Card("Major Arcana", "Strength"));
		pile.add(new Card("Major Arcana", "The Hermit"));
		pile.add(new Card("Major Arcana", "Wheel of Fortune"));
		pile.add(new Card("Major Arcana", "Justice"));
		pile.add(new Card("Major Arcana", "The Hanged Man"));
		pile.add(new Card("Major Arcana", "Death"));
		pile.add(new Card("Major Arcana", "Temperance"));
		pile.add(new Card("Major Arcana", "The Devil"));
		pile.add(new Card("Major Arcana", "The Tower"));
		pile.add(new Card("Major Arcana", "The Star"));
		pile.add(new Card("Major Arcana", "The Moon"));
		pile.add(new Card("Major Arcana", "The Sun"));
		pile.add(new Card("Major Arcana", "Judgement"));
		pile.add(new Card("Major Arcana", "The World"));
		pile.add(new Card("Swords", "2"));
		pile.add(new Card("Swords", "3"));
		pile.add(new Card("Swords", "4"));
		pile.add(new Card("Swords", "5"));
		pile.add(new Card("Swords", "6"));
		pile.add(new Card("Swords", "7"));
		pile.add(new Card("Swords", "8"));
		pile.add(new Card("Swords", "9"));
		pile.add(new Card("Swords", "10"));
		pile.add(new Card("Swords", "Page"));
		pile.add(new Card("Swords", "Knight"));
		pile.add(new Card("Swords", "Queen"));
		pile.add(new Card("Swords", "King"));
		pile.add(new Card("Swords", "Ace"));
		pile.add(new Card("Batons", "2"));
		pile.add(new Card("Batons", "3"));
		pile.add(new Card("Batons", "4"));
		pile.add(new Card("Batons", "5"));
		pile.add(new Card("Batons", "6"));
		pile.add(new Card("Batons", "7"));
		pile.add(new Card("Batons", "8"));
		pile.add(new Card("Batons", "9"));
		pile.add(new Card("Batons", "10"));
		pile.add(new Card("Batons", "Page"));
		pile.add(new Card("Batons", "Knight"));
		pile.add(new Card("Batons", "Queen"));
		pile.add(new Card("Batons", "King"));
		pile.add(new Card("Batons", "Ace"));
		pile.add(new Card("Coins", "2"));
		pile.add(new Card("Coins", "3"));
		pile.add(new Card("Coins", "4"));
		pile.add(new Card("Coins", "5"));
		pile.add(new Card("Coins", "6"));
		pile.add(new Card("Coins", "7"));
		pile.add(new Card("Coins", "8"));
		pile.add(new Card("Coins", "9"));
		pile.add(new Card("Coins", "10"));
		pile.add(new Card("Coins", "Page"));
		pile.add(new Card("Coins", "Knight"));
		pile.add(new Card("Coins", "Queen"));
		pile.add(new Card("Coins", "King"));
		pile.add(new Card("Coins", "Ace"));
		pile.add(new Card("Cups", "2"));
		pile.add(new Card("Cups", "3"));
		pile.add(new Card("Cups", "4"));
		pile.add(new Card("Cups", "5"));
		pile.add(new Card("Cups", "6"));
		pile.add(new Card("Cups", "7"));
		pile.add(new Card("Cups", "8"));
		pile.add(new Card("Cups", "9"));
		pile.add(new Card("Cups", "10"));
		pile.add(new Card("Cups", "Page"));
		pile.add(new Card("Cups", "Knight"));
		pile.add(new Card("Cups", "Queen"));
		pile.add(new Card("Cups", "King"));
		pile.add(new Card("Cups", "Ace"));
		return pile;
	}

	public static ArrayList<Card> deckmahjong() {
		ArrayList<Card> pile = new ArrayList<Card>();
		pile.add(new Card("Dots", "1"));
		pile.add(new Card("Dots", "2"));
		pile.add(new Card("Dots", "3"));
		pile.add(new Card("Dots", "4"));
		pile.add(new Card("Dots", "5"));
		pile.add(new Card("Dots", "6"));
		pile.add(new Card("Dots", "7"));
		pile.add(new Card("Dots", "8"));
		pile.add(new Card("Dots", "9"));
		pile.add(new Card("Bamboo", "1"));
		pile.add(new Card("Bamboo", "2"));
		pile.add(new Card("Bamboo", "3"));
		pile.add(new Card("Bamboo", "4"));
		pile.add(new Card("Bamboo", "5"));
		pile.add(new Card("Bamboo", "6"));
		pile.add(new Card("Bamboo", "7"));
		pile.add(new Card("Bamboo", "8"));
		pile.add(new Card("Bamboo", "9"));
		pile.add(new Card("Characters", "1"));
		pile.add(new Card("Characters", "2"));
		pile.add(new Card("Characters", "3"));
		pile.add(new Card("Characters", "4"));
		pile.add(new Card("Characters", "5"));
		pile.add(new Card("Characters", "6"));
		pile.add(new Card("Characters", "7"));
		pile.add(new Card("Characters", "8"));
		pile.add(new Card("Characters", "9"));
		pile.add(new Card("Winds", "East"));
		pile.add(new Card("Winds", "South"));
		pile.add(new Card("Winds", "West"));
		pile.add(new Card("Winds", "North"));
		pile.add(new Card("Dragons", "Red"));
		pile.add(new Card("Dragons", "Green"));
		pile.add(new Card("Dragons", "White"));
		pile.add(new Card("Dots", "1"));
		pile.add(new Card("Dots", "2"));
		pile.add(new Card("Dots", "3"));
		pile.add(new Card("Dots", "4"));
		pile.add(new Card("Dots", "5"));
		pile.add(new Card("Dots", "6"));
		pile.add(new Card("Dots", "7"));
		pile.add(new Card("Dots", "8"));
		pile.add(new Card("Dots", "9"));
		pile.add(new Card("Bamboo", "1"));
		pile.add(new Card("Bamboo", "2"));
		pile.add(new Card("Bamboo", "3"));
		pile.add(new Card("Bamboo", "4"));
		pile.add(new Card("Bamboo", "5"));
		pile.add(new Card("Bamboo", "6"));
		pile.add(new Card("Bamboo", "7"));
		pile.add(new Card("Bamboo", "8"));
		pile.add(new Card("Bamboo", "9"));
		pile.add(new Card("Characters", "1"));
		pile.add(new Card("Characters", "2"));
		pile.add(new Card("Characters", "3"));
		pile.add(new Card("Characters", "4"));
		pile.add(new Card("Characters", "5"));
		pile.add(new Card("Characters", "6"));
		pile.add(new Card("Characters", "7"));
		pile.add(new Card("Characters", "8"));
		pile.add(new Card("Characters", "9"));
		pile.add(new Card("Winds", "East"));
		pile.add(new Card("Winds", "South"));
		pile.add(new Card("Winds", "West"));
		pile.add(new Card("Winds", "North"));
		pile.add(new Card("Dragons", "Red"));
		pile.add(new Card("Dragons", "Green"));
		pile.add(new Card("Dragons", "White"));
		pile.add(new Card("Dots", "1"));
		pile.add(new Card("Dots", "2"));
		pile.add(new Card("Dots", "3"));
		pile.add(new Card("Dots", "4"));
		pile.add(new Card("Dots", "5"));
		pile.add(new Card("Dots", "6"));
		pile.add(new Card("Dots", "7"));
		pile.add(new Card("Dots", "8"));
		pile.add(new Card("Dots", "9"));
		pile.add(new Card("Bamboo", "1"));
		pile.add(new Card("Bamboo", "2"));
		pile.add(new Card("Bamboo", "3"));
		pile.add(new Card("Bamboo", "4"));
		pile.add(new Card("Bamboo", "5"));
		pile.add(new Card("Bamboo", "6"));
		pile.add(new Card("Bamboo", "7"));
		pile.add(new Card("Bamboo", "8"));
		pile.add(new Card("Bamboo", "9"));
		pile.add(new Card("Characters", "1"));
		pile.add(new Card("Characters", "2"));
		pile.add(new Card("Characters", "3"));
		pile.add(new Card("Characters", "4"));
		pile.add(new Card("Characters", "5"));
		pile.add(new Card("Characters", "6"));
		pile.add(new Card("Characters", "7"));
		pile.add(new Card("Characters", "8"));
		pile.add(new Card("Characters", "9"));
		pile.add(new Card("Winds", "East"));
		pile.add(new Card("Winds", "South"));
		pile.add(new Card("Winds", "West"));
		pile.add(new Card("Winds", "North"));
		pile.add(new Card("Dragons", "Red"));
		pile.add(new Card("Dragons", "Green"));
		pile.add(new Card("Dragons", "White"));
		pile.add(new Card("Dots", "1"));
		pile.add(new Card("Dots", "2"));
		pile.add(new Card("Dots", "3"));
		pile.add(new Card("Dots", "4"));
		pile.add(new Card("Dots", "5"));
		pile.add(new Card("Dots", "6"));
		pile.add(new Card("Dots", "7"));
		pile.add(new Card("Dots", "8"));
		pile.add(new Card("Dots", "9"));
		pile.add(new Card("Bamboo", "1"));
		pile.add(new Card("Bamboo", "2"));
		pile.add(new Card("Bamboo", "3"));
		pile.add(new Card("Bamboo", "4"));
		pile.add(new Card("Bamboo", "5"));
		pile.add(new Card("Bamboo", "6"));
		pile.add(new Card("Bamboo", "7"));
		pile.add(new Card("Bamboo", "8"));
		pile.add(new Card("Bamboo", "9"));
		pile.add(new Card("Characters", "1"));
		pile.add(new Card("Characters", "2"));
		pile.add(new Card("Characters", "3"));
		pile.add(new Card("Characters", "4"));
		pile.add(new Card("Characters", "5"));
		pile.add(new Card("Characters", "6"));
		pile.add(new Card("Characters", "7"));
		pile.add(new Card("Characters", "8"));
		pile.add(new Card("Characters", "9"));
		pile.add(new Card("Winds", "East"));
		pile.add(new Card("Winds", "South"));
		pile.add(new Card("Winds", "West"));
		pile.add(new Card("Winds", "North"));
		pile.add(new Card("Dragons", "Red"));
		pile.add(new Card("Dragons", "Green"));
		pile.add(new Card("Dragons", "White"));
		pile.add(new Card("Seasons", "Spring"));
		pile.add(new Card("Seasons", "Summer"));
		pile.add(new Card("Seasons", "Autumn"));
		pile.add(new Card("Seasons", "Winter"));
		pile.add(new Card("Gentlemen", "Plum"));
		pile.add(new Card("Gentlemen", "Orchid"));
		pile.add(new Card("Gentlemen", "Chrysanthemum"));
		pile.add(new Card("Gentlemen", "Bamboo"));
		return pile;
	}
	
	//
	// Below are the operations that are done on stacks of cards
	//

	// Functions that change the order cards are in	
	// SortValue, Draw
	
	public static ArrayList<Card> shuffle(ArrayList<Card> deck) {		
		int rand = (int)(Math.random() * (deck.size()));		
		for (int i=0; i<deck.size(); i++) {
			rand = (int)(Math.random() * (deck.size()));
			deck = swap(deck, i, rand);
		};
		return deck;
	}

	public static ArrayList<Card> swap(ArrayList<Card> deck, int index1, int index2) {
		Card temp1 = deck.get(index1);
		deck.set(index1, deck.get(index2));
		deck.set(index2, temp1);
		return deck;
	}
	
	// Functions that give you information about a collection of cards
	// analyze Pokerhand, 
		
	// Methods to add: 
	// Card[] move all blank cards to end of stack
	// Remove top card, Card[] Move all cards up one
	// Remove bottom card, Card[] Move all cards down one
	// Card[] Remove single card at position #, replace with empty card

	// Recognize poker hands
	// Recognize mahjong hands
}


// Flower Mahjong dump
// There are heaps of possible flower tiles.  usually a set has the four seasons and four gentlemen.
// flower sets: four seasons (spring/summer/autumn/winter flowers),
//				four gentlemen (plum, orchid, chrysanthemum, bamboo)
//				four arts (Guqin, Go, Calligraphy, Painting)  (guqin is a musical instrument)
//				four noble professions (fisher, woodcutter, farmer, scholar)
// 				four emperors (first, second, third, fourth)
//				four empresses (first, second, third, fourth)
// Items below this aren't included yet.
//	animal tiles are unnumbered flowers that automatically match the player's seat and usually occur in pairs
//	animal sets: cat & mouse, rooster & centipede, Dragon & flaming pearl, 
// 					Caishen & Sycee, Jiang Ziya & Fish,Liu Haichan & Jin Chan
//				four faces  (unnumbered, sometimes 2 male 2 female)
//	suit restricted jokers (circle, bamboo, character, universal/flying, dragon, wind, flower, honor ...)
//  value-restricted jokers (terminal (1/9), 147(1/4/7), 258,369, 
