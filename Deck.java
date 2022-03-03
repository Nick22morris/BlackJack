import java.util.ArrayList;
import java.util.Random;
public class Deck {
	private ArrayList<Card> cards;
	public Deck() {
		cards = new ArrayList<Card>();
		generateDeck();
		shuffleDeck();
		
	}
	private void generateDeck() {
		int[] nums = {2,3,4,5,6,7,8,9,10,10,10,10,11};
		String[] words = {"2", "3", "4", "5", "6", "7","8","9","10","Jack","Queen","King","Ace"};
		String[] suits = {"Diamonds", "Spades", "Hearts", "Clubs"};

		for(int x = 0; x < suits.length; x++) {
			for (int y = 0; y < words.length; y++) {
				Card tempCard = new Card(nums[y],suits[x],words[y]);
				cards.add(tempCard);
			}
		}
	}
	private void shuffleDeck() {
		Random r = new Random();
		for(int i = 0; i < cards.size(); i++) {
			int rando = r.nextInt(52-i) + i;
			Card hold = cards.get(rando);
			cards.set(rando,cards.get(i));
			cards.set(i, hold);
		}
	}
	public Card dealCard() {
		int rando = (int)Math.random() * 51;
		Card dealtCard = cards.get(rando);
		cards.remove(rando);
		return dealtCard;
	}
}