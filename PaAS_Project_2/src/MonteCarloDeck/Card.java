package MonteCarloDeck;

public class Card {
	private char suite;
	private char rank;
	
	public Card(char s, char r) {
		suite = s;
		rank = r;
	}
	
	public char getSuite() {
		return suite;
	}
	
	public char getRank() {
		return rank;
	}
	
	public int getPoints() {
		char[] ranks = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K', 'A'};
		// '0' == 10
		int index = -1;
		for(int i = 0; i < ranks.length; i++) {
			if(suite == ranks[i]) {
				index = i;
				i = 100;	// to end early
			}
		}
		return index+1;
	}
	
	public int compare(Card other) {		// -# means this < other, +# means >, 0 mean =
		char[] ranks = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K', 'A'};
		// '0' == 10
		int indexThis = -1;
		int indexOther = -1;
		for(int i = 0; i < ranks.length; i++) {
			if(rank == ranks[i]) {
				indexThis = i;
			}
			if(other.rank == ranks[i]) {
				indexOther = i;
			}
			if(indexThis != -1 && indexOther != -1) {	// to end early
				i = 100;
			}
		}
		return indexThis - indexOther;
	}
	
	public String toString() {
		String s;
		if(suite == 'S') {
			s = "Spades";
		} else if(suite == 'H') {
			s = "Hearts";
		} else if(suite == 'C') {
			s = "Clubs";
		} else {	// suite == 'D'
			s = "Diamonds";
		}
		String r;
		if(rank == 'K') {
			r = "King";
		} else if(rank == 'Q') {
			r = "Queen";
		} else if(rank == 'J') {
			r = "Jack";
		} else if(rank == 'A') {
			r = "Ace";
		} else if(rank == '0') {
			r = "10";
		} else {
			r = "" + rank;
		}
		return (r + " of " + s);
	}
	
}
