package serie06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum CoinTypes {
	ONE(1),
	TWO(2),
	FIVE(5),
	TEN(10), 
	TWENTY(20), 
	FIFTY(50),
	ONE_HUNDRED(100),
	TWO_HUNDRED(200);
	
	private int val;
	private CoinTypes(int val) {
		this.val = val;
	}
	
	List<Integer> nums = new ArrayList<Integer>() {{
		add(1);
		add(2);
		add(5);
		add(10);
		add(20);
		add(50);
		add(100);
		add(200);
	}};
	Map<Integer, CoinTypes> m = new HashMap<Integer, CoinTypes>() {{
		put(1, ONE);
		put(2, TWO);
		put(5, FIVE);
		put(10, TEN);
		put(20, TWENTY);
		put(50, FIFTY);
		put(100, ONE_HUNDRED);
		put(200, TWO_HUNDRED);
	}};
	
	public int getFaceValue() {
		return this.val;
	}
	
	@Override
	public String toString() {
		if (val == 1) return "1 ct";
		return val + " cts";
	}
	
	public static CoinTypes getCoinType(int val)
	{
		System.out.println(val);
		if (val == 1) return ONE;
		if (val == 2) return TWO;
		if (val == 5) return FIVE;
		if (val == 10) return TEN;
		if (val == 20) return TWENTY;
		if (val == 50) return FIFTY;
		if (val == 100) return ONE_HUNDRED;
		if (val == 200) return TWO_HUNDRED;
		return null;
	}
}

