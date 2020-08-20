
public class Dice {

	public Dice() {
		// TODO Auto-generated constructor stub
	}

	public int roll(int sides) {
	int rand = (int)((Math.random() * (sides)) + 1);  // between 1 and number of sides
	return rand;
	}
	
	public int[] roll(int num, int sides) {
		int[] rand = new int[num];
		for (int i=0; i<num; i++) {
			rand[i] = (int)((Math.random() * (sides)) + 1);  // between 1 and number of sides
			};
		return rand;
	}
	
	public int sum(int[] num) {
		int sum = 0;
		for (int arb : num) {sum = sum + arb;};
		return sum;
	}
}
