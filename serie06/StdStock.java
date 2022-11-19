package serie06;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StdStock<E> implements Stock<E> {

	private Map<E, Integer> m;
	
	public StdStock() {
		m = new HashMap<E, Integer>();
	}
	
	@Override
	public int getNumber(E e) {
		if (m.get(e) == null) return 0;
		return m.get(e);
	}

	@Override
	public int getTotalNumber() {
		return m.size();
	}

	@Override
	public void addElement(E e) {
		if (e == null) return;
		if (m.get(e) == null) {
			m.put(e, 1);
			return;
		}
		int am = m.get(e);
		removeElement(e);
		am++;
		m.put(e, am);
	}

	@Override
	public void addElement(E e, int qty) {
		if (e == null || qty <= 0) return;
		if (m.get(e) == null || m.get(e) == 0) m.put(e, qty);
		else {
			while(qty-- > 0) {
				addElement(e);
			}
		}
	}

	@Override
	public void removeElement(E e) {
		if (e != null && m.get(e) >= 1) {
			int am = m.get(e);
			am--;
//			System.out.println();
//			System.out.println(m);
			m.remove(e);
			if (am == 0) return;
			m.put(e, am);
//			System.out.println(m);

		}
	}

	@Override
	public void removeElement(E e, int qty) {
		if (e != null && qty > 0 && m.get(e) >= qty) {
			if (m.get(e) <= qty) {
				m.remove(e);
				return;
			}
			while(qty-- > 0) {
				removeElement(e);
			}
		}
	}

	@Override
	public void reset() {
		m = new HashMap<E, Integer>();
	}


}
