package serie01.util;

import util.Contract;

public class StdCurrencyDB implements CurrencyDB {

	private double rates[];
	public StdCurrencyDB() {
		//CurrencyId currencies;
		double rates[] = new double[CurrencyId.values().length];
		int i = 0;
		for (CurrencyId currency : CurrencyId.values()) {
			rates[i++] = currency.rateForYear2001();
		}
	}
	
	public void test() {

	}
	
	@Override
	public double getExchangeRate(CurrencyId id) {
		Contract.checkCondition(id != null);
		return rates[id.ordinal()];
	}

	@Override
	public String getIsoCode(CurrencyId id) {
		Contract.checkCondition(id != null);
		return id.isoCode();
	}

	@Override
	public String getCountry(CurrencyId id) {
		Contract.checkCondition(id != null);
		return id.location();
	}

	@Override
	public String getName(CurrencyId id) {
		Contract.checkCondition(id != null);
		return id.name();
	}

	@Override
	public void setExchangeRate(CurrencyId id, double rate) {
		Contract.checkCondition(id != null);
		rates[id.ordinal()] = rate;
	}
}
