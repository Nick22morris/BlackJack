
public class Card {
	private int val;
	private String suit;
	private String type;
	public Card(int val, String suit, String type) {
		this.val = val;
		this.suit = suit;
		this.type = type;
	}
	public String toString() {
		return type + " of " + suit;
	}
	public int getValue() {
		return val;
	}
	public void setValue(int input) {
		val = input;
	}

}
