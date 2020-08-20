// Should check a global value to see if aces are high or low. Currently: always high
/* Usage Example
 * 		// How to initialize Cards
 *		Card cards = new Card("", "");	
 *		// How to summon a deck of standard playing cards
 *		Card[] deck1 = new Card[54];
 *		deck1 = cards.deckplayingcard();
 *		// How to summon a deck of tarot cards
 *		Card[] deck2 = new Card[78];
 *		deck2 = cards.decktarotcard();
 *		// How to summon a deck of standard playing cards
 *		Card[] deck3 = new Card[144];
 *		deck3 = cards.deckmahjong();				
 *		// Print the name of every card in the deck
 *		for (Card arb : deck3) {System.out.println(arb.name());};
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

	// Functions that compare two cards
	
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
	
	public Card[] deckplayingcard(boolean jokers) {
		int size = 54;
		if (jokers==false) {size=52;};
		Card[] pile = new Card[size];
		pile[0] = new Card("Spades", "Ace");
		pile[1] = new Card("Spades", "2");
		pile[2] = new Card("Spades", "3");
		pile[3] = new Card("Spades", "4");
		pile[4] = new Card("Spades", "5");
		pile[5] = new Card("Spades", "6");
		pile[6] = new Card("Spades", "7");
		pile[7] = new Card("Spades", "8");
		pile[8] = new Card("Spades", "9");
		pile[9] = new Card("Spades", "10");
		pile[10] = new Card("Spades", "Jack");
		pile[11] = new Card("Spades", "Queen");
		pile[12] = new Card("Spades", "King");
		pile[13] = new Card("Clubs", "Ace");
		pile[14] = new Card("Clubs", "2");
		pile[15] = new Card("Clubs", "3");
		pile[16] = new Card("Clubs", "4");
		pile[17] = new Card("Clubs", "5");
		pile[18] = new Card("Clubs", "6");
		pile[19] = new Card("Clubs", "7");
		pile[20] = new Card("Clubs", "8");
		pile[21] = new Card("Clubs", "9");
		pile[22] = new Card("Clubs", "10");
		pile[23] = new Card("Clubs", "Jack");
		pile[24] = new Card("Clubs", "Queen");
		pile[25] = new Card("Clubs", "King");
		pile[26] = new Card("Diamonds", "Ace");
		pile[27] = new Card("Diamonds", "2");
		pile[28] = new Card("Diamonds", "3");
		pile[29] = new Card("Diamonds", "4");
		pile[30] = new Card("Diamonds", "5");
		pile[31] = new Card("Diamonds", "6");
		pile[32] = new Card("Diamonds", "7");
		pile[33] = new Card("Diamonds", "8");
		pile[34] = new Card("Diamonds", "9");
		pile[35] = new Card("Diamonds", "10");
		pile[36] = new Card("Diamonds", "Jack");
		pile[37] = new Card("Diamonds", "Queen");
		pile[38] = new Card("Diamonds", "King");
		pile[39] = new Card("Hearts", "Ace");
		pile[40] = new Card("Hearts", "2");
		pile[41] = new Card("Hearts", "3");
		pile[42] = new Card("Hearts", "4");
		pile[43] = new Card("Hearts", "5");
		pile[44] = new Card("Hearts", "6");
		pile[45] = new Card("Hearts", "7");
		pile[46] = new Card("Hearts", "8");
		pile[47] = new Card("Hearts", "9");
		pile[48] = new Card("Hearts", "10");
		pile[49] = new Card("Hearts", "Jack");
		pile[50] = new Card("Hearts", "Queen");
		pile[51] = new Card("Hearts", "King");
		if (jokers==true) {pile[52] = new Card("Black", "Joker");}; 
		if (jokers==true) {pile[53] = new Card("Red", "Joker");}; 
		return pile;
	}

	public Card[] decktarotcard() {
		Card[] pile = new Card[78];
		pile[0] = new Card("Major Arcana", "The Fool");
		pile[1] = new Card("Major Arcana", "The Magician");
		pile[2] = new Card("Major Arcana", "The High Priestess");
		pile[3] = new Card("Major Arcana", "The Empress");
		pile[4] = new Card("Major Arcana", "The Emperor");
		pile[5] = new Card("Major Arcana", "The Hierophant");
		pile[6] = new Card("Major Arcana", "The Lovers");
		pile[7] = new Card("Major Arcana", "The Chariot");
		pile[8] = new Card("Major Arcana", "Strength");
		pile[9] = new Card("Major Arcana", "The Hermit");
		pile[10] = new Card("Major Arcana", "Wheel of Fortune");
		pile[11] = new Card("Major Arcana", "Justice");
		pile[12] = new Card("Major Arcana", "The Hanged Man");
		pile[13] = new Card("Major Arcana", "Death");
		pile[14] = new Card("Major Arcana", "Temperance");
		pile[15] = new Card("Major Arcana", "The Devil");
		pile[16] = new Card("Major Arcana", "The Tower");
		pile[17] = new Card("Major Arcana", "The Star");
		pile[18] = new Card("Major Arcana", "The Moon");
		pile[19] = new Card("Major Arcana", "The Sun");
		pile[20] = new Card("Major Arcana", "Judgement");
		pile[21] = new Card("Major Arcana", "The World");
		pile[22] = new Card("Swords", "2");
		pile[23] = new Card("Swords", "3");
		pile[24] = new Card("Swords", "4");
		pile[25] = new Card("Swords", "5");
		pile[26] = new Card("Swords", "6");
		pile[27] = new Card("Swords", "7");
		pile[28] = new Card("Swords", "8");
		pile[29] = new Card("Swords", "9");
		pile[30] = new Card("Swords", "10");
		pile[31] = new Card("Swords", "Page");
		pile[32] = new Card("Swords", "Knight");
		pile[33] = new Card("Swords", "Queen");
		pile[34] = new Card("Swords", "King");
		pile[35] = new Card("Swords", "Ace");
		pile[36] = new Card("Batons", "2");
		pile[37] = new Card("Batons", "3");
		pile[38] = new Card("Batons", "4");
		pile[39] = new Card("Batons", "5");
		pile[40] = new Card("Batons", "6");
		pile[41] = new Card("Batons", "7");
		pile[42] = new Card("Batons", "8");
		pile[43] = new Card("Batons", "9");
		pile[44] = new Card("Batons", "10");
		pile[45] = new Card("Batons", "Page");
		pile[46] = new Card("Batons", "Knight");
		pile[47] = new Card("Batons", "Queen");
		pile[48] = new Card("Batons", "King");
		pile[49] = new Card("Batons", "Ace");
		pile[50] = new Card("Coins", "2");
		pile[51] = new Card("Coins", "3");
		pile[52] = new Card("Coins", "4");
		pile[53] = new Card("Coins", "5");
		pile[54] = new Card("Coins", "6");
		pile[55] = new Card("Coins", "7");
		pile[56] = new Card("Coins", "8");
		pile[57] = new Card("Coins", "9");
		pile[58] = new Card("Coins", "10");
		pile[59] = new Card("Coins", "Page");
		pile[60] = new Card("Coins", "Knight");
		pile[61] = new Card("Coins", "Queen");
		pile[62] = new Card("Coins", "King");
		pile[63] = new Card("Coins", "Ace");
		pile[64] = new Card("Cups", "2");
		pile[65] = new Card("Cups", "3");
		pile[66] = new Card("Cups", "4");
		pile[67] = new Card("Cups", "5");
		pile[68] = new Card("Cups", "6");
		pile[69] = new Card("Cups", "7");
		pile[70] = new Card("Cups", "8");
		pile[71] = new Card("Cups", "9");
		pile[72] = new Card("Cups", "10");
		pile[73] = new Card("Cups", "Page");
		pile[74] = new Card("Cups", "Knight");
		pile[75] = new Card("Cups", "Queen");
		pile[76] = new Card("Cups", "King");
		pile[77] = new Card("Cups", "Ace");
		return pile;
	}

	public Card[] deckmahjong() {
		Card[] pile = new Card[144];
		pile[0] = new Card("Dots", "1");
		pile[1] = new Card("Dots", "2");
		pile[2] = new Card("Dots", "3");
		pile[3] = new Card("Dots", "4");
		pile[4] = new Card("Dots", "5");
		pile[5] = new Card("Dots", "6");
		pile[6] = new Card("Dots", "7");
		pile[7] = new Card("Dots", "8");
		pile[8] = new Card("Dots", "9");
		pile[9] = new Card("Bamboo", "1");
		pile[10] = new Card("Bamboo", "2");
		pile[11] = new Card("Bamboo", "3");
		pile[12] = new Card("Bamboo", "4");
		pile[13] = new Card("Bamboo", "5");
		pile[14] = new Card("Bamboo", "6");
		pile[15] = new Card("Bamboo", "7");
		pile[16] = new Card("Bamboo", "8");
		pile[17] = new Card("Bamboo", "9");
		pile[18] = new Card("Characters", "1");
		pile[19] = new Card("Characters", "2");
		pile[20] = new Card("Characters", "3");
		pile[21] = new Card("Characters", "4");
		pile[22] = new Card("Characters", "5");
		pile[23] = new Card("Characters", "6");
		pile[24] = new Card("Characters", "7");
		pile[25] = new Card("Characters", "8");
		pile[26] = new Card("Characters", "9");
		pile[27] = new Card("Winds", "East");
		pile[28] = new Card("Winds", "South");
		pile[29] = new Card("Winds", "West");
		pile[30] = new Card("Winds", "North");
		pile[31] = new Card("Dragons", "Red");
		pile[32] = new Card("Dragons", "Green");
		pile[33] = new Card("Dragons", "White");
		pile[34] = new Card("Dots", "1");
		pile[35] = new Card("Dots", "2");
		pile[36] = new Card("Dots", "3");
		pile[37] = new Card("Dots", "4");
		pile[38] = new Card("Dots", "5");
		pile[39] = new Card("Dots", "6");
		pile[40] = new Card("Dots", "7");
		pile[41] = new Card("Dots", "8");
		pile[42] = new Card("Dots", "9");
		pile[43] = new Card("Bamboo", "1");
		pile[44] = new Card("Bamboo", "2");
		pile[45] = new Card("Bamboo", "3");
		pile[46] = new Card("Bamboo", "4");
		pile[47] = new Card("Bamboo", "5");
		pile[48] = new Card("Bamboo", "6");
		pile[49] = new Card("Bamboo", "7");
		pile[50] = new Card("Bamboo", "8");
		pile[51] = new Card("Bamboo", "9");
		pile[52] = new Card("Characters", "1");
		pile[53] = new Card("Characters", "2");
		pile[54] = new Card("Characters", "3");
		pile[55] = new Card("Characters", "4");
		pile[56] = new Card("Characters", "5");
		pile[57] = new Card("Characters", "6");
		pile[58] = new Card("Characters", "7");
		pile[59] = new Card("Characters", "8");
		pile[60] = new Card("Characters", "9");
		pile[61] = new Card("Winds", "East");
		pile[62] = new Card("Winds", "South");
		pile[63] = new Card("Winds", "West");
		pile[64] = new Card("Winds", "North");
		pile[65] = new Card("Dragons", "Red");
		pile[66] = new Card("Dragons", "Green");
		pile[67] = new Card("Dragons", "White");
		pile[68] = new Card("Dots", "1");
		pile[69] = new Card("Dots", "2");
		pile[70] = new Card("Dots", "3");
		pile[71] = new Card("Dots", "4");
		pile[72] = new Card("Dots", "5");
		pile[73] = new Card("Dots", "6");
		pile[74] = new Card("Dots", "7");
		pile[75] = new Card("Dots", "8");
		pile[76] = new Card("Dots", "9");
		pile[77] = new Card("Bamboo", "1");
		pile[78] = new Card("Bamboo", "2");
		pile[79] = new Card("Bamboo", "3");
		pile[80] = new Card("Bamboo", "4");
		pile[81] = new Card("Bamboo", "5");
		pile[82] = new Card("Bamboo", "6");
		pile[83] = new Card("Bamboo", "7");
		pile[84] = new Card("Bamboo", "8");
		pile[85] = new Card("Bamboo", "9");
		pile[86] = new Card("Characters", "1");
		pile[87] = new Card("Characters", "2");
		pile[88] = new Card("Characters", "3");
		pile[89] = new Card("Characters", "4");
		pile[90] = new Card("Characters", "5");
		pile[91] = new Card("Characters", "6");
		pile[92] = new Card("Characters", "7");
		pile[93] = new Card("Characters", "8");
		pile[94] = new Card("Characters", "9");
		pile[95] = new Card("Winds", "East");
		pile[96] = new Card("Winds", "South");
		pile[97] = new Card("Winds", "West");
		pile[98] = new Card("Winds", "North");
		pile[99] = new Card("Dragons", "Red");
		pile[100] = new Card("Dragons", "Green");
		pile[101] = new Card("Dragons", "White");
		pile[102] = new Card("Dots", "1");
		pile[103] = new Card("Dots", "2");
		pile[104] = new Card("Dots", "3");
		pile[105] = new Card("Dots", "4");
		pile[106] = new Card("Dots", "5");
		pile[107] = new Card("Dots", "6");
		pile[108] = new Card("Dots", "7");
		pile[109] = new Card("Dots", "8");
		pile[110] = new Card("Dots", "9");
		pile[111] = new Card("Bamboo", "1");
		pile[112] = new Card("Bamboo", "2");
		pile[113] = new Card("Bamboo", "3");
		pile[114] = new Card("Bamboo", "4");
		pile[115] = new Card("Bamboo", "5");
		pile[116] = new Card("Bamboo", "6");
		pile[117] = new Card("Bamboo", "7");
		pile[118] = new Card("Bamboo", "8");
		pile[119] = new Card("Bamboo", "9");
		pile[120] = new Card("Characters", "1");
		pile[121] = new Card("Characters", "2");
		pile[122] = new Card("Characters", "3");
		pile[123] = new Card("Characters", "4");
		pile[124] = new Card("Characters", "5");
		pile[125] = new Card("Characters", "6");
		pile[126] = new Card("Characters", "7");
		pile[127] = new Card("Characters", "8");
		pile[128] = new Card("Characters", "9");
		pile[129] = new Card("Winds", "East");
		pile[130] = new Card("Winds", "South");
		pile[131] = new Card("Winds", "West");
		pile[132] = new Card("Winds", "North");
		pile[133] = new Card("Dragons", "Red");
		pile[134] = new Card("Dragons", "Green");
		pile[135] = new Card("Dragons", "White");
		pile[136] = new Card("Seasons", "Spring");
		pile[137] = new Card("Seasons", "Summer");
		pile[138] = new Card("Seasons", "Autumn");
		pile[139] = new Card("Seasons", "Winter");
		pile[140] = new Card("Gentlemen", "Plum");
		pile[141] = new Card("Gentlemen", "Orchid");
		pile[142] = new Card("Gentlemen", "Chrysanthemum");
		pile[143] = new Card("Gentlemen", "Bamboo");
		return pile;
	}
	
	//
	// Below are the operations that are done on stacks of cards
	//

	// Functions that change the order cards are in
	
	public Card[] shuffle(Card[] deck) {
		int rand = (int)(Math.random() * (deck.length));		
		for (int i=0; i<deck.length; i++) {
			rand = (int)(Math.random() * (deck.length));
			deck = swap(deck, i, rand);
		};
		return deck;
	}
	
	public Card[] sortvalue(Card[] indeck) {
		Card[] outdeck = new Card[indeck.length];
		outdeck = indeck;
//		// For each position in the outdeck
//		for (int o=0; o<outdeck.length; o++) {
//			// For each position in the indeck
//			for (int i=0; i<indeck.length; i++) {
//				// inner loop : indeck[i].valuenum() <= indeck[o-1].valuenum()
//			}; // end indeck loop
//			// outer loop
//		}; //end outdeck
		return outdeck;
	}

	public Card[] swap(Card[] deck, int index1, int index2) {
		Card temp1 = new Card("", "");
		temp1 = deck[index1];
		deck[index1] = deck[index2];
		deck[index2] = temp1;		
		return deck;
	}
	
	public Card[] draw(Card[] deck, int number) {
		Card[] hand = new Card[number];
		for (int i=0; i<number; i++) {
			hand[i] = deck[i];
			deck[i] = new Card("", "");
		};
		return hand;
	}

	// Functions that give you information about a collection of cards
	
	public String pokerhand(Card[] hand) {
		hand = sortvalue(hand);
		String result = "nada";

		// Test if it's a flush (all same suit)
		boolean flush = false;
		if ((hand[0].suit==hand[1].suit)&&(hand[0].suit==hand[2].suit)&&(hand[0].suit==hand[3].suit)&&(hand[0].suit==hand[4].suit)) {
			flush = true;};

		// Test if it's a straight (all cards are unique, and nextto eachother)
		boolean straight = false;
		boolean possiblestraight = true;
		while (possiblestraight) {
			// if any two cards match in value, it's not a straight.
			if ((hand[0].value==hand[1].value)||(hand[0].value==hand[2].value)) {possiblestraight=false;};
			if ((hand[0].value==hand[3].value)||(hand[0].value==hand[4].value)) {possiblestraight=false;};
			if ((hand[1].value==hand[2].value)||(hand[1].value==hand[3].value)) {possiblestraight=false;};
			if (hand[1].value==hand[4].value) {possiblestraight=false;};
			if ((hand[2].value==hand[3].value)||(hand[2].value==hand[4].value)) {possiblestraight=false;};
			if (hand[3].value==hand[4].value) {possiblestraight=false;};
			if (possiblestraight==false) {break;};
			// If we've gotten this far, they all have unique values.
			
			// Sort them in value order.

			//  check if they're next to eachother 
			int checksum = 0; // you want at least 4 of these statements to be true
			if (hand[0].nextto(hand[1])) {checksum++;};  // Aces are weird and wrap around.
			if (hand[1].nextto(hand[2])) {checksum++;};
			if (hand[2].nextto(hand[3])) {checksum++;};
			if (hand[3].nextto(hand[4])) {checksum++;};
			if (hand[4].nextto(hand[0])) {checksum++;};  // Aces are weird and wrap around.  
			if ((checksum>3)&&(possiblestraight==true)) {straight=true;};
			possiblestraight = false; // exit the loop
		}
		
		// Produce a result string from acquired information
		if ((flush==true)&&(straight==false)) {result="Flush";};
		if ((flush==true)&&(straight==true))  {result="Straight Flush";};
		if ((flush==false)&&(straight==true)) {result="Straight";};
		// info about straights is not yet accurate.
		return result;
	}
	
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
