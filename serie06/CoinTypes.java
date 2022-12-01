package serie06;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum CoinTypes {
	ONE (1),
	TWO (2),
	FIVE (5),
	TEN (10),
	TWENTY (20),
	FIFTY (50),
	ONE_HUNDRED (100),
	TWO_HUNDRED (200);
	
	int val;
	private static CoinTypes[] coins = CoinTypes.values();
	
	private CoinTypes(int val) {
		this.val = val;
	}
	
	public int getFaceValue() {
		return val;
	}

	@Override
	public String toString() {
		if (val == 1) return "1 ct";
		return val + " cts";
		
	}
	
	public static CoinTypes getCoinType(int val) {
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
	
};
















/*package serie06;

public enum CoinTypes {
	ONE { 
		public int getFaceValue() {
				return 1;
			}
		},
	TWO { 
		public int getFaceValue() {
			return 2;
		}
		},
	FIVE{ 
		public int getFaceValue() {
			return 5;
		}
	},
	TEN{ 
		public int getFaceValue() {
			return 10;
		}
	},
	TWENTY{ 
		public int getFaceValue() {
			return 20;
		}
	},
	FIFTY{ 
		public int getFaceValue() {
			return 50;
		}
	},
	ONE_HUNDRED{ val
		public int getFaceValue() {
			return 100;
		}
	},
	TWO_HUNDRED{ 
		public int getFaceValue() {
			return 1;
		}
	};
	
	int val;
	
	public CoinTypes() {
		
	}
	
	@Override
	public String toString() {
		return CoinTypes.valueOf(this).ordinal();
	}
	
	public static CoinTypes getCoinType(int val) {
		return CoinTypes.values()[val];
	}
	
};
*/