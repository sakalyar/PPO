package serie06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StdMoneyAmount extends StdStock<CoinTypes> implements MoneyAmount {

	private StdStock<CoinTypes> stock;
	int p[];
	int x[];
	List<Integer> n;
	
	public StdMoneyAmount() {
		stock = new StdStock<CoinTypes>();
		int p[] = {1, 2, 5, 10, 20, 50, 100, 200};
		this.p = p;
		x = new int[8];
		n = new ArrayList<Integer>(8);
	}
	
	public StdMoneyAmount(StdStock<CoinTypes> stock, int x[]) {
		this.stock = stock;
		stock = new StdStock<CoinTypes>();
		int p[] = {1, 2, 5, 10, 20, 50, 100, 200};
		this.p = p;
		this.x = x;
		n = new ArrayList<Integer>(8);
	}

	@Override
	public int getValue(CoinTypes c) {
		return stock.getNumber(c) * c.getFaceValue();
	}

	@Override
	public int getTotalValue() {
		int am = 0;
		for (CoinTypes types : CoinTypes.values()) {
			am += getValue(types);
		}
		return am;
	}

	@Override
	public void addAmount(MoneyAmount amount) {
		if (amount != null) {
			for (CoinTypes types : CoinTypes.values()) {
				stock.addElement(types, stock.getNumber(types) + 
						amount.getValue(types) / types.getFaceValue());
			}
		}
	}

	@Override
	public MoneyAmount computeChange(int s) {
		if (s > 0) {
			StdStock<CoinTypes> newStock = new StdStock<CoinTypes>();
			for (CoinTypes types : CoinTypes.values()) {
				n.add(stock.getNumber(types));
			}
			int i = 8;
			int q;
			while (i >= 0 && s > 0) {
				i--;
				q = Math.min((int) n.get(i), (int) s / p[i]);
				s -= q * p[i];
				x[i] = q;
			}
			int j = 0;
			for (CoinTypes types : CoinTypes.values()) {
				newStock.addElement(types, x[j++]);
			}
			StdMoneyAmount change = new StdMoneyAmount(newStock, x);
			return change;
		}
			
		return null;
	}

	@Override
	public void removeAmount(MoneyAmount amount) {
		if (amount != null) {
			for (CoinTypes types : CoinTypes.values()) {
				if (stock.getNumber(types) < amount.getNumber(types)) return;
			}
			for (CoinTypes types : CoinTypes.values()) {
				stock.removeElement(types, amount.getNumber(types));
			}
		}
	}

}
