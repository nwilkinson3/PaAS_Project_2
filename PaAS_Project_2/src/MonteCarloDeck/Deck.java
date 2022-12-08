package MonteCarloDeck;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> deck;
	private ArrayList<Card> discard;
	private Random rng;
	
	public Deck() {
		deck = new ArrayList<>();
		discard = new ArrayList<>();
		rng = new Random();
		char[] suites = {'S', 'H', 'C', 'D'};
		char[] ranks = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K', 'A'};
		// '0' == 10
		for(char s: suites) {
			for(char r: ranks) {
				deck.add(new Card(s, r));
			}
		}
		shuffle();
	}
	
	public void shuffle(int rounds) {
		for(Card c: discard) {
			deck.add(c);
		}
		discard.clear();
		int a;
		int b;
		Card temp;
		for(int i = 0; i < rounds; i++) {
			a = rng.nextInt(deck.size());
			b = rng.nextInt(deck.size());
			while(a == b) {
				b = rng.nextInt(deck.size());
			}
			temp = deck.get(a);
			deck.set(a, deck.get(b));
			deck.set(b, temp);
		}
	}
	
	public void shuffle() {
		shuffle(250);
	}
	
	public Card draw() {
		if(deck.isEmpty()) {
			shuffle(250);
		}
		return deck.remove(0);
	}
	
	public ArrayList<Card> draw(int num){
		ArrayList<Card> draw = new ArrayList<>();
		for(int i = 0; i < num; i++) {
			draw.add(draw());
		}
		return draw;
	}
	
	public void discard(Card card) {
		discard.add(card);
	}
	
	public void discard(ArrayList<Card> cards) {
		for(int i = 0; i < cards.size(); i++) {
			discard(cards.get(i));
		}
	}
	
}
