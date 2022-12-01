package serie06;

public enum CoinTypes {
     ONE(1),
     TWO(2),
     FIVE(5),
     TEN(10),
     TWENTY(20),
     FIFTY(50),
     ONE_HUNDRED(100),
     TWO_HUNDRED(200);
    
    private int value;
    
    CoinTypes(int value) {
        this.value = value;
    }
    
    public int getFaceValue() {
        return value;
    }
    
    public String toString() {
        if (this == ONE) {
            return getFaceValue() + " ct";
        }
        return getFaceValue() + " cts";
    }
    
    public static CoinTypes getCoinType(int val) {
        for (CoinTypes c:CoinTypes.values()) {
            if (c.getFaceValue() == val) {
                return c;
            }
        }
        return null;
    }
}
