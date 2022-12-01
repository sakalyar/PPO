package serie07;

public class StdSlotModel implements SlotModel {

	
	private int[] credits;
	
	public StdSlotModel(int[] credits) {
		this.credits = credits.clone();
	}
	
	
	
	@Override
	public int credit(int n) {
		if (n >= 1 && n <= size())
			return credits[n-1];
		return 0;
	}

	@Override
	public int lastPayout() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int moneyLost() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int moneyWon() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String result() {
		return null;
	}

	@Override
	public int size() {
		return result().length();
	}

	@Override
	public void gamble() {
		
		
	}

}

