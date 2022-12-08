package MonteCarloDeck;

import java.util.ArrayList;
import java.util.Arrays;

public class MonteCarloCards {
	
	public static void main(String[] args) {
		
		Deck deck = new Deck();
		Hand hand = new Hand();
		
		ArrayList<Integer> values = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
		String[] names = {"Nothing", "Pair", "Two Pairs", "Three of a Kind", "Straight", "Flush",
				"Full House", "Four of a Kind", "Straight Flush", "Royal Flush"};
		ArrayList<Integer> count = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0));
		for(int i = 0; i < 100000; i++) {
			hand.redraw(deck);
			int index = values.indexOf(hand.evaluatePoints());
			int value = count.get(index) + 1;
			count.set(index, value);
		}
		for(int i = 0; i < 10; i++) {
			System.out.println(names[i] + " \n Count: " + count.get(i) + 
					" \t| Probability: " + (count.get(i)/100000.0));
		}
		
	}
	
}
