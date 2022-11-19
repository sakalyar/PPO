package serie07;

import java.util.Arrays;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class StdSlotModel extends Observable implements SlotModel {

	private int[] credits;
	private int payout;
	private int won;
	private int lost;
	final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public StdSlotModel(int[] credits) {
		this.credits = credits.clone();
		gamble();
	}
	
	@Override
	public int credit(int n) {
		if (n >= 1 && n <= size()) {
			return credits[n-1];
		}
		return 0;
	}

	@Override
	public int lastPayout() {
		return payout;
	}

	@Override
	public int moneyLost() {
		return lost;
	}

	@Override
	public int moneyWon() {
		return won;
	}

	@Override
	public String result() {
		String s = "";
		s += alphabet.charAt(credit(1));
		s += alphabet.charAt(credit(2));
		s += alphabet.charAt(credit(3));		
		return s;
	}

	@Override
	public int size() {
		return credits.length;
	}

	@Override
	public void gamble() {
		for (int i = 0; i < 3; ++i) {
			int x = ThreadLocalRandom.current().nextInt(0, 26);
			credits[i] = x;
		}
		Arrays.sort(credits);
		String s = result();
		int sameLetters = 1;
		if (s.charAt(0) == s.charAt(1))
			sameLetters += 1;
		if (s.charAt(1) == s.charAt(2))
			sameLetters += 1;
		if (sameLetters == 2) {
			won = 5;
			payout += 5;
		}
		else if (sameLetters == 3) {
			won = 300;
			payout += 300;
		} else {
			won = 0;
			lost += 1;
		}
	}
}
