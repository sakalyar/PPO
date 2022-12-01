package serie09;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import util.Contract;

public class StdStockEnum<E extends Enum<E>> implements Stock<E> {

    // ATTRIBUTS

    private Map<E, Integer> data;

    // CONSTRUCTEURS

    public StdStockEnum(Class<E> keyType) {
        data = new EnumMap<E, Integer>(keyType);
    }

    // REQUETES

    @Override
    public int getNumber(E e) {
        Contract.checkCondition(e != null);

        if (!data.containsKey(e)) {
            return 0;
        }
        return data.get(e);
    }

    @Override
    public int getTotalNumber() {
        int nb = 0;
        for (E e:data.keySet()) {
            nb += data.get(e);
        }
        return nb;
    }

    // COMMANDES

    @Override
    public void addElement(E e) {
        Contract.checkCondition(e != null);

        data.put(e, getNumber(e) + 1);
    }

    @Override
    public void addElement(E e, int qty) {
        Contract.checkCondition(e != null);
        Contract.checkCondition(qty > 0);

        data.put(e, getNumber(e) + qty);
    }

    @Override
    public void removeElement(E e) {
        Contract.checkCondition(e != null);
        Contract.checkCondition(getNumber(e) >= 1);

        data.put(e, getNumber(e) - 1);
    }

    @Override
    public void removeElement(E e, int qty) {
        Contract.checkCondition(e != null);
        Contract.checkCondition(qty > 0);
        Contract.checkCondition(getNumber(e) >= qty);

        data.put(e, getNumber(e) - qty);
    }

    @Override
    public void reset() {
        data = new HashMap<E, Integer>();
    }

}
