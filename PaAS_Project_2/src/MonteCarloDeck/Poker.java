package MonteCarloDeck;

import java.util.Scanner;

public class Poker {
	private Deck deck;
	private Hand handOne;
	private Hand handTwo;
	private Scanner scan;
	
	public Poker() {
		deck = new Deck();
		handOne = new Hand();
		handTwo = new Hand();
		scan = new Scanner(System.in);
	}
	
	public void singleplayer() {
		handOne.draw(deck);
		handOne.sort();
		handTwo.draw(deck);
		handTwo.sort();
		changeHand(false);
		System.out.println(handOne.toString());
		System.out.println(handTwo.toString());
		int one = handOne.evaluatePoints();
		int two = handTwo.evaluatePoints();
		if(one > two) {
			System.out.println("Player One wins!");
		} else if(two > one) {
			System.out.println("Player Two wins!");
		} else {
			if(one == 9) {
				System.out.println("It's a tie!");
			}
			one = handOne.evaluateRaw();
			two = handTwo.evaluateRaw();
			if(one > two) {
				System.out.println("Player One wins!");
			} else if(two > one) {
				System.out.println("Player Two wins!");
			} else {
				System.out.println("It's a tie!");
			}
		}
	}
	
	public void twoplayer() {
		handOne.draw(deck);
		handOne.sort();
		handTwo.draw(deck);
		handTwo.sort();
		changeHand(true);
		int one = handOne.evaluatePoints();
		int two = handTwo.evaluatePoints();
		System.out.println("Player One: " + handOne.toString());
		System.out.println("Player Two: " + handTwo.toString());
		if(one > two) {
			System.out.println("Player One wins!");
		} else if(two > one) {
			System.out.println("Player Two wins!");
		} else {
			if(one == 9) {
				System.out.println("It's a tie!");
			}
			one = handOne.evaluateRaw();
			two = handTwo.evaluateRaw();
			if(one > two) {
				System.out.println("Player One wins!");
			} else if(two > one) {
				System.out.println("Player Two wins!");
			} else {
				System.out.println("It's a tie!");
			}
		}
	}
	
	private void changeHand(boolean secondPlayer) {
		if(secondPlayer) {
			System.out.println("Player One's turn to modify hand - no peaking Player Two!");
			scan.nextLine();
			for(int i = 0; i < 5; i++) {
				System.out.println("\n\n\n\n\n\n\n\n\n\n");
			}
			modifyHand(handOne);
			for(int i = 0; i < 5; i++) {
				System.out.println("\n\n\n\n\n\n\n\n\n\n");
			}
			if(scan.hasNextLine()) {
				scan.nextLine();
			}
			System.out.println("Player Two's turn to modify hand - no peaking Player One!");
			scan.nextLine();
			for(int i = 0; i < 5; i++) {
				System.out.println("\n\n\n\n\n\n\n\n\n\n");
			}
			modifyHand(handTwo);
			for(int i = 0; i < 5; i++) {
				System.out.println("\n\n\n\n\n\n\n\n\n\n");
			}
		} else {
			modifyHand(handOne);
		}
	}
	
	private void modifyHand(Hand hand) {
		
		boolean draw = true;
		int index = -1;
		while(draw) {
			System.out.println(hand.toString());
			System.out.println("Would you like to remove a card? ('true' or 'false'): ");
			boolean answered = false;
			while(!answered) {
				try {
					draw = scan.nextBoolean();
					answered = true;
				} catch(Exception e) {
					System.out.println("ERROR: Input is not exactly 'true' or 'false'!");
					draw = true;
					if(scan.hasNextLine()) {
						scan.nextLine();
					}
				}
			}
			if(draw) {
				System.out.println(hand.toString());
				System.out.println("Select index of card you wish to remove (starts at 1): ");
				while(index == -1) {
					try {
						index = scan.nextInt()-1;
						hand.discard(deck, index);
					} catch(Exception e) {
						System.out.println("ERROR: Input not a number of too big, try again: ");
						index = -1;
						if(scan.hasNextLine()) {
							scan.nextLine();
						}
					}
				}
			}
			index = -1;
		}
		hand.draw(deck);
	}
	
}
