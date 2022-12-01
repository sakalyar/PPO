package serie09.copy;

import util.Contract;

public class StdMoneyAmount extends StdStock<CoinTypes> implements MoneyAmount {

    // REQUETES

    @Override
    public int getValue(CoinTypes c) {
        Contract.checkCondition(c != null);

        return getNumber(c) * c.getFaceValue();
    }

    @Override
    public int getTotalValue() {
        int total = 0;
        for (CoinTypes c:CoinTypes.values()) {
            total += getValue(c);
        }
        return total;
    }

    // COMMANDES

    @Override
    public void addAmount(MoneyAmount amount) {
        Contract.checkCondition(amount != null);

        for (CoinTypes c:CoinTypes.values()) {
            int qty = amount.getNumber(c);
            if (qty > 0) {
                addElement(c, qty);
            }
        }
    }

    @Override
    public MoneyAmount computeChange(int s) {
        Contract.checkCondition(s > 0);

        MoneyAmount left = new StdMoneyAmount();
        left.addAmount(this);
        for (CoinTypes c:CoinTypes.values()) {
            if (c.getFaceValue() > s && left.getNumber(c) > 0) {
                left.removeElement(c, left.getNumber(c));
            }
        }
        if (left.getTotalValue() >= s) {
            MoneyAmount res = new StdMoneyAmount();
            res = backtracking(left, res, s);
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    @Override
    public void removeAmount(MoneyAmount amount) {
        Contract.checkCondition(amount != null);

        for (CoinTypes c:CoinTypes.values()) {
            Contract.checkCondition(this.getNumber(c) >= amount.getNumber(c));
        }
        for (CoinTypes c:CoinTypes.values()) {
            int qty = amount.getNumber(c);
            if (qty > 0) {
                this.removeElement(c, qty);
            }
        }
    }

    // OUTILS

    private MoneyAmount backtracking(MoneyAmount left, MoneyAmount res, int s) {
        if (left.getTotalNumber() == 0) {
            return null;
        }
        for (int i = CoinTypes.values().length - 1; i >= 0; i--) {
            CoinTypes c = CoinTypes.values()[i];
            if (left.getNumber(c) > 0) {
                if (res.getTotalValue() + c.getFaceValue() == s) {
                    res.addElement(c);
                    return res;
                }
                if (res.getTotalValue() + c.getFaceValue() < s) {
                    left.removeElement(c);
                    res.addElement(c);
                    MoneyAmount resultat = backtracking(left, res, s);
                    if (resultat != null) {
                        return resultat;
                    }
                    left.addElement(c);
                    res.removeElement(c);
                }
                MoneyAmount tmp = new StdMoneyAmount();
                tmp.addAmount(left);
                left = tmp;
                left.removeElement(c, left.getNumber(c));
                if (left.getTotalValue() + res.getTotalValue() < s) {
                    return null;
                }
            }
        }
        return null;
    }

}
