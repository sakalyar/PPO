package serie06;

public class StdDrinksMachineModel implements DrinksMachineModel {

	private StdStock<DrinkTypes> drinksStock;
	private StdMoneyAmount cashbox;
	private DrinkTypes lastDrink;
	private StdMoneyAmount credit;
	private StdMoneyAmount change;
	
	public StdDrinksMachineModel() {
		drinksStock = new StdStock<DrinkTypes>();
		credit = new StdMoneyAmount();
		cashbox = new StdMoneyAmount();
		change = new StdMoneyAmount();
	}
	
	@Override
	public int getDrinkNb(DrinkTypes d) {
		if (d != null)
			return drinksStock.getNumber(d);
		return 0;
	}

	@Override
	public DrinkTypes getLastDrink() {
		return lastDrink;
	}

	@Override
	public int getCreditAmount() {
		return credit.getTotalValue();
	}

	@Override
	public int getCreditNb(CoinTypes c) {
		return credit.getNumber(c);
	}

	@Override
	public int getCashAmount() {
		return credit.getTotalValue();
	}

	@Override
	public int getCashNb(CoinTypes c) {
		if (c != null)
			return credit.getNumber(c);
		return 0;
	}

	@Override
	public int getChangeAmount() {
		return change.getTotalValue();
	}

	@Override
	public int getChangeNb(CoinTypes c) {
		if (c != null)
			return change.getNumber(c);
		return 0;
	}

	@Override
	public boolean canGetChange() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void selectDrink(DrinkTypes d) {
		boolean changeIsOK = canGetChange();
		cashbox.addAmount(credit);
		drinksStock.removeElement(d);
		int value = credit.getValue(null);
		if (value > d.getPrice() && changeIsOK) {
//			int ma = cashbox.computeChange(value - d.getPrice());
//			changebox.addAmount(ma);
//			cashbox.removeAmount(ma);
		}
		reset();
		lastDrink = d;
	}

	@Override
	public void fillStock(DrinkTypes d, int q) {
		int am = drinksStock.getNumber(d);
		am += q;
		drinksStock.addElement(d, am);
	}

	@Override
	public void fillCash(CoinTypes c, int q) {
		
	}

	@Override
	public void insertCoin(CoinTypes c) {
		
	}

	@Override
	public void cancelCredit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeDrink() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeChange() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}


}
