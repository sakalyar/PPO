package Ex8;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class StdListIterator implements ListIterator {

	@Override
	public void fillTheList() {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 4; ++i)
			list.add(i);
	}
	public void printList() {
		fillTheList();
		// 3èmè devoir dessous
		Iterator<Integer> iteratorBeforeFirstElement = list.iterator();
		System.out.println(iteratorBeforeFirstElement.next());
		//System.out.print(list.get(0));
		for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
			System.out.println(it.next());
			//it.next();
		}
		System.out.println();
		// 4ème devoir
		for (Iterator<Integer> it = list.iterator(); it.hasNext();) {
				if (it.next() == 4) {
					it.remove();
					break;
				}
		}
		list.add(5);
		list.add(4);
		
		for (Integer in : list)
			System.out.println(in);
	}
	
}
