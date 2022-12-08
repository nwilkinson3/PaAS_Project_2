package MonteCarloDeck;

import java.util.ArrayList;

public class Hand {
	private ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<>();
	}
	
	public void draw(Deck deck) {
		while(hand.size() < 5) {
			hand.add(deck.draw());
		}
	}
	
	public void discard(Deck deck) {
		deck.discard(hand);
		hand.clear();
	}
	
	public void discard(Deck deck, int index) {
		deck.discard(hand.remove(index));
	}
	
	public void redraw(Deck deck) {
		discard(deck);
		draw(deck);
	}
	
	public int evaluatePoints(){
		if(hand.size() != 5) {
			return -1;
		}
		ArrayList<Integer> set1 = new ArrayList<>();
		ArrayList<Integer> set2 = new ArrayList<>();
		boolean flush = true;
		for(int i = 1; i < hand.size(); i++) {
			if(hand.get(0).getSuite() != hand.get(i).getSuite()) {
				flush = false;
				i = 5;
			}
		}
		for(int i = 0; i < hand.size(); i++) {
			if(set1.isEmpty()) {
				set1.add(i);
				for(int j = i+1; j < hand.size(); j++) {
					if(hand.get(i).compare(hand.get(j)) == 0) {
						set1.add(j);
					}
				}
				if(set1.size() == 1) {
					set1.clear();
				}
			} else if(set2.isEmpty() && !set1.contains(i)) {
				set2.add(i);
				for(int j = i+1; j < hand.size(); j++) {
					if(hand.get(i).compare(hand.get(j)) == 0) {
						set2.add(j);
					}
				}
				if(set2.size() == 1) {
					set2.clear();
				}
			}
		}
		if(!set1.isEmpty()) {
			if(set1.size() == 2){
				if(set2.isEmpty()) {
					return 1; // 1 pair
				} else if(set2.size() == 2) {
					return 2; // 2 pair
				} else {
					return 6; // full house
				}
			} else if(set1.size() == 3) {
				if(set2.isEmpty()) {
					return 3; // 3 of a kind
				} else{
					return 6; // full house
				}
			} else if(set1.size() == 4) {
				return 7; // 4 of a kind
			}
		}
		sort();
		if(hand.get(0).compare(hand.get(1)) == -1){
			if(hand.get(1).compare(hand.get(2)) == -1){
				if(hand.get(2).compare(hand.get(3)) == -1){
					if(hand.get(3).compare(hand.get(4)) == -1){
						if(flush) {
							if(hand.get(0).getRank() == '0') {
								return 9;	// royal flush
							} else {
								return 8;	// straight flush
							}
						} else {
							return 4;	// straight
						}
					}
				}
			}
		}
		if(flush) {
			return 5;	// flush
		}
		return 0;
	}
	
	public int evaluateRaw() {
		if(hand.isEmpty()) {
			return -1;
		}
		int raw = 0;
		for(int i = 0; i < hand.size(); i++) {
			raw += hand.get(i).getPoints();
		}
		return raw;
	}
	
	public void sort() {
		ArrayList<Card> sorted = new ArrayList<>();
		while(!hand.isEmpty()) {
			if(hand.size() == 1) {
				sorted.add(hand.remove(0));
			} else {
				int indexLowest = 0;
				for(int i = 1; i < hand.size(); i++) {
					if(hand.get(i).compare(hand.get(indexLowest)) < 0) {
						indexLowest = i;
					}
				}
				sorted.add(hand.remove(indexLowest));
			}
		}
		for(int i = 0; i < 5; i++) {
			hand.add(sorted.get(i));
		}
	}
	
	public String toString() {
		String answer = "{ ";
		for(int i = 0; i < hand.size(); i++) {
			answer += hand.get(i).toString();
			if(i < 4) {
				answer += ", ";
			}
		}
		answer += " }";
		return answer;
	}
	
	public int size() {
		return hand.size();
	}
	
}
