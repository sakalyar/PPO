package serie09.copy;

import java.util.Observable;

import util.Contract;

public class StdDrinksMachineModel extends Observable implements DrinksMachineModel {

    // ATTRIBUTS

    private DrinkTypes lastDrink;
    private StdStock<DrinkTypes> drinkStock;
    private StdMoneyAmount cashBox;
    private StdMoneyAmount creditBox;
    private StdMoneyAmount changeBox;
    
    // CONSTRUCTEURS

    public StdDrinksMachineModel() {
        drinkStock = new StdStock<DrinkTypes>();
        for (DrinkTypes d:DrinkTypes.values()) {
            drinkStock.addElement(d, MAX_DRINK);
        }
        cashBox = new StdMoneyAmount();
        creditBox = new StdMoneyAmount();
        changeBox = new StdMoneyAmount();
        lastDrink = null;
    }

    // REQUETES

    @Override
    public int getDrinkNb(DrinkTypes d) {
        Contract.checkCondition(d != null);

        return drinkStock.getNumber(d);
    }

    @Override
    public DrinkTypes getLastDrink() {
        return lastDrink;
    }

    @Override
    public int getCreditAmount() {
    	drinkStock.getTotalNumber();
        return creditBox.getTotalValue();
    }

    @Override
    public int getCreditNb(CoinTypes c) {
        Contract.checkCondition(c != null);
        
        return creditBox.getNumber(c);
    }

    @Override
    public int getCashAmount() {
    	
        return cashBox.getTotalValue();
    }

    @Override
    public int getCashNb(CoinTypes c) {
        Contract.checkCondition(c != null);
//        System.out.println(cashBox.getValue(CoinTypes.ONE));
//        System.out.println(cashBox.getValue(CoinTypes.TWO));
//        System.out.println(cashBox.getValue(CoinTypes.FIVE));
//        System.out.println(cashBox.getValue(CoinTypes.TEN));
//        System.out.println(cashBox.getValue(CoinTypes.TWENTY));
//        System.out.println(cashBox.getValue(CoinTypes.FIFTY));
//        System.out.println(cashBox.getValue(CoinTypes.ONE_HUNDRED));
//        System.out.println(cashBox.getValue(CoinTypes.TWO_HUNDRED));
//        System.out.println();
        return cashBox.getNumber(c);
    }

    @Override
    public int getChangeAmount() {
        return changeBox.getTotalValue();
    }

    @Override
    public int getChangeNb(CoinTypes c) {
        Contract.checkCondition(c != null);

        return changeBox.getNumber(c);
    }

    @Override
    public boolean canGetChange() {
        int maxValueCoin = 1;
        for (CoinTypes c:CoinTypes.values()) {
            if (maxValueCoin < c.getFaceValue()) {
                maxValueCoin = c.getFaceValue();
            }
        }
        for (int i = 1; i < maxValueCoin; i++) {
            if (cashBox.computeChange(i) == null) {
            	setChanged();
                notifyObservers();
                return false;
            }
        }
        setChanged();
        notifyObservers();
        return true;
    }

    // COMMANDES

    @Override
    public void selectDrink(DrinkTypes d) {
        Contract.checkCondition(d != null);
        Contract.checkCondition(getDrinkNb(d) >= 1);
        Contract.checkCondition(getCreditAmount() >= d.getPrice());
//
        boolean changeIsOK = canGetChange();
        cashBox.addAmount(creditBox);
        drinkStock.removeElement(d);
        int value = creditBox.getTotalValue();
        if (value > d.getPrice() && changeIsOK) {
            MoneyAmount ma = cashBox.computeChange(value - d.getPrice());
            changeBox.addAmount(ma);
            cashBox.removeAmount(ma);
        }
//        creditBox = new StdMoneyAmount();
        lastDrink = d;
        setChanged();
        notifyObservers();
    }

    @Override
    public void fillStock(DrinkTypes d, int q) {
        Contract.checkCondition(d != null);
        Contract.checkCondition(q > 0 && getDrinkNb(d) + q <= MAX_DRINK);

        drinkStock.addElement(d, q);
//        setChanged();
//        notifyObservers();
    }

    @Override
    public void fillCash(CoinTypes c, int q) {
        Contract.checkCondition(c != null);
        Contract.checkCondition(q > 0
                && getCashNb(c) + getCreditNb(c) + q <= MAX_COIN);
        cashBox.addElement(c, q);
        setChanged();
        notifyObservers();
    }

    @Override
    public void insertCoin(CoinTypes c) {
        Contract.checkCondition(c != null);

        if (getCashNb(c) + getCreditNb(c) == MAX_COIN) {
            changeBox.addElement(c);
        } else {
            creditBox.addElement(c);
        }
        setChanged();
        notifyObservers();
    }

    @Override
    public void cancelCredit() {
        changeBox.addAmount(creditBox);
        creditBox = new StdMoneyAmount();
        setChanged();
        notifyObservers();
    }

    @Override
    public void takeDrink() {
    	setChanged();
        notifyObservers();
        drinkStock.removeElement(lastDrink);
        lastDrink = null;
    }

    @Override
    public void takeChange() {
        changeBox = new StdMoneyAmount();
        setChanged();
        notifyObservers();
    }

    @Override
    public void reset() {
        drinkStock = new StdStock<DrinkTypes>();
        cashBox = new StdMoneyAmount();
        creditBox = new StdMoneyAmount();
        changeBox = new StdMoneyAmount();
        lastDrink = null;
        setChanged();
        notifyObservers();
    }

}
